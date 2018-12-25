package com.tdat.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * A class that creates graphical reports using the ChartJS library in the back-end
 */
public class ChartJSRequest {

    private static String API_URL = "http://localhost:3000/";

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MMM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String create(String json) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Content-type", "application/json");
            StringEntity params = new StringEntity(json);
            post.setEntity(params);
        
            HttpResponse response = httpClient.execute(post);
            InputStream in = response.getEntity().getContent();

            Path directoryPath = Paths.get(System.getProperty("user.dir"), "reports");
            Path filePath = Paths.get(directoryPath.toString(), "report-" + getDate() + ".html");
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }

            FileOutputStream fos = new FileOutputStream(new File(filePath.toString()));
            byte[] buffer = new byte[4096];
            int length; 
            while((length = in.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.close();

            return filePath.toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
