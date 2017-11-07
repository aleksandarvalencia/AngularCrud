/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.model;

import java.sql.Date;
import java.util.List;

public class User {

	Integer idUser;
        Integer idAuth;
        Integer id_Userold;
        Integer status;
	String firstname;
        String secondname;
        String username;
	String password;
	String confirmPassword;
        Integer logged;
        Date logonattempt;
        Date passwordchanged;
	List<String> framework;
        String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getId_Userold() {
        return id_Userold;
    }

    public void setId_Userold(Integer id_Userold) {
        this.id_Userold = id_Userold;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

     public Integer getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(Integer idAuth) {
        this.idAuth = idAuth;
    }    
        
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLogonattempt() {
        return logonattempt;
    }

    public void setLogonattempt(Date logonattempt) {
        this.logonattempt = logonattempt;
    }

    public Date getPasswordchanged() {
        return passwordchanged;
    }

    public void setPasswordchanged(Date passwordchanged) {
        this.passwordchanged = passwordchanged;
    }

    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public String getConfirmPassword() {
            return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
    }

    public List<String> getFramework() {
            return framework;
    }

    public void setFramework(List<String> framework) {
            this.framework = framework;
    }
}

