/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.service;

/**
 *
 * @author BD101009
 */

import com.projects.angularcrud.model.User;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;

     public OnRegistrationCompleteEvent(
      User user, Locale locale, String appUrl) {
        super(user);
         
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    private User user;

    public String getappUrl() {
        return appUrl;
    }

    public void setappUrl(String v_appUrl) {
        this.appUrl = v_appUrl;
    }
    
   

    // standard getters and setters
}