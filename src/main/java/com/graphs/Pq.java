package com.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by cesar on 5/5/17.
 */
public class Pq {
    private List<Path> array;
    public int nextAdd;

    public Pq() {
        array = new ArrayList<>();
    }

    /**
     * Adds paths to the priority queue
     * <p>
     * The method checks if the path to be added to queue shares a
     * destination with any of the paths already present, then it checks
     * to see if the current path has smaller weight to the existing
     * one, in which case it replaces the old path with the shorter one.
     * Otherwise it doesn't add the path. If the path has a unique
     * destination it is added to the queue and siftUp is called to
     * sort the queue
     *
     * @param  t  the path to be added to the priority queue
     */

    public void add(Path t) {
        Optional<Path> replasable = Optional.empty();
        for (Path p : array) {
            if (p.getDestination() == t.getDestination()) {
                if (t.getWeight() < p.getWeight()) {
                    replasable = Optional.of(p);
                    nextAdd--;
                } else { //similar path was found but with larger weight
                    return;
                }
            }
        }
        replasable.ifPresent(path -> array.remove(path));
        int k = nextAdd++;
        array.add(k, t);
        if (nextAdd > 1) {
            siftUp(k);
        }
    }

    /**
     * Sorts the queue based on smallest weight
     * <p>
     * The method checks the last path in the queue and compares its weight
     * to its left neighbor. If less, the two paths are swapped places. The indicie
     * are decremented so that it compares every path going left until it finds a left path
     * with smaller weight, in which case the queue is sorted
     *
     * @param  p  an index for where the last path is on the queue
     */

    public void siftUp(int p) {
        while (p > 0) {
            Path current = array.get(p);
            Path left = array.get(p - 1);
            if (current.getWeight() < left.getWeight()) {
                array.set(p - 1, current);
                array.set(p, left);
                p--;
            } else {
                break;
            }
        }
    }

    /**
     * Grabs the path at the front of the queue
     * <p>
     * The method returns the first path in the queue, removes it, and
     * decrements the count. If the queue is empty it returns empty.
     *
     * @return      the first path in the queue, or empty if queue is empty
     */

    public Optional<Path> poll() {
        if (array.isEmpty()) return Optional.empty();
        Path x = array.remove(0);
        nextAdd--;
        return Optional.of(x);
    }

    public void clear() {
        array.clear();
        nextAdd = 0;
    }

    public boolean isEmpty()
    {
        return array.isEmpty();
    }
}
