package intepreter;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Player implements Expression {

    public static final String KEYWORD = "player";
    private Coordinate coor;

    public Player(Coordinate coor) {
        this.coor = coor;
    }

    public Coordinate getCoor() {
        return coor;
    }

    public void setCoor(Coordinate coor) {
        this.coor = coor;
    }

    @Override
    public Coordinate intepret(Context context) {
        Collection<Expression> expressions = context.getExpressions();
        for (Expression ex : expressions) {
            if (!(ex instanceof Player)) {
                ex.intepret(context);
            }
        }

        return coor;
    }
}
