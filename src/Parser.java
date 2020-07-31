
//Change to interface? 
public class Parser extends Tools{
	//Get quality of current object
		public static String parseQuality(String inputTitle){
			String qual = null;
			String qualities[] = {"360p", "480p", "720p", "1080p"};
			//Gets quality
			for(int i = 0; i < qualities.length; i++){
				if(inputTitle.contains(qualities[i])){
					qual = qualities[i];
				}else{
					//printStr("Does not contain " + qualities[i]);
				}
			}
			return qual;
		}//End parseQuality
		//----------------------------------------------------------------
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
		//------------------------------------------------------------------
		//Old parse all function
		public static void parseTitle(String inputTitle, Torrent inputTorrent){
			
			//Constants
			
			
			String qual = null;
			String qualities[] = {"360p", "480p", "720p", "1080p"};
			
			final String leadingBracket = "[";
			final String endingBracket = "]";
			String subGroup = "";
			String title = "";
			boolean appendSub = false, appendTitle = false;
			
			//Gets quality
//			for(int i = 0; i < qualities.length; i++){
//				if(inputTitle.contains(qualities[i])){
//					qual = qualities[i];
//				}else{
//					//printStr("Does not contain " + qualities[i]);
//				}
//			}
			//-----------------------------------------------------------
			//Gets episode number
//			String num = "";
			int epNumIndex = inputTitle.lastIndexOf("-");
//			//printStr("Index: " + epNumIndex);
//			for(int i = epNumIndex +2; i < inputTitle.length(); i++){
//				char thisChar = inputTitle.charAt(i);
//				if(thisChar >= 48 && thisChar <= 57){
//					num+= thisChar;
//				}else{
//					break;
//				}
//			}
//			if(num.equals("")){	//Should never happen - episode # missing
//				num = "-1";
//				printStr("WTF IS GOING ON");
//			}
			//---------------------------------------------------------------
			//SubGroup + title
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
				}
				
				if(appendTitle){
					title+=currentChar;
				}
				
				if(currentChar.equals(leadingBracket)){
					if(appendSub == false){
						appendSub = true;
					}
				}
			}
			
			//Set attr
			
			inputTorrent.title = title;
			//inputTorrent.episode = Integer.parseInt(num);
			//inputTorrent.quality = qual;
			inputTorrent.subGroup = subGroup;
			
			//Debug
			printStr("In function title: " + inputTorrent.title);
//			printStr("In function episode: " + inputTorrent.episode);
//			printStr("In function qual: " + inputTorrent.quality);
			printStr("In function subgroup: " + inputTorrent.subGroup);	
		}


}
