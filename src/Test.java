import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import javax.swing.JEditorPane;
import javax.swing.text.Document;

//Desired functionality
//Specific quality || subgroup || batch(?) maybe in future

public class Test extends WebScraper {
	public static void main(String[] args){
		//updateTorrent();
		//ArrayList<Torrent> myTorrents = new ArrayList<Torrent>();
		//webPage("https://www.google.com/");
		ArrayList<Torrent> myList = new ArrayList<Torrent>();
		updateTorrent();
	}

	//Goes to specific URL (download)
	
}
