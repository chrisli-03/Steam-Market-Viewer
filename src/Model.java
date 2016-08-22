import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Scanner;
>>>>>>> a9346966dbffad2cfb4c412d3d181dcbae4ae18b

public class Model {
	private View view;
	Toolbar toolbar;
<<<<<<< HEAD
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

	public void addView(View view) {
=======
	private ArrayList<Request> requestList = new ArrayList<Request>();
	
	public void addView (View view) {
>>>>>>> a9346966dbffad2cfb4c412d3d181dcbae4ae18b
		this.view = view;
	}

	public void addToolbar(Toolbar tb) {
		this.toolbar = tb;
	}

<<<<<<< HEAD
	public String getItemName() {
		String itemName = view.getItemName();
		return itemName;
	}

	public String getGameName() {
		String gameName = view.getGameName();
		return gameName;
	}
=======
	public ArrayList<Request> getRequestList() {
		return requestList;
	}

	public void addRequest(String itemName, String gameName) { 
		/*Todo: add duplicate check*/
		Request newRequest = new Request(itemName, gameName);
		requestList.add(newRequest);
		view.updateView();
	}
	
	public String getItemNameFromView() {
		return view.getItemName();
	}
	
	public String getGameNameFromView() {
		return view.getGameName();
	}
	
>>>>>>> a9346966dbffad2cfb4c412d3d181dcbae4ae18b
}
