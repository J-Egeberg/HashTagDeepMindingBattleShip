package y5.model.defending;

import battleship.interfaces.Position;

/**
 *
 * @author Tobias Edit Dec 2015 Peter Lorensen: -added JavaDoc comments
 */
public class OurShip {

    private Position position;

    public OurShip(int x, int y) {
        position = new Position(x,y);
    }

    public int getX() {
        return this.position.x;
    }

    public int getY() {
        return this.position.y;
    }

}
