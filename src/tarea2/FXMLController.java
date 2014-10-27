/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author salvador_afane
 */
public class FXMLController implements Initializable {
    // Creating objects..
    private Stage stage;
    // The FXML file needs all of the "@FXML" tags in order to function otherwise the program 
    // does not know what are you talking about, or it does not know to what you are making reference to
    // There MUST be an @FXML tag previous to each declaration of a private variable
    @FXML
    private Label greetingLabel;
    @FXML
    private MenuItem quit;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem open2;
    @FXML
    private MenuItem newFile;
    @FXML
    private MenuItem saveFile;
    @FXML
    private MenuItem saveXmlFile;
    @FXML
    private TextField field;
    @FXML
    private TextArea textArea;
    @FXML
    private Button addEntry;
    @FXML
    private Label eMessage;
    @FXML
    private TextField searchBar;
    @FXML
    private Button sbutton;
    
       
    Journal journal = new Journal();
   
    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        Date today = new Date(); // make a date object
//        String date = String.format("%tA, %<tB %<td, %<tY", today);
//        field.setText(date);
        
         Journal journal = new Journal();
         journal.displayList();
         new SavingXML().run(journal);
         journal = null;
        
    }
    
    // pointer to the primary stage, allows the controller to have access to the 
    // main stage
     public void init(Stage primaryStage) {
         this.stage = primaryStage;
         //Set the date 
     }
    
    // TESTER!!!!!! Button
    public void addEntry (ActionEvent event){
        if (field.getText().matches("") && textArea.getText().matches("")){
            eMessage.setText("Error, Please enter a date an your Entry");
        } else {
        Entry entry = new Entry();
        entry.setDate(field.getText());
        entry.setContent(textArea.getText());
        journal.addEntry(entry);
        
        field.setText("");
        textArea.setText("");
        }
    }
    
    // This will exit the program when the user click on "Quit"
    public void doExit(){
        Platform.exit();
    }
    
    // OPENS an XML file 
    public void openfile(ActionEvent event) throws SAXException, IOException{
        Journal journal = new Journal();
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", ".txt"),
                new FileChooser.ExtensionFilter("XML Files", ".xml")

        );
        
        if (file != null){
            // to read an xml file and parse its content and place all the content 
            // in its repective classes 
            new ReadFile2().read(file,journal);
            journal.display();
            
           // new SavingXML().run(journal);
            String text = "";
            
            for (Entry entry : journal.getEntryList()){
                  text += "-----\n" + "Date: " + entry.getDate() + "\n\n";
                  text += "Content: " + entry.getContent() + "\n\n";
                  
            }
          
            textArea.setText(text);
        }else 
          System.out.println("Error reading file: " + file);
        
    }
    // Open a TEXT FILE
    public void openFile2 (ActionEvent event){
        Journal journal = new Journal();
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", ".txt"),
                new FileChooser.ExtensionFilter("XML Files", ".xml")

        );
        if (file != null){
            new SavingXML().run2(journal, file.getPath(), file.getName());
            String text = "";
            for (Entry entry : journal.getEntryList()){
                  text += "-----\n" + "Date: " + entry.getDate() + "\n\n";
                  text += "Content: " + entry.getContent() + "\n\n";
                  
            }
          
            textArea.setText(text);
        }else 
          System.out.println("Error reading file: " + file);
        
    }
    
    public void saveXMLFile(ActionEvent event){
        SavingXML saver = new SavingXML(); // making a class object
        String fileName = "text.txt";
        
        String text = textArea.getText();
        saver.writeSimpleText(fileName, text);
       
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null){
        String convert = "text.txt";
        saver.run2(journal, convert, file.getPath());
  
        Document xml = saver.buildXmlDocument(journal.getEntryList());
        
        
       // saver.searchTopic(, journal);    
       // saver.run2(journal, file.getPath());

        saver.saveDocumentXML(xml, file.getPath());
        field.setText(text);
        System.out.println("something must be saved " + text);
    }
        else{
        //display in master!!!
        }
    }
   
    public void saveTextFile(ActionEvent event){
        Journal journal = new Journal();
        
        Entry entry = new Entry();
        entry.setDate(field.getText());
        entry.setContent(textArea.getText());
        
        journal.addEntry(entry);
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null){
            SavingXML saver = new SavingXML();
            saver.writeTextFile(file.getPath(), journal); 
        }
        
        
    }
    // clear the entry fieds, prepare the scene for a new entry.
    public void newFile(){
       textArea.setText("");
       field.setText("");
       
       Date today = new Date(); // make a date object
       String date = String.format("%tA, %<tB %<td, %<tY", today);
       field.setText(date);
   }
    
    public void searchBar(){
        String search = searchBar.getText();
        for (Entry entry : journal.getEntryList()){
           
        }
    }
    
}