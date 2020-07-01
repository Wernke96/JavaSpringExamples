package com.hackathon.nku.controllers;

import com.hackathon.nku.services.api.IHackathonApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@RestController
public class ServicesImpl {

    @Autowired
    IHackathonApp iHackathonApp;

    @RequestMapping("/hackathon")
    public String getData(@Context HttpServletRequest request) throws IOException {
        return iHackathonApp.getHackathonData();
    }

    @RequestMapping(value = "/hackathon/getbio")
    public String getBio(@Context HttpServletRequest request) {
        return iHackathonApp.getBioData(request.getHeader("id"));
    }

    @RequestMapping(value = "/hackathon/postbio")
    public String postBio(@Context HttpServletRequest request) {
        return iHackathonApp.postBio(request.getHeader("id"), request.getParameter("bio"));
    }

    @RequestMapping(value = "/hackathon/getimage")
    public String getImage(@Context HttpServletRequest request) {
        return iHackathonApp.getImage(request.getHeader("id"));
    }

    @RequestMapping(value = "/hackathon/postimage")
    public String postImage(@Context HttpServletRequest request) {
        try {
            return iHackathonApp.postImage(request.getHeader("id"), request.getInputStream(), request.getHeader("type"));
        } catch (IOException e) {
            e.printStackTrace();
            return "New image failed to post";
        }
    }

}