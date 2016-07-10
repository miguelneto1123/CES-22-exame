package ui;

import javax.swing.*;
import engine.*;
import utils.*;

@SuppressWarnings("unused")
public class Board extends JPanel{
	
	private static final long serialVersionUID = 8059622502494342669L;
	
	private Gameboard Board1, Board2;
	private Ships Ships1, Ships2;
    private Shoot Shoots1, Shoots2;
    private int attempts1, shotHit1, attempts2, shotHit2;
    private GameState state;
    private ImageIcon ii;
    
    public Board() {
		initBoard();
	}

	private void initBoard() {
		ii = new ImageIcon("background.jpg");
		
		
		this.Board1 = new Gameboard();
		this.Board2 = new Gameboard();
		this.Ships1 = new Ships();
		this.Ships2 = new Ships();
		this.Shoots1 = new Shoot();
		this.Shoots2 = new Shoot();
		this.attempts1 = 0;
		this.shotHit1 = 0;
		this.attempts2 = 0;
		this.shotHit2 = 0;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}
    
    
}
