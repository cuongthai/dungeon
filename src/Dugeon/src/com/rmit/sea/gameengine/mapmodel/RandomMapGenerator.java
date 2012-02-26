package com.rmit.sea.gameengine.mapmodel;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.component.Cavern;
import com.rmit.sea.gameengine.mapmodel.component.Corridor;
import com.rmit.sea.gameengine.mapmodel.component.MapComponent;
import com.rmit.sea.gameengine.mapmodel.component.Maze;
import com.rmit.sea.gameengine.mapmodel.component.OvalRoom;
import com.rmit.sea.gameengine.mapmodel.component.RectangleRoom;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import com.rmit.sea.gameengine.mapmodel.pixel.Wall;
import com.rmit.sea.gameengine.mapmodel.pixel.Way;
import com.rmit.sea.dungeon.resources.Constant;
import com.rmit.sea.gameengine.mapmodel.component.GenericShapeMap;
import com.rmit.sea.view.ConsoleView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.HashMap;

public class RandomMapGenerator implements MapGenerator {

    private Map<Coordinate, ViewablePixel> pixelsMap;
    private List<MapComponent> mapComponents;

    public RandomMapGenerator() {
    }

    /**
     * generate and return the new gamemap here
     * @return
     */
    @Override
    public GameMap generate() {
        pixelsMap = new HashMap<Coordinate, ViewablePixel>();
        mapComponents = new ArrayList<MapComponent>();
        Random r = new Random();
        switch (r.nextInt(2)) {
            case 0:
                return generateCavernStyleMap();
            default:

                return generateMazeStyleMap();
        }


    }

    private GameMap generateCavernStyleMap() {
        Random r = new Random();
        int numberOfComponent = r.nextInt(Constant.MAX_COMPONENTS_NUMBER) + 1;
        //int numberOfComponent = 5;
        long al=System.currentTimeMillis();
        for (int i = 0; i < numberOfComponent; i++) {
            long t = System.currentTimeMillis();
            //generate the component
            MapComponent component = getRandomMapComponent();
            ConsoleView.out("create " + (System.currentTimeMillis() - t));
            Coordinate c;
            if (i != 0) {
                //if not the first get the coordinate to place the new component
                c = generateRandomCoordinateNextToComponent(mapComponents.get(i - 1));
                ConsoleView.out("Random " + (System.currentTimeMillis() - t));

                //shift them to the new coordinate
                shiftAllCoordinateTo(component, c);
                ConsoleView.out("Shift " + (System.currentTimeMillis() - t));


                //generate a list of link between 2 component
                Coordinate[] points = getNearestWayPoints(new GenericShapeMap(pixelsMap), component);
                ConsoleView.out("nearest " + (System.currentTimeMillis() - t));
                //create corridor to link them
                MapComponent corridor = new Corridor(points[0], points[1]);
                ConsoleView.out("Corri " + (System.currentTimeMillis() - t));

                pixelsMap.putAll(component.getViewablePixels());
                pixelsMap.putAll(corridor.getViewablePixels());
                //addWall(pixelsMap);
                ConsoleView.out("connect " + (System.currentTimeMillis() - t));

            } else {
                pixelsMap.putAll(component.getViewablePixels());

            }
            mapComponents.add(component);
            ConsoleView.out("Each " + (System.currentTimeMillis() - t));

        }
            ConsoleView.out("All " + (System.currentTimeMillis() - al));

        //create gamemap
        GameMap gameMap = new MapView(mapComponents, pixelsMap);

        //generateWalls();
        return gameMap;
    }

    private Coordinate generateRandomCoordinateNextToComponent(MapComponent component) {
        Random r = new Random();
        Coordinate maxCoordinate = getMaxCoordinateOfMapComponent(component);
        Coordinate minCoordinate = getMinCoordinateOfMapComponent(component);
        Coordinate c;
        switch (r.nextInt(3)) {
            case 0:
                c = new Coordinate(maxCoordinate.getX() + r.nextInt(Constant.MAX_DISTANCE_BETWEEN_MAP_COMPONENT) + 2, minCoordinate.getY());
                break;
            case 1:
                c = new Coordinate(minCoordinate.getX(), maxCoordinate.getY() + r.nextInt(Constant.MAX_DISTANCE_BETWEEN_MAP_COMPONENT) + 2);
                break;
            default:
                c = new Coordinate(maxCoordinate.getX() + r.nextInt(Constant.MAX_DISTANCE_BETWEEN_MAP_COMPONENT) + 2, maxCoordinate.getY() + r.nextInt(Constant.MAX_DISTANCE_BETWEEN_MAP_COMPONENT) + 2);
                break;

        }
        return c;
    }

    private Coordinate[] getNearestWayPoints(MapComponent componentA, MapComponent componentB) {
        int minDistance = Integer.MAX_VALUE;
        List<Coordinate[]> l = new ArrayList<Coordinate[]>();

        List<Coordinate> outerWay1 = new ArrayList<Coordinate>();
        List<Coordinate> outerWay2 = new ArrayList<Coordinate>();

        //create a list of the rear way of the component
        for (Coordinate c : componentA.getOuterWalls().keySet()) {

            Coordinate c1 = new Coordinate(c.getX() + 1, c.getY());
            Coordinate c2 = new Coordinate(c.getX() - 1, c.getY());
            Coordinate c3 = new Coordinate(c.getX(), c.getY() + 1);
            Coordinate c4 = new Coordinate(c.getX(), c.getY() - 1);

            ViewablePixel vp1=componentA.getWays().get(c1);
            ViewablePixel vp2=componentA.getWays().get(c2);
            ViewablePixel vp3=componentA.getWays().get(c3);
            ViewablePixel vp4=componentA.getWays().get(c4);

            if (vp1 !=null) {
                outerWay1.add(c1);
            } else if (vp2!=null) {
                outerWay1.add(c2);
            } else if (vp3!=null) {
                outerWay1.add(c3);
            } else if (vp4!=null) {
                outerWay1.add(c4);
            }

        }

        //create a list of the rear way of the component
        for (Coordinate c : componentB.getOuterWalls().keySet()) {

            Coordinate c1 = new Coordinate(c.getX() + 1, c.getY());
            Coordinate c2 = new Coordinate(c.getX() - 1, c.getY());
            Coordinate c3 = new Coordinate(c.getX(), c.getY() + 1);
            Coordinate c4 = new Coordinate(c.getX(), c.getY() - 1);

            ViewablePixel vp1=componentB.getWays().get(c1);
            ViewablePixel vp2=componentB.getWays().get(c2);
            ViewablePixel vp3=componentB.getWays().get(c3);
            ViewablePixel vp4=componentB.getWays().get(c4);

            if (vp1 !=null) {
                outerWay2.add(c1);
            } else if (vp2!=null) {
                outerWay2.add(c2);
            } else if (vp3!=null) {
                outerWay2.add(c3);
            } else if (vp4!=null) {
                outerWay2.add(c4);
            }

        }

        ConsoleView.out(outerWay1.size());
        ConsoleView.out(outerWay2.size());


        for (Coordinate cA : outerWay1) {
            for (Coordinate cB : outerWay2) {
                if (minDistance > cA.distanceTo(cB)) {
                    minDistance = cA.distanceTo(cB);
                    Coordinate[] points = new Coordinate[2];
                    points[0] = cA;
                    points[1] = cB;
                    l.clear();
                    l.add(points);

                } else if (minDistance == cA.distanceTo(cB)) {
                    Coordinate[] points = new Coordinate[2];
                    points[0] = cA;
                    points[1] = cB;
                    l.add(points);
                }
            }
        }
        Random r = new Random();

        return l.get(r.nextInt(l.size()));
    }

    private MapComponent getRandomMapComponent() {
        Random r = new Random();
        int randomIntForType = r.nextInt(3);
        int randomIntForRadius = limitRangeForValue(Constant.MAX_SIDE_OF_COMPONENT / 2, Constant.MIN_SIDE_OF_COMPONENT, r.nextInt());
        int randomIntForSide = limitRangeForValue(Constant.MAX_SIDE_OF_COMPONENT, Constant.MIN_SIDE_OF_COMPONENT, r.nextInt());
        MapComponent component;
        switch (randomIntForType) {
            case 0:
                component = new Cavern(randomIntForRadius * 2);
                break;

            case 1:
                component = new OvalRoom(randomIntForRadius);
                break;
            default:
                component = new RectangleRoom(randomIntForSide, randomIntForSide);
                break;
        }
        return component;
    }

    private int limitRangeForValue(int max, int min, int value) {
        if (max < value) {
            return max;
        }
        if (min > value) {
            return min;
        }
        return value;
    }

    private Coordinate getMaxCoordinateOfMapComponent(MapComponent component) {
        int maxX = 0;
        int maxY = 0;
        for (Coordinate c : component.getViewablePixels().keySet()) {
            if (c.getX() > maxX) {
                maxX = c.getX();
            }
            if (c.getY() > maxY) {
                maxY = c.getY();
            }
        }
        return new Coordinate(maxX, maxY);
    }

    private Coordinate getMinCoordinateOfMapComponent(MapComponent component) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Coordinate c : component.getViewablePixels().keySet()) {
            if (c.getX() < minX) {
                minX = c.getX();
            }
            if (c.getY() < minY) {
                minY = c.getY();
            }
        }
        return new Coordinate(minX, minY);
    }

    private void shiftAllCoordinateTo(MapComponent component, Coordinate coordinate) {
        Map<Coordinate, ViewablePixel> newViewablePixels = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : component.getViewablePixels().values()) {
            p.setCoordinate(new Coordinate(p.getCoordinate().getX() + coordinate.getX(), p.getCoordinate().getY() + coordinate.getY()));
            newViewablePixels.put(p.getCoordinate(), p);
        }
        component.getViewablePixels().clear();
        component.getViewablePixels().putAll(newViewablePixels);

    }

    private void addWall(Map<Coordinate, ViewablePixel> pixelsMap) {
        Map<Coordinate, ViewablePixel> walls = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : pixelsMap.values()) {
            if (p instanceof Way) {
                Coordinate c1 = new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY());
                Coordinate c2 = new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY());
                Coordinate c3 = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1);
                Coordinate c4 = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1);
                if (!pixelsMap.containsKey(c1)) {
                    Wall w = new Wall(c1);
                    walls.put(w.getCoordinate(), w);
                }
                if (!pixelsMap.containsKey(c2)) {
                    Wall w = new Wall(c2);
                    walls.put(w.getCoordinate(), w);
                }
                if (!pixelsMap.containsKey(c3)) {
                    Wall w = new Wall(c3);
                    walls.put(w.getCoordinate(), w);
                }
                if (!pixelsMap.containsKey(c4)) {
                    Wall w = new Wall(c4);
                    walls.put(w.getCoordinate(), w);
                }
            }
        }
        pixelsMap.putAll(walls);

    }

    private GameMap generateMazeStyleMap() {
        MapComponent maze = new Maze(Constant.MAX_SIDE_OF_COMPONENT, Constant.MAX_SIDE_OF_COMPONENT);
        pixelsMap.putAll(maze.getViewablePixels());
        mapComponents.add(maze);
        GameMap gameMap = new MapView(mapComponents, pixelsMap);
        return gameMap;
    }
}
