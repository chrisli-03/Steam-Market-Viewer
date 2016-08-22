import javax.swing.JOptionPane;

public class Request {
	String itemName = "";
	String gameId = "";
	
	public Request(String itemName, String gameName) {
		this.itemName = itemName;
		this.gameId = Games.valueOf(gameName.toUpperCase()).getValue();
	}
	
	public String getUrl() {
		return "http://steamcommunity.com/market/priceoverview/?appid="+gameId+"&market_hash_name="+convertIntoURLName(itemName);
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getGameId() {
		return gameId;
	}
	
	public String convertIntoURLName(String itemName){
		String [] splitedString=itemName.split(" ");
		String result= splitedString[0];
		for(int i =1;i<splitedString.length;i++){
			result+="%20";
			result+=splitedString[i];
		}
		return result;		
	}
}
