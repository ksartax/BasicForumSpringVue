package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/error")
@Controller
public class ErrorController {
    @RequestMapping("")
    public String error(HttpServletRequest httpRequest) {

        int httpErrorCode = getErrorCode(httpRequest);
        String errorMsg = "";

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "500. Internal Server Error";
                break;
            }
        }

        System.err.println(errorMsg);

        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
