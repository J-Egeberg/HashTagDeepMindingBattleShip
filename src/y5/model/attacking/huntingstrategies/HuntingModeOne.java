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
public class HuntingModeOne implements AbstractHunting {

    private int boardSizeX;
    private int boardSizeY;
    private Random rnd;
    private int x;
    private int y;

    public HuntingModeOne(int boardSizeX, int boardSizeY) {
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
        this.rnd = new Random();
    }

    @Override
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotPrevious, EnemyShips enemyShips) {
        x = rnd.nextInt(boardSizeX); // Random returns a number between 0 and the size of the board
        y = rnd.nextInt(boardSizeY); // Random returns a number between 0 and the size of the board
        return new OurShot(x,y,ourShots.size());
    }

    @Override
    public int getHuntLevel() {
        return 1;
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
