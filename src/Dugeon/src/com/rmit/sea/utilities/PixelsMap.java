/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rmit.sea.utilities;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.mapmodel.pixel.ViewablePixel;
import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author gia
 */
public class PixelsMap implements Map<Coordinate, ViewablePixel> {

    private Set<ViewablePixel> set;

    public PixelsMap() {
        set = new HashSet<ViewablePixel>();
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof Coordinate) {
            for (ViewablePixel p : set) {
                if (key.equals(p.getCoordinate())) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }

    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof ViewablePixel && set.contains((ViewablePixel) value)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ViewablePixel get(Object key) {
        if (key instanceof Coordinate) {
            for (ViewablePixel p : set) {
                if (key.equals(p.getCoordinate())) {
                    return p;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    public ViewablePixel put(Coordinate key, ViewablePixel value) {
        set.add(value);
        return value;
    }

    public ViewablePixel put(ViewablePixel value) {
        set.add(value);
        return value;
    }

    @Override
    public ViewablePixel remove(Object key) {
        if (key instanceof Coordinate) {
            ViewablePixel p = this.get((Coordinate) key);
            set.remove(p);
            return p;
        } else {
            return null;
        }
    }

    @Override
    public void putAll(Map<? extends Coordinate, ? extends ViewablePixel> m) {
        for (ViewablePixel p : m.values()) {
            this.put(p);
        }
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public Set<Coordinate> keySet() {
        Set<Coordinate> keys = new HashSet();
        for (ViewablePixel p : this.values()) {
            keys.add(p.getCoordinate());
        }
        return keys;
    }

    @Override
    public Collection<ViewablePixel> values() {
        return set;
    }

    @Override
    public Set<Entry<Coordinate, ViewablePixel>> entrySet() {
        Set<Entry<Coordinate, ViewablePixel>> entrySet=new HashSet<Entry<Coordinate, ViewablePixel>>();
        for (ViewablePixel p : values()) {
            Entry<Coordinate, ViewablePixel> entry = new SimpleImmutableEntry<Coordinate, ViewablePixel>(p.getCoordinate(), p);
            entrySet.add(entry);
        }
        return entrySet;
    }
}
