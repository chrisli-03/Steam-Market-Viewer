
public enum Games {
	DOTA2(570),
	CSGO(730);
	
	private int value;
	private Games(int value) {
		this.value = value;
	}
	
	public String getValue() {
		return Integer.toString(value);
	}
}
