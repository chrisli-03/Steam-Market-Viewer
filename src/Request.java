
public class Request {
	String itemName = "";
	String gameId = "";
	
	public Request(String itemName, String gameName) {
		this.itemName = itemName;
		this.gameId = Games.valueOf(gameName).getValue();
	}
	
	public String getUrl() {
		return "http://steamcommunity.com/market/priceoverview/?appid="+gameId+"&market_hash_name="+itemName;
	}
}
