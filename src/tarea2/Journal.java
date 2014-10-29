/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author salvador_afane
 */
public class Journal {
    
    // preparing variables 
    private List<String> list;
    private List<Entry> entryList;
    private List<String> bookList;
    private List<String> topicList;
    Entry entry;
    
    
    public static void main(String[] args) throws SAXException, IOException {
    Journal journal = new Journal();
    File file = null;
    new ReadFile2().read(file, journal);
    journal.displayList();
    new SavingXML().run(journal);
    
    journal.displayAll();
    }
            
    // Constructor 
    public Journal(){
        entryList = new ArrayList<>();
        bookList = new ArrayList<>();
        topicList = new ArrayList<>();
    }
    
    // add an Entry object
    public void addEntry(Entry entr){
        entryList.add(entr);
        
    }   
    public Entry getEntry() {return entry;}
    
    
    
      public void readBookList(){
        String fileName = "src/tarea2/books.txt";
        String line = "";
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
                bookList.add(line);
            }
        } catch (IOException e){
            System.out.println("Error reading list from file");
        }
    }
    
    
    public void readTopicList(){
        String fileName = "src/tarea2/terms.txt";
        String line = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
                topicList.add(line);
            }
        } catch (IOException e){
            System.out.println("Error reading list from file");
        }
    }
    
    //getter
    public List<String> getBookList(){
        return bookList;
    }
    
    //getter
    public List<String> getTopicList(){
        return topicList;
    }
    
    public List<Entry> getEntryList(){
        return entryList;
    }
   
    
    
  public void searchEntryBookList(List<String>list){
        boolean isFound = false;
        
        readBookList();
        
        for (String str : list) {
        boolean first = true;
            
        for (Entry entry : entryList) {
                      
            for (int i = 0; i < entry.getScriptureList().size(); i++) {
                
                Scripture s = entry.getScriptureList().get(i);
                isFound = compareBooks(s.getBook(), str);
                
                if (isFound && first) {
                    System.out.println(s.getBook());
                    first = false;
                }
                
                if (isFound) {
                    System.out.println("\t" + entry.getDate());
                    break;
                }                  
            }      
        }      
      }
    }    
    
     public void searchEntryTopicList(List<String>list){
        
        boolean isFound = false;
        
        readTopicList();
        
        for (String str : list) {
        boolean first = true;
            
        for (Entry entry : entryList) {
                      
            for (int i = 0; i < entry.getTopicList().size(); i++) {
                
                String topic = entry.getTopicList().get(i);
                isFound = compareTopics(topic, str);
                
                if (isFound && first) {
                    System.out.println(topic);
                    first = false;
                }
                
                if (isFound) {
                    System.out.println("\t" + entry.getDate());
                    break;
                }                
            }          
        }      
      }
    }
    
      public boolean compareTopics(String topic1, String topic2){
        boolean isEqual = false;
       
        if (topic1.equals(topic2)){
            isEqual = true;
        }
        
        return isEqual;
    }
    
     public boolean compareBooks(String book1, String book2){
        boolean isEqual = false;
       
        if (book1.equals(book2)){
            isEqual = true;
        }      
        return isEqual;
    }
    
     public void displayList(){
        
        System.out.println();
        System.out.println("Journal: ");
        System.out.println("Scripture References:");
        searchEntryBookList(bookList);
        System.out.println();
        System.out.println("Topic References:");
        searchEntryTopicList(topicList);
        
    }
     
     public void display() {
        // Read out the individual entries here
        for (Entry entry : entryList) {
            // This will print out the entry contents
          System.out.println("\nEntry:");
          System.out.println("Date: " + entry.getDate());
          entry.displayScriptureList(entry.getScriptureList());
          entry.displayTopicList(entry.getTopicList());
          System.out.println("Content: " + entry.getContent());  
      }
    }
    public void displayAll(){
        for (Entry entry : entryList){
           
            entry.displayScriptureList(entry.getScriptureList());
            entry.displayTopicList(entry.getTopicList());
            System.out.println("Content: " + entry.getContent());
            
        }
    }
      
}