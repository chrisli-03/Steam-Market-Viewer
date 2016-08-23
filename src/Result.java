public class Result {
	private String itemName = "";
	private String price;
	private String date = "2016/01/01"; // TODO: implement real date
	private String volumn;
	private String median;
	private Boolean found;
	
	public Result() {
		found = false;
		this.price = "-";
		this.volumn = "0";
		this.median = "-";
	}
	
	public Result(String itemName, String price, String volumn, String median) {
		found = false;
		this.itemName = itemName;
		this.price = price;
		this.volumn = volumn;
		this.median = median;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getVolumn() {
		return volumn;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getMedian() {
		return median;
	}

	public Boolean getFound() {
		return found;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public void setMedian(String median) {
		this.median = median;
	}

	public void setFound(Boolean found) {
		this.found = found;
	}
	
	
}
