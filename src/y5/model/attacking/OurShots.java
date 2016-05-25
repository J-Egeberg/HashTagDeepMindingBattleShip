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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isLastShotHorizontal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public OurShot getCurrentModeFirstShot() {
        for (int i = ourShots.size()-1; i > 0; i--) {
            if (ourShots.get(i).isModeChange()) {
                return ourShots.get(i);
            }
        }
        return ourShots.get(0);
    }
    
}
