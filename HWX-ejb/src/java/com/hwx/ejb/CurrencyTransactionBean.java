/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.ejb;

import com.hwx.comm.SuperBean;
import com.hwx.entity.CurrencyTransaction;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class CurrencyTransactionBean extends SuperBean<CurrencyTransaction> {

    public CurrencyTransactionBean() {
        super(CurrencyTransaction.class);
    }

}
