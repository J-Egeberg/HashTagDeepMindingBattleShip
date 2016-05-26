/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking.huntingstrategies;

import java.util.Random;
import y5.model.attacking.EnemyShips;
import y5.model.attacking.OurShot;
import y5.model.attacking.OurShots;

/**
 *
 * @author scheldejonas
 */
public class HuntingModeTwo implements AbstractHunting {

    private int boardSizeX;
    private int boardSizeY;
    private Random rnd;
    private int round;
    private int roundDoubleIncreaser;
    private int roundDoubleDecreaser;

    public HuntingModeTwo(int boardSizeX, int boardSizeY) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        rnd = new Random();
        round = 0;
        roundDoubleIncreaser = 0;
        roundDoubleDecreaser = 9;
    }

    @Override
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotPrevious, EnemyShips enemyShips) {
        
        int x = 0;
        int y = 0;
        boolean isNotNewPosition;
        
        do { // Loops old shots, and finds new shot if previous
            isNotNewPosition = false;
            
            if (x < 9) {
                x += 2;
            }
            else if (x == 9) {
                y += 1;
                x = 0;
            }
            else if (y == 9) {
                x = rnd.nextInt(boardSizeX); //HuntingMode 1 - Random, except previous shots
                y = rnd.nextInt(boardSizeY); //HuntingMode 1 - Tandom, except previous shots
            }
            if (ourShots.size() != 0) {
                for (OurShot ourShot : ourShots.getOurShots()) {
                    if (ourShot.getPosition().x == x) {
                        if (ourShot.getPosition().y == y) {
                            isNotNewPosition = true;
                        }
                    }
                    if (ourShot.getPosition().y == y) {
                        if (ourShot.getPosition().x == x) {
                            isNotNewPosition = true;
                        }
                    }
                }
            }
            
        } while (isNotNewPosition); //Checks it is a new position

        round++;
        
        return new OurShot(x, y, ourShots.size()); //HuntingMode 1
    }

    @Override
    public int getHuntLevel() {
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
    
}
