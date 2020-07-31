import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.JEditorPane;
import javax.swing.text.Document;
import java.time.*;
//Desired functionality
//Specific quality || subgroup || batch(?) maybe in future

public class Test extends WebScraper {
	public static void main(String[] args){
		//updateTorrent();
		//ArrayList<Torrent> myTorrents = new ArrayList<Torrent>();
		//webPage("https://www.google.com/");
		long startTime = System.currentTimeMillis();	
		
		ArrayList<String> searchList = new ArrayList<String>();
		ArrayList<Torrent> myList = new ArrayList<Torrent>();
		
		//Input 
		Scanner scanner = new Scanner(System.in);
		printStr("Search Anime Torrent: ");
	    String readString = scanner.nextLine();
	    int counter = 0;
	    while(readString!=null) {
	    	printStr("Counter " + counter);
	        //System.out.println(readString);

	        if (readString.isEmpty()) {
	            printStr("Enter detected");
	            break;
	        }

	        if (scanner.hasNextLine()) {        	
	            readString = scanner.nextLine();
	            searchList.add(readString);
	            printStr(readString + " added to search!");
	        } else {
	            readString = null;
	        }
	        counter++;
	    }
		
	    //Debugging
	    printListStr(searchList);
	    //Account for the "Enter"
	    if(searchList.size() > 0){
	    	searchList.remove(searchList.size()-1);
	    	//Run main function
		    String searchArg;
		    for(int i = 0; i < searchList.size(); i++){
		    	searchArg = searchList.get(i);
		    	updateTorrent(searchArg);
		    }
	    }else{
	    	updateTorrent();
	    }
	    
	    
		
		
		
		
		
		//Keep track of time
		long elapsedTime = System.currentTimeMillis() - startTime;
		long elapsedSeconds = elapsedTime / 1000;
		long secondsDisplay = elapsedSeconds % 60;
		long elapsedMinutes = elapsedSeconds / 60;
		printStr("Elapsed Time: " + elapsedTime + " ms");
		printStr("Elapsed Sec: " + elapsedSeconds +" s");
		printStr("Elapsed Min: " + elapsedMinutes + " m");

	}


	//Goes to specific URL (download)
	
}
