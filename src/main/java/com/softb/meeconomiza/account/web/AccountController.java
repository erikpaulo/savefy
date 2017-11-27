package com.softb.meeconomiza.account.web;

import com.softb.meeconomiza.account.model.Account;
import com.softb.meeconomiza.account.model.Conciliation;
import com.softb.meeconomiza.account.service.AccountService;
import com.softb.meeconomiza.account.service.ConciliationService;
import com.softb.meeconomiza.preferences.services.UserPreferencesService;
import com.softb.system.errorhandler.exception.FormValidationError;
import com.softb.system.errorhandler.exception.SystemException;
import com.softb.system.rest.AbstractRestController;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController("AppAccountController")
@RequestMapping("/api/account")
public class AccountController extends AbstractRestController<Account, Integer> {

    @Inject
    private AccountService accountService;

    @Inject
    private ConciliationService conciliationService;

    @Inject
    private UserPreferencesService userPreferencesService;


    /**
     * Lists all account registered for this user, but its entries aren't loaded.
     *
     * @return List Accounts without its entries
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Account> listAll() {
        List<Account> accounts = accountService.getAllActiveAccounts(getGroupId());
        return accounts;
    }

    /**
     * Returns the informed account with no entry.
     * @param id Id of the account
     * @return The Account selected
     * @throws FormValidationError
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getSummarize(@PathVariable Integer id) throws FormValidationError {
        Account account = accountService.getAccount(id, getGroupId());
        account.setEntries(null);
        return account;
    }

    /**
     * Import account entries from a CSV file. This point prepares the file's data to be complemented by the user.
     * @param id Id of the Account
     * @param request Request
     * @param response Response
     * @return
     * @throws SystemException
     * @throws DataAccessException
     * @throws FileUploadException
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value="/{id}/conciliation/upload", method = RequestMethod.POST)
    @ResponseBody public Conciliation uploadEntries(@PathVariable Integer id,
                                                    final HttpServletRequest request,
                                                    final HttpServletResponse response  )
            throws SystemException, DataAccessException, FileUploadException, IOException, ParseException {

        // Treat file data, creating a conciliation structure
        Conciliation conciliation = conciliationService.uploadEntries(id, request, response, getGroupId());

        // Save the draft and return.
        return conciliationService.save(conciliation, getGroupId());
    }

    @RequestMapping(value="/{id}/conciliation/{conciliationId}", method = RequestMethod.GET)
    @ResponseBody public Conciliation getConciliation( @PathVariable Integer conciliationId  ){
        return conciliationService.get(conciliationId, getGroupId(), true);
    }

    @RequestMapping(value="/{id}/conciliation/{conciliationId}", method = RequestMethod.POST)
    @ResponseBody public Conciliation saveConciliation( @RequestBody Conciliation conciliation  ){

        return conciliationService.save(conciliation, getGroupId());
    }

    @RequestMapping(value="/{id}/conciliation/{conciliationId}", method = RequestMethod.DELETE)
    @ResponseBody public void delConciliation( @PathVariable Integer id, @PathVariable Integer conciliationId ){
        conciliationService.delete(conciliationId, getGroupId());
    }

    @RequestMapping(value="/{id}/conciliation/{conciliationId}/sync", method = RequestMethod.POST)
    @ResponseBody public Conciliation syncConciliationIntoAccount( @PathVariable Integer id, @RequestBody Conciliation conciliation  ){
        return conciliationService.syncEntriesIntoAccount(conciliation, getGroupId());
    }

    @RequestMapping(value="/{id}/conciliation/{conciliationId}/rollback", method = RequestMethod.POST)
    @ResponseBody public Conciliation rollbackConciliation( @PathVariable Integer conciliationId  ){
        return conciliationService.rollback(conciliationId, getGroupId());
    }

}

