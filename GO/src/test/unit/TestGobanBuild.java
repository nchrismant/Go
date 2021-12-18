package test.unit;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import goban.map.Goban;
import goban.process.GameBuilder;
import goban.process.StonesManager;


/**
 * Unit test of goban building. 
 *  
 * @author afatchawo junior
 * @version 1.0
 */
public class TestGobanBuild {
	private Goban goban;
	private StonesManager manager;

	@Before
	public void prepareGoban() {
		this.goban = GameBuilder.buildGoban();
		this.manager = GameBuilder.InitStones(goban);
	}

	@Test
	public void testGoban() {
		assertNotNull(goban);
		assertNotNull(manager);
	}
}