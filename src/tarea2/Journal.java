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
 * @author salvador_afane, Ahslie Horst 
 */
public class Journal {
    
    // preparing variables 
    private List<String> list;
    private List<Entry> entryList;
    private List<String> bookList;
    private List<String> topicList;
    Entry entry;
    
    /**************************************************************************
    * This function will call the other ones in order to generate a well 
    * organized list for the scriptures, books, topics and entries.
    ***************************************************************************/
    public static void main(String[] args) throws SAXException, IOException {
    Journal journal = new Journal();
    File file = null;
    new ReadFile2().read(file, journal);
    journal.displayList();
    new SavingXML().run(journal);
    
    journal.displayAll();
    }
         
    /**************************************************************************
    * Constructor: this function initialize the arrayList
    ***************************************************************************/
    public Journal(){
        entryList = new ArrayList<>();
        bookList = new ArrayList<>();
        topicList = new ArrayList<>();
    }
    
  
    /**************************************************************************
    * This function provides a way to add an entry unto the Entry List
    ***************************************************************************/
    public void addEntry(Entry entr){
        entryList.add(entr);
        
    }   
    
   
    public Entry getEntry() {return entry;}
    
    
    /***************************************************************************
    * ReadBookList: This function reads the book from the source folder and it
    * parse through it 
    ***************************************************************************/
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
    
    /***************************************************************************
    * readTopicList: This function read the terms that are being passes.
    * This information is later use to compare the term with a map 
    ***************************************************************************/
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
   
    
    /***************************************************************************
    * searchEntryBookList: This function is finding the books of the entry. it 
    * compare if the name of the book, meaning one book from the standards works
    * (Scriptures) if found in the string.
    ***************************************************************************/
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
    
    /***************************************************************************
    * This function search for a list of topics that we are interested in 
    * finding. It uses the data that was grabbed from the file called terms and 
    * compare the terms with the given entry 
    ***************************************************************************/
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
    /***************************************************************************
    * This function compares 2 strings, it compares if the topics are equal or 
    * not 
    ***************************************************************************/
      public boolean compareTopics(String topic1, String topic2){
        boolean isEqual = false;
       
        if (topic1.equals(topic2)){
            isEqual = true;
        }
        
        return isEqual;
    }
    
    /***************************************************************************
    * The function compares if the books are equal or not.
    ***************************************************************************/
     public boolean compareBooks(String book1, String book2){
        boolean isEqual = false;
       
        if (book1.equals(book2)){
            isEqual = true;
        }      
        return isEqual;
    }
    
    /***************************************************************************
    * This function display the journal with references to scriptures and topics 
    ***************************************************************************/
     public void displayList(){
        // This was left like this ON PURPOSE!!!
//        System.out.println();
//        System.out.println("Journal: ");
//        System.out.println("Scripture References:");
//        searchEntryBookList(bookList);
//        System.out.println();
//        System.out.println("Topic References:");
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