/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.query;

import com.hwx.ejb.CurrencyBean;
import com.hwx.entity.Currency;
import com.hwx.lazy.CurrencyModel;
import com.hwx.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@ViewScoped
public class CurrencyQueryBean extends SuperQueryBean<Currency> {

    @EJB
    private CurrencyBean currencyBean;

    public CurrencyQueryBean() {
        super(Currency.class);
    }

    @Override
    public void init() {
        this.superEJB = currencyBean;
        setModel(new CurrencyModel(currencyBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("currency", this.queryFormId);
            }
        }
    }

}
