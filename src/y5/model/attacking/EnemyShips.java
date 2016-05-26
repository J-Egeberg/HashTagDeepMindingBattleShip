/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking;

import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author scheldejonas
 */
public class EnemyShips {
    
    private ArrayList<EnemyShip> enemyShips;
    private int verticalShipCount;
    private int horizontalShipCount;
    private int enemyShipCountStatus;

    public EnemyShips(ArrayList<EnemyShip> enemyShips) {
        this.enemyShips = enemyShips;
    }

    public EnemyShips() {
        this.enemyShips = new ArrayList();
    }

    public ArrayList<EnemyShip> getEnemyShips() {
        return enemyShips;
    }

    public void setEnemyShips(ArrayList<EnemyShip> enemyShips) {
        this.enemyShips = enemyShips;
    }

    public int getVerticalShipCount() {
        return verticalShipCount;
    }

    public void setVerticalShipCount(int verticalShipCount) {
        this.verticalShipCount = verticalShipCount;
    }

    public int getHorizontalShipCount() {
        return horizontalShipCount;
    }

    public void setHorizontalShipCount(int horizontalShipCount) {
        this.horizontalShipCount = horizontalShipCount;
    }

    @Override
    public String toString() {
        return "EnemyShips{" + "enemyShips=" + enemyShips.toString() + ", verticalShipCount=" + verticalShipCount + ", horizontalShipCount=" + horizontalShipCount + '}';
    }

    public void setEnemyShipCountStatus(int numberOfShips) {
        this.enemyShipCountStatus = numberOfShips;
    }

    public int getEnemyShipCountStatus() {
        return enemyShipCountStatus;
    }

    public void addEnemyShip(Ship ship) {
        enemyShips.add(new EnemyShip(ship.size(), ship.hashCode()));
    }

    public EnemyShip getEnemyShip(int i) {
        return this.enemyShips.get(i);
    }

    public int size() {
        return enemyShips.size();
    }

    public int countLiveShips() {
        int countLiveShips = 0;
        for (EnemyShip enemyShip : this.enemyShips) {
            if (enemyShip.isLive()) {
                countLiveShips++;
            }
        }
        return countLiveShips;
    }
    
}
