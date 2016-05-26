/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking.aimingstrategies;

import battleship.interfaces.Position;
import y5.model.attacking.aimingstrategies.AbstractAiming;
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
    private boolean currentModeFirstShot;
    private boolean isEndOfBoard;

    public AimingModeTwo(int boardSizeX, int boardSizeY) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
    }
    
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
        
        OurShot newOurShot = null;
        
        if (ourShots.containsPosition(ourShotCurrent.getNorthPosition()) || ourShotCurrent.getNorthPosition().equals(null)) {
            newOurShot = new OurShot(ourShotCurrent.getSouthPosition(),ourShots.size());
            
        } else if (ourShots.containsPosition(ourShotCurrent.getSouthPosition()) || ourShotCurrent.getSouthPosition().equals(null)) {
            newOurShot = new OurShot(ourShotCurrent.getNorthPosition(),ourShots.size());
            
        } else if (ourShots.containsPosition(ourShotCurrent.getEastPosition()) || ourShotCurrent.getEastPosition().equals(null)) {
            newOurShot = new OurShot(ourShotCurrent.getWestPosition(),ourShots.size());
            
        } else if (ourShots.containsPosition(ourShotCurrent.getWestPosition()) || ourShotCurrent.getWestPosition().equals(null)) {
            newOurShot = new OurShot(ourShotCurrent.getEastPosition(),ourShots.size());
            
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
    public boolean isCurrentModeFirstShot() {
        return currentModeFirstShot;
    }

    @Override
    public void setCurrentModeFirstShot(boolean isCurrentModeFirstShot) {
        currentModeFirstShot = isCurrentModeFirstShot;
    }

    @Override
    public boolean isEndOfMode() {
        return this.isEndOfBoard;
    }

    @Override
    public boolean isTwoManyMisses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position getCurrentModeTopPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position getCurrentModeBottomPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position getCurrentModeRightEndPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Position getCurrentModeLeftEndPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
