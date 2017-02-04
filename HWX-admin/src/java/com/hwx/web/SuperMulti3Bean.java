/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.web;

import com.lightshell.comm.SuperEntity;
import com.hwx.control.UserManagedBean;
import com.hwx.ejb.SysprgBean;
import com.hwx.entity.Sysprg;
import com.lightshell.comm.SuperDetailEntity;
import com.lightshell.comm.SuperMulti3ManagedBean;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author KevinDong
 * @param <T>
 * @param <D1>
 * @param <D2>
 * @param <D3>
 */
public abstract class SuperMulti3Bean<T extends SuperEntity, D1 extends SuperDetailEntity, D2 extends SuperDetailEntity, D3 extends SuperDetailEntity> extends SuperMulti3ManagedBean<T, D1, D2, D3> {

    @EJB
    protected SysprgBean sysprgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected String persistenceUnitName;
    protected String appDataPath;
    protected String appImgPath;
    protected Sysprg currentPrgGrant;

    /**
     * @param entityClass
     * @param detailClass
     * @param detailClass2
     * @param detailClass3
     */
    public SuperMulti3Bean(Class<T> entityClass, Class<D1> detailClass, Class<D2> detailClass2, Class<D3> detailClass3) {
        this.entityClass = entityClass;
        this.detailClass = detailClass;
        this.detailClass2 = detailClass2;
        this.detailClass3 = detailClass3;
    }

    @Override
    protected void buildJsonObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildJsonArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void construct() {
        FacesContext fc = FacesContext.getCurrentInstance();
        appDataPath = fc.getExternalContext().getRealPath("/") + fc.getExternalContext().getInitParameter("com.hwx.web.appdatapath");
        appImgPath = fc.getExternalContext().getRealPath("/") + fc.getExternalContext().getInitParameter("com.hwx.web.appimgpath");
        reportPath = fc.getExternalContext().getRealPath("/") + fc.getExternalContext().getInitParameter("com.hwx.web.reportpath");
        reportOutputFormat = fc.getExternalContext().getInitParameter("com.hwx.web.reportoutputformat");
        reportOutputPath = fc.getExternalContext().getRealPath("/") + fc.getExternalContext().getInitParameter("com.hwx.web.reportoutputpath");
        reportViewContext = fc.getExternalContext().getInitParameter("com.hwx.web.reportviewcontext");
        persistenceUnitName = fc.getExternalContext().getInitParameter("com.hwx.jpa.unitname");
        int beginIndex = fc.getViewRoot().getViewId().lastIndexOf("/") + 1;
        int endIndex = fc.getViewRoot().getViewId().lastIndexOf(".");
        currentPrgGrant = sysprgBean.findByAPI(fc.getViewRoot().getViewId().substring(beginIndex, endIndex));
        if (getCurrentPrgGrant() != null) {
            this.doAdd = getCurrentPrgGrant().getDoadd();
            this.doPrt = getCurrentPrgGrant().getDoprt();
        }
        super.construct();
    }

    @Override
    public void create() {
        super.create();
        if (newEntity != null) {
            newEntity.setStatus("N");
            newEntity.setCreator(getUserManagedBean().getCurrentUser().getUsername());
            newEntity.setCredateToNow();
        }
        setCurrentEntity(newEntity);
    }

    @Override
    public String getAppDataPath() {
        return this.appDataPath;
    }

    @Override
    public String getAppImgPath() {
        return this.appImgPath;
    }

    @Override
    public String getPersistenceUnitName() {
        return this.persistenceUnitName;
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
        params.put("pid", currentEntity.getId());
        params.put("JNDIName", this.currentPrgGrant.getRptjndi());
        //设置报表名称
        String reportFormat;
        if (this.currentPrgGrant.getRptformat() != null) {
            reportFormat = this.currentPrgGrant.getRptformat();
        } else {
            reportFormat = reportOutputFormat;
        }
        String reportName = reportPath + this.currentPrgGrant.getRptdesign();
        String outputName = reportOutputPath + currentEntity.getId() + "." + reportFormat;
        this.reportViewPath = reportViewContext + currentEntity.getId() + "." + reportFormat;
        try {
            if (this.currentPrgGrant != null && this.currentPrgGrant.getRptclazz() != null) {
                reportClassLoader = Class.forName(this.currentPrgGrant.getRptclazz()).getClassLoader();
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
    public void preview() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.reportViewPath);
    }

    @Override
    public void pull() {

    }

    @Override
    public void push() {
        buildJsonArray();
    }

    @Override
    protected void setToolBar() {
        if (currentEntity != null && getCurrentPrgGrant() != null && currentEntity.getStatus() != null) {
            switch (currentEntity.getStatus()) {
                case "V":
                    this.doEdit = getCurrentPrgGrant().getDoedit() && false;
                    this.doDel = getCurrentPrgGrant().getDodel() && false;
                    this.doCfm = false;
                    this.doUnCfm = getCurrentPrgGrant().getDouncfm() && true;
                    break;
                default:
                    this.doEdit = getCurrentPrgGrant().getDoedit() && true;
                    this.doDel = getCurrentPrgGrant().getDodel() && true;
                    this.doCfm = getCurrentPrgGrant().getDocfm() && true;
                    this.doUnCfm = false;
            }
        } else {
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
        }
    }

    @Override
    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeUnverify()) {
                    currentEntity.setStatus("N");
                    currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setOptdateToNow();
                    currentEntity.setCfmuser(null);
                    currentEntity.setCfmdate(null);
                    superEJB.unverify(currentEntity);
                    doAfterUnverify();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "还原前检查失败");
                }
            } catch (Exception ex) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", ex.toString());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
        }
    }

    @Override
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                if (doBeforeVerify()) {
                    currentEntity.setStatus("V");
                    currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUsername());
                    currentEntity.setCfmdateToNow();
                    superEJB.verify(currentEntity);
                    doAfterVerify();
                    showMsg(FacesMessage.SEVERITY_INFO, "Info", "更新成功");
                } else {
                    showMsg(FacesMessage.SEVERITY_WARN, "Warn", "审核前检查失败");
                }
            } catch (Exception ex) {
                showMsg(FacesMessage.SEVERITY_ERROR, "Error", ex.toString());
            }
        } else {
            showMsg(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据");
        }
    }

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    /**
     * @return the currentPrgGrant
     */
    public Sysprg getCurrentPrgGrant() {
        return currentPrgGrant;
    }

}