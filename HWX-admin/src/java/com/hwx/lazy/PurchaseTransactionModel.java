/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.lazy;

import com.hwx.entity.PurchaseTransaction;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;

/**
 *
 * @author kevindong
 */
public class PurchaseTransactionModel extends BaseLazyModel<PurchaseTransaction>{
    
    public PurchaseTransactionModel(SuperEJB superEJB){
        this.superEJB = superEJB;
    }
    
}
