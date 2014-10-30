/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author salvador_afane
 * Collaborators: Ashlie Katherine Horst, Paul O'Neil, Alex Mitchell
 */
public class Scripture {
    
    private String book;
    private String chapter;
    private String verse;
    private String startVerse;
    private String endVerse;

    // getters
    public Entry getEntry() {return entry;}
    public String getStartVerse() {return startVerse;}
    public String getEndVerse() {return endVerse;}
    Entry entry = new Entry();
    public String getBook(){return book;}
    public String getChapter(){return chapter;}
    public String getVerse(){return verse;}
    
    //Setters 
    public void setBook(String b){book = b;}
    public void setChapter(String c){chapter = c;}
    public void setVerse(String v){verse = v;}
    public void setEndVerse(String endVerse) {
        this.endVerse = endVerse;
    }
     public void setStartVerse(String startVerse) {
        this.startVerse = startVerse;
    }
      public void setEntry(Entry entry) {
        this.entry = entry;
    }
}

