/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking.aimingstrategies;

import battleship.interfaces.Position;
import y5.model.attacking.EnemyShip;
import y5.model.attacking.EnemyShips;
import y5.model.attacking.OurShot;
import y5.model.attacking.OurShots;

/**
 *
 * @author scheldejonas
 */
public interface AbstractAiming {
    
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotPrevious, EnemyShips enemyShips);
    public int getAimLevel();
    public int getBoardSizeX();
    public int getBoardSizeY();
    public boolean isCurrentModeFirstShot();
    public void setCurrentModeFirstShot(boolean isCurrentModeFirstShot);
    public boolean isEndOfMode();
    public boolean isTwoManyMisses();
    
}
