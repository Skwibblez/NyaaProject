import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

//Examples(Retrieving title
//String titleSelector1 = "body > div > div.table-responsive > table > tbody > tr:nth-child(" + Integer.toString(i) + ") > td:nth-child(2) > a";
//String titleSelector2 = "body > div > div.table-responsive > table > tbody > tr:nth-child(" + Integer.toString(i) + ") > td:nth-child(2) > a:nth-child(2)";
//Element.text() to store JUST THE TEXT

public class WebScraper extends Tools{
//Build path need to be fixed
	//Constants
	final static int maxEntries = 75;
	
	//Base url arg
	static String baseUrl = "https://nyaa.si";
	static String url = "https://nyaa.si/?f=2&c=1_2&q=";
	
	//Title arguments
	static String queryTitleFirst = "body > div > div.table-responsive > table > tbody > tr:nth-child(";
	static String queryTitleLast1 = ") > td:nth-child(2) > a:nth-child(2)";
	static String queryTitleLast2 = ") > td:nth-child(2) > a";
	
	//Link arguments
	static String queryLinkFirst = "body > div > div.table-responsive > table > tbody > tr:nth-child(";
	static String queryLinkLast = ") > td:nth-child(3) > a:nth-child(1)";
	
	public static void main(String[] args) {
		updateTorrent();
		
		//File Writing Functionality
		String fileName1 = "testing";
		String fileName = "nyaaTorrents.txt";
		//writeFile(fileName1);
		
			
		//File Writing	
			
//		try{
//			Document doc = Jsoup.connect(url).get();
//			//String title = doc.select("#table-responsive.comments").text();
//			String title = doc.title();
//			System.out.println(title);
//			//System.out.println(doc);
//			//System.out.println("TITLE: " + title);
//			//printStr("title", title);
//			
//			//final Document myDoc = Jsoup.connect(url).maxBodySize(1000000).timeout(0).get();
//			//Document myDoc = Jsoup.parse(html);
//			//Include .get(0).text() to remove title tag
//			//System.out.println(myDoc.getElementsByTag("title").get(0).text());
//			//System.out.println(doc.outerHtml());
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}		
	}
	
	public static void writeFile(String fileName){
		//check if txt included	
		fileName+=".txt";	
		
		try{
			//Setup
			File file = new File(fileName);
			if(file.createNewFile()){
				System.out.println("File created: " + file.getName());
			}else{
				System.out.println("File already exists.");
			}
			FileWriter myWriter = new FileWriter(fileName);
			
			//Write content here
			myWriter.write("Hey there");
			myWriter.close();
			
			printStr("File successfully written");
			
		} catch(IOException e){
			System.out.println("An error has occurred.");
			e.printStackTrace();
		}
	}
	
	//This class should create torrent objects and append to an arraylist
	public static void updateTorrent(){
		
		//Initialize
		String titleSelector1;
		String titleSelector2;
		String linkSelector;


		//Fetch html doc
		try{
			Document doc = Jsoup.connect(url).get();
	
			//Max entries = 75/page? 
			for(int i = 0 ; i < maxEntries; i++){
		
				titleSelector1 = queryTitleFirst + Integer.toString(i) + queryTitleLast1;
				titleSelector2 =queryTitleFirst + Integer.toString(i) + queryTitleLast2;
				
				linkSelector = queryLinkFirst + Integer.toString(i) + queryLinkLast;				
				
				Elements test1 = doc.select(titleSelector1);
				Elements test2 = doc.select(titleSelector2);
				Elements test3 = doc.select(linkSelector);
				
				//Logic faulty here
				if(!test1.text().equals("")){	//Check for blank string
					//printStr("Test1: " + test1.text());
					printStr(test1.text());
				}else{
					printStr(test2.text());

				}
					
				if(!test3.attr("href").equals("")){	//Check for blank string
					printStr(baseUrl + test3.attr("href"));
					
				}else{
					//Do nothing
					//test(3);
				}				
				
//				printStr("Test1: " + test1.text());
//				printStr("Test2: " + test2.text());									
			}		

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void webPage(String url){
		try{		
			URI uri = new URI(url);
			java.awt.Desktop.getDesktop().browse(uri); 
			 System.out.println("Web page opened in browser"); 
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	


}
