package intepreter;

public class MoveUp implements Expression {

    public static final String KEYWORD = "up";

    @Override
    public Coordinate intepret(Context context) {
        Player player = context.getPlayer();
        Coordinate curretnCoordinate = player.getCoor();
        Coordinate newCoordinate = new Coordinate(
                curretnCoordinate.getX(), curretnCoordinate.getY() - 1);
        player.setCoor(newCoordinate);

        return newCoordinate;
    }
}
