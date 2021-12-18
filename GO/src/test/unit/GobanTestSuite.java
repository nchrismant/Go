package test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is the global test suite of unit tests.
 * 
 * It includes two test cases : {@link TestGobanBuild} and {@link TestAlgoCalcul}.
 * 
 * @author afatchawo junior
 * @version 1.0
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({TestGobanBuild.class, TestAlgoCalcul.class})

public class GobanTestSuite {

}
