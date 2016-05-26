/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package y5;

import y5.model.attacking.huntingstrategies.HuntingModeOne;
import y5.model.attacking.huntingstrategies.AbstractHunting;
import y5.model.attacking.aimingstrategies.AbstractAiming;
import y5.model.attacking.OurShots;
import y5.model.attacking.OurShot;
import y5.model.defending.OurShip;
import y5.model.attacking.EnemyShip;
import y5.model.attacking.EnemyShips;
import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Board;
import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.Random;
import y5.model.attacking.aimingstrategies.AimingModeThree;

/**
 *
 * @author Tobias
 */
public class Player implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;
    private Board myBoard;
    private ArrayList<OurShip> ourShipPositionList;
    private ArrayList<OurShot> ourShotPositionList;
    private int currentShotNumber; //Zero based number
    private boolean isHuntingMode;
    private boolean isAimingMode;
    private EnemyShips enemyShips;
    private EnemyShip currentEnemyShip;
    private OurShot currentOurShot;
    private OurShots ourShots;
    private AbstractHunting huntingStrategy;
    private AbstractAiming aimingStrategy;
    private boolean isFirstShot;
    private int currentModeAimingShotsCount;
    private int currentModeHuntingShotsCount;

    public Player() {
        ourShotPositionList = new ArrayList();
        ourShipPositionList = new ArrayList();
        currentShotNumber = 0;
        isHuntingMode = true;
        isAimingMode = false;
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
        enemyShips = new EnemyShips();
        ourShots = new OurShots();
        aimingStrategy = new AimingModeThree(sizeX, sizeY);
        huntingStrategy = new HuntingModeOne(sizeX, sizeY);
        isFirstShot = true;

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
                        for (OurShip ourShip : ourShipPositionList) { //Cross checking with each saved position.
                            if (ourShip.getX() == x) {
                                if (ourShip.getY() == y + j) {
                                    spotIsNotAvailable = true; //Starting the getting of a new position for this ship again, because it can't be placed on this random spot.
                                }
                            }
                            if (ourShip.getY() == y + j) {
                                if (ourShip.getX() == x) {
                                    spotIsNotAvailable = true; //Starting the getting of a new position for this ship again, because it can't be placed on this random spot.
                                }
                            }
                        }
                    }
                } while (spotIsNotAvailable);

                for (int j = 0; j < s.size(); j++) { //for each ekstra size spot we need to save a tempPosition, for each ekstra size spot, and check if the tempList, contains that spot also.
                    ourShipPositionList.add(new OurShip(x, y + j));
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
                        for (OurShip ourShipPosition : ourShipPositionList) { //Cross checking with each saved position.
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
                    ourShipPositionList.add(new OurShip(x + j, y));
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
        if (true) {
            boolean setFalse = false;
        }
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
        this.enemyShips.setEnemyShipCountStatus(enemyShips.getNumberOfShips()); //Gets the enemy ship count to compare after shot.
        if (isFirstShot) {
            for (int i = 0; i < enemyShips.getNumberOfShips(); i++) {
                this.enemyShips.addEnemyShip(enemyShips.getShip(i)); //Add's all the ships live in first round, to know the size's when checking what ship is dead.
            }
        }
        
        
        if (isAimingMode) { // AimingMode
            currentOurShot = aimingStrategy.getNextTarget(ourShots, currentOurShot, this.enemyShips);  //Get's the next target accourding to the set aimingstrategy
            currentModeAimingShotsCount++;
        }
        
        
        boolean isModeChange = false;
        if (aimingStrategy.isEndOfMode()) {
            currentModeAimingShotsCount--;
            isModeChange = true;
        }

        
        if (isHuntingMode) { // Hunting mode
            currentOurShot = huntingStrategy.getNextTarget(ourShots, currentOurShot, this.enemyShips); //Get's the next target accourding to the set huntingstrategy
            currentModeHuntingShotsCount++;
        }
        
        
        if (isModeChange) {
            isHuntingMode = true;
            isAimingMode = false;
            currentOurShot.setIsModeChange(true);
        }

        return currentOurShot.getPosition(); // giver koordinaterne tilbage til Battleship om hvor der skal skydes
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
        enemyShips.getNumberOfShips(); //Gets the enemy ships count to found out of wrecked amount

        currentShotNumber++; //Increments the currentShotCount, Because being here is proff of shot is fired.
        isFirstShot = false; //Turns of the first shot checker, until next roud begins
        boolean isHit = hit; //Transfers hit status to local field
        currentOurShot.setHit(hit); //Sets the status of the hit, to the shot just fire above in getFireCoordinates.
        boolean isCurrentlyWrecked = false; //Stars a check for wrecking a ship.

        if (this.enemyShips.countLiveShips() > enemyShips.getNumberOfShips()) { //Checks if a enemyship i wrecked, and below wanna update all needed information to calculate on later for new aiming and hunting.
            currentOurShot.setEnemyShipKillShot(hit); //Sets the status of the ship kill shot, the the shot just fired above.
            isCurrentlyWrecked = true;
            boolean isVerticalWrecked = false;
            if (ourShots.isLastShotVertical()) { //Checks if last shot is vertical
                isVerticalWrecked = true;
            }
            if (ourShots.isLastShotHorizontal()) { //Checks if last shot is horizontal
                isVerticalWrecked = false;
            }        
            if (isVerticalWrecked) { //Checks if ship was vertical or horizontal wrecked  - Basicly here, we wanna save the wrecked ship in our know direction it died.
                boolean isOneShipDown = true;
                for (EnemyShip enemyShip : this.enemyShips.getEnemyShips()) { //Finds the correct size of the wrecked enemyShip in this's enemyShips
                    if (enemyShip.isLive() && isOneShipDown) { //takes down a ship.
                        enemyShip.setaLive(false);
                        isOneShipDown = false;
                        enemyShip.setTopPostion(aimingStrategy.getCurrentModeTopPosition());
                    }
                }
            }
            else {
                boolean isOneShipDown = true;
                for (EnemyShip enemyShip : this.enemyShips.getEnemyShips()) { //Finds the correct size of the wrecked enemyShip in this's enemyShips
                    if (enemyShip.isLive() && isOneShipDown) { //Checks if the current hit aiming shots is 2, 3, 4 or 5 size
                        enemyShip.setaLive(false);
                        isOneShipDown = false;
                        enemyShip.setRightPostion(aimingStrategy.getCurrentModeRightEndPosition());
                        
                    }
                }
            }
        }
        
        if (isHuntingMode) {
            currentOurShot.setIsHuntingMode(true);
        }
        if (isAimingMode) {
            currentOurShot.setIsAimingMode(true);
        }

        if (isHuntingMode && isHit) { // Checks if there is hit and if we were hunting
            currentModeAimingShotsCount = 0;
            isAimingMode = true; //Goes into aiming mode
            isHuntingMode = false; //Turns off hunting mode
            aimingStrategy.setCurrentModeFirstShot(true);
            currentOurShot.setIsModeChange(true);
        }
        
        if (isAimingMode && isCurrentlyWrecked) { //Checks if ship is wrecked to go back into hunting
            currentModeHuntingShotsCount = 0;
            isAimingMode = false; //Turns off aiming mode
            isHuntingMode = true; //Goes into hunting mode
            currentOurShot.setIsModeChange(true);
        }

        ourShots.addOurShot(currentOurShot); //Saves our complete updated shot fired against enemyships in our list.
    }

    /**
     * Called in the beginning of each match to inform about the number of
     * rounds being played.
     *
     * @param rounds int the number of rounds i a match
     */
    @Override
    public void startMatch(int rounds) {
        if (true) {
            boolean setFalse = false;
        }
    }

    /**
     * Called at the beginning of each round.
     *
     * @param round int the current round number.
     */
    @Override
    public void startRound(int round) {
        currentShotNumber = 0;

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
        if (true) {
            boolean setFalse = false;
        }
    }
}
