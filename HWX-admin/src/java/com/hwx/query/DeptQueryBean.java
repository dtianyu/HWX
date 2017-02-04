/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.query;

import com.hwx.ejb.DepartmentBean;
import com.hwx.entity.Department;
import com.hwx.lazy.DepartmentModel;
import com.hwx.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "deptQueryBean")
@ViewScoped
public class DeptQueryBean extends SuperQueryBean<Department> {

    @EJB
    private DepartmentBean departmentBean;

    public DeptQueryBean() {
        super(Department.class);
    }

    @Override
    public void init() {
        this.superEJB = departmentBean;
        setModel(new DepartmentModel(departmentBean));
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null) {
            this.model.getFilterFields().clear();
            if (this.queryFormId != null && !"".equals(this.queryFormId)) {
                this.model.getFilterFields().put("deptno", this.queryFormId);
            }
        }
    }

}
