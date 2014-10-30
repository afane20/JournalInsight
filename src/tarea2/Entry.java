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
   
    //private variables...
    private String name;
    private String content;
    private String date;
    private String journal;
    private String fileName;
    private String topics;
    private String parrafo;
    private Scripture scripture;
    private List<Scripture> scriptureList;
    private List<String> topicList;
    
    //getters
    public List<Scripture> getScriptureList() {return scriptureList;}
    public List<String> getTopicList() {return topicList;}
    public String getName(){return name;}
    public String getJournal(){return journal;}
    public String getTopics(){return topics;}
    public String getParrafo(){return parrafo;}
    public String getDate() {return date;}
    public Scripture getScripture() {return scripture;}
    public String getContent() {return content;}
    public String getFileName(){return fileName;}

    //setters
    public void setTopicList(List _topics) {this.topicList = _topics;}
    public void setScriptureList(List _scripture) {this.scriptureList = _scripture;}
    public void setName(String name){this.name = name;}
    public void setJournal(String j){journal = j;}
    public void setTopics(String t){topics = t;}
    public void setParrafo(String p){parrafo = p;}
    public void setDate(String date) {this.date = date;}
    public void setScripture(Scripture scripture) {this.scripture = scripture;}
    public void setContent(String content) {this.content = content;}
    public void setFileName(String file){fileName = file;}

    // add a list of topics as a string 
    public void addTopic(String t){
        topicList.add(t);
    }
    
    // add a Scripture Object 
    public void addScripture(Scripture escri){
        scriptureList.add(escri);
    }
    
    //constructor
    public Entry(){
       topicList = new ArrayList<>();
       scriptureList = new ArrayList<>();
    }
    
    // Copy constructor 
    public Entry(String journal){}
   
    // It finds all the entries with the same date 
    public String findDate(){return fileName;}
    
    public Scripture findS(String journal){return scripture;}
    public String findTopic(String journal){return topics; }
   
    // Display the scripture reference 
    public void displayScriptureList(List<Scripture> scripture1){
        for (Scripture scripture: scripture1){
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

    public Object findTopic() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
