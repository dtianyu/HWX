/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.PurchaseKindBean;
import com.hwx.entity.PurchaseKind;
import com.hwx.lazy.PurchaseKindModel;
import com.hwx.web.SuperSingleBean;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class PurchaseKindManagedBean extends SuperSingleBean<PurchaseKind> {

    @EJB
    private PurchaseKindBean purchaseKindBean;

    /**
     * Creates a new instance of PurchaseKindManagedBean
     */
    public PurchaseKindManagedBean() {
        super(PurchaseKind.class);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (!super.doBeforePersist()) {
            return false;
        }
        if (this.getCurrentPrgGrant() != null) {
            if (this.getCurrentPrgGrant().getSysprg().getNoauto() && !this.getCurrentPrgGrant().getSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "purkind");
                this.newEntity.setPurkind(formid);
            } else {
                Map<String, Object> filters = new HashMap<>();
                filters.put("purkind", this.newEntity.getPurkind());
                if (purchaseKindBean.getRowCount(filters) > 0) {
                    showErrorMsg("Error", "编号已存在无法保存");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void init() {
        this.superEJB = purchaseKindBean;
        setModel(new PurchaseKindModel(purchaseKindBean));
        super.init();
    }

}
