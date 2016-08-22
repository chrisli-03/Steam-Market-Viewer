import java.util.ArrayList;
import java.util.Scanner;

public class Model {
	private View view;
	Toolbar toolbar;
	private ArrayList<Request> requestList = new ArrayList<Request>();
	
	public void addView (View view) {
		this.view = view;
	}
	
	public void addToolbar(Toolbar tb) {
		this.toolbar = tb;
	}

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
	
}
