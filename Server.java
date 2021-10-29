package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static String liveLocation;
    public static String destination;
    public  static  String request;
    public static  String reading;


    public static void main(String[] args)  throws IOException {
        //-----Connection Phase with sensor
        Socket serverAsClient = new Socket("localHost",9999);
        System.out.println("[SERVER] connected with sensor");

        //-----Connection Phase with computer
        ServerSocket server = new ServerSocket(7171);
        System.out.println("[SERVER] Waiting for connections ...");
        Socket forComputer = server.accept();
        System.out.println("[SERVER] Connection is established");

       //-----Construct input and output streams for the computer socket
        BufferedReader inputToServer = new BufferedReader(new InputStreamReader(forComputer.getInputStream()));
        PrintWriter    outputToComputer = new PrintWriter(new OutputStreamWriter(forComputer.getOutputStream()),true);

        //---Construct input and output streams for serverAsClient socket
        BufferedReader inputFromSensor = new BufferedReader(new InputStreamReader(serverAsClient.getInputStream()));
        PrintWriter outputToSensor = new PrintWriter(new OutputStreamWriter(serverAsClient.getOutputStream()),true);

       //---- get the live location and the destination location from the computer

        while (true){
            request=inputToServer.readLine();
            outputToSensor.println(request);
            if(request.equals("quite")) break;
            liveLocation =inputToServer.readLine();
            outputToSensor.println(liveLocation);
            destination= inputToServer.readLine();
            outputToSensor.println(destination);
            reading=inputFromSensor.readLine();
          outputToComputer.println(calcBestRoute(reading));
        }

    }

    public static String calcBestRoute(String reading)
    {   //Dummy logic for calcBestRoute

        return  "take road 1 then road 2 " ;

    }
}
