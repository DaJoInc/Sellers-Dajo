package com.dajo.controller;

import com.dajo.model.User;
import com.dajo.model.UserProfile;
import com.dajo.service.UserProfileService;
import com.dajo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by JoDa on 28/05/2017.
 */
@RestController
public class ProfileRestController {

    @Autowired
    UserProfileService profileService;


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/profile/", method = RequestMethod.GET)
    public ResponseEntity<List<UserProfile>> listAllUsers() {
        List<UserProfile> profiles = profileService.findAll();
        if(profiles.isEmpty()){
            return new ResponseEntity<List<UserProfile>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserProfile>>(profiles, HttpStatus.OK);
    }




}
