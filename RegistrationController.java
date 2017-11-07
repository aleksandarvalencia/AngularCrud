/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.controller;

import com.projects.angularcrud.model.User;
import java.util.Calendar;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegistrationController 
{
    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public ResponseEntity<String> confirmRegistration(HttpServletRequest request, Model model, @RequestParam("token") String token) 
    {

        Locale locale = request.getLocale();

        /*VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) 
        {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale)
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        } 

        user.setEnabled(true); 
        service.saveRegisteredUser(user); */
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
