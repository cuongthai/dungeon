package intepreter;

public class MoveLeft implements Expression {

    public static final String KEYWORD = "left";

    @Override
    public Coordinate intepret(Context context) {
        Player player = context.getPlayer();
        Coordinate curretnCoordinate = player.getCoor();
        Coordinate newCoordinate = new Coordinate(
                curretnCoordinate.getX() - 1, curretnCoordinate.getY());
        player.setCoor(newCoordinate);

        return newCoordinate;
    }
}
