/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package y5;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Board;
import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class Player implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private Board myBoard;
    private ArrayList<OurShipPosition> ourShipPositionList = new ArrayList();
    private boolean huntHit; 
    private ShotPosition shotPosition;
    private ArrayList<ShotPosition> ourShotPositionList;
    

    public Player() {
        ourShotPositionList = new ArrayList();
    }

    /**
     * The method called when its time for the AI to place ships on the board
     * (at the beginning of each round).
     *
     * The Ship object to be placed MUST be taken from the Fleet given (do not
     * create your own Ship objects!).
     *
     * A ship is placed by calling the board.placeShip(..., Ship ship, ...) for
     * each ship in the fleet (see board interface for details on placeShip()).
     *
     * A player is not required to place all the ships. Ships placed outside the
     * board or on top of each other are wrecked.
     *
     * @param fleet Fleet all the ships that a player should place.
     * @param board Board the board were the ships must be placed.
     */
    @Override
    public void placeShips(Fleet fleet, Board board) {
        myBoard = board;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            Position pos;
            if (vertical) {
                int x = 0;
                int y = 0;

                boolean spotIsNotAvailable;
                
                do { //We need to get a temp position for each spot the ship is placed, s.size of the ship is more then one, we basicly need to store a tempPostion more of where the ship is being placed.
                    x = rnd.nextInt(sizeX);
                    y = rnd.nextInt(sizeY - (s.size() - 1)); //Making the ship coordinate not place ship outside of board
                    spotIsNotAvailable = false; //It should in general be available now, because it is a new random position.
                
                    for (int j = 0; j < s.size(); j++) { //For the new position created right here above, we need to check if the position is able to add, by checking ourShipPositions
                        for (OurShipPosition ourShipPosition : ourShipPositionList) { //Cross checking with each saved position.
                            if (ourShipPosition.getX() == x) {
                                if (ourShipPosition.getY() == y + j) {
                                    spotIsNotAvailable = true; //Starting the getting of a new position for this ship again, because it can't be placed on this random spot.
                                }
                            }
                            if (ourShipPosition.getY() == y + j) {
                                if (ourShipPosition.getX() == x) {
                                    spotIsNotAvailable = true; //Starting the getting of a new position for this ship again, because it can't be placed on this random spot.
                                }
                            }
                        }
                    }
                } while (spotIsNotAvailable);
                
                for (int j = 0; j < s.size(); j++) { //for each ekstra size spot we need to save a tempPosition, for each ekstra size spot, and check if the tempList, contains that spot also.
                    ourShipPositionList.add(new OurShipPosition(x, y + j));
                }
                
                pos = new Position(x, y);
            } else {
                int x = 0;
                int y = 0;
                
                boolean spotIsNotAvailable;
                
                do { //We need to get a temp position for each spot the ship is placed, s.size of the ship is more then one, we basicly need to store a tempPostion more of where the ship is being placed.
                    x = rnd.nextInt(sizeX - (s.size() - 1));//Making ship coordinate not place ship outside of board.
                    y = rnd.nextInt(sizeY);
                    spotIsNotAvailable = false; //It should in general be available now, because it is a new random position.
                
                    for (int j = 0; j < s.size(); j++) { //For the new position created right here above, we need to check if the position is able to add, by checking ourShipPositions
                        for (OurShipPosition ourShipPosition : ourShipPositionList) { //Cross checking with each saved position.
                            if (ourShipPosition.getX() == x + j) {
                                if (ourShipPosition.getY() == y) {
                                    spotIsNotAvailable = true; //Starting the getting of a new position for this ship again, because it can't be placed on this random spot.
                                }
                            }
                            if (ourShipPosition.getY() == y) {
                                if (ourShipPosition.getX() == x + j) {
                                    spotIsNotAvailable = true; //Starting the getting of a new position for this ship again, because it can't be placed on this random spot.
                                }
                            }
                        }
                    }
                } while (spotIsNotAvailable);
                
                for (int j = 0; j < s.size(); j++) {
                    ourShipPositionList.add(new OurShipPosition(x + j, y));
                }

                pos = new Position(x, y);

            }
            board.placeShip(pos, s, vertical);
        }
    }

    /**
     * Called every time the enemy has fired a shot.
     *
     * The purpose of this method is to allow the AI to react to the enemy's
     * incoming fire and place his/her ships differently next round.
     *
     * @param pos Position of the enemy's shot
     */
    @Override
    public void incoming(Position pos) {
        //Do nothing
    }

    /**
     * Called by the Game application to get the Position of your shot.
     * hitFeedBack(...) is called right after this method.
     *
     * @param enemyShips Fleet the enemy's ships. Compare this to the Fleet
     * supplied in the hitFeedBack(...) method to see if you have sunk any
     * ships.
     *
     * @return Position of you next shot.
     */
    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
         
        int x = rnd.nextInt(sizeX);
        int y = rnd.nextInt(sizeY);
        Position position = new Position (x,y);
        
        
        // Hvis man rammer et skib, hitFeedBack
        
        if (huntHit){
            ShotPosition sp = ourShotPositionList.get(ourShotPositionList.size()-2); //jeg modtager mit forrige skud
            
            
            position = sp.getNorthTarget(); //henter potitionen en y plads større end den gamle position og gemmer den i position.
            if (position==null){
                position = sp.getSouthTarget();
            }
        }
            
        shotPosition = new ShotPosition(position.x , position.y);
        ourShotPositionList.add(shotPosition); // tilføjer og gemmer alle skud
        return position;
    }
            
    /**
     * Called right after getFireCoordinates(...) to let your AI know if you hit
     * something or not.
     *
     * Compare the number of ships in the enemyShips with that given in
     * getFireCoordinates in order to see if you sunk a ship.
     *
     * @param hit boolean is true if your last shot hit a ship. False otherwise.
     * @param enemyShips Fleet the enemy's ships.
     */
    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        huntHit=hit;    
        

        
    }
    
    public boolean huntNorth (int x , int y , boolean hit){

        //hvis den rammer skal hit returne true
        //der skal skydes igen hvis hit er true
        
    //hvis den ikke rammer returner hit false og der skydes syd for istedet
       return hit = false;
    }

    /**
     * Called in the beginning of each match to inform about the number of
     * rounds being played.
     *
     * @param rounds int the number of rounds i a match
     */
    @Override
    public void startMatch(int rounds) {
        //Do nothing
    }

    /**
     * Called at the beginning of each round.
     *
     * @param round int the current round number.
     */
    @Override
    public void startRound(int round) {
        //Do nothing
    }

    /**
     * Called at the end of each round to let you know if you won or lost.
     * Compare your points with the enemy's to see who won.
     *
     * @param round int current round number.
     * @param points your points this round: 100 - number of shot used to sink
     * all of the enemy's ships.
     *
     * @param enemyPoints int enemy's points this round.
     */
    @Override
    public void endRound(int round, int points, int enemyPoints) {
        //Do nothing
    }

    /**
     * Called at the end of a match (that usually last 1000 rounds) to let you
     * know how many losses, victories and draws you scored.
     *
     * @param won int the number of victories in this match.
     * @param lost int the number of losses in this match.
     * @param draw int the number of draws in this match.
     */
    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }
}
