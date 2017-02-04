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
@Table(name = "salestransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesTransaction.findAll", query = "SELECT s FROM SalesTransaction s")
    , @NamedQuery(name = "SalesTransaction.findById", query = "SELECT s FROM SalesTransaction s WHERE s.id = :id")
    , @NamedQuery(name = "SalesTransaction.findByFormid", query = "SELECT s FROM SalesTransaction s WHERE s.formid = :formid")
    , @NamedQuery(name = "SalesTransaction.findByFormdate", query = "SELECT s FROM SalesTransaction s WHERE s.formdate = :formdate")
    , @NamedQuery(name = "SalesTransaction.findByFormtype", query = "SELECT s FROM SalesTransaction s WHERE s.formtype = :formtype")
    , @NamedQuery(name = "SalesTransaction.findByFormkind", query = "SELECT s FROM SalesTransaction s WHERE s.formkind = :formkind")
    , @NamedQuery(name = "SalesTransaction.findByAbroad", query = "SELECT s FROM SalesTransaction s WHERE s.abroad = :abroad")
    , @NamedQuery(name = "SalesTransaction.findByCustomerid", query = "SELECT s FROM SalesTransaction s WHERE s.customerid = :customerid")
    , @NamedQuery(name = "SalesTransaction.findByPId", query = "SELECT s FROM SalesTransaction s WHERE s.pid = :pid")
    , @NamedQuery(name = "SalesTransaction.findByStatus", query = "SELECT s FROM SalesTransaction s WHERE s.status = :status")})
public class SalesTransaction extends FormEntity {

    @Size(max = 10)
    @Column(name = "formtype")
    private String formtype;
    @Size(max = 10)
    @Column(name = "formkind")
    private String formkind;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abroad")
    private boolean abroad;

    @JoinColumn(name = "customerid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customer;
    @JoinColumn(name = "deptid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Department dept;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private SystemUser user;
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
    @Size(max = 10)
    @Column(name = "tradetype")
    private String tradetype;
    @Size(max = 45)
    @Column(name = "tradename")
    private String tradename;
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
    @Column(name = "customeritemno")
    private String customeritemno;
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
    @Column(name = "recamt")
    private BigDecimal recamt;
    @Column(name = "preamt")
    private BigDecimal preamt;
    @Column(name = "addamt")
    private BigDecimal addamt;
    @Column(name = "offamt")
    private BigDecimal offamt;
    @Column(name = "backqty")
    private BigDecimal backqty;
    @Column(name = "backamt")
    private BigDecimal backamt;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;

    public SalesTransaction() {
        this.abroad = false;
        this.seq = 1;
        this.recamt = BigDecimal.ZERO;
        this.preamt = BigDecimal.ZERO;
        this.addamt = BigDecimal.ZERO;
        this.offamt = BigDecimal.ZERO;
        this.backqty = BigDecimal.ZERO;
        this.backamt = BigDecimal.ZERO;
    }

    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }

    public String getFormkind() {
        return formkind;
    }

    public void setFormkind(String formkind) {
        this.formkind = formkind;
    }

    public boolean getAbroad() {
        return abroad;
    }

    public void setAbroad(boolean abroad) {
        this.abroad = abroad;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
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

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getPaykind() {
        return paykind;
    }

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

    public String getCustomeritemno() {
        return customeritemno;
    }

    public void setCustomeritemno(String customeritemno) {
        this.customeritemno = customeritemno;
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

    public BigDecimal getRecamt() {
        return recamt;
    }

    public void setRecamt(BigDecimal recamt) {
        this.recamt = recamt;
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
        if (!(object instanceof SalesTransaction)) {
            return false;
        }
        SalesTransaction other = (SalesTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hwx.entity.SalesTransaction[ id=" + id + " ]";
    }

}
