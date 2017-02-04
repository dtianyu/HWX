/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.ejb;

import com.hwx.comm.SuperBean;
import com.hwx.entity.Unit;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class UnitBean extends SuperBean<Unit> {

    public UnitBean() {
        super(Unit.class);
    }
    
}
