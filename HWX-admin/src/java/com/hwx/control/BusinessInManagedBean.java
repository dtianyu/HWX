/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.SalesKindBean;
import com.hwx.entity.SalesKind;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "businessInManagedBean")
@SessionScoped
public class BusinessInManagedBean extends BusinessTransactionManagedBean {

    @EJB
    private SalesKindBean salesKindBean;

    private List<SalesKind> salesKindList;

    public BusinessInManagedBean() {
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setFormtype("RQT");
    }

    @Override
    public void init() {
        salesKindList = salesKindBean.findAll();
        super.init();
        this.model.getFilterFields().put("formtype", "R");
    }

    @Override
    public void query() {
        super.query();
        this.model.getFilterFields().put("formtype", "R");
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("formtype", "R");
    }

    /**
     * @return the salesKindList
     */
    public List<SalesKind> getSalesKindList() {
        return salesKindList;
    }

}
