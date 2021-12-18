/**
 * 
 */
package goban.stones;

import java.util.ArrayList;
import java.util.List;

import goban.map.Intersection;
import goban.process.StonesManager;

/**
 * @author mathis
 *
 */
public class Liberties {
	private StonesManager manager;
	private Stones stones;
	private Chain chain;
	
	public Liberties(Stones stones) {
		this.stones = stones;
	}
	
	public int countLiberties(Stones stones) {
		Intersection left = new Intersection(stones.getPosition().getAbscisse()-1, stones.getPosition().getOrdonnee());
		Intersection right = new Intersection(stones.getPosition().getAbscisse()+1, stones.getPosition().getOrdonnee());
		Intersection up = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()-1);
		Intersection down = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()+1);
		int liberties=4;
		if(manager.isOccupied(left) || left.getAbscisse()<0) {
			liberties--;
		}
		if(manager.isOccupied(right) || right.getAbscisse()>16) {
			liberties--;
		}
		if(manager.isOccupied(up) || up.getOrdonnee()<0) {
			liberties--;
		}
		if(manager.isOccupied(down) || down.getOrdonnee()>16) {
			liberties--;
		}
		return liberties;
	}
	
	public int countLiberties(List<Stones> Chain) {
		int totalLiberties=0;
		for(Stones stones : chain.getChain()) {
			totalLiberties += countLiberties(stones);
		}
		return totalLiberties;
	}
}
