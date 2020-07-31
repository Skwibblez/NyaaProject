import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

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
	
	
	//Title arguments
	static String queryTitleFirst = "body > div > div.table-responsive > table > tbody > tr:nth-child(";
	static String queryTitleLast1 = ") > td:nth-child(2) > a:nth-child(2)";
	static String queryTitleLast2 = ") > td:nth-child(2) > a";
	
	//Link arguments
	static String queryLinkFirst = "body > div > div.table-responsive > table > tbody > tr:nth-child(";
	static String queryLinkLast = ") > td:nth-child(3) > a:nth-child(1)";
	
	//Useless main here
	public static void main(String[] args) {
		updateTorrent();
		
		//File Writing Functionality
		String fileName1 = "testing";
		String fileName = "nyaaTorrents.txt";
		//writeFile(fileName1);
		
			
		//File Writing	
			
		/*try{
			Document doc = Jsoup.connect(url).get();
			//String title = doc.select("#table-responsive.comments").text();
			String title = doc.title();
			System.out.println(title);
			//System.out.println(doc);
			//System.out.println("TITLE: " + title);
			//printStr("title", title);
			
			//final Document myDoc = Jsoup.connect(url).maxBodySize(1000000).timeout(0).get();
			//Document myDoc = Jsoup.parse(html);
			//Include .get(0).text() to remove title tag
			//System.out.println(myDoc.getElementsByTag("title").get(0).text());
			//System.out.println(doc.outerHtml());
		}catch(Exception ex){
			ex.printStackTrace();
		}	*/	
	}
	
	public static void parseTitle(String inputTitle, Torrent inputTorrent){
		
		//Constants		
		final String leadingBracket = "[";
		final String endingBracket = "]";
		String subGroup = "";
		String title = "";
		boolean appendSub = false, appendTitle = false;
		//---------------------------------------------------------------
		//SubGroup + title
		int epNumIndex = inputTitle.lastIndexOf("-");
		for(int i = 0; i < epNumIndex-1; i++){
			String currentChar = ""+inputTitle.charAt(i);
			if(currentChar.equals(endingBracket)){
				i++;	//Assume next char is space
				appendTitle = true;
				appendSub = false;
				continue;
			}
			
			if(appendSub){
				subGroup+=currentChar;
			}else if(appendTitle){
				title+=currentChar;
			}
			
			if(currentChar.equals(leadingBracket) && (appendSub == false)){
				appendSub = true;	
			}
		}
		
		//Set attr
		inputTorrent.setTitle(title);
		inputTorrent.setSub(subGroup);
		
		//Debug
		printStr("In function title: " + inputTorrent.title);
		printStr("In function subgroup: " + inputTorrent.subGroup);	
	}//end subGroup + title parsing
	
	//Get quality of current object
	public static String parseQuality(String inputTitle){
		String qual="";
		String qualities[] = {"360p", "480p", "720p", "1080p"};
		//Gets quality	
		for(int i = 0; i < qualities.length; i++){
			if(inputTitle.contains(qualities[i])){
				qual = qualities[i];
				break;
			}else{
				//printStr("Does not contain " + qualities[i]);
				//Maybe change for 
				qual = "000p";
			}
		}
		return qual;
	}//End parseQuality
	
	//Get Episode number
	public static int parseEpisodeNumber(String inputTitle){
		//Gets episode number
		String num = "";
		int epNumIndex = inputTitle.lastIndexOf("-");
		//printStr("Index: " + epNumIndex);
		for(int i = epNumIndex +2; i < inputTitle.length(); i++){
			char thisChar = inputTitle.charAt(i);
			if(thisChar >= 48 && thisChar <= 57){
				num+= thisChar;
			}else{
				break;
			}
		}
		if(num.equals("")){	//Should never happen - episode # missing
			num = "-1";
			printStr("WTF IS GOING ON");
		}
		return Integer.parseInt(num);
	}//end parseEpisodeNumber
	
	//This class should create torrent objects and append to an arraylist
	//Can accept no arguments if simply updating
	public static void updateTorrent(String searchTerm, int inputQuality, int startRange, int endRange){
		//This string is going to be changed based on user search
		String url = "https://nyaa.si/?f=2&c=1_2&q=" + searchTerm;
		printStr("Search url: " + url);
		
		//Add functionality for no desired quality
		String desiredQuality = Integer.toString(inputQuality) + "p";
		
		//Initialize
		String titleSelector1,  titleSelector2,  linkSelector;	
		String torrentTitle = "", torrentUrl = "", torrentQuality = "";
		
		//Need to adjust this later to desired episode and strange ranges
		int currentEpisodeNum = 2000;
		int parsedEpisodeNumber;
		boolean normalize = false;
		//ArrayList
		ArrayList<Torrent> myList = new ArrayList<Torrent>();
		//Fetch html doc
		try{
			Document doc = Jsoup.connect(url).get();
	
			//Max entries = 75/page? 
			//for(int i = 0 ; i < maxEntries; i++){
			for(int i = 0 ; i < 40; i++){
				titleSelector1 = queryTitleFirst + Integer.toString(i) + queryTitleLast1;
				titleSelector2 =queryTitleFirst + Integer.toString(i) + queryTitleLast2;
				linkSelector = queryLinkFirst + Integer.toString(i) + queryLinkLast;				
				
				Elements test1 = doc.select(titleSelector1);
				Elements test2 = doc.select(titleSelector2);
				Elements test3 = doc.select(linkSelector);
				
				//Logic faulty here
				//Title
				if(!test1.text().equals("")){	//Check for blank string
					//printStr("Test1: " + test1.text());
					printStr(test1.text());
					torrentTitle = test1.text();
					
				}else{
					printStr(test2.text());
					torrentTitle = test2.text();	
				}
				
				//URL
				if(!test3.attr("href").equals("")){	//Check for blank string
					printStr(baseUrl + test3.attr("href"));
					torrentUrl = baseUrl + test3.attr("href");	//Link obtained
					parsedEpisodeNumber = parseEpisodeNumber(torrentTitle);
					//If correct quality -> parse rest of information
					if(parsedEpisodeNumber == currentEpisodeNum ){
						torrentQuality = parseQuality(torrentTitle);	//Quality obtained
						if(torrentQuality.equals(desiredQuality) || torrentQuality.equals("000p")){
							//Parse rest of information -- do this now		
							Torrent newTorrent = new Torrent();
							//Set attr(quality | url | title | episode | subgroup)
							newTorrent.setQuality(torrentQuality);
							newTorrent.setUrl(torrentUrl);
							newTorrent.setEpisode(parsedEpisodeNumber);
							parseTitle(torrentTitle, newTorrent);	//Subgroup and real title
							myList.add(newTorrent);
							currentEpisodeNum--;
							printStr("ADDED!\n");
							//THIS LINE BE CAREFUL - goes through all the links
							//webPage(torrentUrl);}
						}else{	//Incorrect quality type
							printStr("SKIPPING " + torrentQuality + " not desired.\n");
							continue;
						}	
					}else{	//Incorrect episode number + normalizing
						printStr("SKIPPING episode " + parsedEpisodeNumber + " not desired.\n");
						if(currentEpisodeNum >= parsedEpisodeNumber && normalize == false){
							normalize = true;
							printStr("Normalized from " + currentEpisodeNum + " to " + parsedEpisodeNumber + "\n");
							currentEpisodeNum = parsedEpisodeNumber;
							i--;
						}
						continue;
					}
											
				}else{
					//Do nothing
					//test(3);
					printStr("Url error");
				}												
			}//end for		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//Testing
		printList(myList);
	}
	
	//First two used for random anime -- not very useful
	public static void updateTorrent(){
		updateTorrent("",720,1,12);
	}
	
	//Makes sense for random anime
	public static void updateTorrent(int quality){
		if(quality != 360 || quality!= 480 || quality != 720 || quality != 1080){
			printStr("Quality not accepted - defaulting to 720\n");
			quality = 720;
		}
		updateTorrent("", quality,1,12);
	}
	
	//Default to 720p
	public static void updateTorrent(String searchTerm){
		updateTorrent(searchTerm, 720, 1, 12);
	}
	public static void updateTorrent(int start, int end){
		updateTorrent("", 720, start, end);
	}
	public static void updateTorrent(String searchTerm, int start, int end){
		updateTorrent(searchTerm, 720, start, end);
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


}
