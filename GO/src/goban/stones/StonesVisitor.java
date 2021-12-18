/**
 * 
 */
package goban.stones;


/**
 * @author afatchawo junior
 * @version 1.0
 *
 */
public interface StonesVisitor {
	
	void visit(WhiteStone whitestone);
	
	void visit(BlackStone blackstone);
	
	void visit(RedStone redstone);

}
