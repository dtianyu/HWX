/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.query;

import com.hwx.ejb.VendorBean;
import com.hwx.entity.Vendor;
import com.hwx.lazy.VendorModel;
import com.hwx.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "vendorQueryBean")
@ViewScoped
public class VendorQueryBean extends SuperQueryBean<Vendor> {

    @EJB
    private VendorBean vendorBean;

    public VendorQueryBean() {
        super(Vendor.class);
    }

    @Override
    public void init() {
        this.superEJB = vendorBean;
        setModel(new VendorModel(vendorBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("vendorno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("vendor", queryName);
            }
        }
    }
}
