/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.web;

import com.hwx.control.UserManagedBean;
import com.hwx.ejb.SysprgBean;
import com.hwx.entity.SysGrantPrg;
import com.lightshell.comm.BaseEntity;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.SuperSingleManagedBean;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author KevinDong
 * @param <T>
 */
public abstract class SuperQueryBean<T extends BaseEntity> extends SuperSingleManagedBean<T> {

    @EJB
    protected SysprgBean sysprgBean;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected String persistenceUnitName;
    protected String appDataPath;
    protected String appImgPath;
    protected SysGrantPrg currentPrgGrant;

    protected Map<String, String[]> params;//页面传参

    /**
     * @param entityClass
     */
    public SuperQueryBean(Class<T> entityClass) {
        this.entityClass = entityClass;
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
        //不需要进行操作权限设置
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
        if (userManagedBean.getSysGrantPrgList() != null && !userManagedBean.getSysGrantPrgList().isEmpty()) {
            userManagedBean.getSysGrantPrgList().stream().filter((p) -> (p.getSysprg().getApi().equals(fc.getViewRoot().getViewId().substring(beginIndex, endIndex)))).forEachOrdered((p) -> {
                currentPrgGrant = p;
            });
        }
        super.construct();
    }

    @Override
    public void persist() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        if (getCurrentPrgGrant() != null && getCurrentPrgGrant().getDoprt()) {
            HashMap<String, Object> reportParams = new HashMap<>();
            reportParams.put("JNDIName", this.getCurrentPrgGrant().getSysprg().getRptjndi());
            if (!this.model.getFilterFields().isEmpty()) {
                reportParams.put("filterFields", BaseLib.convertMapToStringWithClass(this.model.getFilterFields()));
            } else {
                reportParams.put("filterFields", "");
            }
            if (!this.model.getSortFields().isEmpty()) {
                reportParams.put("sortFields", BaseLib.convertMapToString(this.model.getSortFields()));
            } else {
                reportParams.put("sortFields", "");
            }
            //设置报表名称
            String reportFormat;
            if (this.getCurrentPrgGrant().getSysprg().getRptformat() != null) {
                reportFormat = this.getCurrentPrgGrant().getSysprg().getRptformat();
            } else {
                reportFormat = reportOutputFormat;
            }
            this.fileName = this.getCurrentPrgGrant().getSysprg().getApi() + BaseLib.formatDate("yyyyMMddHHss", this.getDate()) + "." + reportFormat;
            String reportName = reportPath + this.getCurrentPrgGrant().getSysprg().getRptdesign();
            String outputName = reportOutputPath + this.fileName;
            this.reportViewPath = reportViewContext + this.fileName;
            try {
                if (this.getCurrentPrgGrant() != null && this.getCurrentPrgGrant().getSysprg().getRptclazz() != null) {
                    reportClassLoader = Class.forName(this.getCurrentPrgGrant().getSysprg().getRptclazz()).getClassLoader();
                }
                //初始配置
                this.reportInitAndConfig();
                //生成报表
                this.reportRunAndOutput(reportName, reportParams, outputName, reportFormat, null);
                //预览报表
                this.preview();
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    @Override
    public void preview() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().redirect(this.reportViewPath);
    }

    @Override
    public void pull() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void push() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void setToolBar() {
        if (this.getCurrentPrgGrant() != null) {
            this.doAdd = false;
            this.doEdit = false;
            this.doDel = false;
            this.doCfm = false;
            this.doUnCfm = false;
            this.doPriv = currentPrgGrant.getDopriv();
            this.doPrt = this.getCurrentPrgGrant().getDoprt();
        }
    }

    @Override
    public void unverify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public SysGrantPrg getCurrentPrgGrant() {
        return currentPrgGrant;
    }

}
