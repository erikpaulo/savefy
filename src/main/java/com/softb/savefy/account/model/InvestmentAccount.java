package com.softb.savefy.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa uma conta de investimentos. Existem tipos de investimentos diferentes de acordo com
 * cada produto que se destina seus patrimônios
 * @author Erik Lacerda 
 *
 */
@Data
@Entity
@DiscriminatorValue("INV")
public class InvestmentAccount extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name="PRODUCT")
    @NotNull
    protected Product product;

    @Column(name="ADMIN_TAX")
    @NotNull
    protected Double adminTax;

    // D+ OR DUE DATE
    @Column(name="LIQUIDITY_TYPE")
    @NotNull
    protected LiquidityType liquidityType;

    // IF D+, DEFINE HOW MANY DAYS GET THE MONEY
    @Column(name="LIQUIDITY_DAYS")
    protected Integer liquidityDays;

    // IF DUE DATE, DEFINE THE DAY TO GET FULL REMUNERATION
    @Column(name="LIQUIDITY_DUE_DATE")
    protected Date liquidityDueDate;

//    // DEFINE THE DATE, INDEPENDENT OF THE LIQUIDITY TYPE
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
//    protected List<AssetPrice> indexValues;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    protected List<InvestmentAccountEntry> entries;

    @Transient
    protected Double grossBalance;

    @Transient
    protected Double grossProfit;

    @Transient
    protected Double percentGrossProfit;

    @Transient
    protected Double netProfit;

    @Transient
    protected Double percentNetProfit;

    public enum Product {
        FIXED_INCOME ( "Renda Fixa" ), MULTI_SHARES( "Multimercado" ), FUND_OF_SHARES( "Fundo de Acões" ), PENSION_FUND( "Fundo de Previdência" );
        private String name;

        Product(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum LiquidityType {
        DPLUS ( "D+" ), DUE_DATE ( "Vencimento" );
        private String name;

        LiquidityType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    @Override
    @JsonIgnore
    public Date getLiquidityDate() {
        Date date = null;

        if (this.liquidityType.equals(LiquidityType.DPLUS)){
            Calendar cal = Calendar.getInstance();
            cal.clear(Calendar.HOUR);
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);
            cal.add(Calendar.DAY_OF_MONTH, this.liquidityDays);
            date = cal.getTime();
        } else {
            date = this.liquidityDueDate;
        }

        return date;
    }
}
