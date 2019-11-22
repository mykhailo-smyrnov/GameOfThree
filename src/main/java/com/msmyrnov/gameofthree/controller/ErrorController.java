package com.msmyrnov.gameofthree.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //do something like logging
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
