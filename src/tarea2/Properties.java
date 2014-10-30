/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salvador_afane
 */
public class Properties {
     String propertiesFile = "/resources/properties.properties";
    
    
     public String getTopicFile(){
	java.util.Properties prop = new java.util.Properties();
         try {
             prop.load(getClass().getResourceAsStream(propertiesFile));
         } catch (IOException ex) {
             Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        String topicFile = prop.getProperty("terms");
        return topicFile;
     }
     
     public String getScriptureFile(){
        java.util.Properties prop = new java.util.Properties();
         try {
             prop.load(getClass().getResourceAsStream(propertiesFile));
         } catch (IOException ex) {
             Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        String scriptureFile = prop.getProperty("books");
        return scriptureFile;
     } 

}

