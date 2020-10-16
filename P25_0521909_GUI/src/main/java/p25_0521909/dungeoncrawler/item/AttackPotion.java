/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p25_0521909.dungeoncrawler.item;

/**
 *
 * @author ludmi
 */
public class AttackPotion extends ExpirableItem{

    public AttackPotion(String name, String description, int effectDuration) {
        super(name, description, effectDuration);
    }

    @Override
    public void consume() {
        //increase attack damage for x seconds
    }
    
}
