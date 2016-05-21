/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5;

import battleship.interfaces.Position;

/**
 *
 * @author coilx
 */
class ShotPosition {

    private int x;
    private int y;
    private boolean hit;

    public ShotPosition(int x, int y, boolean hit) {
        this.x = x;
        this.y = y;
        this.hit=hit;
    }



    public Position getNorthTarget() {
        Position position = new Position(this.x, this.y + 1);

        if (position.y > 9) {
            return null;
        } else {
            return position;
        }
    }

    public Position getSouthTarget() {

        Position position = new Position(this.x, this.y - 1);

        if (position.y < 0) {
            return null;
        } else {
            return position;
        }
    }

    public Position getWestTarget() {
        Position position = new Position(this.x - 1, this.y);

        if (position.x < 0) {
            return null;
        } else {
            return position;
        }
    }

    public Position getEastTarget() {
        Position position = new Position(this.x + 1, this.y);

        if (position.x > 9) {
            return null;
        } else {
            return position;
        }
    }

    @Override
    public String toString() {
        return "ShotPosition{" + "x=" + x + ", y=" + y + '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    

}