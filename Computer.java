package com.company;

import java.io.*;
import java.net.Socket;

public class Computer {
    public static void main(String[] args) throws IOException {
        //----Things happen automatically when we run the application ( Connection phase)
        System.out.println("Application started");
        Socket computer = new Socket("localHost",7171);
        System.out.println("How can i help you?");

        //----Construct input stream between the user (keyboard) and the computer socket
        BufferedReader userKeyboard = new BufferedReader(new InputStreamReader(System.in));

        //----Construct input and output streams between the computer socket and the server socket
        BufferedReader inputToComputer = new BufferedReader(new InputStreamReader(computer.getInputStream()));
        PrintWriter  outputToServer = new PrintWriter(new OutputStreamWriter(computer.getOutputStream()),true);

        //--- The logic of getting the user requests
        String location , destination ,userRequest;
        while (true){

            userRequest = userKeyboard.readLine();
            outputToServer.println(userRequest);
            if (userRequest.equals("quite")) break;

            outputToServer.println(userRequest);
            if(userRequest.equals("Best Route")) {
                System.out.println("Send me your live location");
                 location = userKeyboard.readLine();
                 outputToServer.println(location);
                System.out.println("Send me your destination location");
                destination =userKeyboard.readLine();
                outputToServer.println(destination);
                System.out.println(inputToComputer.readLine());

            }
            System.out.println("Do you want to quite? [Y/N]");
            if(userKeyboard.readLine().equals("Y")) break;
            System.out.println("Enter another request");

            }
    }
}
