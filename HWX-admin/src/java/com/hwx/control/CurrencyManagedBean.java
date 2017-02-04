/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.CurrencyBean;
import com.hwx.entity.Currency;
import com.hwx.lazy.CurrencyModel;
import com.hwx.web.SuperSingleBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class CurrencyManagedBean extends SuperSingleBean<Currency> {

    @EJB
    private CurrencyBean currencyBean;
   
    public CurrencyManagedBean() {
        super(Currency.class);
    }

    @Override
    public void init() {
        this.superEJB = currencyBean;
        setModel(new CurrencyModel(currencyBean));
        super.init(); 
    }   
    
}
