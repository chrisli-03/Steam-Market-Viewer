import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane; 

public class SteamMarketViewer {
	private static JFrame frame;
	private static JPanel panel;
	
    public static void main(final String[] args) throws IOException {
    	frame = new JFrame("Steam Market Viewer");
    	Model model = new Model();
    	Controller controller = new Controller(model);
    	View view = new View(model, controller);
    	
    	model.addView(view);
    	
		frame.setSize(new Dimension(800,600));
		frame.setMinimumSize(new Dimension(320, 400));
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponentListener(controller);
		
		panel = new JPanel(new BorderLayout());                                                          
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Main", view);
		tabbedPane.addTab("Setting", panel);
		frame.add(tabbedPane);
		frame.setVisible(true);
    }
}