/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.aimingstrategies;

import battleship.interfaces.Position;
import y5.model.attacking.EnemyShips;
import y5.model.attacking.OurShot;
import y5.model.attacking.OurShots;

/**
 *
 * @author scheldejonas
 */
public class AimingModeOne implements AbstractAiming {
    
    private OurShot ourShot;
    private int boardSizeX;
    private int boardSizeY;
    private int aimShotCount;
    private OurShots ourShotsSinceLastNextTarget;
    private EnemyShips enemyShipsSinceLastNextTarget;

    public AimingModeOne(int sizeX, int sizeY) {
        this.boardSizeX = sizeX;
        this.boardSizeY = sizeY;
    }

    @Override
    public int getAimLevel() {
        return 1;
    }

    @Override
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotCurrent, EnemyShips enemyShips) {
        //Newer version of Aiming Mode One
        OurShot newShot = null;
        if (ourShotsSinceLastNextTarget.size() + 1 < ourShots.size()) { //Checks for first Aim Shot
            aimShotCount = 1;
            newShot = new OurShot(ourShotCurrent.getNorthPosition(),ourShots.size()); //Because first shot, shoot north
            if (newShot.isNotPositioned()) {
                newShot = new OurShot(ourShotCurrent.getSouthPosition(),ourShots.size()); //Because it is the top of the board
            }
        }       
            
        aimShotCount++;
        ourShotsSinceLastNextTarget = ourShots;
        enemyShipsSinceLastNextTarget = enemyShips;
        return newShot;
    }
    
    
//    /**
//     * This returns the next target,calculated by the systematic aiming, with enemyShip knowledge gathered
//     * @param ourShots
//     * @param ourShotCurrent
//     * @param enemyShips
//     * @return OurSHot
//     * @since 1.0.0
//     */
//    @Override
//    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotCurrent, EnemyShips enemyShips) {
//        Newer version of Aiming Mode One
//        OurShot newOurShot;
//        if (isFirstHit) //If hit and first Aim Shot, go north
//        
//        Old version of Aiming Mode One
//        if (ourShots.contains(previousShot.getNorthPosition()) || previousShot.getNorthPosition().equals(null)) {
//            ourShotCurrent.setPosition(previousShot.getSouthPosition());
//        } else if (ourShots.contains(previousShot.getSouthPosition()) || previousShot.getSouthPosition().equals(null)) {
//            ourShotCurrent.setPosition(previousShot.getNorthPosition());
//        } else if (ourShots.contains(previousShot.getEastPosition()) || previousShot.getEastPosition().equals(null)) {
//            ourShotCurrent.setPosition(previousShot.getWestPosition());
//        } else if (ourShots.contains(previousShot.getWestPosition()) || previousShot.getWestPosition().equals(null)) {
//            ourShotCurrent.setPosition(previousShot.getEastPosition());
//        }
//            
//            
//        return newOurShot;
//    }

    @Override
    public int getBoardSizeX() {
        return this.boardSizeX;
    }

    @Override
    public int getBoardSizeY() {
        return this.boardSizeY;
    }


    
}
