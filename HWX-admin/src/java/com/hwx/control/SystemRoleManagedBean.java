/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.SystemRoleBean;
import com.hwx.ejb.SystemRoleDetailBean;
import com.hwx.entity.SystemRole;
import com.hwx.entity.SystemRoleDetail;
import com.hwx.entity.SystemUser;
import com.hwx.lazy.SystemRoleModel;
import com.hwx.web.SuperMultiBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "systemRoleManagedBean")
@SessionScoped
public class SystemRoleManagedBean extends SuperMultiBean<SystemRole, SystemRoleDetail> {

    @EJB
    private SystemRoleBean systemRoleBean;

    @EJB
    private SystemRoleDetailBean systemRoleDetailBean;

    public SystemRoleManagedBean() {
        super(SystemRole.class, SystemRoleDetail.class);
    }

    @Override
    public void handleDialogReturnWhenDetailNew(SelectEvent event) {
        handleDialogReturnWhenDetailEdit(event);
    }

    @Override
    public void handleDialogReturnWhenDetailEdit(SelectEvent event) {
        if (event.getObject() != null && currentDetail != null) {
            SystemUser u = (SystemUser) event.getObject();
            currentDetail.setSystemUser(u);
        }
    }

    @Override
    public void init() {
        this.superEJB = systemRoleBean;
        this.detailEJB = systemRoleDetailBean;
        setModel(new SystemRoleModel(systemRoleBean));
        super.init();
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "V":
                    this.doEdit = false;
                    this.doDel = false;
                    this.doCfm = false;
                    this.doUnCfm = true;
                    break;
                default:
                    this.doEdit = true;
                    this.doDel = true;
                    this.doCfm = true;
                    this.doUnCfm = false;
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

}
