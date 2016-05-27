/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking.aimingstrategies;

import battleship.interfaces.Position;
import java.util.Random;
import y5.model.attacking.EnemyShips;
import y5.model.attacking.OurShot;
import y5.model.attacking.OurShots;

/**
 *
 * @author scheldejonas
 */
public class AimingModeFour implements AbstractAiming {
    
    private OurShot ourShot;
    private final int boardSizeX;
    private final int boardSizeY;
    private int aimShotCount;
    private EnemyShips currentModeEnemyShips;
    private boolean currentModeFirstShot;
    private boolean isEndOfMode;
    private int currentModeMisses;
    private boolean isNextShotGoingNorthOrEast;
    private Position currentModeTopPosition;
    private Position currentModeBottomPosition;
    private Position currentModeRightEndPosition;
    private Position currentModeLeftEndPosition;

    public AimingModeFour(int sizeX, int sizeY) {
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
        OurShot currentModeFirstOurShot = ourShots.getCurrentModeFirstShot();
        OurShot newOurShot = new OurShot(ourShotCurrent.getPosition(),ourShots.size());
        
        aimShotCount++;
        
        if (currentModeFirstShot) { //Checks for first Aim Shot
            aimShotCount = 1;
            currentModeMisses = 0;
            isEndOfMode = false;
            currentModeEnemyShips = enemyShips;
            isNextShotGoingNorthOrEast = true;
            currentModeTopPosition = null;
            currentModeBottomPosition = null;
            currentModeRightEndPosition = null;
            currentModeLeftEndPosition = null;
            newOurShot = new OurShot(currentModeFirstOurShot.getNorthPosition(),ourShots.size()); //In second shot, after the hunting hit, therefrom shoot North  = Strategy choice.
            currentModeTopPosition = newOurShot.getPosition();
            if (newOurShot.isNotPositioned()) { //Checks if it is top of the board, then we have to go south
                newOurShot = new OurShot(currentModeFirstOurShot.getSouthPosition(),ourShots.size());
                currentModeTopPosition = newOurShot.getPosition();
            }
        }
        
        boolean isCurrentModeSecondShotHit = ourShots.getCurrentModeSecondShot().isHit();

        int countOutOfBoundsEastAndWest = 0;
        int countOutOfBoundsNorthAndSouth = 0;
        boolean isThisShipMaybeVertical = false;
        boolean isThisShipMaybeHorizontal = false;
        boolean isFreePosition = false;
        
        do {
            
            if (!currentModeFirstShot && !isCurrentModeSecondShotHit) { //Checks if not first shot and that first shot was a miss = Then a new horizontal target is returned

                isFreePosition = false;
                isThisShipMaybeHorizontal = false;
                
                newOurShot = new OurShot(currentModeFirstOurShot.getPosition(),ourShots.size()); //This is the first hit shot from hunting mode change
                OurShot currentShot = new OurShot(currentModeFirstOurShot.getPosition(),ourShots.size());

                do { //Loop West and East positions, until it does not contain previous shots.

                    if (!ourShots.containsShotPosition(newOurShot.getPosition())) { //Checks if this new position is free and is north  direction
                        newOurShot = new OurShot(newOurShot.getPosition(), ourShots.size());  //in next shot, then shoot south = Strategy choice.
                        isFreePosition = true;
                    }

                    if (!isFreePosition) {
                        if (isNextShotGoingNorthOrEast) {
                            currentShot = newOurShot;
                            newOurShot = new OurShot(newOurShot.getEastPosition(),ourShots.size()); //This is the second shot, and south shot until free position
                            if (newOurShot.isNotPositioned() || ourShots.isPositionPreviousMiss(newOurShot)) { //Checks if it is bottom of the board, then we have to go north
                                currentModeRightEndPosition = currentShot.getPosition();
                                isNextShotGoingNorthOrEast = false;
                                countOutOfBoundsEastAndWest++;
                            }
                        }
                        else {
                            currentShot = newOurShot;
                            newOurShot = new OurShot(newOurShot.getWestPosition(),ourShots.size()); //This is third shot, and north shot until free position
                            if (newOurShot.isNotPositioned() || ourShots.isPositionPreviousMiss(newOurShot)) { //Checks if it is bottom of the board, then we have to go north
                                currentModeLeftEndPosition = currentShot.getPosition();
                                isNextShotGoingNorthOrEast = true;
                                countOutOfBoundsEastAndWest++;
                            }
                        }
                    }

                    if (countOutOfBoundsEastAndWest == 2) {
                        isThisShipMaybeHorizontal = true;
                        isCurrentModeSecondShotHit = true;
                        isNextShotGoingNorthOrEast = false;
                    }


                } while (!isFreePosition && !isThisShipMaybeHorizontal); //Loops while it is false that there is a free position and while 2 ends of the board is touched.
            }

            if (!currentModeFirstShot && isCurrentModeSecondShotHit) { //Checks if not first shot and that first shot was a hit = Then a new vertical target is returned

                isFreePosition = false;
                isThisShipMaybeVertical = false;
                newOurShot = new OurShot(currentModeFirstOurShot.getPosition(),ourShots.size()); //This is the first hit shot from hunting mode change
                OurShot currentShot = new OurShot(currentModeFirstOurShot.getPosition(),ourShots.size());

                do { //Loop West and East positions, until it does not contain previous shots.

                    if (!ourShots.containsShotPosition(newOurShot.getPosition())) { //Checks if this new position is free and is north  direction
                        newOurShot = new OurShot(newOurShot.getPosition(), ourShots.size());  //in next shot, then shoot south = Strategy choice.
                        isFreePosition = true;
                    }

                    if (!isFreePosition) {
                        if (isNextShotGoingNorthOrEast) {
                            currentShot = newOurShot;
                            newOurShot = new OurShot(newOurShot.getNorthPosition(),ourShots.size()); //This is third shot, and north shot until free position
                            if (newOurShot.isNotPositioned() || ourShots.isPositionPreviousMiss(newOurShot)) { //Checks if it is bottom of the board, then we have to go north
                                currentModeTopPosition = currentShot.getPosition();
                                isNextShotGoingNorthOrEast = false;
                                countOutOfBoundsNorthAndSouth++;
                            }
                        }
                        else {
                            currentShot = newOurShot;
                            newOurShot = new OurShot(newOurShot.getSouthPosition(),ourShots.size()); //This is the second shot, and south shot until free position
                            if (newOurShot.isNotPositioned() || ourShots.isPositionPreviousMiss(newOurShot)) { //Checks if it is bottom of the board, then we have to go north
                                currentModeBottomPosition = currentShot.getPosition();
                                isNextShotGoingNorthOrEast = true;
                                countOutOfBoundsNorthAndSouth++;
                            }
                        }
                    }

                    if (countOutOfBoundsNorthAndSouth == 2) {
                        isThisShipMaybeVertical = true;
                        isCurrentModeSecondShotHit = false;
                        isNextShotGoingNorthOrEast = false;
                    }


                } while (!isFreePosition && !isThisShipMaybeVertical); //Loops while it is false that there is a free position and while 2 ends of the board is touched. 
            }
            
            if (countOutOfBoundsEastAndWest == 2 && countOutOfBoundsNorthAndSouth == 2) {
                isEndOfMode = true;
            }
            
        } while (!isEndOfMode && !isFreePosition );

        currentModeFirstShot = false;
        
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

    @Override
    public boolean isEndOfMode() {
        return this.isEndOfMode;
    }

    @Override
    public boolean isTwoManyMisses() {
        if (currentModeMisses == 2) {
            return true;
        }
        return false;
    }

    @Override
    public Position getCurrentModeTopPosition() {
        return currentModeTopPosition;
    }

    @Override
    public Position getCurrentModeBottomPosition() {
        return currentModeBottomPosition;
    }

    @Override
    public Position getCurrentModeRightEndPosition() {
        return currentModeRightEndPosition;
    }

    @Override
    public Position getCurrentModeLeftEndPosition() {
        return currentModeLeftEndPosition;
    }

        
    
    
}
