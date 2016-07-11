package ui;

/*import java.awt.event.*;
import java.awt.*;*/
import javax.swing.JFrame;

public class Application extends JFrame{
	
	private static final long serialVersionUID = -3750115173880688072L;
	private Board b;
	
	public Application() {
		initUI();
	}

	private void initUI() {
		b = new Board();
		
		add(b);
		pack();
		setTitle("Battleship");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public Board getBoard(){
		return b;
	}
	
	public static void main(String args[]){
		Application a = new Application();
		a.setVisible(true);
	}

}
