/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking.huntingstrategies;

import y5.model.attacking.huntingstrategies.AbstractHunting;
import battleship.interfaces.Position;
import java.util.Random;
import y5.model.attacking.EnemyShips;
import y5.model.attacking.OurShot;
import y5.model.attacking.OurShots;

/**
 *
 * @author scheldejonas
 */
public class HuntingModeFour implements AbstractHunting {

    private int boardSizeX;
    private int boardSizeY;
    private Random rnd;
    private OurShots notFiredShots;

    public HuntingModeFour(int sizeX, int sizeY) {
        
        this.boardSizeX = sizeX;
        this.boardSizeY = sizeY;
        rnd = new Random();
        
        notFiredShots = new OurShots();
        int shotNumber = 1;
        
        for (int y = 0; y < boardSizeY; y++) { //Runs from bottom to top of board
            for (int x = 0; x < boardSizeX; x++) { // Runs from left to right
                notFiredShots.addOurShot(new OurShot(x,y,shotNumber)); //Add's a empty position as a new shot to a notFired ArrayList of our shots
                shotNumber++; //Incementer for OurShot creation
            }
        }
        
    }

    @Override
    public int getHuntLevel() {
        return 4;
    }

    @Override
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotCurrent, EnemyShips enemyShips) {
        
        for (OurShot ourShot : ourShots.getOurShots()) {
            if (notFiredShots.containsShotPosition(ourShot.getPosition())) { //Check's if shot has been fired in other mode's and removes it.
                notFiredShots.removeShot(ourShot); // Removes Shot from not used shots
            }
        }

        return new OurShot(notFiredShots.getOurShot(rnd.nextInt(notFiredShots.size())).getPosition(), ourShots.size()); //HuntingMode 1
    }

    @Override
    public int getBoardSizeX() {
        return this.boardSizeX;
    }

    @Override
    public int getBoardSizeY() {
        return this.boardSizeY;
    }

}
