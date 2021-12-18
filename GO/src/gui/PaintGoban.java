/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;

import config.GobanConfiguration;
import goban.map.Goban;
import goban.map.Intersection;
import goban.stones.Stones;


/**
 * @author afatc
 *
 */
public class PaintGoban {
	
	/*Creation of the background of the goban using fillRect method*/
	public void paint(Goban goban , Graphics graphics) {
		Color customColor = new Color(222,184,135);
		int blockSize = GobanConfiguration.BLOCK_SIZE;
		int hoshiSize = GobanConfiguration.HOSHI_SIZE;
		Intersection[][] intersections = goban.getIntersections();
		Intersection intersection = intersections[0][0];
		graphics.setColor(customColor);
		graphics.fillRect(intersection.getAbscisse()-GobanConfiguration.BLOCK_SIZE, intersection.getOrdonnee()-GobanConfiguration.BLOCK_SIZE, blockSize*(GobanConfiguration.ABSCISSE_COUNT+1), blockSize*(GobanConfiguration.ORDONNEE_COUNT+1));
	
		/*Creation of the horizontal lines for the goban using drawLine method*/
		for (int abscisseIndex = 0 , ordonneeIndex = 0 ; ordonneeIndex < goban.getOrdonneeCount(); ordonneeIndex++) {
			Intersection Intersection = intersections[abscisseIndex][ordonneeIndex];
			Intersection IntersectionEnd = intersections[goban.getAbscisseCount()-1][ordonneeIndex];
			graphics.setColor(Color.BLACK);
			graphics.drawLine(Intersection.getAbscisse(), Intersection.getOrdonnee(), IntersectionEnd.getAbscisse(), IntersectionEnd.getOrdonnee());
		}

		/*Creation of the vertical lines for the goban using drawLine method*/
		for (int abscisseIndex = 0 , ordonneeIndex = 0 ; abscisseIndex < goban.getAbscisseCount(); abscisseIndex++) {
			Intersection Intersection = intersections[abscisseIndex][ordonneeIndex];
			Intersection IntersectionEnd = intersections[abscisseIndex][goban.getOrdonneeCount()-1];
			graphics.setColor(Color.BLACK);
			graphics.drawLine(Intersection.getAbscisse(), Intersection.getOrdonnee(), IntersectionEnd.getAbscisse(), IntersectionEnd.getOrdonnee());
		}
		/*Creation of the hoshis using fillOval method*/
		for (int abscisseIndex = 1; abscisseIndex < (goban.getAbscisseCount()); abscisseIndex++) {
			for (int ordonneeIndex = 1; ordonneeIndex < (goban.getOrdonneeCount()); ordonneeIndex++) {
				if (abscisseIndex == 3 && ordonneeIndex == 3 || abscisseIndex == 13 && ordonneeIndex == 13 || abscisseIndex == 3 && ordonneeIndex == 13 || abscisseIndex == 13 && ordonneeIndex == 3)  {			
					Intersection Intersection = intersections[abscisseIndex][ordonneeIndex];
					graphics.setColor(Color.BLACK);
					graphics.fillOval((Intersection.getAbscisse())-(hoshiSize/2), Intersection.getOrdonnee()-(hoshiSize/2), hoshiSize, hoshiSize);	
				}
			}
		}
	}
	
	/*Paint of the stones in the intersections place*/
	public void paint(Graphics g, Stones stones) {
		int size = GobanConfiguration.BLOCK_SIZE;
		Intersection position = stones.getPosition();
		
		int x = position.getAbscisse();
		int y = position.getOrdonnee();
	
		g.fillOval(x-(size/2),y-(size/2), size, size);
	}
}

