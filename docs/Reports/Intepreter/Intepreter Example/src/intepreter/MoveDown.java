package intepreter;

public class MoveDown implements Expression {

    public static final String KEYWORD = "down";

    @Override
    public Coordinate intepret(Context context) {
        Player player = context.getPlayer();
        Coordinate curretnCoordinate = player.getCoor();
        Coordinate newCoordinate = new Coordinate(
                curretnCoordinate.getX(), curretnCoordinate.getY() +1);
        player.setCoor(newCoordinate);
        
        return newCoordinate;
    }
}
