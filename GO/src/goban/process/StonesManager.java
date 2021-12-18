/**
 * 
 */
package goban.process;
import goban.stones.*;
import gui.GameDisplay;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import config.GobanConfiguration;
import goban.map.Goban;
import goban.map.Intersection;



/**
 * @author mathis dacruz
 *
 */
public class StonesManager {
	private Goban goban;	
	private GameDisplay dashboard;
	private StonesManager manager;	
	private List<Stones> StonesIntersection = new ArrayList<Stones>();
	public static int interRed = 0;
	public static int interBlack = 0;
	public static int interWhite = 0;
	
	public StonesManager(Goban goban) {
		this.goban = goban;
	}
	
	

	public void putStones (Stones stones) {	
		StonesIntersection.add(stones);
	}
	
	public Stones searchStones (Intersection intersection) {
		Stones result = null;
		for(Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {
				result = stones;
			}
		}
		return result;
	}
	
	public boolean isExist (Intersection intersection) {
		Intersection result= new Intersection(0,0);
		for(Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {
				result = stones.getPosition();
			}
		}
		return result == intersection;
	}
	
	public boolean isExist (Stones stone, List<Stones> list) {
		Intersection result= new Intersection(0,0);
		for(Stones stones : list) {
			if (stones.getPosition().equals(stone.getPosition())) {
				result = stones.getPosition();
			}
		}
		return result == stone.getPosition();
	}
	
	public void remove (Stones stones) {
		StonesIntersection.remove(stones);
	}

	public List<Stones> getStonesIntersection() {
		return StonesIntersection;
	}

	public void setStonesIntersection(List<Stones> stonesIntersection) {
		StonesIntersection = stonesIntersection;
	}
	
	public void isCaptured() {
		List<Stones> eliminatedStones = new ArrayList<Stones>();
		for(Stones stones : getStonesIntersection()) {
			if (countLiberties(stones.getPosition())==0) {			
				eliminatedStones.add(stones);
			}
		}	
		for(Stones stones : eliminatedStones) {
			remove(stones);
		}		
	}
	
	public int countLiberties(Intersection intersection) {
		goban = GameBuilder.buildGoban();
		manager = GameBuilder.InitStones(goban);
		dashboard = new GameDisplay(goban, manager);
		int liberties=4;
		
		int x = intersection.getAbscisse();
		int y = intersection.getOrdonnee();
		
		int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
		int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
		
		int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
		int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
		
		if(!goban.isOnLeftBorder(intersection)) {
			Intersection left = dashboard.getStonePosition(xl, y);
			Stones l = searchStones(left);
			if((/*isExist(left) && */isOccupied(left)) /*&& isEnemy(turnColor(),l.getColor())*/) {
				liberties--;
			}
		}
		if(!goban.isOnRightBorder(intersection)) {
			Intersection right = dashboard.getStonePosition(xr, y);
			Stones r = searchStones(right); 
			if((/*isExist(right) && */isOccupied(right)) /*&& isEnemy(turnColor(),r.getColor())*/) {
				liberties--;
			}
		}
		if(!goban.isOnTopBorder(intersection)) {
			Intersection up = dashboard.getStonePosition(x, yu);
			Stones u = searchStones(up);
			if((/*isExist(up)&& */isOccupied(up)) /*&& isEnemy(turnColor(),u.getColor())*/) {
				liberties--;
			}
		}
		if(!goban.isOnBottomBorder(intersection)) {
			Intersection down = dashboard.getStonePosition(x, yd);
			Stones d = searchStones(down);
			if((/*isExist(down)&& */isOccupied(down)) /*&& isEnemy(turnColor(),d.getColor())*/) {
				liberties--;
			}
		}
	
		if(goban.isOnBottom(intersection)) {
			liberties = liberties-2;
		}
		else if(goban.isOnBorder(intersection)) {
			liberties--;
		}
		System.out.println("liberties = "+liberties);
		
		return liberties;
	}
	
	public boolean isOccupied(Intersection intersection) {
		Intersection find = new Intersection(0,0);
		for (Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {
				find = stones.getPosition();
			}
		}
		return find == intersection;
	}

	
	public boolean isEnemy(Color a, Color b) {
		return a != b;
	}
	
	public boolean isForbidden(Intersection intersection) {	
		goban = GameBuilder.buildGoban();
		manager = GameBuilder.InitStones(goban);
		dashboard = new GameDisplay(goban, manager);
		int liberties=4;
		
		int x = intersection.getAbscisse();
		int y = intersection.getOrdonnee();
		
		int xl = intersection.getAbscisse()-(GobanConfiguration.BLOCK_SIZE);
		int xr = intersection.getAbscisse()+(GobanConfiguration.BLOCK_SIZE);
		
		int yu = intersection.getOrdonnee()-(GobanConfiguration.BLOCK_SIZE);
		int yd = intersection.getOrdonnee()+(GobanConfiguration.BLOCK_SIZE);
		
		if(!goban.isOnLeftBorder(intersection)) {
			Intersection left = dashboard.getStonePosition(xl, y);
			Stones l = searchStones(left);
			if((/*isExist(left) && */isOccupied(left)) /*&& isEnemy(turnColor(),l.getColor())*/) {
				liberties--;
			}
		}
		if(!goban.isOnRightBorder(intersection)) {
			Intersection right = dashboard.getStonePosition(xr, y);
			Stones r = searchStones(right); 
			if((/*isExist(right) && */isOccupied(right)) /*&& isEnemy(turnColor(),r.getColor())*/) {
				liberties--;
			}
		}
		if(!goban.isOnTopBorder(intersection)) {
			Intersection up = dashboard.getStonePosition(x, yu);
			Stones u = searchStones(up);
			if((/*isExist(up)&& */isOccupied(up)) /*&& isEnemy(turnColor(),u.getColor())*/) {
				liberties--;
			}
		}
		if(!goban.isOnBottomBorder(intersection)) {
			Intersection down = dashboard.getStonePosition(x, yd);
			Stones d = searchStones(down);
			if((/*isExist(down)&& */isOccupied(down)) /*&& isEnemy(turnColor(),d.getColor())*/) {
				liberties--;
			}
		}
	
		if(goban.isOnBottom(intersection)) {
			liberties = liberties-2;
		}
		else if(goban.isOnBorder(intersection)) {
			liberties--;
		}
		System.out.println("liberties = "+liberties);
		
		return liberties == 0;
	}
	
	public String toString () {
		String result = "List=";
		for(Stones stones : getStonesIntersection()) {
				result += "["+stones.getPosition().toString()+"]";			
		}
		result += "\n";
		return result;
	}

	public Color colors(Stones stones) {
		if(GobanConfiguration.TURN != 0) {
			return stones.getColor();
		}
		else {
			return Color.BLACK;
		}
	}

	public Color turnColor() {
		Color color;
		if(GobanConfiguration.TURN%3==0) {
			color = Color.BLACK;
		}
		else if(GobanConfiguration.TURN%3==1) {
			color = Color.WHITE;
		}
		else {
			color = Color.RED;
		}
		return color;
	}
	
	public void CountIntersections (Intersection intersection) {		
		for (Stones stones : getStonesIntersection()) {
			if (stones.getPosition().equals(intersection)) {		
				if(colors(stones) == Color.BLACK) {
					interBlack++;
				}
				else if(colors(stones) == Color.WHITE) {
					interWhite++;
				}
				else {
					interRed++;
				}
			}
		}
	}
	
	public void MegaStonePower(Intersection intersection) {
		Intersection left = new Intersection(intersection.getAbscisse()-1, intersection.getOrdonnee());
		Intersection right = new Intersection(intersection.getAbscisse()+1, intersection.getOrdonnee());
		Intersection up = new Intersection(intersection.getAbscisse(), intersection.getOrdonnee()-1);
		Intersection down = new Intersection(intersection.getAbscisse(), intersection.getOrdonnee()+1);
		
		Stones l = searchStones(left);
		Stones r = searchStones(right);
		Stones u = searchStones(up);
		Stones d = searchStones(down);
		
		if(isOccupied(left) && isEnemy(turnColor(),l.getColor())) {
			remove(l);
		}
		if((isOccupied(right) && isEnemy(turnColor(),r.getColor()))) {
			remove(r);
		}
		if((isOccupied(up) && isEnemy(turnColor(),u.getColor()))) {
			remove(u);
		}
		if((isOccupied(down) && isEnemy(turnColor(),d.getColor()))) {
			remove(d);
		}
	}

}

	
