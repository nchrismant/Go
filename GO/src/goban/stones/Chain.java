/**
 * 
 */
package goban.stones;

import java.util.ArrayList;
import java.util.List;

import goban.map.Goban;
import goban.map.Intersection;
import goban.process.StonesManager;

/**
 * @author mathis dacruz
 *
 */
public class Chain {
	private StonesManager manager;
	private List<Stones> Chain = new ArrayList<Stones>();
	private int nbStones=1;
	
	public Chain(Stones stones, List<Stones> chain) {
		addChain(stones, chain);
	}
	
	public void addChain(Stones stones, List<Stones> chain) {
		Intersection left = new Intersection(stones.getPosition().getAbscisse()-1, stones.getPosition().getOrdonnee());
		Intersection right = new Intersection(stones.getPosition().getAbscisse()+1, stones.getPosition().getOrdonnee());
		Intersection up = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()-1);
		Intersection down = new Intersection(stones.getPosition().getAbscisse(), stones.getPosition().getOrdonnee()+1);
		Stones l = manager.searchStones(left);
		Stones r = manager.searchStones(right);
		Stones u = manager.searchStones(up);
		Stones d = manager.searchStones(down);
		if(!isExist(stones, chain)) {
			if(manager.isOccupied(left) && !manager.isEnemy(stones.getColor(), l.getColor())) {
				chain.add(stones);
				nbStones++;
				addChain(l, chain);
			}
			if(manager.isOccupied(right) && !manager.isEnemy(stones.getColor(), r.getColor())) {
				chain.add(stones);
				nbStones++;
				addChain(d, chain);
			}
			if(manager.isOccupied(up) && !manager.isEnemy(stones.getColor(), u.getColor())) {
				chain.add(stones);
				nbStones++;
				addChain(u, chain);
			}
			if(manager.isOccupied(down) && !manager.isEnemy(stones.getColor(), d.getColor())) {
				chain.add(stones);
				nbStones++;
				addChain(d, chain);
			}
		}
		
	}
	
	public boolean isExist(Stones stones, List<Stones> chain) {
		Stones result = null;
		for(Stones s : getChain()) {
			if (s.getPosition() == stones.getPosition()) {
				result = s;
			}
		}
		return result == stones;
	}

	/**
	 * @return the chain
	 */
	public List<Stones> getChain() {
		return Chain;
	}

	/**
	 * @param chain the chain to set
	 */
	public void setChain(List<Stones> chain) {
		Chain = chain;
	}

	/**
	 * @return the nbStones
	 */
	public int getNbStones() {
		return nbStones;
	}

	/**
	 * @param nbStones the nbStones to set
	 */
	public void setNbStones(int nbStones) {
		this.nbStones = nbStones;
	}
	
	
}
