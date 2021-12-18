/**
 * 
 */
package goban.stones;

import java.awt.Color;

import config.GobanConfiguration;
import goban.map.Intersection;


/**
 * Class of the blackstones 
 * @author afatchawo junior
 * @author dacruz mathis
 * @author chriqui nathan
 * @version 1.0
 *
 */

/*Abstract class used for manipulate stones position on the goban*/
public abstract class Stones {
	private Intersection position;
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Stones(Intersection position) {
		this.position = position;
	}
	
	public Intersection getPosition() {
		return position;
	}
	
	public void setPosition(Intersection position) {
		this.position = position;
	}
}
