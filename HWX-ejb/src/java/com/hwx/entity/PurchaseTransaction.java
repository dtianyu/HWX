/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.entity;

import com.lightshell.comm.FormEntity;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "purchasetransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseTransaction.findAll", query = "SELECT p FROM PurchaseTransaction p")
    , @NamedQuery(name = "PurchaseTransaction.findById", query = "SELECT p FROM PurchaseTransaction p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseTransaction.findByFormid", query = "SELECT p FROM PurchaseTransaction p WHERE p.formid = :formid")
    , @NamedQuery(name = "PurchaseTransaction.findByFormdate", query = "SELECT p FROM PurchaseTransaction p WHERE p.formdate = :formdate")
    , @NamedQuery(name = "PurchaseTransaction.findByPurtype", query = "SELECT p FROM PurchaseTransaction p WHERE p.purtype = :purtype")
    , @NamedQuery(name = "PurchaseTransaction.findByPurkind", query = "SELECT p FROM PurchaseTransaction p WHERE p.purkind = :purkind")
    , @NamedQuery(name = "PurchaseTransaction.findByAbroad", query = "SELECT p FROM PurchaseTransaction p WHERE p.abroad = :abroad")
    , @NamedQuery(name = "PurchaseTransaction.findByVendorid", query = "SELECT p FROM PurchaseTransaction p WHERE p.vendor.id = :vendorid")
    , @NamedQuery(name = "PurchaseTransaction.findByItemno", query = "SELECT p FROM PurchaseTransaction p WHERE p.itemno = :itemno")
    , @NamedQuery(name = "PurchaseTransaction.findByPId", query = "SELECT p FROM PurchaseTransaction p WHERE p.pid = :pid")})
public class PurchaseTransaction extends FormEntity {

    @Size(max = 10)
    @Column(name = "purtype")
    private String purtype;
    @Size(max = 10)
    @Column(name = "purkind")
    private String purkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abroad")
    private boolean abroad;

    @JoinColumn(name = "vendorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vendor vendor;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Department dept;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private SystemUser buyer;

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "taxtype")
    private String taxtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "taxkind")
    private String taxkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxrate")
    private BigDecimal taxrate;
    @Size(max = 45)
    @Column(name = "paykind")
    private String paykind;
    @Size(max = 45)
    @Column(name = "payment")
    private String payment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq")
    private int seq;

    @JoinColumn(name = "itemid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private ItemMaster itemMaster;

    @Size(max = 45)
    @Column(name = "itemno")
    private String itemno;
    @Size(max = 45)
    @Column(name = "vendoritemno")
    private String vendoritemno;
    @Size(max = 20)
    @Column(name = "brand")
    private String brand;
    @Size(max = 20)
    @Column(name = "batch")
    private String batch;
    @Size(max = 20)
    @Column(name = "sn")
    private String sn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private BigDecimal qty;
    @Size(max = 10)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amts")
    private BigDecimal amts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "extax")
    private BigDecimal extax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taxes")
    private BigDecimal taxes;
    @Size(max = 100)
    @Column(name = "srcapi")
    private String srcapi;
    @Size(max = 20)
    @Column(name = "srcformid")
    private String srcformid;
    @Column(name = "srcseq")
    private Integer srcseq;
    @Size(max = 20)
    @Column(name = "pid")
    private String pid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payamt")
    private BigDecimal payamt;
    @Column(name = "preamt")
    private BigDecimal preamt;
    @Column(name = "addamt")
    private BigDecimal addamt;
    @Column(name = "offamt")
    private BigDecimal offamt;
    @Column(name = "shortamt")
    private BigDecimal shortamt;
    @Column(name = "overamt")
    private BigDecimal overamt;
    @Column(name = "backqty")
    private BigDecimal backqty;
    @Column(name = "backamt")
    private BigDecimal backamt;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public PurchaseTransaction() {
        this.abroad = false;
        this.seq = 1;
        this.payamt = BigDecimal.ZERO;
        this.preamt = BigDecimal.ZERO;
        this.addamt = BigDecimal.ZERO;
        this.offamt = BigDecimal.ZERO;
        this.shortamt = BigDecimal.ZERO;
        this.overamt = BigDecimal.ZERO;
        this.backqty = BigDecimal.ZERO;
        this.backamt = BigDecimal.ZERO;
    }

    public String getPurtype() {
        return purtype;
    }

    public void setPurtype(String purtype) {
        this.purtype = purtype;
    }

    public String getPurkind() {
        return purkind;
    }

    public void setPurkind(String purkind) {
        this.purkind = purkind;
    }

    public boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(boolean abroad) {
        this.abroad = abroad;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public SystemUser getBuyer() {
        return buyer;
    }

    public void setBuyer(SystemUser buyer) {
        this.buyer = buyer;
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

    public String getTaxtype() {
        return taxtype;
    }

    public void setTaxtype(String taxtype) {
        this.taxtype = taxtype;
    }

    public String getTaxkind() {
        return taxkind;
    }

    public void setTaxkind(String taxkind) {
        this.taxkind = taxkind;
    }

    /**
     * @return the taxrate
     */
    public BigDecimal getTaxrate() {
        return taxrate;
    }

    /**
     * @param taxrate the taxrate to set
     */
    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    /**
     * @return the paykind
     */
    public String getPaykind() {
        return paykind;
    }

    /**
     * @param paykind the paykind to set
     */
    public void setPaykind(String paykind) {
        this.paykind = paykind;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public ItemMaster getItemMaster() {
        return itemMaster;
    }

    public void setItemMaster(ItemMaster itemMaster) {
        this.itemMaster = itemMaster;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getVendoritemno() {
        return vendoritemno;
    }

    public void setVendoritemno(String vendoritemno) {
        this.vendoritemno = vendoritemno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmts() {
        return amts;
    }

    public void setAmts(BigDecimal amts) {
        this.amts = amts;
    }

    public BigDecimal getExtax() {
        return extax;
    }

    public void setExtax(BigDecimal extax) {
        this.extax = extax;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public String getSrcapi() {
        return srcapi;
    }

    public void setSrcapi(String srcapi) {
        this.srcapi = srcapi;
    }

    public String getSrcformid() {
        return srcformid;
    }

    public void setSrcformid(String srcformid) {
        this.srcformid = srcformid;
    }

    public Integer getSrcseq() {
        return srcseq;
    }

    public void setSrcseq(Integer srcseq) {
        this.srcseq = srcseq;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public BigDecimal getPayamt() {
        return payamt;
    }

    public void setPayamt(BigDecimal payamt) {
        this.payamt = payamt;
    }

    public BigDecimal getPreamt() {
        return preamt;
    }

    public void setPreamt(BigDecimal preamt) {
        this.preamt = preamt;
    }

    public BigDecimal getAddamt() {
        return addamt;
    }

    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

    public BigDecimal getOffamt() {
        return offamt;
    }

    public void setOffamt(BigDecimal offamt) {
        this.offamt = offamt;
    }

    public BigDecimal getShortamt() {
        return shortamt;
    }

    public void setShortamt(BigDecimal shortamt) {
        this.shortamt = shortamt;
    }

    public BigDecimal getOveramt() {
        return overamt;
    }

    public void setOveramt(BigDecimal overamt) {
        this.overamt = overamt;
    }

    public BigDecimal getBackqty() {
        return backqty;
    }

    public void setBackqty(BigDecimal backqty) {
        this.backqty = backqty;
    }

    public BigDecimal getBackamt() {
        return backamt;
    }

    public void setBackamt(BigDecimal backamt) {
        this.backamt = backamt;
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
        if (!(object instanceof PurchaseTransaction)) {
            return false;
        }
        PurchaseTransaction other = (PurchaseTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.PurchaseTransaction[ id=" + id + " ]";
    }

}
