import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner; 

public class Testing {


	enum Games {
		DOTA2(570);
		
		private int value;
		private Games(int value) {
			this.value = value;
		}
		
		public String getValue() {
			return Integer.toString(value);
		}
	};
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
		System.out.print(result);
		scan.close();
		return result;		
	}
    public static void main(final String[] args) throws IOException {
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
        }
    }
}