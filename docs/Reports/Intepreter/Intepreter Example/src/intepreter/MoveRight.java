package intepreter;

public class MoveRight implements Expression {

    public static final String KEYWORD = "right";

    @Override
    public Coordinate intepret(Context context) {
        Player player = context.getPlayer();
        Coordinate curretnCoordinate = player.getCoor();
        Coordinate newCoordinate = new Coordinate(
                curretnCoordinate.getX() + 1, curretnCoordinate.getY());
        player.setCoor(newCoordinate);

        return newCoordinate;
    }
}
