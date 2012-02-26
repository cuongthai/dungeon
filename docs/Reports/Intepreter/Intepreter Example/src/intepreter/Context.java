package intepreter;

import java.util.LinkedHashSet;
import java.util.Set;

public class Context {

    public static final String EXAMPLE_DESC =
            Player.KEYWORD + " " + Coordinate.KEYWORD + " 1 1 "
            + " move " + MoveLeft.KEYWORD
            + " move " + MoveRight.KEYWORD
            + " move " + MoveUp.KEYWORD
            + " move " + MoveDown.KEYWORD;
    private Set<Expression> expressions;

    public Context(String desc) {
        parse(desc);
    }

    public Set<Expression> getExpressions() {
        return expressions;
    }

    public Player getPlayer() {
        for (Expression ex : expressions) {
            if (ex instanceof Player) {
                return (Player) ex;
            }
        }

        return null;
    }

    public void removeExpression(Expression ex) {
        expressions.remove(ex);
    }

    private void parse(String desc)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (desc == null || desc.isEmpty()) {
            throw new IllegalArgumentException();
        }

        expressions = new LinkedHashSet<Expression>();
        String[] chunks = desc.split(" ");
        int length = chunks.length;
        String current = null;

        for (int i = 0; i < length;) {
            current = chunks[i];

            // Parse coordinate
            if (current.equalsIgnoreCase(Player.KEYWORD)) {

                if (getPlayer() != null) {
                    // Player already exists
                    throw new IllegalArgumentException();
                }

                // Get the coordinate
                if (!chunks[i + 1].equalsIgnoreCase(Coordinate.KEYWORD)) {
                    throw new IllegalArgumentException();
                }

                int x = Integer.parseInt(chunks[i + 2]);
                int y = Integer.parseInt(chunks[i + 3]);
                Coordinate coor = new Coordinate(x, y);

                expressions.add(new Player(coor));

                i += 4;
            } else if (current.equalsIgnoreCase("move")) {
                String next = chunks[i + 1];

                if (next.equalsIgnoreCase(MoveLeft.KEYWORD)) {
                    expressions.add(new MoveLeft());
                } else if (next.equalsIgnoreCase(MoveRight.KEYWORD)) {
                    expressions.add(new MoveRight());
                } else if (next.equalsIgnoreCase(MoveUp.KEYWORD)) {
                    expressions.add(new MoveUp());
                } else if (next.equalsIgnoreCase(MoveDown.KEYWORD)) {
                    expressions.add(new MoveDown());
                } else {
                    throw new IllegalArgumentException();
                }

                i += 2;
            } else {
                throw new IllegalArgumentException();
            }
        }

        // No player in it
        if (getPlayer() == null) {
            throw new IllegalArgumentException();
        }
    }
}
