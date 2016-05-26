/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package y5.model.attacking;

import battleship.interfaces.Position;

/**
 *
 * @author scheldejonas
 */
public class EnemyShip {
    
    private int size;
    private boolean vertical;
    private boolean aLive;
    private Position topPostion;
    private Position rightPostion;
    private int tournamentHashCodeShip;

    public EnemyShip(int size, boolean vertical, boolean aLive, Position topPostion, Position leftPostion) {
        this.size = size;
        this.vertical = vertical;
        this.aLive = aLive;
        this.topPostion = topPostion;
        this.rightPostion = leftPostion;
    }

    public EnemyShip(int size, int hashCode) {
        this.size = size;
        this.tournamentHashCodeShip = hashCode;
        this.topPostion = null;
        this.rightPostion = null;
        this.aLive = true;
        this.vertical = true;
    }

    @Override
    public String toString() {
        return "EnemyShip{" + "size=" + size + ", vertical=" + vertical + ", aLive=" + aLive + ", tournamentHashCodeShip=" + tournamentHashCodeShip + '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isLive() {
        return aLive;
    }

    public void setaLive(boolean aLive) {
        this.aLive = aLive;
    }

    public Position getTopPostion() {
        return topPostion;
    }

    public void setTopPostion(Position topPostion) {
        this.topPostion = topPostion;
    }

    public Position getLeftPostion() {
        return rightPostion;
    }

    public void setRightPostion(Position rightPostion) {
        this.rightPostion = rightPostion;
    }

    public int getTournamentHashCodeShip() {
        return tournamentHashCodeShip;
    }

    public void setTournamentHashCodeShip(int tournamentHashCodeShip) {
        this.tournamentHashCodeShip = tournamentHashCodeShip;
    }
    
    
    
}
