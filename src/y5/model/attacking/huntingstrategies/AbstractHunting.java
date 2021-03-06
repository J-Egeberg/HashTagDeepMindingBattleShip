/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking.huntingstrategies;

import battleship.interfaces.Position;
import y5.model.attacking.EnemyShips;
import y5.model.attacking.OurShot;
import y5.model.attacking.OurShots;

/**
 *
 * @author scheldejonas
 */
public interface AbstractHunting {
    
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotPrevious, EnemyShips enemyShips);
    
    public int getHuntLevel();
    
    public int getBoardSizeX();
    public int getBoardSizeY();
    
}
