import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel; 

public class SteamMarketViewer {
	private static JFrame frame;
	private static JPanel panel;
	
	public static String convertIntoURLName(){
		System.out.println("Please enter the item name:");		
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String [] splitedString=input.split(" ");
		String result= splitedString[0];
		for(int i =1;i<splitedString.length;i++){
			result+="%20";
			result+=splitedString[i];
		}
		scan.close();
		return result;		
	}
    public static void main(final String[] args) throws IOException {
    	frame = new JFrame("Steam Market Viewer");
    	Model model = new Model();
    	Controller controller = new Controller(model);
    	View view = new View(model, controller);
    	Toolbar toolbar = new Toolbar(model, controller);
    	
    	model.addView(view);
    	model.addToolbar(toolbar);
    	
		frame.setSize(new Dimension(800,600));
		frame.setMinimumSize(new Dimension(320, 400));
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.addComponentListener(controller);
		
		panel = new JPanel(new BorderLayout());
		panel.add(toolbar);
		frame.add(panel);
		frame.setVisible(true);
    	
    	/*
        BufferedReader reader = null;
        try {
        	String setName=convertIntoURLName();
        	Request newRequest = new Request(setName, "DOTA2");
            URL url = new URL(newRequest.getUrl());
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            System.out.println(buffer.toString());
        } finally {
            if (reader != null)
                reader.close();
        }*/
    }
}