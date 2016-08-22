public class Request {
	String itemName = "";
	String gameName = "";
	
	public Request(String itemName, String gameName) {
		this.itemName = itemName;
		this.gameName = gameName.toUpperCase();
	}
	
	public String getUrl() {
		String gameId = Games.valueOf(gameName).getValue();
		return "http://steamcommunity.com/market/priceoverview/?appid="
				+gameId
				+"&market_hash_name="
				+convertIntoURLName(itemName);
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getGameId() {
		return Games.valueOf(gameName).getValue();
	}
	
	public String getGameName() {
		return gameName;
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
