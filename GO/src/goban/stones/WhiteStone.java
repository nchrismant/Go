/**
 * 
 */
package goban.stones;

import java.awt.Color;

import goban.map.Intersection;


/**
 * Class of the whitestones 
 * @author afatchawo junior
 * @author dacruz mathis
 * @author chriqui nathan
 * @version 1.0
 *
 */

/*Creation of the WhiteStone class with white color*/
public class WhiteStone extends Stones{
	/**
	 * Stone color 
	 */
	private Color color = Color.WHITE;
	/**
	 * 
	 * @param position
	 * 	the position of the stone
	 */
	public WhiteStone(Intersection position) {
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
