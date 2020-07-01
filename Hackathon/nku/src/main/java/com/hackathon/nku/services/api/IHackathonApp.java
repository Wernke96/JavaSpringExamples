package com.hackathon.nku.services.api;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface IHackathonApp {
    String getHackathonData() throws IOException;

    String getBioData(String id);

    String getImage(String id);

    String postImage(String id, InputStream input, String type) throws FileNotFoundException, IOException;

    String postBio(String id, String bio);
}