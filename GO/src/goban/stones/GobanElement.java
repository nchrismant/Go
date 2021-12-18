package goban.stones;

/**
 * @author afatchawo junior
 * @version 1.0
 *
 */
public interface GobanElement {
	void accept (StonesVisitor visitor, int pres);
}
