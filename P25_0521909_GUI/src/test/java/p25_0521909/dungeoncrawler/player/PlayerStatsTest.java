package p25_0521909.dungeoncrawler.player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import p25_0521909.dungeoncrawler.enemy.EnemyStats;
import p25_0521909.dungeoncrawler.interfaces.Combatable;

/**
 *
 * @author ludmi
 */
public class PlayerStatsTest {
    PlayerStats instance;
    
    public PlayerStatsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of attack method, of class PlayerStats.
     */
    @Test
    public void testAttack() {
        System.out.println("attack");
        
        Combatable combatable = new EnemyStats(1, 3);
        instance = new PlayerStats();
        instance.attack(combatable);
        
        int expResult = 2;
        int result = combatable.getCurrentHealth();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of takeDamage method, of class PlayerStats.
     */
    @Test
    public void testTakeDamage() {
        System.out.println("takeDamage");
        
        int damageAmount = 2;
        instance = new PlayerStats();
        instance.takeDamage(damageAmount);
        
        int expResult = 8;
        int result = instance.getCurrentHealth();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of isDead method, of class PlayerStats.
     */
    @Test
    public void testIsDead() {
        System.out.println("isDead");
        
        instance = new PlayerStats();
        instance.takeDamage(instance.getCurrentHealth());
        
        boolean expResult = true;
        boolean result = instance.isDead();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentHealth method, of class PlayerStats.
     */
    @Test
    public void testGetCurrentHealth() {
        System.out.println("getCurrentHealth");
        
        instance = new PlayerStats();
        
        int expResult = 10;
        int result = instance.getCurrentHealth();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of regenHealth method, of class PlayerStats.
     */
    @Test
    public void testRegenHealth() {
        System.out.println("regenHealth");
        
        int regenAmount = 2;
        instance = new PlayerStats();
        instance.takeDamage(regenAmount);
        instance.regenHealth(regenAmount);
        
        int expResult = 10;
        int result = instance.getCurrentHealth();
        
        assertEquals(expResult, result);
    }
}
