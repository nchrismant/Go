/**
 * 
 */
package goban.stones;

import config.GobanConfiguration;
import goban.map.Intersection;

/**
 * @author afatchawo jjunior
 * @version 1.0
 *
 */
public class GobanElementFactory {
	
	public static Stones createStones(Intersection position) {
		if(GobanConfiguration.TURN%3 == 0) {
			return new BlackStone(position);
		}
		else if (GobanConfiguration.TURN%3 == 1) {
			return new WhiteStone(position);
		}
		else {
			return new RedStone(position);
		}
	}

}
