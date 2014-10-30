/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.util.Date;
import java.util.Calendar;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author salvador_afane, Ahslie Horst 
 */
public class JournalDisplay extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       

        try{
        // This gets the Parent Node from the FXML file that was generated
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tarea2/FXML.fxml"));
        Parent root = loader.load();    
        
        FXMLController controller = (FXMLController)loader.getController();
        controller.init(primaryStage);
        
        
        Scene scene = new Scene(root, 900, 630);  // it generates the GUI
        
        //This gets the CSS file from the project
        scene.getStylesheets().add(JournalDisplay.class.getResource("css.css").toExternalForm());
        primaryStage.setTitle("Journal Entry");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
       
        }catch (Exception ex){
        ex.printStackTrace();
        }
    }
    
 
    public static void main(String[] args) {
        launch(args);
    
    }
    
}
