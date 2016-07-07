package engine;
import java.util.Scanner;

public class shoot {
	
	public int[] shoot = new int[2];
	
	@SuppressWarnings("resource")
	public void getshoot(int[] shoot){
        Scanner input = new Scanner(System.in);
        
        do{
        	System.out.print("Linha: ");
        	shoot[0] = input.nextInt();
        	if(shoot[0] < 1 || shoot[0] > 7) {
        		System.out.println("Digite um n�mero de 1 a 7");
        	}
        	shoot[0]--;}while(shoot[0] < 0 || shoot[0] > 6);
        
        do{
        	System.out.print("Coluna: ");
        	shoot[1] = input.nextInt();
        	if(shoot[1] < 1 || shoot[1] > 7) {
        		System.out.println("Digite um n�mero de 1 a 7");
        	}
        	shoot[1]--;}while(shoot[1] < 0 || shoot[1] > 6);
        
    }
	
	public boolean hit(int[] shoot, int[][] ships){
        
        for(int ship=0 ; ship<ships.length ; ship++){
            if( shoot[0]==ships[ship][0] && shoot[1]==ships[ship][1]){
                System.out.printf("You hit a ship located in (%d,%d)\n",shoot[0]+1,shoot[1]+1);
                return true;
            }
        }
        return false;
    }	

}
