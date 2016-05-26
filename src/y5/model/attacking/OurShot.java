/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking;

import battleship.interfaces.Position;

/**
 *
 * @author coilx
 */
public class OurShot {

    private Position position;
    private int shotNumber;
    private boolean isHit;
    private boolean isEnemyShipKillShot;
    private boolean isModeChange;
    private boolean isHuntingMode;
    private boolean isAimingMode;

    public OurShot(int x, int y, int shotNumber) {
        this.position = new Position(x,y);
        this.shotNumber = shotNumber;
        isHit = false;
        isModeChange = false;
        isEnemyShipKillShot = false;
        isHuntingMode = false;
        isAimingMode = false;
    }

    public OurShot(Position position, int shotNumber) {
        this.position = position;
        this.shotNumber = shotNumber;
        isHit = false;
        isModeChange = false;
        isEnemyShipKillShot = false;
        isHuntingMode = false;
        isAimingMode = false;
    }

    public Position getNorthPosition() {
        Position position = new Position(this.position.x, this.position.y + 1);

        if (position.y > 9) {
            return null;
        } else {
            return position;
        }
    }

    public Position getSouthPosition() {
        Position position = new Position(this.position.x, this.position.y - 1);

        if (position.y < 0) {
            return null;
        } else {
            return position;
        }
    }

    public Position getWestPosition() {
        Position position = new Position(this.position.x - 1, this.position.y);

        if (position.x < 0) {
            return null;
        } else {
            return position;
        }
    }

    public Position getEastPosition() {
        Position position = new Position(this.position.x + 1, this.position.y);

        if (position.x > 9) {
            return null;
        } else {
            return position;
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        this.isHit = hit;
    }

    public int getShotNumber() {
        return shotNumber;
    }

    public void setShotNumber(int ShotNumber) {
        this.shotNumber = ShotNumber;
    }

    public boolean isEnemyShipKillShot() {
        return isEnemyShipKillShot;
    }

    public void setEnemyShipKillShot(boolean enemyShipKillShot) {
        this.isEnemyShipKillShot = enemyShipKillShot;
    }

    public boolean isNotPositioned() {
        if (this.position == null) {
            return true;
        }
        return false;
    }

    public boolean isModeChange() {
        return isModeChange;
    }
    
    public void setIsModeChange(boolean isModeChange) {
        this.isModeChange = isModeChange;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isIsEnemyShipKillShot() {
        return isEnemyShipKillShot;
    }

    public void setIsEnemyShipKillShot(boolean isEnemyShipKillShot) {
        this.isEnemyShipKillShot = isEnemyShipKillShot;
    }

    public boolean isIsHuntingMode() {
        return isHuntingMode;
    }

    public void setIsHuntingMode(boolean isHuntingMode) {
        this.isHuntingMode = isHuntingMode;
    }

    public boolean isIsAimingMode() {
        return isAimingMode;
    }

    public void setIsAimingMode(boolean isAimingMode) {
        this.isAimingMode = isAimingMode;
    }

    @Override
    public String toString() {
        return "OurShot{" + "position=" + position.toString() + ", shotNumber=" + shotNumber + ", isHit=" + isHit + ", isEnemyShipKillShot=" + isEnemyShipKillShot + ", isModeChange=" + isModeChange + ", isHuntingMode=" + isHuntingMode + ", isAimingMode=" + isAimingMode + '}';
    }

}
