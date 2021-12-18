package test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import goban.map.Goban;
import goban.map.Intersection;
import goban.process.GameBuilder;
import goban.process.StonesManager;
import gui.GameDisplay;

/**
 * This test case tests the methods for capture a stone or chain of stones.
 * 
 * @author afatchawo junior
 * @version 1.0
 */

public class TestAlgoCalcul {
	private Goban goban;
	private StonesManager manager;
	private GameDisplay dashboard;

	@Before
	public void prepare() {
		this.goban = GameBuilder.buildGoban();
		this.manager = GameBuilder.InitStones(goban);
		this.dashboard = new GameDisplay(goban,manager);
	}
	@Test
	public void testStonePosition() {
		Intersection result = dashboard.getStonePosition(278, 156);
		int abscisse = result.getAbscisse();
		assertEquals(250,abscisse);
		int ordonnee = result.getOrdonnee();
		assertEquals(130,ordonnee);		
	}
	@Test
	public void testEnemy() {
		assertTrue(manager.isEnemy(Color.black, Color.red));
	}
	@Test
	public void testLibertiesNumber() {
		Intersection intersection = new Intersection(220,310);
		assertEquals(4,manager.countLiberties(intersection));
	}
	@Test
	public void testIsExist() {
		Intersection intersection = new Intersection(130,300);
		assertFalse(manager.isExist(intersection));
	}
}
