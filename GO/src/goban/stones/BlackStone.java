/**
 * 
 */
package goban.stones;

import java.awt.Color;

import goban.map.Intersection;

/**
 * Class of the blackstones 
 * @author afatchawo junior
 * @author dacruz mathis
 * @author chriqui nathan
 * @version 1.0
 *
 */

/*Creation of the BlackStone class with black color*/
public class BlackStone extends Stones{
	/**
	 * Stone color
	 */
	private Color color = Color.BLACK;
	/**
	 * Constructor Blackstone
	 * @param position
	 * 	the position of the blackstone
	 */
	public BlackStone(Intersection position) {
		super(position);
		this.setColor(color);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
