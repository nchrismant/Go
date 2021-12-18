/**
 * 
 */
package goban.stones;

import java.awt.Color;

import goban.map.Intersection;

/**
 * Class of the redstones 
 * @author afatchawo junior
 * @author dacruz mathis
 * @author chriqui nathan
 * @version 1.0
 *
 */

/*Creation of the RedStone class with red color*/
public class RedStone extends Stones{
	/**
	 * Stone color
	 */
	private Color color = Color.RED;
	/**
	 * 
	 * @param position
	 * 	the position of the stone
	 */
	public RedStone(Intersection position) {
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
