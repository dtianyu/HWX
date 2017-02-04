/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.ItemCategoryBean;
import com.hwx.entity.ItemCategory;
import com.hwx.lazy.ItemCategoryModel;
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
public class ItemCategoryManagedBean extends SuperSingleBean<ItemCategory> {

    @EJB
    private ItemCategoryBean itemCategoryBean;

    /**
     * Creates a new instance of ItemCategoryManagedBean
     */
    public ItemCategoryManagedBean() {
        super(ItemCategory.class);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentPrgGrant() != null) {
            if (this.getCurrentPrgGrant().getSysprg().getNoauto() && !this.getCurrentPrgGrant().getSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "category");
                this.newEntity.setCategory(formid);
            } else {
                Map<String, Object> filters = new HashMap<>();
                filters.put("category", this.newEntity.getCategory());
                if (itemCategoryBean.getRowCount(filters) > 0) {
                    showErrorMsg("Error", "编号已存在无法保存");
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void init() {
        this.superEJB = itemCategoryBean;
        setModel(new ItemCategoryModel(itemCategoryBean));
        super.init();
    }

}
