package com.hackathon.nku.services.impl;

import com.hackathon.nku.services.api.IHackathonApp;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Service
public class HackathonAppImpl implements IHackathonApp {

    @Override
    public String getHackathonData() throws IOException{
        String apiURL = "https://s3rdf9bxgg.execute-api.us-east-2.amazonaws.com/deploy/all";
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "UTF-8")
        );
        String jsonString = rd.lines().collect(Collectors.joining());
        return jsonString;
    }

    @Override
    public String getBioData(String id) {
        FileReader file = null;
        String bio = "";
        try {

            File pathFile = new File("src/main/java/com/hackathon/nku/services/bios/" + id + ".txt");
            file = new FileReader(pathFile.getCanonicalPath());
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            while(line != null) {
                bio += line + "\n";
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bio;
    }

    @Override
    public String getImage(String id) {
        //This is where the image would be read in and stringified to send back image data.
        return "this is image data" + id;
    }

    @Override
    public String postImage(String id, InputStream input, String type) throws FileNotFoundException, IOException {
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File("src/main/java/com/hackathon/nku/services/images/" + id + "." + type));
        while((read = input.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
        return "Successful image post";
    }

    @Override
    public String postBio(String id, String bio) {
        File pathFile = new File("src/main/java/com/hackathon/nku/services/bios/" + id + ".txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathFile.getCanonicalPath()));
            writer.write(bio);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Successful bio post";
    }
}