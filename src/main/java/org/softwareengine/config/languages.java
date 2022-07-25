package org.softwareengine.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class languages {

    public static Locale lang ;

    public ResourceBundle resourceBundle ;

    public languages() {
         resourceBundle = ResourceBundle.getBundle("locales/lang");
    }


    public String getWord(String key) {
        return resourceBundle.getString(key) ;
    }


}
