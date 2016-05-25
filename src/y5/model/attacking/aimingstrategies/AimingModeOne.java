/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking.aimingstrategies;

import y5.model.attacking.aimingstrategies.AbstractAiming;
import battleship.interfaces.Position;
import java.util.ArrayList;
import java.util.Random;
import y5.model.attacking.EnemyShip;
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
    private OurShots currentModeShots;
    private EnemyShips currentModeEnemyShips;
    private boolean currentModeFirstShot;

    public AimingModeOne(int sizeX, int sizeY) {
        this.boardSizeX = sizeX;
        this.boardSizeY = sizeY;
    }

    @Override
    public int getAimLevel() {
        return 1;
    }

    /**
     * This returns the north target every time after a hit, if hit, then shoots vertical until wreck, if not shot horizontal until wreck.
     * @param ourShots
     * @param ourShotCurrent
     * @param enemyShips
     * @return OurShot
     */
    @Override
    public OurShot getNextTarget(OurShots ourShots, OurShot ourShotCurrent, EnemyShips enemyShips) {
        
        int x = 0;
        int y = 0;
        Random rnd = new Random();
        OurShot newOurShot = null;
        boolean isNextShotGoingNorthOrEast;
        
        aimShotCount++;
        
        if (currentModeFirstShot) { //Checks for first Aim Shot
            aimShotCount = 1;
            currentModeFirstShot = false;
            currentModeShots = new OurShots();
            currentModeEnemyShips = enemyShips;
            newOurShot = new OurShot(ourShotCurrent.getNorthPosition(),ourShots.size()); //In second shot, after the hunting hit, therefrom shoot North  = Strategy choice.
            if (newOurShot.isNotPositioned()) { //Checks if it is top of the board, then we have to go south
                newOurShot = new OurShot(ourShotCurrent.getSouthPosition(),ourShots.size()); 
            }
        }
        if ((aimShotCount & 1) == 0) { //Checks if the currentAimingMode is a even or odd number
            isNextShotGoingNorthOrEast = false; //Then next (third aiming) shot is sent to south or west direction of first hit shot = Strategy choice
        }
        else {
            isNextShotGoingNorthOrEast = true; //The next (second aiming) shot is sent to north or east direction of first hit shot = Strategy choice
        }
        if (!currentModeFirstShot && ourShots.getCurrentModeFirstShot().isHit()) { //Checks if not first shot and that first shot was a hit = Then a new vertical target is returned
            
            boolean isFreePosition = false;
            int endOfBoardHit = 0;
            do { //Loop North and south positions, until it does not contain previous shots.

                if (!ourShots.containsPosition(newOurShot.getPosition()) && isNextShotGoingNorthOrEast) { //Checks if this new position is free and is north  direction
                    newOurShot = new OurShot(ourShotCurrent.getNorthPosition(), ourShots.size());  //in next shot, then shoot south = Strategy choice.
                    isFreePosition = true;
                    if (newOurShot.isNotPositioned()) { //Checks if it is bottom of the board, then we have to go north
                        newOurShot = new OurShot(ourShotCurrent.getSouthPosition(),ourShots.size());
                        isNextShotGoingNorthOrEast = false;
                        isFreePosition = true;
                        endOfBoardHit++;
                    }
                }

                if (!ourShots.containsPosition(newOurShot.getPosition()) && !isNextShotGoingNorthOrEast) { //Checks if this new position is free and is south direction
                    newOurShot = new OurShot(ourShotCurrent.getSouthPosition(), ourShots.size()); //in next shot, then shoot north = Stategy choice.
                    isFreePosition = true;
                    if (newOurShot.isNotPositioned()) { //Checks if it is top of the board, then we have to go south
                        newOurShot = new OurShot(ourShotCurrent.getNorthPosition(),ourShots.size());
                        isNextShotGoingNorthOrEast = true;
                        isFreePosition = true;
                        endOfBoardHit++;
                    }
                }

                if (!isFreePosition) {
                    if (isNextShotGoingNorthOrEast) {
                        newOurShot = new OurShot(newOurShot.getNorthPosition(),ourShots.size()); //This is third shot, and north shot until free position
                    }
                    else {
                        newOurShot = new OurShot(newOurShot.getSouthPosition(),ourShots.size()); //This is the second shot, and south shot until free position
                    }
                }

            } while (!isFreePosition || endOfBoardHit != 2); //Loops while it is false that there is a free position or while 2 ends of the board is touched.
            
        }
        
        if (!currentModeFirstShot && !ourShots.getCurrentModeFirstShot().isHit()) { //Checks if not first shot and that first shot was a miss = Then a new horizontal target is returned

            boolean isFreePosition = false;
            int endOfBoardHit = 0;
            do { //Loop North and south positions, until it does not contain previous shots.

                if (!ourShots.containsPosition(newOurShot.getPosition()) && isNextShotGoingNorthOrEast) { //Checks if this new position is free and is north  direction
                    newOurShot = new OurShot(ourShotCurrent.getEastPosition(), ourShots.size());  //in next shot, then shoot south = Strategy choice.
                    isFreePosition = true;
                    if (newOurShot.isNotPositioned()) { //Checks if it is bottom of the board, then we have to go north
                        newOurShot = new OurShot(ourShotCurrent.getWestPosition(),ourShots.size());
                        isNextShotGoingNorthOrEast = false;
                        isFreePosition = true;
                        endOfBoardHit++;
                    }
                }

                if (!ourShots.containsPosition(newOurShot.getPosition()) && !isNextShotGoingNorthOrEast) { //Checks if this new position is free and is south direction
                    newOurShot = new OurShot(ourShotCurrent.getWestPosition(), ourShots.size()); //in next shot, then shoot north = Stategy choice.
                    isFreePosition = true;
                    if (newOurShot.isNotPositioned()) { //Checks if it is top of the board, then we have to go south
                        newOurShot = new OurShot(ourShotCurrent.getEastPosition(),ourShots.size());
                        isNextShotGoingNorthOrEast = true;
                        isFreePosition = true;
                        endOfBoardHit++;
                    }
                }

                if (!isFreePosition) {
                    if (isNextShotGoingNorthOrEast) {
                        newOurShot = new OurShot(newOurShot.getNorthPosition(),ourShots.size()); //This is third shot, and north shot until free position
                    }
                    else {
                        newOurShot = new OurShot(newOurShot.getSouthPosition(),ourShots.size()); //This is the second shot, and south shot until free position
                    }
                }

            } while (!isFreePosition || endOfBoardHit != 2); //Loops while it is false that there is a free position or while 2 ends of the board is touched.
            
        }   
            
        currentModeShots.addOurShot(newOurShot); //Add's the shot to our currentModeShots, for check's later.
        
        return newOurShot;
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
        this.currentModeFirstShot = isCurrentModeFirstShot;
    }

    

    
}
