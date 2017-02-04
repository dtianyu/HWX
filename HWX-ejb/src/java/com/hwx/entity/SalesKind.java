/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.entity;

import com.lightshell.comm.SuperEntity;
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
@Table(name = "saleskind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesKind.findAll", query = "SELECT s FROM SalesKind s")
    , @NamedQuery(name = "SalesKind.findById", query = "SELECT s FROM SalesKind s WHERE s.id = :id")
    , @NamedQuery(name = "SalesKind.findBySaleskind", query = "SELECT s FROM SalesKind s WHERE s.saleskind = :saleskind")
    , @NamedQuery(name = "SalesKind.findByName", query = "SELECT s FROM SalesKind s WHERE s.name = :name")
    , @NamedQuery(name = "SalesKind.findByStatus", query = "SELECT s FROM SalesKind s WHERE s.status = :status")})
public class SalesKind extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "saleskind")
    private String saleskind;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public SalesKind() {
    }

    public String getSaleskind() {
        return saleskind;
    }

    public void setSaleskind(String saleskind) {
        this.saleskind = saleskind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof SalesKind)) {
            return false;
        }
        SalesKind other = (SalesKind) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.SalesKind[ id=" + id + " ]";
    }

}
