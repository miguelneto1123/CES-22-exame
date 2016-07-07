package engine;
import java.util.Random;

public class ships {
	
	public int[][] ships = new int[9][2];
	
	public ships(){
        Random random = new Random();
        
        for (int ship = 0; ship < 9; ship++){
            ships[ship][0] = random.nextInt(7);
            ships[ship][1] = random.nextInt(7);
            
            for (int last=0; last < ship; last++){
                if ( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) )
                    do {
                        ships[ship][0] = random.nextInt(7);
                        ships[ship][1] = random.nextInt(7);
                    } while ( (ships[ship][0] == ships[last][0])&&(ships[ship][1] == ships[last][1]) );
            }
            
        }
    }
}
