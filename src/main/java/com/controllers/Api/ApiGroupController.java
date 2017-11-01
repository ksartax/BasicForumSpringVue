package com.controllers.Api;

import com.models.Group;
import com.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/api/group")
public class ApiGroupController
{
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private GroupsService groupsService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void add() {
        Group group = new Group();
        group.setDescription("sss");
        group.setMemberCount(0);
        group.setName("aa");
        group.setPathImg("a.jpg");

        groupsService.add(group);

        this.template.convertAndSend("/group", group);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Group> index()
    {
        return this.groupsService.getAll();
    }
}
