/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvador_afane
 * Collaborators: Ashlie Katherine Horst, Paul O'Neil, Alex Mitchell
 */

// This class is for the purpose of browsing, The user will be able to search 
// for an specific entry, scripture, or date. and This class should be able to 
// return all the entries with the date, scripture, or topic desired  
public class Entry {
   

   
    //private Date date;
    private String name;
    private String content;
    private String date;
    private String journal;
    private String fileName;
    private String topics;
    private String parrafo;
    private Scripture scripture;
   
    private List<Scripture> _scripture;
    private List<String> _topic;

    public List<String> getTopicList() {return _topic;}

    public void setTopicList(List _topics) {this._topic = _topics;}
    public List<Scripture> getScriptureList() {return _scripture;}
    public void setScriptureList(List _scripture) {this._scripture = _scripture;}
    
    // add a list of topics as a string 
    public void addTopic(String t){
        _topic.add(t);
    }
    
    // add a Scripture Object 
    public void addScripture(Scripture escri){
        _scripture.add(escri);
    }
    // Setters
    public void setName(String name){this.name = name;}
    public void setJournal(String j){journal = j;}
    public void setTopics(String t){topics = t;}
    public void setParrafo(String p){parrafo = p;}
    public void setDate(String date) {this.date = date;}
    public void setScripture(Scripture scripture) {this.scripture = scripture;}
    public void setContent(String content) {this.content = content;}

  //  public void setDate(String d){date = d;}
    
    //Getters
    public String getName(){return name;}
    public String getJournal(){return journal;}
    public String getTopics(){return topics;}
    public String getParrafo(){return parrafo;}
    public String getDate() {return date;}
    public Scripture getScripture() {return scripture;}
    public String getContent() {return content;}

    //constructor
    public Entry(){
       _topic = new ArrayList<>();
       _scripture = new ArrayList<>();
    }
    public Entry(String journal){}
   
    // It finds all the entries with the same date 
    public String findDate(){return fileName;}
    public void setFileName(String file){fileName = file;}
    public String getFileName(){return fileName;}
    
    public Scripture findS(String journal){return scripture;}
    public String findTopic(String journal){return topics; }
   
    // Display the scripture reference 
    public void displayScriptureList(List<Scripture> scri){
        for (Scripture scripture: scri){
            System.out.println("This should be an scripture " + scripture.getBook() + " " +scripture.getChapter() +
                  " " +  scripture.getStartVerse() + " " + scripture.getEndVerse());
           
        }
        
    }
    
    // Display the content 
    public void displayTopicList(List<String> top){
        for (String topic:top){
            System.out.println("Topic: " + topic);
        }
    }
}
