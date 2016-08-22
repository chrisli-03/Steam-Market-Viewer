import java.util.ArrayList;
import java.util.Scanner;

public class Model {
	private View view;
	Toolbar toolbar;
	private ArrayList<SteamItem> selectedItems;

	public Model() {
		selectedItems = new ArrayList<SteamItem>();
	}

	public void addSteamItem(String item, String game) {
		selectedItems.add(new SteamItem(item, game));
		
		for (SteamItem temp : selectedItems) {
			System.out.print(temp);
		}
	}

	public void addView (View view) {
		this.view = view;
	}

	public void addToolbar(Toolbar tb) {
		this.toolbar = tb;
	}
	public String getItemName() {
		String itemName = view.getItemName();
		return itemName;
	}

	public String getGameName() {
		String gameName = view.getGameName();
		return gameName;
	}
	
	public String getItemNameFromView() {
		return view.getItemName();
	}
	
	public String getGameNameFromView() {
		return view.getGameName();
	}
}
