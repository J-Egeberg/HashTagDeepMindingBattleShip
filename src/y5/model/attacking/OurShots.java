/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking;

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
    
}
