
public class battleShip {
    
	public static void main(String[] args) {
        board Board = new board();
        ships Ships = new ships();
        shoot Shoots = new shoot();
        
        int attempts=0,
            shotHit=0;

        System.out.println();
        
        do{
            Board.showBoard(Board.board);
            Shoots.getshoot(Shoots.shoot);
            attempts++;
            
            if(hit(Shoots.shoot,Ships.ships)){
                hint(Shoots.shoot,Ships.ships,attempts);
                shotHit++;
            }                
            else
                hint(Shoots.shoot,Ships.ships,attempts);
            
            changeboard(Shoots.shoot,Ships.ships,Board.board);
            

        }while(shotHit!=9);
        
        System.out.println("\n\n\nBattleship Java game finished! You hit 3 ships in "+attempts+" attempts");
        Board.showBoard(Board.board);
    }
    
    public static boolean hit(int[] shoot, int[][] ships){
        
        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
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
