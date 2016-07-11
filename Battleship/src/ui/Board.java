package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import engine.*;
import utils.*;

@SuppressWarnings("unused")
public class Board extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 8059622502494342669L;
	
	private Gameboard Board1, Board2;
	private Ships Ships1, Ships2;
    private Shoot Shoots1, Shoots2;
    private int attempts1, shotHit1, attempts2, shotHit2;
    private GameState state;
    private ImageIcon ii;
    
    private int x = 50, y = -30;
    private Timer timer;
    
    private Song menu, ingame;
    private Thread t1, t2;
    private boolean menuPlaying = false;
    private boolean ingamePlaying = false;
    
    private boolean soloPlay = false;
    private boolean twoPresses = false;
    
    
    public Board() {
		initBoard();
	}

	private void initBoard() {
		addKeyListener(new TAdapter());
		
		ii = new ImageIcon("resources/background.jpg");		
		int w = ii.getImage().getWidth(this);
		int h = ii.getImage().getHeight(this);
		setPreferredSize(new Dimension(w, h));
		setMaximumSize(new Dimension(w, h));
		setMinimumSize(new Dimension(w, h));
		
		
		Board1 = new Gameboard();
		Board2 = new Gameboard();
		Ships1 = new Ships();
		Ships2 = new Ships();
		Shoots1 = new Shoot();
		Shoots2 = new Shoot();
		attempts1 = 0;
		shotHit1 = 0;
		attempts2 = 0;
		shotHit2 = 0;
		state = GameState.MAIN_MENU;
		
		timer = new Timer(50, this);
		timer.start();
		

		menu = new Song("resources/main-menu.wav");
		ingame = new Song("resources/in-game.wav");
		t1 = new Thread(menu);
		t2 = new Thread(ingame);
	}
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	doDrawing(g);
    	
    	Toolkit.getDefaultToolkit().sync();
    }

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ii.getImage(), 0, 0, this);
		
		switch (state) {
		case MAIN_MENU:
			if (!menuPlaying){
				t1.start();
				menuPlaying = true;
			}
			g2d.setColor(Color.RED);
			Font f = new Font("Lucida", Font.PLAIN, 64);
			g2d.setFont(f);
			g2d.drawString("BATTLESHIP", x, y);
			if (y < 100)
				y += 1;
			if (y >= 100){
				f = new Font("Lucida", Font.PLAIN, 24);
				g2d.setFont(f);
				String solo = "Press S to play solo";
				String versus = "Press V to play versus";
				int widthSolo = g2d.getFontMetrics().stringWidth(solo);
				int widthVersus = g2d.getFontMetrics().stringWidth(versus);
				g2d.setColor(Color.BLACK);
				g2d.fillRect(800 - widthVersus, 500, widthVersus, 50);
				g2d.setColor(Color.RED);
				g2d.drawString(solo, 800 - widthSolo, 520);
				g2d.drawString(versus, 800 - widthVersus, 544);
			}
			break;

		default:
			break;
		}
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	switch (state) {
			case MAIN_MENU:
				if (e.getKeyCode() == KeyEvent.VK_S){
					soloPlay = true;
					setState(GameState.PLAYER_1_TURN);
					System.out.println("Game state changed");
				}
				else if (e.getKeyCode() == KeyEvent.VK_V){
					soloPlay = false;
					setState(GameState.PLAYER_1_TURN);
				}
				break;

			default:
				System.out.println("Game state haven't changed");
				break;
			}
        }
    }
}
