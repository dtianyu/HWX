/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.ejb;

import com.hwx.comm.SuperBean;
import com.hwx.entity.ItemCategory;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemCategoryBean extends SuperBean<ItemCategory> {

    public ItemCategoryBean() {
        super(ItemCategory.class);
    }

    public ItemCategory findByCategory(String value) {
        Query query = this.getEntityManager().createNamedQuery("ItemCategory.findByCategory");
        query.setParameter("category", value);
        try {
            Object o = query.getSingleResult();
            return (ItemCategory) o;
        } catch (NullPointerException e) {
            return null;
        }
    }

}
