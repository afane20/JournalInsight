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
public class Treads implements Runnable{
    public Label myLabel;
    public Label myLabel2;
    public Label myLabel3;
    
    @Override
    public void run() {
        try {
            
        Thread.sleep(2000);
        
        tester();

        Thread.sleep(2000);
    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void tester(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myLabel.setText("Something in here");
            }
        });
        
    }
}
