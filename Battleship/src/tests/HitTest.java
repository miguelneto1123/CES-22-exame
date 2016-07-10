package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import engine.Ships;
import engine.Shoot;

public class HitTest {

	@Test
	public void test() {
		Ships Ships = new Ships();
		Shoot Shoot = new Shoot();
		
		boolean teste = Shoot.hit(Ships.ships[1], Ships.ships);
		
		assertEquals(true, teste);
	}
}
