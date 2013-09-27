/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicauca.logic;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 *
 * @author Juan
 */
public class MovieSearch {
    
     public static String url = "http://api.nytimes.com/svc/movies/v2/reviews/search.xml";
    public static String parameter = "&thounsand-best=Y&dvd=Y&opening-date=1980-01-01;2013-01-01&";
    public static final String apiKey = "12f2e3dc939a8671387979f7fc415162:0:68178486";

    public static String getMovieReviews(String keyword) {
        HttpClient client = new DefaultHttpClient();
        //keyword = getRandomKeyword();
        String responseBody = null;

        HttpGet request = new HttpGet(url + "?query=" + keyword + parameter + "api-key=" + apiKey);
        try {
            HttpResponse response = client.execute(request);
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException ex) {
            Logger.getLogger(MovieSearch.class.getName()).log(Level.SEVERE, null, ex);
        }

        return processXML(responseBody);
    }

    public static String processXML(String xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;           
        String resu = null;
       
        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
            doc.getDocumentElement().normalize();
            
            Element rootElement = doc.getDocumentElement();
            
            NodeList results = rootElement.getElementsByTagName("results");
            Element e = (Element) results.item(0); 
            
            NodeList review = e.getElementsByTagName("review");
            Element e1 = (Element) review.item(0);
            
            String title = "~";  //titulo pelicula
            NodeList display_title = e1.getElementsByTagName("display_title");
            if (display_title.getLength() != 0) {
                Element e2 = (Element) display_title.item(0);
                title = e2.getTextContent();
            }
            
            String head = "~";   //cabecera pelicula
            NodeList headline = e1.getElementsByTagName("headline");
            if (headline.getLength() != 0) {
                Element e3 = (Element) headline.item(0);
                head = e3.getTextContent();
            }
            
            String movieReview = "~";   //critica pelicula
            NodeList capsule_review = e1.getElementsByTagName("capsule_review");
            if (capsule_review.getLength() != 0) {
                Element e4 = (Element) capsule_review.item(0);
                movieReview = e4.getTextContent();
            }
            
            String summary = "~";   //resumen
            NodeList summary_short = e1.getElementsByTagName("summary_short");
            if (summary_short.getLength() != 0) {
                Element e5 = (Element) summary_short.item(0);
                summary = e5.getTextContent();
            }
           
            String publication = "~";   //fecha publicacion
            NodeList publication_date = e1.getElementsByTagName("publication_date");
            if (publication_date.getLength() != 0) {
                Element e6 = (Element) publication_date.item(0);
                publication = e6.getTextContent();
            }    
            
            NodeList link = e1.getElementsByTagName("related_urls");
            Element eurl = (Element) link.item(0);          
            
            NodeList trailer = eurl.getElementsByTagName("link");
            Element url1 = (Element) trailer.item(4);
            
            String url2 = "~";   //url trailers
            NodeList ur = url1.getElementsByTagName("url");
            if (ur.getLength() != 0) {
                Element e7 = (Element) ur.item(0);
                url2 = e7.getTextContent();
            }        
            
            
//            if("".equals(movieReview)){
//            resu= title+ "*" + head + "*" +summary+ "*" +publication+ "*"+url2;
//            }else{
//             if("".equals(url2)){
//            resu= title+ "*" + head + "*" +movieReview+ "*" +summary+ "*" +publication;
//            }else{
//            resu= title+ "*" + head + "*" +movieReview+ "*" +summary+ "*" +publication+ "*"+url2;
//            }
//            }
         
         resu= title+ "☺" + head + "☺" +movieReview+ "☺" +summary+ "☺" +publication+ "☺" +url2;
        
       
            
        } catch (SAXException ex) {
            Logger.getLogger(MovieSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MovieSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MovieSearch.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return resu;       
    }
    
}
