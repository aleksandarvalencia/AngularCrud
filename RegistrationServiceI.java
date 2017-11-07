/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.service;

import com.projects.angularcrud.config.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.projects.angularcrud.model.User;
import javax.management.relation.Role;


/**
 *
 * @author BD101009
 */
@Service
@Transactional
public class RegistrationServiceI implements RegistrationService{
    /*@Autowired
    private UserRepository repository;*/
 
    @Autowired
    private VerificationTokenRepository tokenRepository;
 
    @Override
    public User registerNewUserAccount(User accountDto) 
    {
         
     
         
        User user = new User();
        user.setFirstname(accountDto.getFirstname());
        user.setSecondname(accountDto.getSecondname());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        //user.setRole(new Role(Integer.valueOf(1), user));
        //return repository.save(user);
        return user;
    }
 
    /*private boolean emailExist(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }*/
     
    @Override
    public User getUser(String verificationToken) {
        //User user = tokenRepository.findByToken(verificationToken).getUser();
        User user=new User();
        return user;
    }
     
    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
     
    /*@Override
    public void saveRegisteredUser(User user) {
        repository.save(user);
    }*/
     
    @Override
    public void createVerificationToken(User user, String token) {
        /*VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);*/
    }
    
}
