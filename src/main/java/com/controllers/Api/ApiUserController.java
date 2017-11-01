package com.controllers.Api;

import com.models.User;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController()
@RequestMapping(path = "/api/user")
public class ApiUserController
{
    @Autowired
    UsersService usersService;

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add() {
        User user = new User();

        this.template.convertAndSend("/user", user);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<User> index() {
        return this.usersService.getAll();
    }
}
