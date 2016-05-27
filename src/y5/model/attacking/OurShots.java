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

    public int getCurrentModeHitShots() {
        
        boolean isFirstModeShot = true;
        int countCurrentHits = 0;
        
        for (int i = ourShots.size()-1; i >= 0; i--) {
            if (ourShots.get(i).isModeChange()) {
                if (isFirstModeShot) {
                    return 0;
                }
                else {
                    return countCurrentHits; //Decrements the final count, for not getting the north target, if existing with.
                }
            }
            if (ourShots.get(i).isHit() && !isFirstModeShot) {
                countCurrentHits++;
            }
            isFirstModeShot = false;
        }
        return countCurrentHits;
    }


    public boolean containsShotPosition(Position position) {
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

    public void removeShot(OurShot removeThisOurShot) {
        
        OurShot shotForRemoval = null;
        
        for (OurShot ourShot : ourShots) {
            if (ourShot.getPosition().x == removeThisOurShot.getPosition().x) {
                if (ourShot.getPosition().y == removeThisOurShot.getPosition().y) {
                    shotForRemoval = ourShot;
                }
            }
            if (ourShot.getPosition().y == removeThisOurShot.getPosition().y) {
                if (ourShot.getPosition().x == removeThisOurShot.getPosition().x) {
                    shotForRemoval = ourShot;
                }
            }
        }
        if (shotForRemoval != null) {
            ourShots.remove(shotForRemoval);
        }
    }

    public OurShot getOurShot(int indexOfShot) {
        return ourShots.get(indexOfShot);
    }
    
}
