package p25_0521909.dungeoncrawler.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import p25_0521909.dungeoncrawler.constants.PanelName;
import p25_0521909.dungeoncrawler.game.GameManager;

/**
 *
 * @author ludmi
 */
public class GameFrameTest {
    GameFrame gf;
    GameManager gm;
    
    public GameFrameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        gf = GameFrame.getInstance();
        gm = GameManager.getInstance();
        gm.initialise();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createLayout method, of class GameFrame.
     */
    @Test
    public void testCreateLayout() {
        System.out.println("createLayout");
        
        gf.createLayout();
        PanelName expResult = PanelName.START;
        PanelName result = gf.getActivePanelName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of switchGamePanels method, of class GameFrame.
     */
    @Test
    public void testSwitchGamePanels() {
        System.out.println("switchGamePanels");
        
        gf.switchGamePanels(PanelName.LOSS);
        
        PanelName expResult = PanelName.LOSS;
        PanelName result = gf.getActivePanelName();

        assertEquals(expResult, result);
    }

    /**
     * Test of getActivePanelName method, of class GameFrame.
     */
    @Test
    public void testGetActivePanelName() {
        System.out.println("getActivePanelName");
        
        gf.switchGamePanels(PanelName.LOSS);
        
        PanelName expResult = PanelName.LOSS;
        PanelName result = gf.getActivePanelName();

        assertEquals(expResult, result);
    }   
}
