package p25_0521909.dungeoncrawler.constants;

import java.awt.Point;
import p25_0521909.dungeoncrawler.game.SpawnPoint;

/**
 *
 * @author ludmi
 */
public class Constants {
    
    private Constants(){}

    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 680;
    public static final int BORDER_MARGIN = 16;
    public static final String FRAME_TITLE = "Dungeon Crawler";
    public static final String BACKGROUND_IMAGE_PATH = "sprites/background.png";
    
    public static final int FRAME_UPDATE_RATE = 10;
    public static final int GAME_DURATION = 60;
    
    public static final int PLAYER_ATTACK_VALUE = 1;
    public static final int PLAYER_HEALTH_VALUE = 10;
    public static final double PLAYER_BASE_DEFENSE = 1.0;
    
    public static final int NUMBER_OF_ENEMIES = 5;

    public static final String ENEMY_TABLE_NAME = "ENEMY";
    public static final String ENEMY_SPAWN_TABLE_NAME = "ENEMY_PROPERTIES";
    public static final String ENEMY_STATS_TABLE_NAME = "ENEMY_STATS";
    public static final String ENEMY_SPRITE_TABLE_NAME = "ENEMY_SPRITE";
    
    public static final String ENEMY_NAME_COLUMN = "ENEMY_NAME";
    
    public static final String ENEMY_SPAWN_RATE_COLUMN = "SPAWN_RATE";
    public static final String ENEMY_MOVE_SPEED_COLUMN = "MOVE_SPEED";
    public static final String ENEMY_ATTACK_SPEED_COLUMN = "ATTACK_SPEED";
    public static final String ENEMY_ATTACKS_NUMBER_COLUMN = "ATTACKS_NUMBER";
    
    public static final String ENEMY_ATTACK_COLUMN = "ATTACK_VALUE";
    public static final String ENEMY_HEALTH_COLUMN = "HEALTH_VALUE";
    public static final String ENEMY_SPRITE_COLUMN = "SPRITE_PATH";
    
    public static int SPAWN_ATTEMPTS = 5;
    public static int SPAWN_POINT_COOLDOWN_DURATION = 5;
    
    public static final SpawnPoint SPAWN_POINT_1 = new SpawnPoint(160, 340);
    public static final SpawnPoint SPAWN_POINT_2 = new SpawnPoint(480, 360);
    public static final SpawnPoint SPAWN_POINT_3 = new SpawnPoint(800, 340);
    
    public static final Point TARGET_POINT_1 = new Point(280, 440);
    public static final Point TARGET_POINT_2 = new Point(480, 470);
    public static final Point TARGET_POINT_3 = new Point(680, 440);   
}
