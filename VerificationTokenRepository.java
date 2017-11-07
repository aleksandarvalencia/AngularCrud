/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.service;

import com.projects.angularcrud.config.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projects.angularcrud.model.User;


/**
 *
 * @author BD101009
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> 
{
 
    VerificationToken findByToken(String token);
 
    VerificationToken findByUser(User user);
    
}
