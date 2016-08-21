
public class Model {
	private View view;
	Toolbar toolbar;
	
	public void addView (View view) {
		this.view = view;
	}
	
	public void addToolbar(Toolbar tb) {
		this.toolbar = tb;
	}
}
