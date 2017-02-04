/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.entity;

import com.lightshell.comm.SuperEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "currency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c")
    , @NamedQuery(name = "Currency.findById", query = "SELECT c FROM Currency c WHERE c.id = :id")
    , @NamedQuery(name = "Currency.findByCurrency", query = "SELECT c FROM Currency c WHERE c.currency = :currency")
    , @NamedQuery(name = "Currency.findByExchange", query = "SELECT c FROM Currency c WHERE c.exchange = :exchange")
    , @NamedQuery(name = "Currency.findByRemark", query = "SELECT c FROM Currency c WHERE c.remark = :remark")
    , @NamedQuery(name = "Currency.findByStatus", query = "SELECT c FROM Currency c WHERE c.status = :status")
    , @NamedQuery(name = "Currency.findByCreator", query = "SELECT c FROM Currency c WHERE c.creator = :creator")
    , @NamedQuery(name = "Currency.findByCredate", query = "SELECT c FROM Currency c WHERE c.credate = :credate")
    , @NamedQuery(name = "Currency.findByOptuser", query = "SELECT c FROM Currency c WHERE c.optuser = :optuser")
    , @NamedQuery(name = "Currency.findByOptdate", query = "SELECT c FROM Currency c WHERE c.optdate = :optdate")
    , @NamedQuery(name = "Currency.findByCfmuser", query = "SELECT c FROM Currency c WHERE c.cfmuser = :cfmuser")
    , @NamedQuery(name = "Currency.findByCfmdate", query = "SELECT c FROM Currency c WHERE c.cfmdate = :cfmdate")})
public class Currency extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "currency")
    private String currency;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "exchange")
    private BigDecimal exchange;
    @Size(max = 200)
    @Column(name = "remark")
    private String remark;

    public Currency() {
    }

    public Currency(Integer id) {
        this.id = id;
    }

    public Currency(Integer id, String currency, BigDecimal exchange, String status) {
        this.id = id;
        this.currency = currency;
        this.exchange = exchange;
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getExchange() {
        return exchange;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
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
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.Currency[ id=" + id + " ]";
    }

}
