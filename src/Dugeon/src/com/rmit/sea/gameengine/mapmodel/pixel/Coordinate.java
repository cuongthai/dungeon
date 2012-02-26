/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.gameengine.mapmodel.pixel;

import com.rmit.sea.gameengine.model.Direction;
import java.io.Serializable;

/**
 *
 * @author thailycuong1202
 */
public class Coordinate implements Comparable<Coordinate>, Serializable {

    /**
     * char[x][y]
     *
     * 00 01 02
     * 10 11 12
     * 20 21 22
     */
    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }



    public int distanceTo(Coordinate coordinate) {
        return (int) Math.sqrt(
                Math.pow((x - coordinate.getX()), 2) + 
                Math.pow((y - coordinate.getY()), 2));
    }


    @Override
    public Coordinate clone(){
        return new Coordinate(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public int compareTo(Coordinate o) {
        if (x != o.x) {
            return x - o.x;
        }

        if (y != o.y) {
            return y - o.y;
        }

        return 0;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public boolean isInSameLineWith(Coordinate c1, Coordinate c2) {
        //[ Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By) ] / 2
        int a = 
                (this.getX() * (c1.getY() - c2.getY()) 
                + c1.getX() * (c2.getY() - this.getY()) 
                + c2.getX() * (this.getY() - c1.getY()));
        //return (a>=0&&a<=0);
        return (a == 0);
    }
}
