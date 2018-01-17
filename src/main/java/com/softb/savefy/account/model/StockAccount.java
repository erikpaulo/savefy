package com.softb.savefy.account.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa um portifólio de ações mantido pelo usuário
 * @author Erik Lacerda
 *
 */
@Data
@Entity
@DiscriminatorValue("STK")
public class StockAccount extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

    // D+ OR DUE DATE
    @Column(name="LIQUIDITY_TYPE")
    @NotNull
    protected InvestmentAccount.LiquidityType liquidityType = InvestmentAccount.LiquidityType.DPLUS;

    // IF D+, DEFINE HOW MANY DAYS GET THE MONEY
    @Column(name="LIQUIDITY_DAYS")
    protected Integer liquidityDays = 3;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    protected List<StockAccountEntry> stocks;

    @Override
    public Date getLiquidityDate() {
        Calendar cal = Calendar.getInstance();
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.add(Calendar.DAY_OF_MONTH, this.liquidityDays);
        return cal.getTime();
    }
}