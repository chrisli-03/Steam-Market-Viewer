import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Model {
	private View view;
	private ArrayList<Request> requestList = new ArrayList<Request>();
	private ArrayList<Result> resultList = new ArrayList<Result>();
	
	public void addView (View view) {
		this.view = view;
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
	
	public void setResultList(ArrayList<Result> resultList) {
		this.resultList = resultList;
	}
	
	public String getItemNameFromView() {
		return view.getItemName();
	}
	
	public String getGameNameFromView() {
		return view.getGameName();
	}
	
	public ArrayList<Request> getRequestList() {
		return requestList;
	}
	
	public ArrayList<Result> getResultList() {
		return resultList;
	}
	
	public void deleteRequestAtN(int n) {
		requestList.remove(n);
		view.updateView();
	}
	
	public void generatePrice() throws IOException {
		BufferedReader reader = null;
		Result res = new Result();
      	resultList = new ArrayList<Result>();
		for (Request req : requestList) {
			try {
		      	String webUrl = req.getUrl();
		      	URL url = new URL(webUrl);
		      	reader = new BufferedReader(new InputStreamReader(url.openStream()));
		      	StringBuffer buffer = new StringBuffer();
		      	int read;
		      	char[] chars = new char[512];
		      	while ((read = reader.read(chars)) != -1)
		      		buffer.append(chars, 0, read);
		      	String result = new String(chars);
		      	String[] processedString = result.split(",");
		      	for (int i = 0; i < processedString.length; i++ ) {
		      		String[] line = processedString[i].split(":");
	      			switch (line[0]) {
	      				case "{\"success\"":
	      					res.setItemName(req.getItemName());
		      				if (line[1].equals("true")) res.setFound(true);
		      				else res.setFound(false);
	      					break;
	      				case "\"lowest_price\"":
	      					res.setPrice(line[1].replace("\"", ""));
	      					break;
	      				case "\"volume\"":
	      					res.setVolumn(line[1].replace("\"",""));
	      					break;
	      				case "\"median_price\"":
	      					res.setMedian(line[1].replace("\"", "").replace("}", "").substring(0, 7));
	      					break;
	      				default:
	      					res.setVolumn(res.getVolumn()+line[0].replace("\"", ""));
	      			}	
		      	}
			} catch (IOException e) {
				res.setItemName(req.getItemName());
				res.setFound(false);
			} finally {
	              if (reader != null)
	                  reader.close();
			      resultList.add(res);
	        }
		}
		view.updateResultView();
	}
}
