package p25_0521909.dungeoncrawler.database;

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
public class DatabaseManagerTest {
    
    public DatabaseManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DatabaseManager.connectToDatabase();        
    }
    
    @After
    public void tearDown() {
    }

    /**
    * Test of checkTableExists method.
    * Test table name is "UNICORN", returns false because the table doesn't exists.
    */
    @Test
    public void testCheckFalseTableExists() {
        System.out.println("Check that an unexistent table doesn't exist");
        String tableName = "UNICORN";
        
        boolean expResult = false;
        boolean result = DatabaseManager.checkTableExists(tableName);
        
        assertEquals(expResult, result);
    }

    /**
    * Test of checkTableExists method.
    * Test table name is "ENEMY", returns true because the table exists.
    */
    @Test
    public void testCheckTrueTableExists() {
        System.out.println("Check that an existent table exists");
        String tableName = "ENEMY";
        
        boolean expResult = true;
        boolean result = DatabaseManager.checkTableExists(tableName);
        
        assertEquals(expResult, result);
    }
}
