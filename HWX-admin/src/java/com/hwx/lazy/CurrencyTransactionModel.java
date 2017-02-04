/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.lazy;

import com.hwx.entity.CurrencyTransaction;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class CurrencyTransactionModel extends BaseLazyModel<CurrencyTransaction>{
    
    public CurrencyTransactionModel(SuperEJB superEJB){
        this.superEJB = superEJB;
    }
    
}
