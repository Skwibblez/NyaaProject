import java.util.ArrayList;
import java.util.Collections;

//Personalized list for each user
public class User extends Tools{
	//Attr
	ArrayList<Anime> animeList;
	
	
	
	//Constructor
	public User(){
		animeList = new ArrayList<Anime>();
	}
	
	//Adding anime | removing anime from list
	public void addAnime(Anime newAnime){
		//Add sorting here - for alphabetical printing
		this.animeList.add(newAnime);
		
	}
	
	public void removeAnime(Anime deleteAnime){
		if(this.animeList.contains(deleteAnime)){
			printStr("Deleting this anime - but not implemented yet");
		}else{
			printStr("Anime wasn't found");
		}
	}
	
	//Add sorting function?

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
