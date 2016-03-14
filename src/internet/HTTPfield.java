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
public class HTTPfield {
    private ArrayList<String> fields;
    private String key;

    public HTTPfield(String key) {
        this.key = key;
        fields = new ArrayList<>();
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public String getKey() {
        return key;
    }
    
}
