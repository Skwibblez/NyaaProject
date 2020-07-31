import java.util.ArrayList;

public class Tools {
	public static void printStr(String n,String s){
		System.out.println(n + ": " + s);
	}

	public static void printStr(String s){
		System.out.println(s);
	}
	public static void print(int n){
		System.out.println(n);
	}
	
	public static void test(int n){
		printStr("Test " + Integer.toString(n));
	}
	//Title + link
	public static void printList(ArrayList<Torrent> list){
		String pad = "\n-----------------------------------------------------";
		printStr("\nPrinting List"+ pad);
		for(int i = 0; i < list.size(); i++){
			printStr("Object Title: " + list.get(i).title);
			printStr("Object URL: " + list.get(i).url);
			printStr("Object SubGroup: " + list.get(i).subGroup);
			printStr("Object quality: " + list.get(i).quality);
			printStr("Object episode number: " + list.get(i).episode + pad);
			
		}
	}
	
}
