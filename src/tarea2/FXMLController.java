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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private MenuItem reset;
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
    @FXML
    private ListView list;
    @FXML
    private ListView list2;
    @FXML
    private Label entry1;
    @FXML
    private Label entry2;
    @FXML
    private Label entry3;
    @FXML
    private Button tester;
    
    Journal journal = new Journal();
     ObservableList items = FXCollections.observableArrayList();              

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
    
    public void init(Stage primaryStage) {
         this.stage = primaryStage;
         //Set the date 
     }
    
   public void tester(){
        Treads tr = new Treads();
        int cEntry = 0;
        int cTopic = 0;
        int cScripture = 0;
        tr.myLabel = entry1;
        tr.myLabel2 = entry2;
        tr.myLabel3 = entry3;
        tr.countEntries = cEntry;
        
        
        for (Entry entry : journal.getEntryList()){
            
                cEntry++;
                tr.countEntries = cEntry;

               for (Scripture s : entry.getScriptureList()){
                   cScripture++;

                    tr.countScriptures = cScripture;
               }
               for (String topic : entry.getTopicList()){
                   cTopic++;
                   tr.countTopic = cTopic;
               }
        }
        Thread thread = new Thread(tr);
        thread.start();
   }
    
    // TESTER!!!!!! Button
    public void addEntry (ActionEvent event){
        // the treading goes in the body of the function 
        if (field.getText().matches("") && textArea.getText().matches("")){
            eMessage.setText("Error, Please enter a date an your Entry");
        } else {
        Entry entry = new Entry();
        entry.setDate(field.getText());
        entry.setContent(textArea.getText());
//        journal.addEntry(entry);
        
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
        //Journal journal = new Journal();
        
        // This opends the dialog to open a document 
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setTitle("Open Text File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", ".txt"),
                new FileChooser.ExtensionFilter("XML Files", ".xml")

        );
        
        // If the file is selected 
        if (file != null){
            // to read an xml file and parse its content and place all the content 
            // in its repective classes 
            new ReadFile2().read(file,journal);
            journal.display();
            
           // new SavingXML().run(journal);
            String text = "";
            
            for (Entry entry : journal.getEntryList()){
                items.add(entry);

                  text += "-----\n" + "Date: " + entry.getDate() + "\n\n";
                  text += "Content: " + entry.getContent() + "\n\n";
                  
            }
            list.setItems(items);
            textArea.setText(text);
            
              list.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>(){
            @Override
            public ListCell<Entry> call(ListView<Entry> listEntry) {
                ListCell<Entry> cell = new ListCell<Entry>() {
                    @Override
                    protected void updateItem(Entry ent, boolean e) {
                        if (ent != null) {
                            setText(ent.getDate());
                        }
                    }
                }; 
                return cell;
            }
        });
              
        }else 
          System.out.println("Error reading file: " + file);
        
    }
    // Open a TEXT FILE
    public void openFile2 (ActionEvent event){
        //Journal journal = new Journal();
        
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
                  items.add(entry);

                  text += "-----\n" + "Date: " + entry.getDate() + "\n\n";
                  text += "Content: " + entry.getContent() + "\n\n";
                  
            }
            list.setItems(items);
          
                list.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>(){
            @Override
            public ListCell<Entry> call(ListView<Entry> listEntry) {
                ListCell<Entry> cell = new ListCell<Entry>() {
                    @Override
                    protected void updateItem(Entry ent, boolean e) {
                        if (ent != null) {
                            setText(ent.getDate());
                        }
                    }
                }; 
                return cell;
            }
        });
            
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
    
    public void searchBar(ActionEvent event){
       String searchBook = searchBar.getText();                    
               // try {         
                    //journal.searchEntryBookList(searchBook);
                    journal.searchEntryTopicMap(searchBook);
               // } catch (IOException ex) {
               //     Logger.getLogger(JavaFx.class.getName()).log(Level.SEVERE, null, ex);
               // }
            searchBar.clear();
            searchBar.requestFocus();
            }
       
 
    public void reset(){
        field.setText("");
        textArea.setText("");
    }
//    public void master(){
//        String date;
//        int count = 0;
//        for (Entry entry : journal.getEntryList()){
//             date = entry.getDate();
//             count++;
//             switch (count){
//                 case 
//             }   
//             entry1.setText(date);
//        }
//    }
}