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
public class HuntingModeTwo implements AbstractHunting {

    private int boardSizeX;
    private int boardSizeY;
    private Random rnd;

    public HuntingModeTwo(int sizeX, int sizeY) {
        this.boardSizeX = sizeX;
        this.boardSizeY = sizeY;
        rnd = new Random();
    }

    @Override
    public int getHuntLevel() {
        return 2;
    }

    @Override
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotCurrent, EnemyShips enemyShips) {
        int x = 0;
        int y = 0;
        boolean isNotNewRandomPosition;
        do { // Loops old shots, and finds new shot if previous
            isNotNewRandomPosition = false;
            x = rnd.nextInt(boardSizeX); //HuntingMode 1 - Random, except previous shots
            y = rnd.nextInt(boardSizeY); //HuntingMode 1 - Tandom, except previous shots
            if (ourShots.size() != 0) {
                for (OurShot ourShot : ourShots.getOurShots()) {
                    if (ourShot.getPosition().x == x) {
                        if (ourShot.getPosition().y == y) {
                            isNotNewRandomPosition = true;
                        }
                    }
                    if (ourShot.getPosition().y == y) {
                        if (ourShot.getPosition().x == x) {
                            isNotNewRandomPosition = true;
                        }
                    }
                }
            }
        } while (isNotNewRandomPosition); //Checks it is a new position

        return new OurShot(x, y, ourShots.size()); //HuntingMode 1
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
