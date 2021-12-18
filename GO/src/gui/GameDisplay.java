/**
 * 
 */
package gui;


import java.awt.Graphics;


import javax.swing.JPanel;

import config.GobanConfiguration;
import goban.map.Goban;
import goban.map.Intersection;
import goban.process.StonesManager;
import goban.stones.Stones;

/**
 * @author afatchawo junior
 * @version 1.0
 *
 */
public class GameDisplay extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Goban goban;
	private StonesManager stonesmanager;
	private PaintGoban paintGoban = new PaintGoban();
	
	public GameDisplay(Goban goban , StonesManager stonesmanager) {
		this.goban = goban;
		this.stonesmanager = stonesmanager;
	}
	
	public GameDisplay(Goban goban) {
		this.goban = goban;
	}

	/*Used for paint the stones*/
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		paintGoban.paint(goban, g);
		for (Stones stones : stonesmanager.getStonesIntersection()) {
			g.setColor(stones.getColor());
			paintGoban.paint(g, stones);
		}
	}
	
	public Intersection getStonePosition(int x , int y) {
		int abscisse = (x-100)/ GobanConfiguration.BLOCK_SIZE;
		int ordonnee = (y-100)/ GobanConfiguration.BLOCK_SIZE;
		return goban.getIntersection(abscisse, ordonnee);
	}
}
