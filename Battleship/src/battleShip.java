import java.io.*;
import java.net.*;
import java.util.StringTokenizer;


public class battleShip {
	
	private static ServerSocket server = null;
	private final static ClientThread[] threads = new ClientThread[2];
	private static Socket player = null;
	
	static final String HTML_START = "<html>" + "<title>Battleship</title>" + "<body>";
	static final String HTML_END = "</body>" + "</html>";
    
	public static void main(String[] args) {
		int port = 8000;
		battleShip b = new battleShip();
		
		try {
			server = new ServerSocket(port, 10, InetAddress.getByName("127.0.0.1"));
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		
		for (int i = 0; i < 2; i++) {
			try {
				System.out.println("Waiting for Player "+(i+1)+" to connect...");
				player = server.accept();
				System.out.println("Player "+(i+1)+" just connected.");
				threads[i] = b.new ClientThread(player);
			} catch (IOException ioe) {
				System.err.println(ioe);
			}
		}
		
		//synchronized (b) {
			threads[0].start();
			threads[1].start();
		//}
		
        board Board1 = new board(), Board2 = new board();
        ships Ships1 = new ships(), Ships2 = new ships();
        shoot Shoots1 = new shoot(), Shoots2 = new shoot();
        
        int attempts1 = 0,
            shotHit1 = 0,
            attempts2 = 0,
            shotHit2 = 0,
            turn = 1;
        
        if(threads[0].is != null){
        	System.out.println("threads[0].is");
        }
        if(threads[1].is == null){
        	System.out.println("threads[1].is");
        }
        if(threads[0].os == null){
        	System.out.println("threads[0].os");
        }
        if(threads[1].os == null){
        	System.out.println("threads[1].os");
        }
        
        BufferedReader inFromP1 = new BufferedReader(new InputStreamReader(threads[0].is)),
        		inFromP2 = new BufferedReader(new InputStreamReader(threads[1].is));
        DataOutputStream outToP1 = new DataOutputStream(threads[0].os),
        		outToP2 = new DataOutputStream(threads[1].os);
        
        
        do{
        	if (turn % 2 == 1){
	            Board2.showBoard(Board2.board);
	            Shoots1.getshoot(Shoots1.shoot);
	            attempts1++;
	            
	            boolean wasHit = hit(Shoots1.shoot,Ships2.ships,Board2.board); 
	            if(wasHit){
	                shotHit1++;
	            } else {
	            	System.out.println("Missed...");
	            }
	            
	            changeboard(Shoots1.shoot,Board2.board, wasHit);
        	}
        	else {
        		System.out.println("Player2's turn");
        		Board1.showBoard(Board1.board);
	            Shoots2.getshoot(Shoots2.shoot);
	            attempts2++;
	            
	            boolean wasHit = hit(Shoots2.shoot,Ships1.ships,Board1.board); 
	            if(wasHit){
	                shotHit2++;
            	} else {
            		System.out.println("Missed...");
            	}
	            
	            changeboard(Shoots2.shoot,Board1.board, wasHit);
        	}
        	turn++;
        } while (shotHit1!=9 && shotHit2 != 9);
        
        if (shotHit1 == 9){
        	System.out.println("Player1 won with "+attempts1+" attempts!");
        	Board2.showBoard(Board2.board);
        }
        else if (shotHit2 == 9){
        	System.out.println("Player2 won with "+attempts2+" attempts!");
        	Board1.showBoard(Board1.board);
        }
    }
    
    public static boolean hit(int[] shoot, int[][] ships, int board[][]){
        
        for (int ship = 0; ship<ships.length; ship++){
            if(shoot[0] == ships[ship][0] && shoot[1] == ships[ship][1]){
            	if (board[shoot[0]][shoot[1]] == 1) {
            		System.out.println("You already shot a ship here...");
            		return false;
            	}
                System.out.printf("You hit a ship located in (%d,%d)\n",shoot[0]+1,shoot[1]+1);
                return true;
            }
        }
        return false;
    }
    
    public static String hitString(int[] shoot, int[][] ships, int board[][]){
        
        for (int ship = 0; ship<ships.length; ship++){
            if(shoot[0] == ships[ship][0] && shoot[1] == ships[ship][1]){
            	if (board[shoot[0]][shoot[1]] == 1) {
            		return "You already shot a ship here...";
            	}
                return "You hit a ship located in ("+(shoot[0]+1)+","+(shoot[1]+1)+")\n";
            }
        }
        return "You missed...";
    }

    public static void hint(int[] shoot, int[][] ships, int attempt){
        int row=0,
            column=0;
        
        for(int line=0 ; line < ships.length ; line++){
            if(ships[line][0]==shoot[0])
                row++;
            if(ships[line][1]==shoot[1])
                column++;
        }
        
        System.out.printf("\nHint %d: \nRow %d -> %d ships\n" +
                                 "Column %d -> %d ships\n",attempt,shoot[0]+1,row,shoot[1]+1,column);
    }

    public static void changeboard(int[] shoot, int[][] board, boolean wasHit){
        if(wasHit)
            board[shoot[0]][shoot[1]]=1;
        else
            board[shoot[0]][shoot[1]]=0;
    }
    
    public String generateResponse (BufferedReader b, int index, int turn,
    		board Board1, board Board2, shoot Shoots1, shoot Shoots2, ships Ships1, ships Ships2) {
    	try {
    		String request  = b.readLine();
	    	String header = request;
	    	String foo;
	    	
	    	StringTokenizer tok = new StringTokenizer(header);
	    	String httpMethod = tok.nextToken();
	    	String httpQuery = tok.nextToken();
	    	
	    	StringBuffer rb = new StringBuffer();
	    	if (index == 0) {
	    		if (turn % 2 == 1 && httpMethod.equals("GET")){
	    			String htmlpage = "<FORM action=\"http://127.0.0.1/\" method=\"post\">"+
					    "<P>"+
					    "<LABEL for=\"row\">Linha: </LABEL>"+
					              "<INPUT type=\"text\" id=\"row\"><BR>"+
					    "<LABEL for=\"column\">Coluna: </LABEL>"+
					              "<INPUT type=\"text\" id=\"column\"><BR>"+
					    "<INPUT type=\"submit\" value=\"Send\"> <INPUT type=\"reset\">"+
					    "</P>"+
					 "</FORM>";
	    			rb.append(htmlpage);
	    			while(b.ready()) {
	    				rb.append(request+"<BR>");
	    				request = b.readLine();
	    			}
	    		} else if (turn % 2 == 1 && httpMethod.equals("POST")) {
	    			String hit = hitString(Shoots1.shoot,Ships2.ships,Board2.board);
	    			rb.append("<P>"+hit+"</P>");
	    			
	    		}
	    	}
	    	return rb.toString();
    	} catch (IOException ioe) {
    		return ioe.getMessage();
    	}
    }
    
    public class ClientThread extends Thread {
    	public InputStream is = null;
    	public OutputStream os = null;
    	public Socket player = null;
    	
    	public ClientThread(Socket client) {
    		player = client;
    	}
    	
    	public void run() {
    		try {
    			is = player.getInputStream();
    			os = player.getOutputStream();
    		} catch (IOException ioe) {
    			System.err.println(ioe);
    		}
    	}
    }

    
}

