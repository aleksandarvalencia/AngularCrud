/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.service;
import com.projects.angularcrud.config.VerificationToken;
import com.projects.angularcrud.model.User;

/**
 *
 * @author BD101009
 */
public interface RegistrationService {
    
    User registerNewUserAccount(User accountDto);
 
    User getUser(String verificationToken);
 
   // void saveRegisteredUser(User user);
 
    void createVerificationToken(User user, String token);
 
    VerificationToken getVerificationToken(String VerificationToken);
    
}
