/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SKings
 */
public class HTTPConnection {

    private static ArrayList<HTTPfield> httpFields = new ArrayList<>();

    public static String excute(String targetURL, boolean isPersistent) {
        httpFields = new ArrayList<>();
        String retval = "";
        retval = createConnection(targetURL, "HEAD", isPersistent);
        createConnection(targetURL, "OPTIONS", isPersistent);

        retval += fieldWeWant.analyze(httpFields);
        System.out.println(retval);
        return retval;
    }

    public static String createConnection(String targetURL, String method, boolean isPersistent) {
        String retval = "";
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method.toUpperCase());
            connection.setRequestProperty("User-Agent", "Mozilla/6.0");
            if (isPersistent) {
                connection.setRequestProperty("Connection", "keep-alive");
            } else {
                connection.setRequestProperty("Connection", "close");
            }
            connection.setUseCaches(true);
            connection.setDoOutput(true);
            if (connection.getResponseCode() > 299) {
                retval = "red";
            } else {
                retval = "green";
            }
            mineFields(connection, method.toUpperCase());

            return retval;
        } catch (UnknownHostException e) {
            retval = "red\n" + "oops Unkown Host or Timeout!!";
            System.out.println(retval);
            return retval;
        } catch (MalformedURLException e) {
            retval = "red\n" + "oops Malformed URL!!";
            System.out.println(retval);
            return retval;
        } catch (IOException e) {
            retval = "red\n" + "oops IO Exception!!";
            System.out.println(retval);
            return retval;
        } catch (Exception e) {
            retval = "red\n" + "oops Unkown Exception!!";
            System.out.println(retval);
            return retval;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static void mineFields(HttpURLConnection connection, String method) {
        Map<String, List<String>> mapHttp = connection.getHeaderFields();
        HTTPfield hf;
        for (int i = 0; i < mapHttp.size(); i++) {
            String key = connection.getHeaderFieldKey(i);
            List<String> mapFileds = mapHttp.get(key);
            if (key == null) {
                hf = new HTTPfield(method);
            } else {
                hf = new HTTPfield(key);
            }
            for (int j = 0; j < mapFileds.size(); j++) {
                if (!hf.getFields().contains(mapFileds.get(j))) {
                    hf.getFields().add(mapFileds.get(j));
                    //System.out.println(hf.getKey() + ": " + mapFileds.get(j));
                }
            }
            httpFields.add(hf);
        }
    }
}
