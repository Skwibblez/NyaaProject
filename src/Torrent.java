
public class Torrent {
	String url;
	String quality;
	String title;
	String subGroup;
	int episode;
	//Todo - not as important
	int size;
	int seeders;
	int leechers;
	String lastUpdated;
	int downloads;
	
	public Torrent(){
		
	}
	
	public Torrent(String title, String url){
		this.title = title;
		this.url = url;
	}
	public Torrent(String url, String quality, String title, int episode, String subGroup){
		this.url = url;
		this.quality = quality;
		this.title = title;
		this.episode = episode;
		this.subGroup = subGroup;
		
	}
	
	
	public Torrent(String url, String quality, String title, String subGroup, int size, int seeders, int leechers, String lastUpdated, int downloads){
		this.url = url;
		this.quality = quality;
		this.title = title;
		this.subGroup = subGroup;
		this.size = size;
		this.seeders = seeders;
		this.leechers = leechers;
		this.lastUpdated = lastUpdated;
		this.downloads = downloads;
	}
	
	//Getters
	public String getSubGroup(){
		return this.subGroup;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getQuality(){
		return this.quality;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public int getSize(){
		return this.size;
	}
	
	//Setters
	public void setUrl(String url){
		this.url = url;
	}
	
	public void setQuality(String quality){
		this.quality = quality;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setSub(String sub){
		this.subGroup = sub;
	}
	
	public void setEpisode(int episode){
		this.episode = episode;
	}
	
	
	
	
	

			
	
	
}
