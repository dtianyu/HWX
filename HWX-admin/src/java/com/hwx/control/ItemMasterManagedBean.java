/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.control;

import com.hwx.ejb.ItemCategoryBean;
import com.hwx.ejb.ItemMakeBean;
import com.hwx.ejb.ItemMasterBean;
import com.hwx.entity.ItemCategory;
import com.hwx.entity.ItemMake;
import com.hwx.entity.ItemMaster;
import com.hwx.entity.Unit;
import com.hwx.lazy.ItemMasterModel;
import com.hwx.web.SuperMultiBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "itemMasterManagedBean")
@SessionScoped
public class ItemMasterManagedBean extends SuperMultiBean<ItemMaster, ItemMake> {

    @EJB
    protected ItemCategoryBean itemCategoryBean;
    @EJB
    protected ItemMasterBean itemMasterBean;
    @EJB
    protected ItemMakeBean itemMakeBean;

    protected List<ItemCategory> itemCategoryList;
    protected String queryItemspec;
    protected String queryItemmake;

    public ItemMasterManagedBean() {
        super(ItemMaster.class, ItemMake.class);
    }

    @Override
    public void create() {
        super.create();
        newEntity.setProptype("3");
        newEntity.setUnittype("1");
        newEntity.setMaketype("P");
        newEntity.setUnitexch(BigDecimal.ONE);
        newEntity.setQcpass(false);
        newEntity.setInvtype(true);
        newEntity.setBbstype("000");
        newEntity.setPurmax(BigDecimal.ZERO);
        newEntity.setPurmin(BigDecimal.ZERO);
        newEntity.setInvmax(BigDecimal.ZERO);
        newEntity.setInvmin(BigDecimal.ZERO);
        if (this.superEJB != null && this.getCurrentPrgGrant().getSysprg().getNoauto()) {
            String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "itemno");
            newEntity.setItemno(formid);
        }
        setCurrentEntity(newEntity);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        this.newDetail.setItemno("");
        setCurrentDetail(newDetail);
    }

    @Override
    protected boolean doAfterPersist() throws Exception {
        //新增后即审核2016/10/31
        this.verify();
        return super.doAfterPersist();
    }

    @Override
    protected boolean doBeforeDelete(ItemMaster entity) throws Exception {
        if (entity != null) {
            //Map<String, Object> filters = new HashMap<>();
            //filters.put("itemno", entity.getItemno());
            //if (salesOrderDetailBean.getRowCount(filters) > 0) {
            //    showErrorMsg("Error", "已有交易记录不能删除");
            //    return false;
            //}
        }
        return super.doBeforeDelete(entity);
    }

    @Override
    protected boolean doBeforePersist() throws Exception {
        if (this.newEntity != null && this.getCurrentPrgGrant() != null) {
            if (this.getCurrentPrgGrant().getSysprg().getNoauto() && !this.getCurrentPrgGrant().getSysprg().getNochange()) {
                String formid = this.superEJB.getFormId(newEntity.getCredate(), this.getCurrentPrgGrant().getSysprg().getNolead(), this.getCurrentPrgGrant().getSysprg().getNoformat(), this.getCurrentPrgGrant().getSysprg().getNoseqlen(), "itemno");
                this.newEntity.setItemno(formid);
            } else {
                Map<String, Object> filters = new HashMap<>();
                filters.put("itemno", this.newEntity.getItemno());
                if (itemMasterBean.getRowCount(filters) > 0) {
                    showErrorMsg("Error", "品号已存在无法保存");
                    return false;
                }
            }
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                this.addedDetailList.forEach((detail) -> {
                    detail.setItemno(newEntity.getItemno());
                });
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                this.editedDetailList.forEach((detail) -> {
                    detail.setItemno(newEntity.getItemno());
                });
            }
            return true;
        }
        return false;
    }

    @Override
    protected boolean doBeforeUpdate() throws Exception {
        if (this.currentEntity != null) {
            if (this.addedDetailList != null && !this.addedDetailList.isEmpty()) {
                for (ItemMake detail : this.addedDetailList) {
                    detail.setPid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            if (this.editedDetailList != null && !this.editedDetailList.isEmpty()) {
                for (ItemMake detail : this.editedDetailList) {
                    detail.setPid(this.currentEntity.getId());
                    detail.setItemno(this.currentEntity.getItemno());
                }
            }
            return super.doBeforeUpdate();
        }
        return false;
    }

    @Override
    public void handleDialogReturnWhenEdit(SelectEvent event) {
        if (event.getObject() != null && currentEntity != null) {
            Unit entity = (Unit) event.getObject();
            this.currentEntity.setUnit(entity.getUnit());
        }
    }

    @Override
    public void handleDialogReturnWhenNew(SelectEvent event) {
        if (event.getObject() != null && newEntity != null) {
            Unit entity = (Unit) event.getObject();
            this.newEntity.setUnit(entity.getUnit());
        }
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null && this.newEntity != null) {
            this.newEntity.setImg1(fileName);
        }
    }

    @Override
    public void handleFileUploadWhenEdit(FileUploadEvent event) {
        super.handleFileUploadWhenEdit(event);
        if (this.fileName != null && this.currentEntity != null) {
            this.currentEntity.setImg1(fileName);
        }
    }

    @Override
    public void init() {
        this.superEJB = itemMasterBean;
        setModel(new ItemMasterModel(itemMasterBean));
        this.detailEJB = itemMakeBean;
        itemCategoryList = itemCategoryBean.findAll();
        super.init();
    }

    @Override
    public void persist() {
        if (getNewEntity() != null) {
            try {
                if (doBeforePersist()) {
                    this.superEJB.persist(newEntity);
                    int pid = newEntity.getId();
                    if (getEditedDetailList() != null && !getEditedDetailList().isEmpty()) {
                        this.editedDetailList.stream().map((detail) -> {
                            detail.setPid(pid);
                            return detail;
                        }).forEach((detail) -> {
                            this.detailEJB.update(detail);
                        });
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        this.deletedDetailList.stream().forEach((detail) -> {
                            this.detailEJB.delete(detail);
                        });
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        this.addedDetailList.stream().map((detail) -> {
                            detail.setPid(pid);
                            return detail;
                        }).forEach((detail) -> {
                            this.detailEJB.persist(detail);
                        });
                    }
                    doAfterPersist();
                    showInfoMsg("Info", "更新成功");
                } else {
                    showErrorMsg("Error", "更新前检核失败");
                }
            } catch (Exception e) {
                showErrorMsg("Error", e.getMessage());
            }
        } else {
            showWarnMsg("Warn", "没有可更新数据");
        }
    }

    @Override
    public void query() {
        if (this.model != null && this.model.getFilterFields() != null) {
            this.model.getFilterFields().clear();
            if (queryFormId != null && !"".equals(queryFormId)) {
                this.model.getFilterFields().put("itemno", queryFormId);
            }
            if (queryName != null && !"".equals(queryName)) {
                this.model.getFilterFields().put("itemdesc", queryName);
            }
            if (queryItemmake != null && !"".equals(queryItemmake)) {
                this.model.getFilterFields().put("itemmake", queryItemmake);
            }
            if (queryItemspec != null && !"".equals(queryItemspec)) {
                this.model.getFilterFields().put("itemspec", queryItemspec);
            }
        }
    }

    @Override
    public void update() {
        if (currentEntity != null) {
            try {
                if (doBeforeUpdate()) {
                    this.superEJB.update(currentEntity);
                    if (getEditedDetailList() != null && !getEditedDetailList().isEmpty()) {
                        for (ItemMake detail : this.editedDetailList) {
                            detail.setPid(currentEntity.getId());
                            this.detailEJB.update(detail);
                        }
                    }
                    if (getDeletedDetailList() != null && !getDeletedDetailList().isEmpty()) {
                        for (ItemMake detail : this.deletedDetailList) {
                            this.detailEJB.delete(detail);
                        }
                    }
                    if (getAddedDetailList() != null && !getAddedDetailList().isEmpty()) {
                        for (ItemMake detail : this.addedDetailList) {
                            detail.setPid(currentEntity.getId());
                            this.detailEJB.persist(detail);
                        }
                    }
                    doAfterUpdate();
                    showInfoMsg("Info", "更新成功");
                } else {
                    showErrorMsg("Error", "更新前检核失败");
                }
            } catch (Exception e) {
                showErrorMsg("Error", e.toString());
            }
        } else {
            showWarnMsg("Warn", "没有可更新数据");
        }
    }

    /**
     * @return the itemCategoryList
     */
    public List<ItemCategory> getItemCategoryList() {
        return itemCategoryList;
    }

    /**
     * @return the queryItemspec
     */
    public String getQueryItemspec() {
        return queryItemspec;
    }

    /**
     * @param queryItemspec the queryItemspec to set
     */
    public void setQueryItemspec(String queryItemspec) {
        this.queryItemspec = queryItemspec;
    }

    /**
     * @return the queryItemmake
     */
    public String getQueryItemmake() {
        return queryItemmake;
    }

    /**
     * @param queryItemmake the queryItemmake to set
     */
    public void setQueryItemmake(String queryItemmake) {
        this.queryItemmake = queryItemmake;
    }

}
