package p25_0521909.dungeoncrawler.enemy;

/**
 *
 * @author ludmi
 */
public class EnemyProperties {
    
    private EnemyProperties(){}
    
    static double ENEMY_MOVE_SPEED;
    static double ENEMY_ATTACK_SPEED;
    static int ENEMY_ATTACKS_NUMBER;
    
    public static void setEnemyProperties(double moveSpeed, double attackSpeed, int attacksNumber){
        ENEMY_MOVE_SPEED = moveSpeed;
        ENEMY_ATTACK_SPEED = attackSpeed;
        ENEMY_ATTACKS_NUMBER = attacksNumber;
    }
}
