/**
 * 
 */
package goban.process;

import config.GobanConfiguration;
import goban.map.Goban;

/**
 * Design pattern builder to "build" the goban and initialize stonesmanager
 * @author afatchawo junior
 * @version 2.0
 *
 */

public class GameBuilder {
	
	public static Goban buildGoban() {
		return new Goban(GobanConfiguration.ABSCISSE_COUNT, GobanConfiguration.ORDONNEE_COUNT);
	}
	
	public static StonesManager InitStones(Goban goban) {
		StonesManager manager = new StonesManager(goban);
		return manager;
	}
}
