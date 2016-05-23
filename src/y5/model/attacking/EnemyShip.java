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
    private Position leftPostion;
    private int tournamentHashCodeShip;

    public EnemyShip(int size, boolean vertical, boolean aLive, Position topPostion, Position leftPostion) {
        this.size = size;
        this.vertical = vertical;
        this.aLive = aLive;
        this.topPostion = topPostion;
        this.leftPostion = leftPostion;
    }

    EnemyShip(int size, int hashCode) {
        this.size = size;
        this.tournamentHashCodeShip = hashCode;
    }

    @Override
    public String toString() {
        return "EnemyShip{" + "size=" + size + ", vertical=" + vertical + ", aLive=" + aLive + ", topPostion=" + topPostion.toString() + ", leftPostion=" + leftPostion.toString() + ", tournamentHashCodeShip=" + tournamentHashCodeShip + '}';
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

    public boolean isaLive() {
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
        return leftPostion;
    }

    public void setLeftPostion(Position leftPostion) {
        this.leftPostion = leftPostion;
    }

    public int getTournamentHashCodeShip() {
        return tournamentHashCodeShip;
    }

    public void setTournamentHashCodeShip(int tournamentHashCodeShip) {
        this.tournamentHashCodeShip = tournamentHashCodeShip;
    }
    
    
    
}
