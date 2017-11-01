/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.model;

/**
 *
 * @author BD101009
 */
public class LoginUser {
    
    String username;
    
    Integer id;
    
    Integer idAuth;
    
    Integer logged;

    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }
    
    

    public Integer getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(Integer idAuth) {
        this.idAuth = idAuth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
    String password;
    
    String displayname;

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
    public boolean IsEmpty()
    {
        return (this.id == null);
    }
    
}
