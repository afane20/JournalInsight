/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author salvador_afane
 */
public class Treads implements Runnable, Updater{
    public Label myLabel;
    public Label myLabel2;
    public Label myLabel3;
    public int countEntries;
    public int countScriptures;
    public String scriptures;
    public int countTopic;
    @Override
    public void run() {
        try {
      int i = 0; 
      int g = 0;
      int t = 0;
       for ( i = 0; i < countEntries;i++){

        Thread.sleep(1000);
        
        update(i, g, t);
       // Thread.sleep(1000);
        
       }
       for (g = 0; g < countScriptures; g++){
           Thread.sleep(1000);
           update(i, g, t);
           
       }

       for (t = 0; t < countTopic;t++){
           Thread.sleep(1000);
           update(i, g, t);
       }
       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void update(int i,int countScriptures,int countTopic){
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
  
                myLabel.setText("Entries found: " + i);
                myLabel2.setText("Scriptures found: " + countScriptures);
                myLabel3.setText("Topics found: " + countTopic);
            }
        });
        
    }

    @Override
    public void update(int count) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
