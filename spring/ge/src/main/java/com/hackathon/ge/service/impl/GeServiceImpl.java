package com.hackathon.ge.service.impl;

import com.google.gson.Gson;
import com.hackathon.ge.model.Data;
import com.hackathon.ge.model.Email;
import com.hackathon.ge.repository.GeRepository;
import com.hackathon.ge.service.GeService;
import com.hackathon.ge.utils.Hash;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GeServiceImpl implements GeService {
    private static final Logger LOG = LoggerFactory.getLogger(GeServiceImpl.class);

    @Autowired
    private GeRepository geRepository;

    @Override
    public String getAllEmails() {
        ArrayList<String> spamWords = new ArrayList<>(Arrays.asList("Rates","Refinance","Refund","Remove","Request","Risk-free","Sales","Satisfaction","Save","Score","Serious","Spam","Success","Supplies","Take","action","Terms","Traffic","Trial","Unlimited","Urgent","Weight","While","supplies","last","Win","Winner", ".exe", ".js", ".opd",".ods", ".flac", ".tiff", ".odt"));
        List<Email> emails = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Data> entity = restTemplate.getForEntity("https://s3rdf9bxgg.execute-api.us-east-2.amazonaws.com/deploy/all", Data.class);
        Data data = entity.getBody();
        for (Email email : data.getData()) {
            boolean isValid = EmailValidator.getInstance().isValid(email.getSender());
            email.setSpam(!isValid);

            for(String spam : spamWords) {
                if(email.getBody().contains(spam)) {
                    email.setSpam(true);
                }
                if(email.getSubject().contains(spam)) {
                    email.setSpam(true);
                }

                if(email.getAttachment().contains(spam)) {
                    email.setSpam(true);
                }
            }
            String stringToBeHashed = email.getSender() + email.getBody() + email.getRecipient();
            String emailHash = Hash.md5(stringToBeHashed);
            email.setHash(emailHash);
            if (geRepository.findByHash(emailHash) == null) {
                geRepository.save(email);
            }
        }
        emails.addAll(geRepository.findAll());
        Gson gson = new Gson();
        return gson.toJson(emails);
    }
}
