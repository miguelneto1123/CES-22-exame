package engine;

public class Gameboard {
	
	public int [][] board = new int[7][7];
	
	public Gameboard(){
        for(int row=0 ; row < 7 ; row++ )
            for(int column=0 ; column < 7 ; column++ )
                board[row][column]=-1;
    }
    
    public void showBoard(int[][] board){
        System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7");
        System.out.println();
        
        for(int row=0 ; row < 7 ; row++ ){
            System.out.print((row+1)+"");
            for(int column=0 ; column < 7 ; column++ ){
                if(board[row][column]==-1){
                    System.out.print("\t"+"~");
                }else if(board[row][column]==0){
                    System.out.print("\t"+"*");
                }else if(board[row][column]==1){
                    System.out.print("\t"+"X");
                }
                
            }
            System.out.println();
        }

    }
    
    public String boardString (int[][] board) {
    	String res = "";
    	
    	res = res + "\t1 \t2 \t3 \t4 \t5 \t6 \t7\n\n";
        
        for(int row = 0; row < 7; row++){
        	res = res + (row+1) + "\n";
            for (int column = 0; column < 7; column++) {
            	
                if (board[row][column] == -1) {
                	res = res + "\t~\n";
                } else if (board[row][column] == 0) {
                	res = res + "\t*\n";
                } else if (board[row][column] == 1) {
                	res = res + "\tX\n";
                }
                
            }
        }
        
        return res;
    }
    
    public String unveiledBoardString(int[][] board, int [][] ships){
    	String res = "";
    	res = res + "\t1 \t2 \t3 \t4 \t5 \t6 \t7\n\n";
    	
    	for (int row = 0; row < 7; row++){
            res = res + (row+1) + "\n";
            
            for (int column = 0; column < 7; column++) {
            	boolean hasShip = false;
            	for (int s = 0; s < ships.length && !hasShip; s++) {
            		if (ships[s][0] == row && ships[s][1] == column) {
            			res = res + "\tX\n";
            			hasShip = true;
            		}
            	}
            	if (!hasShip){
	                if (board[row][column] == -1) {
	                    res = res + "\t~\n";
	                } else if (board[row][column] == 0) {
	                    res = res + "\t*\n";
	                }
            	}
            }
        }
    	return res;
    }
	
}
