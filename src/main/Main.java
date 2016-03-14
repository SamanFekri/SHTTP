/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.MainFrame;
import internet.HTTPConnection;
import internet.MyURL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SKings
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // start GUI
         MainFrame mainFrame = new MainFrame();
         mainFrame.setVisible(true);
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();

//        HTTPConnection httpConnection = new HTTPConnection();
        
//        System.out.println(MyURL.attachHttpWww(input));

 //       httpConnection.excute(input,true);
    }

}
