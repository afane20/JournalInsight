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

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public String getStartVerse() {
        return startVerse;
    }

    public void setStartVerse(String startVerse) {
        this.startVerse = startVerse;
    }

    public String getEndVerse() {
        return endVerse;
    }

    public void setEndVerse(String endVerse) {
        this.endVerse = endVerse;
    }
    Entry entry = new Entry();
    // getters
    public String getBook(){return book;}
    public String getChapter(){return chapter;}
    public String getVerse(){return verse;}
    
    //Setters 
    public void setBook(String b){book = b;}
    public void setChapter(String c){chapter = c;}
    public void setVerse(String v){verse = v;}
    
    // getting the scripture, display the scriptures found,
    // gather the scriptures.

    //Constructors
//    
      public Scripture(){
          
      }
}
//    public Scripture(String book, int chapter, int verse){}
//    public Scripture(String book, int chapter){}
   // public void ScripturesFound(String book, int chapter, int verse){}
    
    
    // This function will go through the entry and find all the scriptures
    //public String findScripture(string journal){return reference; }
//    public void parse(){
//     
//      reading(entry.getContent());
//      
//   }
//         
//      public static void reading(String fileName){
//      // fileName =  args[0];
//       String temp1 = "";
//       try (BufferedReader reader = new BufferedReader (new FileReader(fileName))){
//            
//            while (reader.ready()){
//               temp1 += reader.readLine();
//            }
//            
//         } catch (IOException e){
//         System.out.println ("Error opening file: " + fileName);
//         System.exit(0);
//         grabScripture(temp1);
//      }
//      }
//      public static void grabScripture(String temp1){
//         String chapter = "chapter ";
//         Pattern pattern = Pattern.compile(chapter);
//         Matcher matcher = pattern.matcher(temp1);
//         
//         String story = matcher.replaceAll("");
//      
//      
//         Pattern other =  Pattern.compile ("\\w+\\s\\d[0-9\\:\\-]*");
//         Matcher book_num = other.matcher(story);
//         
//         while (book_num.find()){
//            System.out.println(book_num.group());
//            
//         }
//      }
//   
//        
//    
//
//    
//}
