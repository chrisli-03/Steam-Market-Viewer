import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
		/*TODO: add duplicate check*/
		try {
			Request newRequest = new Request(itemName, gameName);
			if (newRequest.getGameId().equals("")) return;
			requestList.add(newRequest);
			view.updateView();
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null,"Unknown game");
		}
	}
	
	public String getItemNameFromView() {
		return view.getItemName();
	}
	
	public String getGameNameFromView() {
		return view.getGameName();
	}
	
}
