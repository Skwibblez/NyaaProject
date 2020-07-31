
public class Torrent {
	String url;
	int quality;
	String title;
	String subGroup;
	int size;
	
	public Torrent(String url, int quality, String title, String subGroup, int size){
		this.url = url;
		this.quality = quality;
		this.title = title;
		this.subGroup = subGroup;
		this.size = size;
	}
	//Getters
	public String getSubGroup(){
		return this.subGroup;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public int getQuality(){
		return this.quality;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public int getSize(){
		return this.size;
	}
	
	
	
	
}
