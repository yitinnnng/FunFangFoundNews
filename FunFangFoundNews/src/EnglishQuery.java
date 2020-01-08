import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EnglishQuery {
 public String searchKeyword;

 public String url;
 public String time;
 public String content;
 HashMap<String, String> title_time = new HashMap<String, String>();
 public EnglishQuery(String searchKeyword)

 {
  
  this.searchKeyword = searchKeyword;

  this.url = "https://news.google.com/search?q="+encodeURL(searchKeyword)+"&hl=en-US&gl=US&ceid=US%3Aen";
 }
 public String fetchContent() throws IOException

 {
  String retVal = "";

  URL u = new URL(url);

  URLConnection conn = u.openConnection();

  conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

  InputStream in = conn.getInputStream();

  InputStreamReader inReader = new InputStreamReader(in,"utf-8");

  BufferedReader bufReader = new BufferedReader(inReader);
  String line = null;

  while((line=bufReader.readLine())!=null)
  {
   retVal += line;

  }
  return retVal;
 }
 
 public HashMap<String, String> query() throws IOException

 {

  if(content==null)

  {

   content= fetchContent();

  }
  HashMap<String, String> retVal = new HashMap<String, String>();
  
  Document doc = Jsoup.parse(content);
  Elements lis = doc.select("div");
  lis = lis.select(".NiLAwe");
  for(Element li : lis)
  {
   try 

   {
    
    String title = li.select(".DY5T1d").get(0).text();
    String citeUrl = "https://news.google.com" + li.select("a").get(0).attr("href").substring(1);
    if (li.select(".WW6dff").size()!=0) {
    	time=li.select(".WW6dff").get(0).attr("datetime").substring(0,10);
	}else {
		Date date = new Date();
	    SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");       
	    time = a.format(date);
	}
    
    retVal.put(title, citeUrl);
    title_time.put(title,time);
 
   } catch (IndexOutOfBoundsException e) {
    e.printStackTrace();
   }
  }
  return retVal;
 }
 public static String encodeURL(String url) {
  try {
        String encodeURL = URLEncoder.encode( url, "UTF-8" );
        return encodeURL;
  } catch (UnsupportedEncodingException e) {
        return "Error: " + e.getMessage();
  }
 }
}