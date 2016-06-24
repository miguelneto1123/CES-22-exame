
public class battleShip {
    
	public static void main(String[] args) {
        board Board1 = new board(), Board2 = new board();
        ships Ships1 = new ships(), Ships2 = new ships();
        shoot Shoots1 = new shoot(), Shoots2 = new shoot();
        
        int attempts1 = 0,
            shotHit1 = 0,
            attempts2 = 0,
            shotHit2 = 0,
            turn = 1;

        System.out.println();
        
        do{
        	if (turn % 2 == 1){
        		System.out.println("Player1's turn");
	            Board2.showBoard(Board2.board);
	            Shoots1.getshoot(Shoots1.shoot);
	            attempts1++;
	            
	            if(hit(Shoots1.shoot,Ships2.ships)){
	                shotHit1++;
	            } else {
	            	System.out.println("Missed...");
	            }
	            
	            changeboard(Shoots1.shoot,Ships2.ships,Board2.board);
        	}
        	else {
        		System.out.println("Player2's turn");
        		Board1.showBoard(Board1.board);
	            Shoots2.getshoot(Shoots2.shoot);
	            attempts2++;
	            
	            if(hit(Shoots2.shoot,Ships1.ships)){
	                shotHit2++;
            	} else {
            		System.out.println("Missed...");
            	}
	            
	            changeboard(Shoots2.shoot,Ships1.ships,Board1.board);
        	}
        	turn++;
        }while(shotHit1!=9 && shotHit2 != 9);
        
        if (shotHit1 == 9){
        	System.out.println("Player1 won with "+attempts1+" attempts!");
        	Board2.showBoard(Board2.board);
        }
        else if (shotHit2 == 9){
        	System.out.println("Player2 won with "+attempts2+" attempts!");
        	Board1.showBoard(Board1.board);
        }
    }
    
    public static boolean hit(int[] shoot, int[][] ships){
        
        for (int ship = 0; ship<ships.length; ship++){
            if(shoot[0] == ships[ship][0] && shoot[1] == ships[ship][1]){
                System.out.printf("You hit a ship located in (%d,%d)\n",shoot[0]+1,shoot[1]+1);
                return true;
            }
        }
        return false;
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

    public static void changeboard(int[] shoot, int[][] ships, int[][] board){
        if(hit(shoot,ships))
            board[shoot[0]][shoot[1]]=1;
        else
            board[shoot[0]][shoot[1]]=0;
    }
    
    
}
