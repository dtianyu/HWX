/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.UnitBean;
import com.hwx.entity.Unit;
import com.hwx.lazy.UnitModel;
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
public class UnitManagedBean extends SuperSingleBean<Unit> {

    @EJB
    private UnitBean unitBean;
   
    public UnitManagedBean() {
        super(Unit.class);
    }

    @Override
    public void init() {
        this.superEJB = unitBean;
        setModel(new UnitModel(unitBean));
        super.init(); 
    }   
    
}
