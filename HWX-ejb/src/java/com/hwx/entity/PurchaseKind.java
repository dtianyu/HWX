/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.entity;

import com.lightshell.comm.SuperEntity;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "purchasekind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseKind.findAll", query = "SELECT p FROM PurchaseKind p")
    , @NamedQuery(name = "PurchaseKind.findById", query = "SELECT p FROM PurchaseKind p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseKind.findByPurkind", query = "SELECT p FROM PurchaseKind p WHERE p.purkind = :purkind")
    , @NamedQuery(name = "PurchaseKind.findByName", query = "SELECT p FROM PurchaseKind p WHERE p.name = :name")
    , @NamedQuery(name = "PurchaseKind.findByStatus", query = "SELECT p FROM PurchaseKind p WHERE p.status = :status")})
public class PurchaseKind extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 2)
    @Column(name = "proptype")
    private String proptype;
    @Column(name = "charge")
    private Boolean charge;
    @Column(name = "tradeid")
    private Integer tradeid;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public PurchaseKind() {
    }

    public PurchaseKind(Integer id) {
        this.id = id;
    }

    public PurchaseKind(Integer id, String purkind, String name, String status) {
        this.id = id;
        this.purkind = purkind;
        this.name = name;
        this.status = status;
    }

    public String getPurkind() {
        return purkind;
    }

    public void setPurkind(String purkind) {
        this.purkind = purkind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProptype() {
        return proptype;
    }

    public void setProptype(String proptype) {
        this.proptype = proptype;
    }

    public Boolean getCharge() {
        return charge;
    }

    public void setCharge(Boolean charge) {
        this.charge = charge;
    }

    public Integer getTradeid() {
        return tradeid;
    }

    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseKind)) {
            return false;
        }
        PurchaseKind other = (PurchaseKind) object;
        if (Objects.equals(this.purkind, other.purkind)) {
            return true;
        }
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.PurchaseKind[ id=" + id + " ]";
    }

}
