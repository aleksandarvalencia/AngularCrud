/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

 
import com.projects.angularcrud.model.User;
import com.projects.angularcrud.service.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;

 
@RestController
public class AngularRequestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    private ApplicationEventPublisher eventPublisher;
    
    
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody User user,UriComponentsBuilder ucBuilder)
    {
        
      
     HttpHeaders header=new HttpHeaders();
     Map<String, String> map = new HashMap<>();
   
      try
      {
        if ("".equals(userService.DisplayNameByUserName(user.getUsername(),user.getPassword())))
        {
            header.set("statusText", "Klijent ne postoji u bazi!!!");
            map.put("message","GRESKA U LOGOVANJU !!! - STATUS 401 - KORISNIK SA OVIM IMENOM NE POSTOJI U BAZI !!!");
            return new ResponseEntity<>(map,header,HttpStatus.UNAUTHORIZED);
        }
      }
      catch(Exception ex)
      {
          header.set("statusText", "Klijent ne postoji u bazi!!!");
          map.put("message","GRESKA U LOGOVANJU !!! - STATUS 401 - KORISNIK SA OVIM IMENOM NE POSTOJI U BAZI !!!");
          return new ResponseEntity<>(map,header,HttpStatus.UNAUTHORIZED);
      }
      
        map.put("username", user.getUsername());
        map.put("token","fake-jwt-token");
      return new ResponseEntity<>(map ,HttpStatus.OK);
    }
    
    
     
    //-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(HttpServletRequest request) {
       
        /*if ("neki token ovde".equals(request.getHeader("Authorization")))
        {}*/
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") int id, HttpServletRequest req) {
        System.out.println("Fetching User with id " + id);
        /*if ("neki token ovde".equals(request.getHeader("Authorization")))
        {}*/
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/users", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());
 
        /*if ("neki token ovde".equals(request.getHeader("Authorization")))
        {}*/
        
        /*if(!"".equals(userService.DisplayNameByUserName(user.getUsername(),user.getPassword()).trim())) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }*/
 
        userService.executeProcUser("insert", user.getIdUser(), user.getFirstname(),user.getSecondname(),
                user.getUsername(),user.getPassword(),user.getIdAuth(),user.getStatus(),user.getLogged());
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(user.getIdUser()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
 
        /*currentUser.setUsername(user.getUsername());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setSecondname(user.getSecondname());
        currentUser.setIdAuth(user.getIdAuth());*/
         
        userService.executeProcUser("update", user.getIdUser(), user.getFirstname(),user.getSecondname(),
                user.getUsername(),user.getPassword(),user.getIdAuth(),user.getStatus(),user.getLogged());
        
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
 
        userService.executeProcUser("delete", user.getIdUser(), user.getFirstname(),user.getSecondname(),
                user.getUsername(),user.getPassword(),user.getIdAuth(),user.getStatus(),user.getLogged());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
     
    /*@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
 
}