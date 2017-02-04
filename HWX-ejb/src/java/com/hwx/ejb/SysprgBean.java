/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.ejb;

import com.hwx.comm.SuperBean;
import com.hwx.entity.Sysprg;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SysprgBean extends SuperBean<Sysprg> {

    public SysprgBean() {
        super(Sysprg.class);
    }

    public List<Sysprg> findByModuleId(int id) {
        Query query = this.getEntityManager().createNamedQuery("Sysprg.findByModuleId");
        query.setParameter("moduleid", id);
        return query.getResultList();
    }

    public Sysprg findByAPI(String api) {
        Query query = this.getEntityManager().createNamedQuery("Sysprg.findByAPI");
        query.setParameter("api", api);
        try {
            return (Sysprg) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
