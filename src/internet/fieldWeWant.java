/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internet;

import java.util.ArrayList;

/**
 *
 * @author SKings
 */
public class fieldWeWant {

    private static ArrayList<String> methods = new ArrayList<>();

    static {
        methods.add("HEAD");
        methods.add("OPTIONS");
    }

    private static ArrayList<String> fieldsWeWant = new ArrayList<>();

    static {
        fieldsWeWant.add("Server");
        fieldsWeWant.add("Allow");
        fieldsWeWant.add("Set-Cookie");
        fieldsWeWant.add("Expire");
        fieldsWeWant.add("Last-Modified");
        fieldsWeWant.add("ETag");
        fieldsWeWant.add("Cache-Control");
        fieldsWeWant.add("WWW-Authenticate");
        fieldsWeWant.add("Connection");
        fieldsWeWant.add("Keep-Alive");

    }
    private static ArrayList<String> headerWeHave = new ArrayList<>();

    public static String analyze(ArrayList<HTTPfield> fields) {
        headerWeHave = new ArrayList<>();
        String retval = "\n";
        HTTPfield hf;
        for (int i = 0; i < fields.size(); i++) {
            hf = fields.get(i);
            if (methods.contains(hf.getKey())) {
                retval += hf.getKey() + " -> ";
                for (int j = 0; j < hf.getFields().size(); j++) {
                    retval += hf.getFields().get(j) + "\n";
                }
            }
            headerWeHave.add(hf.getKey());
        }
        retval += "\n";

        for (int i = 0; i < fieldsWeWant.size() - 1; i++) {
            retval += fieldsWeWant.get(i) + ": ";
            if (headerWeHave.contains(fieldsWeWant.get(i))) {
                hf = fields.get(headerWeHave.indexOf(fieldsWeWant.get(i)));
                for (int j = 0; j < hf.getFields().size(); j++) {
                    if (j == 0) {
                        for (int k = 20; k > fieldsWeWant.get(i).length() + 2; k--) {
                            retval += " ";
                        }
                    } else {
                        retval += "                    ";
                    }
                    if ("Set-Cookie".equals(fieldsWeWant.get(i))) {
                        retval+="{\n ";
                        String[] parts = hf.getFields().get(j).split(";");
                        for (int k = 0; k < parts.length; k++) {
                            retval += "                    ";
                            retval += parts[k]+"\n";
                        }
                         retval += "                    ";
                        retval+="}\n";
                    } else {
                        retval += hf.getFields().get(j) + "\n";
                    }
                }
            } else {
                for (int k = 20; k > fieldsWeWant.get(i).length() + 2; k--) {
                    retval += " ";
                }
                retval += "oops Response don`t have \"" + fieldsWeWant.get(i) + "\" header !!!!\n";
            }
        }

        if (headerWeHave.contains(fieldsWeWant.get(fieldsWeWant.size() - 1))) {
            retval += fieldsWeWant.get(fieldsWeWant.size() - 1) + ": ";
            hf = fields.get(headerWeHave.indexOf(fieldsWeWant.get(fieldsWeWant.size() - 1)));
            for (int j = 0; j < hf.getFields().size(); j++) {
                retval += hf.getFields().get(j) + "\n";
            }
        }
        return retval.substring(0, retval.length() - 1);
    }

}
