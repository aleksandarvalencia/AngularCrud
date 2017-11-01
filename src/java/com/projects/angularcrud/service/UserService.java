/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.service;

import java.util.List;
import com.projects.angularcrud.model.LoginUser;
import com.projects.angularcrud.model.User;
import com.projects.angularcrud.model.ComboValues;


public interface UserService {
	
	User findById(Integer id);
        
        
        String DisplayNameByUserName(String username,String password);
        
        LoginUser UserLogin(String username, String password);

	List<User> findAll();
        
        List<ComboValues>  getAuthList();

	//void save(User user);
        
       // void insert(User user);

	//void update(User user);

	//void delete(Integer id);
        //void delete(User user);
        
        void setidAuth(Integer idAuth);
        
        Integer getidAuth() ;
        
        void setlogStatus(Integer log);
        
        Integer getlogStatus() ;
        
        
        void setidUser(Integer idUser);
        
        Integer getidUser() ;
        
        List<User> executeProcUser(String p_akcija, Integer p_idUser, String p_firstname,String p_secondname,
                String p_username,String p_password,Integer p_idAuth, Integer p_status,Integer p_logged);
        
        Integer DisplayLogStatusByUserName(String username,String password);
	
}
