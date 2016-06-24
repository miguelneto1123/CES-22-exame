import static org.junit.Assert.*;

import org.junit.Test;

public class hitTest {

	@Test
	public void test() {
		ships Ships = new ships();
		shoot Shoot = new shoot();
		
		boolean teste = Shoot.hit(Ships.ships[1], Ships.ships);
		
		assertEquals(true, teste);
	}
}
