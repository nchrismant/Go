/**
 * 
 */
package goban.map;

import config.GobanConfiguration;
/**
 * @author afatchawo junior
 * @version 1.0
 * This class allows to define all the intersections of the goban
 */
public class Goban {
	private Intersection[][] intersections;
	private int distance = (GobanConfiguration.BLOCK_SIZE * (GobanConfiguration.ABSCISSE_COUNT-1)) + GobanConfiguration.Abscisse_Start;
	private int abscisseCount;
	private int ordonneeCount;
	private int AbscisseStart = 100;
	private int OrdonneeStart = 100;
	
	/*Creation of the table to put stones on the intersections of the goban*/
	public Goban(int abscisseCount , int ordonneeCount) {
		this.abscisseCount = abscisseCount;
		this.ordonneeCount = ordonneeCount;
		
		intersections = new Intersection[abscisseCount][ordonneeCount];
		
		for(int abscisseIndex = 0; abscisseIndex < abscisseCount; abscisseIndex++) {
			for (int ordonneeIndex = 0; ordonneeIndex < ordonneeCount; ordonneeIndex++) {
				intersections[abscisseIndex][ordonneeIndex] = new Intersection(AbscisseStart,OrdonneeStart);	
				OrdonneeStart += GobanConfiguration.BLOCK_SIZE;
			}
			OrdonneeStart = 100;
			AbscisseStart += GobanConfiguration.BLOCK_SIZE;
		}
	}

	public Intersection[][] getIntersections() {
		return intersections;
	}

	public int getAbscisseCount() {
		return abscisseCount;
	}

	public int getOrdonneeCount() {
		return ordonneeCount;
	}
	
	public Intersection getIntersection(int abscisse , int ordonnee) {
		return intersections[abscisse][ordonnee];
	}
	
	/**
	 * Verify that the stone position is on the left top of the goban
	 * @param position
	 * @return boolean
	 */
	public boolean isOnLeftTop(Intersection position) {
        return position.getAbscisse() == GobanConfiguration.Abscisse_Start && position.getOrdonnee() == GobanConfiguration.Ordonnee_Start;
    }
	/**
	 * Verify that the stone position is on the right top of the goban
	 * @param position
	 * @return boolean
	 */
    public boolean isOnRightTop(Intersection position) {
        int abscisse = position.getAbscisse();
        int ordonnee = position.getOrdonnee();
        return abscisse == distance  && ordonnee == GobanConfiguration.Ordonnee_Start;
    }
    /**
     * Verify that the stone position is on the left bottom of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnLeftBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == GobanConfiguration.Abscisse_Start &&  y == distance; 
    }
    /**
     * Verify that the stone position is on the right bottom of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnRightBottom(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y == distance;
    }
    /**
     * Verify that the stone position is on the top border of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnTopBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GobanConfiguration.Abscisse_Start <= x && x <= distance  && y == GobanConfiguration.Ordonnee_Start;
    }
    /**
     * Verify that the stone position is on the bottom border of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnBottomBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GobanConfiguration.Abscisse_Start <= x && x <= distance  && y == distance;
    }
    /**
     * Verify that the stone position is on the left border of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnLeftBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return GobanConfiguration.Abscisse_Start == x  && y >= GobanConfiguration.Ordonnee_Start && y <= distance;
    }
    /**
     * Verify that the stone position is on the right border of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnRightBorder(Intersection position) {
        int x = position.getAbscisse();
        int y = position.getOrdonnee();
        return x == distance && y >= GobanConfiguration.Ordonnee_Start && y <= distance;
    }
    /**
     * Verify that the stone position is on the border of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnBorder(Intersection position) {
		return isOnTopBorder(position) || isOnBottomBorder(position) || isOnLeftBorder(position) || isOnRightBorder(position);
	}
    /**
     * Verify that the stone position is on the bottom of the goban
     * @param position
     * @return boolean
     */
    public boolean isOnBottom(Intersection position) {
		return isOnRightTop(position) || isOnLeftTop(position) || isOnLeftBottom(position) || isOnRightBottom(position);
	}
}