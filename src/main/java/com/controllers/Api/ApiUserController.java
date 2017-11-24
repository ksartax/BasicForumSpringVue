package com.controllers.Api;

import com.dvo.UserView;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequestMapping(path = "/api/user")
public class ApiUserController {
    private UsersService usersService;

    @Autowired
    public ApiUserController(
            UsersService usersService
    ) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<UserView> index(@RequestParam(value = "limit", required = false, defaultValue = "999999") int limit) {
        return this.usersService.getAll(limit);
    }

    @RequestMapping(path = "/upload-img", method = RequestMethod.POST)
    public ResponseEntity uploadImg(@RequestParam("file") MultipartFile file) {
        System.out.println("File Description:");
        try {
            usersService.importImage(file);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
