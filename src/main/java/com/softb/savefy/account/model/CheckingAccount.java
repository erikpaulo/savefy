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
 * Classe que representa as Contas bancárias do usuário
 * @author Erik Lacerda 
 *
 */
@Data
@Entity
@DiscriminatorValue("CKA")
public class CheckingAccount extends Account implements Serializable {

	private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID")
    protected List<CheckingAccountEntry> entries;

    @Column(name="START_BALANCE")
    @NotNull
    protected Double startBalance;

    @Override
    @JsonIgnore
    public Date getLiquidityDate() {
        Calendar cal = Calendar.getInstance();
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        return cal.getTime();
    }
}
