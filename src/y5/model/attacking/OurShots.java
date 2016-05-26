/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking;

import battleship.interfaces.Position;
import y5.model.attacking.OurShot;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author scheldejonas
 */
public class OurShots {
    
    private ArrayList<OurShot> ourShots;
    
    public OurShots(ArrayList<OurShot> ourShots) {
        this.ourShots = ourShots;
    }

    public OurShots() {
        this.ourShots = new ArrayList();
    }
    
    public ArrayList<OurShot> getOurShots() {
        return ourShots;
    }
    
    public void setOurShots(ArrayList<OurShot> ourShots) {
        this.ourShots = ourShots;
    }
    
    @Override
    public String toString() {
        return "OurShots{" + "ourShots=" + ourShots.toString() + '}';
    }
    
    public int getMissesCount() {
        int missedCount = 0;
        for (OurShot ourShot : ourShots) {
            if (!ourShot.isHit()) {
                missedCount++;
            }
        }
        return missedCount;
    }
    
    public int getHitsCount() {
        int hitsCount = 0;
        for (OurShot ourShot : ourShots) {
            if (ourShot.isHit()) {
                hitsCount++;
            }
        }
        return hitsCount;
    }
    
    public int size() {
        return ourShots.size();
    }

    public void addOurShot(OurShot newOurShot) {
        this.ourShots.add(newOurShot);
    }

    public boolean isLastShotVertical() {
        int lastShotX = ourShots.get(ourShots.size()-1).getPosition().x;
        int secondLastShotX = ourShots.get(ourShots.size()-2).getPosition().x;
        if (lastShotX == secondLastShotX) {
            return true;
        }
        return false;
    }

    public boolean isLastShotHorizontal() {
        int lastShotY = ourShots.get(ourShots.size()-1).getPosition().y;
        int secondLastShotY = ourShots.get(ourShots.size()-2).getPosition().y;
        if (lastShotY == secondLastShotY) {
            return true;
        }
        return false;
    }

    public int getCurrentVerticalHitShots(int currentRoundAimingShotsCount) {
        int verticalHitShotsCount = 0;

        for (int i = ourShots.size()-1; i >= ourShots.size()-currentRoundAimingShotsCount; i--) { //Rolls through our shots backwards, and finds the rows, where the last aiming shots were fired
            if (ourShots.get(i).isHit() && ourShots.get(ourShots.size()-1).getPosition().x == ourShots.get(i).getPosition().x) { //Check's if our shot was a fired hit, and is same row as last fired shot
                verticalHitShotsCount++; //increment a shot fired vertical for the last shots
            }
        }
        
        return verticalHitShotsCount;
    }

    public int getCurrentHorizontalHitShots(int currentRoundAimingShotsCount) {
        int horizontalHitShotsCount = 0;

        for (int i = ourShots.size()-1; i >= ourShots.size()-currentRoundAimingShotsCount; i--) { //Rolls through our shots backwards, and finds the rows, where the last aiming shots were fired
            if (ourShots.get(i).isHit() && ourShots.get(ourShots.size()-1).getPosition().x == ourShots.get(i).getPosition().x) { //Check's if our shot was a fired hit, and is same row as last fired shot
                horizontalHitShotsCount++;
            }
        }
        
        return horizontalHitShotsCount;
    }

    public boolean containsPosition(Position position) {
        boolean isContaingPosition = false;
        for (OurShot ourShot : ourShots) {
            if (ourShot.getPosition().x == position.x) {
                if (ourShot.getPosition().y == position.y) {
                    return true;
                }
            }
            if (ourShot.getPosition().y == position.y) {
                if (ourShot.getPosition().x == position.x) {
                    return true;
                }
            }
        }
        return isContaingPosition;
    }

    public OurShot getCurrentModeSecondShot() {
        boolean isFirstModeShot = true;
        for (int i = ourShots.size()-1; i >= 0; i--) {
            if (ourShots.get(i).isModeChange()) {
                if (isFirstModeShot) {
                    return ourShots.get(i);
                }
                else {
                    return ourShots.get(i+1);
                }
            }
            isFirstModeShot = false;
        }
        return ourShots.get(0);
    }

    public OurShot getCurrentModeFirstShot() {
        for (int i = ourShots.size()-1; i >= 0; i--) {
            if (ourShots.get(i).isModeChange()) {
                return ourShots.get(i);
            }
        }
        return ourShots.get(0);
    }

    public boolean isPositionPreviousMiss(OurShot newOurShot) {
        if (newOurShot.getPosition() == null) {
            return false;
        }
        for (OurShot ourShot : ourShots) {
            if (ourShot.getPosition().x == newOurShot.getPosition().x) {
                if (ourShot.getPosition().y == newOurShot.getPosition().y) {
                    if (!ourShot.isHit()) {
                        return true;
                    }
                }
            }
            if (ourShot.getPosition().y == newOurShot.getPosition().y) {
                if (ourShot.getPosition().x == newOurShot.getPosition().x) {
                    if (!ourShot.isHit()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int countCurrentModeOuterBounderiesMisses() {
        boolean isFirstModeShot = true;
        int countCurrentMisses = 0;
        for (int i = ourShots.size()-1; i >= 0; i--) {
            if (ourShots.get(i).isModeChange()) {
                if (isFirstModeShot) {
                    return 0;
                }
                else {
                    return countCurrentMisses; //Decrements the final count, for not getting the north target, if existing with.
                }
            }
            if (!ourShots.get(i).isHit() && !isFirstModeShot) {
                countCurrentMisses++;
            }
            isFirstModeShot = false;
        }
        return 0;
    }
    
}
