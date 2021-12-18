/**
 * 
 */
package goban.map;

/**
 * This class define the intersection of a stone 
 * @author afatchawo junior
 * @author dacruz mathis
 * @author chriqui nathan
 * @version 1.0
 *
 */

/*Creation of the Intersection class*/
public class Intersection {
	
	private int abscisse;
	private int ordonnee;
	
	/**
	 * Constructor Intersection
	 * @param abscisse 
	 * @param ordonnee
	 */
	public Intersection(int abscisse, int ordonnee) {
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}

	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonnee() {
		return ordonnee;
	}

	public void setOrdonnee(int ordonnee) {
		this.ordonnee = ordonnee;
	}
		
	public String toString() {
		return "Intersection [abscisse=" + abscisse + ", ordonnee=" + ordonnee + "]";
	}
}
