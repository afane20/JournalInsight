/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author salvador_afane
 */
// This class will read an xml file and display the content.
public class ReadFile2 {
    
    /***************************************************************************
    * Essential!!, This function can read an XML file. it can identify the 
    * nodes, and assign the values where they belong. It can parse through an 
    * XML file and make a journal entry according to the content of the file
    ***************************************************************************/
    public void read (File fileName, Journal journal) throws SAXException, IOException{
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        
            // make counters 
            int cEntries = 0, cScriptures = 0, cTopics = 0;
            
        try{
            File xmlfile = new File(fileName.getAbsolutePath());
            DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
            
            Document document = dBuilder.parse(xmlfile);
            document.normalize();
            
           
            
            NodeList rootNodes = document.getElementsByTagName("journal");// the root element 
            Node rootNode = rootNodes.item(0);
            Element rootElement = (Element) rootNode; // entryList will hold the entry Nodes
            
            NodeList entryList = rootElement.getElementsByTagName("entry"); // entry
            String scriptureHolder = "";
            String verseHolder = "";
            String chapterHolder = "";
            String contentHolder = "";
            String topicHolder = "";
            String bookHolder = "";
            String dateHolder = "";
            String startVerseHolder = "";
            String endVerseHolder = "";
            List <String> listOfTopics = null;
            for (int i = 0; i < entryList.getLength();i++){
                Entry entry = new Entry();

                Node theEntry = entryList.item(i);
                Element entryElement = (Element) theEntry;

                dateHolder = entryElement.getAttribute("date");
                entry.setDate(dateHolder);

                
                NodeList scriptureList = entryElement.getElementsByTagName("scripture");
                
                //Scripture "for loop"
                for (int g = 0; g < scriptureList.getLength();g++){
                
                Scripture Scripture1 = new Scripture();
                Node theScripture = scriptureList.item(g);
                Element scripture = (Element) theScripture;
                
                bookHolder = scripture.getAttribute("book");
                verseHolder = scripture.getAttribute("verse");
                chapterHolder = scripture.getAttribute("chapter");
                startVerseHolder = scripture.getAttribute("startverse");
                
                Scripture1.setBook(bookHolder);
                Scripture1.setChapter(chapterHolder);
                Scripture1.setStartVerse(startVerseHolder);
               
                if (scripture.hasAttribute("endverse")){
                Scripture1.setEndVerse(endVerseHolder);
                } else {
                    Scripture1.setEndVerse("");
                }
                
                entry.addScripture(Scripture1);
                cScriptures++;
                
                }
                
                
                NodeList topicsList = entryElement.getElementsByTagName("topic");
                
                // get the topics!!!!
                for (int t = 0; t < topicsList.getLength();t++ ) {
                Node theTopic = topicsList.item(t);
                Element topic = (Element) theTopic;
                
                topicHolder = (topic.getTextContent());
                entry.addTopic(topicHolder);
                cTopics++; // counter
                }
                
                // get the content and parse it, cut the spaces and print it in the screen
                String content = entryElement.getElementsByTagName("content").item(0).getTextContent();
                content = content.trim();
                content = content.replaceAll("\\n\\s+", "\n");
 
                entry.setContent(content);
                
                journal.addEntry(entry);
                cEntries++;
            }
            
            Treads tr = new Treads();
            tr.countEntries = cEntries;
            tr.countScriptures = cScriptures;
            tr.countTopic = cTopics;
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
