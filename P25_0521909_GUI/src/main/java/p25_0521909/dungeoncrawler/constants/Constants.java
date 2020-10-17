package p25_0521909.dungeoncrawler.constants;

import java.awt.Point;
import p25_0521909.dungeoncrawler.game.SpawnPoint;

/**
 *
 * @author ludmi
 */
public class Constants {

    
    
    private Constants(){}

    public static final int FRAME_UPDATE_RATE = 10;
    public static final int GAME_DURATION = 60;
    
    public static final long ENEMY_SPAWN_RATE = 3;
    public static final int ENEMY_MOVE_SPEED = 1;
    public static int SPAWN_ATTEMPTS = 5;
    public static int SPAWN_POINT_COOLDOWN_DURATION = 3;
    
    public static final SpawnPoint SPAWN_POINT_1 = new SpawnPoint(270, 320);
    public static final SpawnPoint SPAWN_POINT_2 = new SpawnPoint(470, 330);
    public static final SpawnPoint SPAWN_POINT_3 = new SpawnPoint(670, 320);
    
    public static final Point TARGET_POINT_1 = new Point(270, 340);
    public static final Point TARGET_POINT_2 = new Point(470, 370);
    public static final Point TARGET_POINT_3 = new Point(670, 340);   
}
