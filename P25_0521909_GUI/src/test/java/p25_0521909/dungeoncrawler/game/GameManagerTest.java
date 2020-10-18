package p25_0521909.dungeoncrawler.game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ludmi
 */
public class GameManagerTest {
    GameManager gm;
    
    public GameManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        gm = GameManager.getInstance();
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of clone method.
     * CloneNotSupportedException is thrown when GameManager instance is cloned.
     */
    @Test (expected = CloneNotSupportedException.class)
    public void testClone() throws CloneNotSupportedException {
        System.out.println("clone");
        
        gm.clone();
    }


    /**
     * Test of getInstance method.
     * , of class GameManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("Check that get instance retrieves a single instance of the Game Manager class.");
        
        GameManager expResult = gm;
        GameManager result = GameManager.getInstance();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeLeft method, of class GameManager.
     */
    @Test
    public void testGetTimeLeft() {
        System.out.println("getTimeLeft");

        String expResult = "60";
        String result = gm.getTimeLeft();
        
        assertEquals(expResult, result);
    }
}
