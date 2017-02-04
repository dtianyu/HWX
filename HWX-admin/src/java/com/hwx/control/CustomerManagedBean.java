/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.CustomerBean;
import com.hwx.ejb.CustomerContacterBean;
import com.hwx.entity.Currency;
import com.hwx.entity.Customer;
import com.hwx.entity.CustomerContacter;
import com.hwx.lazy.CustomerModel;
import com.hwx.web.SuperMultiBean;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean extends SuperMultiBean<Customer, CustomerContacter> {

    @EJB
    private CustomerBean customerBean;
    @EJB
    private CustomerContacterBean customerContacterBean;

    public CustomerManagedBean() {
        super(Customer.class, CustomerContacter.class);
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setCurrency("CNY");
        this.newEntity.setTaxtype("0");
        this.newEntity.setTaxkind("VAT17");
        this.newEntity.setTaxrate(BigDecimal.valueOf(17));
        this.newEntity.setTradetype("C&F");
    }

    @Override
    public void createDetail() {
        super.createDetail();
        newDetail.setMajor(false);
    }

    @Override
    protected boolean doBeforeDelete(Customer entity) throws Exception {
        if (entity != null) {
            //Map<String, Object> filters = new HashMap<>();
            //filters.put("customer.customerno", entity.getCustomerno());
            //if (salesOrderBean.getRowCount(filters) > 0) {
            //    showErrorMsg("Error", "已有交易记录不能删除");
            //    return false;
            //}
        }
        return super.doBeforeDelete(entity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentPrgGrant() != null) {
            if (this.getCurrentPrgGrant().getSysprg().getNoauto()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "customerno");
                this.newEntity.setCustomerno(formid);
            }
            return true;
        }
        return false;
    }

    public void handleDialogReturnCurrencyWhenEdit(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.currentEntity.setCurrency(entity.getCurrency());
        }
    }

    public void handleDialogReturnCurrencyWhenNew(SelectEvent event) {
        if (event.getObject() != null) {
            Currency entity = (Currency) event.getObject();
            this.newEntity.setCurrency(entity.getCurrency());
        }
    }

    @Override
    public void init() {
        this.superEJB = customerBean;
        setModel(new CustomerModel(customerBean));
        this.detailEJB = customerContacterBean;
        super.init();
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("customerno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("customer", queryName);
            }
        }
    }

}