/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.aimingstrategies;

import y5.model.attacking.EnemyShips;
import y5.model.attacking.OurShot;
import y5.model.attacking.OurShots;

/**
 *
 * @author scheldejonas
 */
public class AimingModeTwo implements AbstractAiming {
    
    private int boardSizeX;
    private int boardSizeY;
    
    /**
     * This returns the next target,calculated by the systematic aiming, with enemyShip knowledge gathered
     * @param ourShots
     * @param ourShotCurrent
     * @param enemyShips
     * @return OurSHot
     * @since 1.0.0
     */
    @Override
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotCurrent, EnemyShips enemyShips) {
        
        OurShot newOurShot = new OurShot();
        
        if (ourShots.contains(ourShotCurrent.getNorthPosition()) || ourShotCurrent.getNorthPosition().equals(null)) {
            ourShotCurrent.setPosition(ourShotCurrent.getSouthPosition());
            
        } else if (ourShots.contains(ourShotCurrent.getSouthPosition()) || ourShotCurrent.getSouthPosition().equals(null)) {
            ourShotCurrent.setPosition(ourShotCurrent.getNorthPosition());
            
        } else if (ourShots.contains(ourShotCurrent.getEastPosition()) || ourShotCurrent.getEastPosition().equals(null)) {
            ourShotCurrent.setPosition(ourShotCurrent.getWestPosition());
            
        } else if (ourShots.contains(ourShotCurrent.getWestPosition()) || ourShotCurrent.getWestPosition().equals(null)) {
            ourShotCurrent.setPosition(ourShotCurrent.getEastPosition());
            
        }
            
        return newOurShot;
    }

    @Override
    public int getAimLevel() {
        return 2;
    }

    @Override
    public int getBoardSizeX() {
        return this.boardSizeX;
    }

    @Override
    public int getBoardSizeY() {
        return this.boardSizeY;
    }

    @Override
    public int getCurrentVerticalHitAimingShots() {
        throw new UnsupportedOperationException("Not supported in this aiming level"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCurrentHorizontalHitAimingShots() {
        throw new UnsupportedOperationException("Not supported in this aiming level"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
