package com.controllers;

import com.models.Group;
import com.models.GroupMember;
import com.service.GroupsService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class te {
    @Autowired
    private UsersService usersService;

    @Autowired
    private GroupsService groupsService;


}
