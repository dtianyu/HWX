/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.DepartmentBean;
import com.hwx.ejb.SalesTransactionBean;
import com.hwx.ejb.SystemUserBean;
import com.hwx.entity.Currency;
import com.hwx.entity.Department;
import com.hwx.entity.ItemMaster;
import com.hwx.entity.SalesTransaction;
import com.hwx.entity.SystemUser;
import com.hwx.entity.Unit;
import com.hwx.entity.Customer;
import com.hwx.lazy.SalesTransactionModel;
import com.hwx.web.SuperSingleBean;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.Tax;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesTransactionManagedBean")
@SessionScoped
public class SalesTransactionManagedBean extends SuperSingleBean<SalesTransaction> {

    @EJB
    protected DepartmentBean departmentBean;
    @EJB
    protected SystemUserBean systemUserBean;

    @EJB
    private SalesTransactionBean salesTransactionBean;

    private List<SystemUser> systemUserList;
    private List<Department> deptList;

    private String queryItemno;

    public SalesTransactionManagedBean() {
        super(SalesTransaction.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setFormdate(getDate());
        newEntity.setUser(this.userManagedBean.getCurrentUser());
        newEntity.setDept(this.userManagedBean.getCurrentUser().getDept());
        newEntity.setCurrency("CNY");
        newEntity.setExchange(BigDecimal.ONE);
        newEntity.setTaxtype("0");
        newEntity.setTaxkind("VAT17");
        newEntity.setTaxrate(BigDecimal.valueOf(17));
        newEntity.setQty(BigDecimal.ONE);
        newEntity.setPrice(BigDecimal.ZERO);
        newEntity.setAmts(BigDecimal.ZERO);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (!super.doBeforePersist()) {
            return false;
        }
        if (this.getNewEntity().getCustomer() == null) {
            showErrorMsg("Error", "请输入客户");
            return false;
        }
        if (this.getCurrentPrgGrant() != null && this.getCurrentPrgGrant().getSysprg().getNoauto()) {
            String formid = superEJB.getFormId(newEntity.getFormdate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "formid");
            this.newEntity.setFormid(formid);
        }
        this.newEntity.setAmts(this.newEntity.getQty().multiply(this.newEntity.getPrice()));
        Tax t = BaseLib.getTaxes(this.newEntity.getTaxtype(), this.newEntity.getTaxkind(), this.newEntity.getTaxrate(), this.newEntity.getAmts(), 2);
        this.newEntity.setExtax(t.getExtax());
        this.newEntity.setTaxes(t.getTaxes());
        return true;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (!super.doBeforeUpdate()) {
            return false;
        }
        this.currentEntity.setAmts(this.currentEntity.getQty().multiply(this.currentEntity.getPrice()));
        Tax t = BaseLib.getTaxes(this.currentEntity.getTaxtype(), this.currentEntity.getTaxkind(), this.currentEntity.getTaxrate(), this.currentEntity.getAmts(), 2);
        this.currentEntity.setExtax(t.getExtax());
        this.currentEntity.setTaxes(t.getTaxes());
        return true;
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.currentEntity.setCustomer(entity);
            this.currentEntity.setItemMaster(null);
            this.currentEntity.setCustomeritemno("");
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setTaxtype(entity.getTaxtype());
            this.currentEntity.setTaxkind(entity.getTaxkind());
            this.currentEntity.setTaxrate(entity.getTaxrate());
            this.currentEntity.setPayment(entity.getPayment());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Customer entity = (Customer) event.getObject();
            this.newEntity.setCustomer(entity);
            this.newEntity.setItemMaster(null);
            this.newEntity.setCustomeritemno("");
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setTaxtype(entity.getTaxtype());
            this.newEntity.setTaxkind(entity.getTaxkind());
            this.newEntity.setTaxrate(entity.getTaxrate());
            this.newEntity.setPayment(entity.getPayment());
        }
    }

    public void handleDialogReturnCurrencyWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
            this.currentEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnCurrencyWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.newEntity.setCurrency(entity.getCurrency());
            this.newEntity.setExchange(entity.getExchange());
        }
    }

    public void handleDialogReturnItemMasterWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.currentEntity.setItemMaster(entity);
            this.currentEntity.setItemno(entity.getItemno());
            this.currentEntity.setUnit(entity.getUnit());
        }
    }

    public void handleDialogReturnItemMasterWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            ItemMaster entity = (ItemMaster) event.getObject();
            this.newEntity.setItemMaster(entity);
            this.newEntity.setItemno(entity.getItemno());
            this.newEntity.setUnit(entity.getUnit());
        }
    }

    public void handleDialogReturnUnitWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Unit entity = (Unit) event.getObject();
            this.currentEntity.setUnit(entity.getUnit());
        }
    }

    public void handleDialogReturnUnitWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Unit entity = (Unit) event.getObject();
            this.newEntity.setUnit(entity.getUnit());
        }
    }

    @Override
    public void init() {
        this.superEJB = salesTransactionBean;
        setModel(new SalesTransactionModel(salesTransactionBean));
        this.model.getFilterFields().put("status", "N");
        this.model.getSortFields().put("status", "ASC");
        this.model.getSortFields().put("formid", "DESC");
        setSystemUserList(systemUserBean.findAll());
        setDeptList(departmentBean.findAll());
        super.init();
    }

    @Override
    public void print() throws Exception {
        if (currentEntity == null) {
            showWarnMsg("Warn", "没有可打印数据");
            return;
        }
        if (currentPrgGrant == null) {
            showErrorMsg("Error", "获取程序信息失败");
            return;
        }
        //设置报表参数
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", currentEntity.getId());
        params.put("formid", currentEntity.getFormid());
        params.put("JNDIName", this.currentPrgGrant.getSysprg().getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentPrgGrant.getSysprg().getRptformat() != null) {
            reportFormat = this.currentPrgGrant.getSysprg().getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentPrgGrant.getSysprg().getRptdesign() + ".rptdesign";
        String outputName = reportOutputPath + currentEntity.getFormid() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getFormid() + "." + reportFormat;
        try {
            if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getRptclazz() != null) {
                reportClassLoader = Class.forName(this.currentPrgGrant.getSysprg().getRptclazz()).getClassLoader();
            }
            //初始配置
            this.reportInitAndConfig();
            //生成报表
            this.reportRunAndOutput(reportName, params, outputName, reportFormat, null);
            //预览报表
            this.preview();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("formid", queryFormId);
            }
            if (queryItemno != null && !"".equals(queryItemno)) {
                this.model.getFilterFields().put("itemno", queryItemno);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("customer.customer", queryName);
            }
            if (queryDateBegin != null) {
                this.model.getFilterFields().put("formdateBegin", queryDateBegin);
            }
            if (queryDateEnd != null) {
                this.model.getFilterFields().put("formdateEnd", queryDateEnd);
            }
            if (queryState != null && !"ALL".equals(queryState)) {
                this.model.getFilterFields().put("status", queryState);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.model.getFilterFields().put("status", "N");
    }

    /**
     * @return the queryItemno
     */
    public String getQueryItemno() {
        return queryItemno;
    }

    /**
     * @param queryItemno the queryItemno to set
     */
    public void setQueryItemno(String queryItemno) {
        this.queryItemno = queryItemno;
    }

    /**
     * @return the systemUserList
     */
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    /**
     * @param systemUserList the systemUserList to set
     */
    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    /**
     * @return the deptList
     */
    public List<Department> getDeptList() {
        return deptList;
    }

    /**
     * @param deptList the deptList to set
     */
    public void setDeptList(List<Department> deptList) {
        this.deptList = deptList;
    }

}
