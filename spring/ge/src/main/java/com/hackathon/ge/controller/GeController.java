package com.hackathon.ge.controller;

import com.hackathon.ge.model.Email;
import com.hackathon.ge.service.GeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeController {
    private final Logger LOG = LoggerFactory.getLogger(GeController.class);

    @Autowired
    GeService geService;

    @GetMapping("/emails")
    public String getAllEmails() {
        return geService.getAllEmails();
    }
}
