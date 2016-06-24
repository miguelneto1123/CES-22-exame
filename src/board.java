
public class board {
	
	int [][] board = new int[7][7];
	
	public board(){
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
	
}
