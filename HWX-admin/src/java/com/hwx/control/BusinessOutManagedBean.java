/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.PurchaseKindBean;
import com.hwx.entity.PurchaseKind;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "businessOutManagedBean")
@SessionScoped
public class BusinessOutManagedBean extends BusinessTransactionManagedBean {

    @EJB
    private PurchaseKindBean purchaseKindBean;

    private List<PurchaseKind> purchaseKindList;

    public BusinessOutManagedBean() {
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormtype("PQT");
    }

    @Override
    public void init() {
        purchaseKindList = purchaseKindBean.findAll();
        super.init();
        this.model.getFilterFields().put("formtype", "P");
    }

    @Override
    public void query() {
        super.query();
        this.model.getFilterFields().put("formtype", "P");
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("formtype", "P");
    }

    /**
     * @return the purchaseKindList
     */
    public List<PurchaseKind> getPurchaseKindList() {
        return purchaseKindList;
    }

}
