/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hwx.validator;

import com.hwx.ejb.SystemUserBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author KevinDong
 */
@FacesValidator("com.hwx.validator.UserMailAdd")
public class UserMailAddValidator implements Validator {

    @EJB
    SystemUserBean systemUserBean;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            if (systemUserBean.findByMailAdd(value.toString()) == null) {
                return;
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "邮箱已注册", "邮箱已注册");
        throw new ValidatorException(message);
    }

}
