package y5;

/**
 *
 * @author Tobias Edit Dec 2015 Peter Lorensen: -added JavaDoc comments
 */
public class OurShipPosition {

    private int x;
    private int y;
    
    

    public OurShipPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
