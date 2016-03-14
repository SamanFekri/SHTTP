/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet;


/**
 *
 * @author SKings
 */
public class MyURL {

    final static String http = "http://", www = "www.";

    public static String attachHttpWww(String badFormURL) {
        String url = "";
        // add http:// in first of url if it doesnt have it
        try {
            if (!(http.equalsIgnoreCase(badFormURL.substring(0, 7)))) {
                url = http;
            } else {
                try {
                    badFormURL = badFormURL.substring(7);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            url = http;
            try {
                badFormURL = badFormURL.substring(7);
            } catch (Exception exp) {
            }
        }
        if (url.equals("")) {
            url = http;
        }
        // add www. in first of url if it doesnt have it
        try {
            if (!(www.equalsIgnoreCase(badFormURL.substring(0, 4)))) {
                url += www;
            }
        }catch (Exception e) {
            url += www;
        }
        url += badFormURL;
        return url;

    }
    
}
