package com.softb.savefy.categorization.web;

import com.softb.savefy.categorization.model.Category;
import com.softb.savefy.categorization.model.SubCategory;
import com.softb.savefy.categorization.repository.CategoryRepository;
import com.softb.savefy.categorization.repository.SubCategoryRepository;
import com.softb.system.errorhandler.exception.FormValidationError;
import com.softb.system.rest.AbstractRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@RestController("AppCategoryController")
@RequestMapping("/api/categorization")
public class CategoryController extends AbstractRestController<Category, Integer> {

	public static final String CATEGORY_OBJECT_NAME = "Category";
	public static final String SUBCATEGORY_OBJECT_NAME = "SubCategory";

	@Autowired
	private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    /**
     * This URL lists all categories created by the current user. This categories are grouped into
     * groups that represents the category type, i.e.: expenses, incomes and investiments.
     *
     * @return List
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public List<Category> listAllCategories() {
        // Gets all categories of the logged user, grouping by category types
        List<Category> categories = categoryRepository.listAllByUser ( getGroupId() );

        return categories;
    }

    /**
     * Gets all subcategories registered for this user with its full name
     * @return
     * @throws FormValidationError
     */
    @RequestMapping(value = "/category/all/subcategory", method = RequestMethod.GET)
    public List<SubCategory> listAllSubcategories() throws FormValidationError {
        List<SubCategory> listToReturn = new ArrayList<>(  );

        // Gets all categories of the logged user, grouping by category types
        List<Category> categories = categoryRepository.listAllByUser( getGroupId() );
        Iterator<Category> categs = categories.iterator ();
        while (categs.hasNext ()){
            Category category = categs.next ();

            // Define each subcategory full name.
            List<SubCategory> subcategories = category.getSubcategories();
            Iterator<SubCategory> s = subcategories.iterator();
            while (s.hasNext()){
                SubCategory subCategory = s.next();
                if (subCategory.getActivated()){
                    listToReturn.add( subCategory );
                }
            }
        }

        Collections.sort(listToReturn, new Comparator<SubCategory>(){
            public int compare(SubCategory o1, SubCategory o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });

        return listToReturn;
    }

    /**
     * Gets all subcategories registered for this user with its full name
     * @return
     * @throws FormValidationError
     */
    @RequestMapping(value = "/category/investment/subcategory", method = RequestMethod.GET)
    public List<SubCategory> listInvestmentSubcategories() throws FormValidationError {
        List<SubCategory> listToReturn = new ArrayList<>(  );

        // Gets all categories of the logged user, grouping by category types
        List<Category> categories = categoryRepository.listAllByUser( getGroupId() );
        Iterator<Category> categs = categories.iterator ();
        while (categs.hasNext ()){
            Category category = categs.next ();
            if (category.getType().equals(Category.Type.INV)){
                // Define each subcategory full name.
                List<SubCategory> subcategories = category.getSubcategories();
                Iterator<SubCategory> s = subcategories.iterator();
                while (s.hasNext()){
                    SubCategory subCategory = s.next();
                    if (subCategory.getActivated()){
                        listToReturn.add( subCategory );
                    }
                }
            }
        }

        Collections.sort(listToReturn, new Comparator<SubCategory>(){
            public int compare(SubCategory o1, SubCategory o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });

        return listToReturn;
    }

    /**
     * This point creates a new Category into the system.
     * @param category Category to create
     * @return Category created
     * @throws FormValidationError
     */
    @RequestMapping(value = "/category", method = RequestMethod.POST)
	public Category create(@RequestBody Category category) throws FormValidationError {

        category.setGroupId( getGroupId() );
        validate( CATEGORY_OBJECT_NAME,  category);

        // Salva a categoria.
		category = categoryRepository.save(category);
        category.setSubcategories( new ArrayList<SubCategory>(  ) );
		return category;
	}

    /**
     * This point creates a new Sub Category into the system.
     * @param subCategory SubCategory to create
     * @return SubCategory created
     * @throws FormValidationError
     */
    @RequestMapping(value = "/category/{categoryId}/subcategory", method = RequestMethod.POST)
    public SubCategory create(@PathVariable Integer categoryId, @RequestBody SubCategory subCategory) throws FormValidationError {

        Category category = categoryRepository.findOneByUser( categoryId, getGroupId() );
        subCategory.setGroupId( getGroupId() );
        subCategory.setActivated( true );
        subCategory.setCategory( category );

        validate( SUBCATEGORY_OBJECT_NAME,  subCategory);

        // Salva a categoria.
        subCategory = subCategoryRepository.save(subCategory);
        return subCategory;
    }

	@Transactional
    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	public Category update(@PathVariable Integer id, @RequestBody Category category) {
		
        category.setGroupId( getGroupId() );
        validate( CATEGORY_OBJECT_NAME, category );

        // Salva a categoria.
		category = categoryRepository.save( category );
		return category;
	}

	@Transactional
    @RequestMapping(value = "/category/{categoryId}/subcategory/{id}", method = RequestMethod.PUT)
	public SubCategory update(@PathVariable Integer categoryId, @RequestBody SubCategory subCategory) {

        Category category = categoryRepository.findOneByUser( categoryId, getGroupId() );
        subCategory.setCategory( category );
        subCategory.setGroupId( getGroupId() );

        validate( SUBCATEGORY_OBJECT_NAME, subCategory );

        // Salva a categoria.
        subCategory = subCategoryRepository.save( subCategory );
		return subCategory;
	}

	@Transactional
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Integer id) throws FormValidationError{
		categoryRepository.delete(id);
	}
}

