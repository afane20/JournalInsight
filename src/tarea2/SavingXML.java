

// Colaborators: Ashlie Horst, Paul O'Neil 


package tarea2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 */
public class SavingXML {

    Map<String, String> map2;
     
     // This was implemented in the milestones
     public void run(Journal journal) {
        //try {
        String fileName = "/Users/salvador_afane/Desktop/Tarea2/src/tarea2/journal1.txt";
        List<String> list = readTitles(fileName);
        findEntry(list, journal); // call the function 
        
       // String terms = "/Users/salvador_afane/Desktop/Tarea2/src/tarea2/terms.txt";
        Properties properties = new Properties();
        String terms = properties.getTopicFile();
        map2 = makeMapOfList(terms, journal);
        //displayMapList(map2);

        
        Document doc = buildXmlDocument(journal.getEntryList());
        String file = "";
        saveDocument(doc, file);
        
        String newFile = "/Users/salvador_afane/Desktop/Tarea2/src/tarea2/newFile.txt";
        writeTextFile(newFile,journal);
       // } catch (FileNotFoundException ex) {
         //   System.out.println("File Not Found");
        //} 
    }
     /**************************************************************************
    * Using the GUI
    ***************************************************************************/
    public void run2(Journal journal, String path1, String path2) {
        //try {
        String fileName = path1;
        List<String> list = readTitles(fileName);
        findEntry(list, journal); // call the function 
        
        String terms = "/Users/salvador_afane/Desktop/Tarea2/src/tarea2/terms.txt";
        map2 = makeMapOfList(terms, journal);
        //displayMapList(map2);

        
        Document doc = buildXmlDocument(journal.getEntryList());
        String file = "";
        saveDocumentXML(doc, path2);
        
//        String newFile = "/Users/salvador_afane/Desktop/Tarea2/src/tarea2/newFile.txt";
//        writeTextFile(newFile,journal);
//       // } catch (FileNotFoundException ex) {
//         //   System.out.println("File Not Found");
//        //} 
    }
    /***************************************************************************
    *
    ***************************************************************************/
    public List<String> readTitles(String fileName) {
        List<String> list = new ArrayList<>();
   
        try {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }

        } catch (IOException ex) {
            ex.printStackTrace();
            // IOExepction is a super class of File Not Found
        }
        
        System.out.println("List: " + list.get(4));
        
        return list;           
    }
    /***************************************************************************
    * This function finds the entries of a given file, with its scriptures and 
    * topics, the "journal" object is being pass which will contain the "Entry"
    * object.
    ***************************************************************************/
    public void findEntry(List<String>list, Journal journal){
        String content = ""; // create the content 
        Entry entry2 = new Entry(); // make a new entry 
        //preparing counters
        int cScriptures = 0;
        int cTopics = 0;
        for (int i = 0; i < list.size();i++)
        {
            if (list.get(i).equals("-----"))  // if there is  new entry 
            {
                entry2.setContent(content);
                if (!(entry2.getContent().equals("")))
                journal.addEntry(entry2);
                
                content = "";
                entry2 = new Entry();
                entry2.setDate(list.get(i + 1));
            }
            if (!(list.get(i).equals("-----") || list.get(i).equals(entry2.getDate())))
            content += list.get(i);

        String chap = "chapter "; 
        Pattern pattern = Pattern.compile(chap); // find the word in the string 
        Matcher matcher = pattern.matcher(content);   // Match all the words in the journal 
   
        content = matcher.replaceAll("");      // r
        Pattern other =  Pattern.compile ("\\w+\\s\\d[0-9\\:\\-]*");
        Matcher bookNum = other.matcher(list.get(i));

        String group = "";
        
        while (bookNum.find())
        {   
            Scripture scripture = new Scripture();
            group = bookNum.group();
            String[] book = group.split(" ");
            
//            if(book[0].equals("C")){book[0] = "Doctrine and Covenants";}
//            scripture.setBook(book[0]);
//            scripture.setChapter(book[1]);
            
            if (book[1].split(":").length == 2 )
            {
            String[] chapter = book[1].split(":");
            scripture.setChapter(chapter[0]);
            scripture.setStartVerse(chapter[1]);
            
            if (chapter[1].split("-").length == 2)
                {
                    String[] verse = chapter[1].split("-");
                    scripture.setStartVerse(verse[0]);
                    scripture.setEndVerse(verse[1]);
                }
            }
            entry2.addScripture(scripture);
            cScriptures++;
            
        }// end of while loop
        
        
         
        }// end of for loop
        int cEntry = 0;
        entry2.setContent(content);
        journal.addEntry(entry2);
        cEntry++;
        Treads tr = new Treads();
        //tr.countScriptures = cScriptures;
       // tr.tester(cEntry, cScriptures, cTopics);
    }
        
//   public void organize(){
//       
//   }
      public Document buildXmlDocument(List<Entry> list) {
        Document doc = null;
        try {
//                organize();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("journal");
		doc.appendChild(rootElement);
 
		// gets the data from the entry
                for (Entry entry : list) {                 
                    Element entry1 = doc.createElement("entry");
                    rootElement.appendChild(entry1);
                    entry1.setAttribute("date", entry.getDate());
                    
                    for (Scripture scripture : entry.getScriptureList())
                    {
                       Element scripture2 = doc.createElement("scripture"); // make a tag name scrpture 
                       entry1.appendChild(scripture2); // make it a child of the entries 
                       scripture2.setAttribute("book", scripture.getBook()); // make an attr. called book
                       scripture2.setAttribute("chapter", scripture.getChapter()); // make another attr for scripture called chapter
                       scripture2.setAttribute("startverse", scripture.getStartVerse()); // same as above 
                       scripture2.setAttribute("endverse", scripture.getEndVerse()); // same as above 

                    }
                    for (String topic : entry.getTopicList()){
                    Element topic1 = doc.createElement("topic");
                    entry1.appendChild(topic1);
                    topic1.appendChild(doc.createTextNode(topic));

                            }
                    // Get the content of the document and journal
                    Element content = doc.createElement("content");
                    entry1.appendChild(content);
                    content.appendChild(doc.createTextNode(entry.getContent()));
                }
                 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  }
        return doc;
    }
    
      //Save XML document 
    public void saveDocument(Document doc, String file) {
 	try {
                // write the content into xml file
                
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                
                // Make the XML look pretty
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                 
                // Create the new file
		DOMSource source = new DOMSource(doc);
                
                StreamResult result = new StreamResult(new File("/Users/salvador_afane/Desktop/Tarea2/src/tarea2/hello.xml"));
                //StreamResult result = new StreamResult(new File());

                //  StreamResult result = new StreamResult(new File("C:\\file.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out)
               
		transformer.transform(source, result);
 
		System.out.println("XML File saved. Called \"" + file + "\"!");   
    
	    }catch (TransformerException tfe) {
		tfe.printStackTrace();
            }
        
        
    }
    
    public Map<String, String> makeMapOfList(String terms, Journal journal){
       
        Map <String, String> map = new HashMap<>();
       // String line;
        try{
             BufferedReader br = new BufferedReader(new FileReader(terms));
             String line; //declare 
            
        while ((line = br.readLine()) != null) {
              String[] parts = line.split(":");
              String key = parts[0];
              
              String valueList = parts[1];
              String[]terms2 = valueList.split(",");
              
              String list = "";
              
              for (String terms3 : terms2){
                  
                  map.put(terms3, key);
              }
              
            }
          }catch (Exception ex){
              
          } 
     searchTopic(map, journal);
     return map;  
    }
    public void searchTopic(Map<String, String> map, Journal journal){
        
        for (String terms : map.keySet()){
            for (Entry entry : journal.getEntryList()){
                String content = entry.getContent();
                if (content.contains(terms)){
                    if (!(entry.getTopicList().contains(map.get(terms))))
                       entry.addTopic(map.get(terms));
                }
            }
        } 
    }
    public void writeSimpleText(String fileName, String content){
    try {
        PrintWriter write = new PrintWriter(fileName, "UTF-8");  
        write.print(content);
        
         write.close();
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error Reading File");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
    public void writeTextFile(String newFile, Journal journal) {      
        try {
            PrintWriter write = new PrintWriter(newFile, "UTF-8");  
            for (Entry entry : journal.getEntryList()){
                String content = entry.getContent();
                String date = entry.getDate();
                
                write.println("------");
                write.println(date);
                write.println(content);
                
             }// end of for loop
            write.close();
            System.out.println("A normal text file made, Called ");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Error Reading File");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
    
    public void saveDocumentXML(Document doc, String file) {
 	try {
                // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                
                // Make the XML look pretty
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                 
                // Create the new file
		DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(file));

		transformer.transform(source, result);
 
		System.out.println("XML File saved. Called \"" + file + "\"!");   
    
	    }catch (TransformerException tfe) {
		tfe.printStackTrace();
            }
        
        
    }
    
//    public void displayMapList(Map<String, List<String>> map) {
//    // This sorts the list 
//    //Collections.sort(list);
//    for(String term : map.keySet()) {
//    System.out.println(term + " " + map.get(term));
//         }
//    }
}
