package com.wyre;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) {
        //get the json reponse from the geoip server
        try {
            System.out.print("Retreiving json to parse.....");
            //use a url connection to retreive the json
            URL url = new URL("https://freegeoip.net/json/");
            URLConnection connection = url.openConnection();
            //read it with a buffered reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonToParse = "";
            String line;
            //while the stream is not null retreive the next line of the json and add it to the jsonToParse object
            while ((line = reader.readLine()) != null) {
                jsonToParse += line;
            }
            System.out.println("Complete!!!!");
            System.out.print("Parsing json....");
            //use google gson to parse the json into a usable java object
            GeoIp geoIp = new Gson().fromJson(jsonToParse, GeoIp.class);
            System.out.println("Complete!!!");
            System.out.print("Sending results to the server....");
            //create a url to contact the location tracking server
            URL url2 = new URL("https://freegeoip.net/json/");
            url.openConnection();
            System.out.println("Complete!!!");
            System.out.print("Sleeping for 10 seconds....");
            Thread.sleep(10000);
            System.out.println("Complete!!!");
        } catch (Exception ex) {
            System.out.println("There was a error: " + ex.getMessage());
        }
    }
}
