/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.util.HashSet;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Date;
import tarea2.Entry;
import java.io.File;
import tarea2.Journal;
import tarea2.Scripture;

/**
 *
 * @author salvador_afane
 */
public class Tester {
    
    public Tester() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void scriptureTest(){
        Scripture s = new Scripture();
        s.setBook("Moses");
        s.setChapter("1");
        Assert.assertEquals (s.getBook(), "Moses");
        Assert.assertEquals(s.getChapter(), 1);
        
     }
    
    // Testing if the scripture class is able to find the book 
    // and the scripture reference, and grab the string 
    @Test 
    public void test2(){
        Entry finder = new Entry();
        Scripture s = finder.findS("I went to bed 6, then I read 1 Nephi 1:35");
        
        Assert.assertEquals(s.getBook(), "Nephi");
        Assert.assertEquals(s.toString(), "Nephi 1:35");
    }
    
    // Testing the constructor..
    @Test
    public void test3(){
       

    }
    
    // Date should be able to grab numbers and names of months
    @Test
    public void test4(){
        

    }
    
    // Testing one form to input a date
    @Test
    public void test5(){
       
    }
    
    // Testing setters and getters 
    @Test
    public void test6(){
       
    }
    // testing the month
    @Test
    public void test7(){
       
    }
    
    // testing the word as a month
    @Test
    public void test8(){
       
    }
    
    // testing the getters and setters of the scripture class 
    @Test
    public void test9(){
        
    }
    
    // testing what is in the journal
    @Test
    public void test10(){
        Entry e = new Entry();
        e.setJournal("I was studying this morning Matt 5");
        Assert.assertEquals(e.getJournal(), "I was studying this morning Matt 5");
    }
    
    // Testing for topics, finds a topic in a string
    @Test
    public void test11(){
        Entry e = new Entry("What the spirit says, that we should follow.");
       e.setContent("There is joy in my soul today");
       Assert.assertEquals(e.getContent(), "There is joy in my soul today");
    }
    
    // testing if the find scripture function is capable to grab an scripture
   
    @Test
    public void test12(){
        Entry e = new Entry("jacob 3");
        // notice that the book should be return with upper case 
        Assert.assertEquals(e.findS("jacob 5"), "Jacob 5");
        Scripture s = new Scripture();
       
    }
    
    // Testing the constructor of the file class
    @Test
    public void test13(){
       
       
    }
    
    // testing the getfileName from the class 
    @Test
    public void test14(){
       
        
    }
    
    // This test will test the find date method, which should find the date 
    // from a given entry, so the user knows when he did input a journal entry
    @Test
    public void test15(){
        Entry e = new Entry("may 3, 2003");
        e.findDate();
        Assert.assertEquals(e.findDate(), "Jan-12-2000");
    }
    
    // Parse throught the file, and then pass the journal to another function
    // This function should find the scriptures in the string 
    @Test
    public void test16(){
        Entry e = new Entry("this is Nephi 3:4-6");
        Scripture s = new Scripture();
       
        Assert.assertEquals(s.getBook(), "Nephi");
        Assert.assertEquals(s.getChapter(), 3);
        Assert.assertEquals(s.getVerse(), 4-6);
        
    }
    
    @Test
    public void test17(){
        Journal journal =  new Journal();
        journal.compareBooks("Nephi", "Nephi");
        Assert.assertEquals(journal, true);
    }
    
    // Check if the file was read 
    @Test 
    public void readFile(){
        if (load == true){
            statusField.setText("Loaded");
            
            if (load == false)
                statusField.setText("Error reading file");
            
        }
    }
    
    // I am not completely sure how to test against the code that I did
    // with the buttons that open and save the files. I already have a textfield 
    // and I think I am going in a good direction. but Eventually that is what i 
    // want to do to pass the test 
    @Test 
    public void saveFile(){
        if (save == true){
            statusField.setText("saved");
            
            if (save == false)
                statusField.setText("Error saving file");
            
        }
        
    }
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
