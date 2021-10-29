package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sensor {
    public static String liveLocation;
    public static String destination;
    public  static  String request;

    public static void main(String[] args) throws IOException {
        ServerSocket sensor = new ServerSocket(9999);
        System.out.println("[SENSOR] waiting for connections...");
        Socket server = sensor.accept();
        System.out.println("[SENSOR] connection is established");

        //----Construct input and output streams  with the server
        BufferedReader inputToSensor = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter   outputToServer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()),true);

        //----get the locations from the server

        while (true){
            request=inputToSensor.readLine();
            if(request.equals("quite")) break;
            liveLocation=inputToSensor.readLine();
            destination=inputToSensor.readLine();
            outputToServer.println(getReadings(liveLocation,destination));

        }


    }
    public static String getReadings (String liveLocation , String destination){
        //Dummy logic to get readings
        String reading="10";
        return reading;
    }
}
