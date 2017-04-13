package com.graphs;

import java.io.FileNotFoundException;

/**
 * Created by graphs on 3/5/17.
 */
public class Cache {
    private static Entry defaultEntry = new Entry(null, null, 0);
    int startingSize = (int) Math.pow(2, 11);

    public Cache() throws FileNotFoundException {
    }

    static class Entry {
        final String url;
        String json;
        int hash;

        Entry(String k, String j, int h) {
            url = k;
            json = j;
            hash = h;
        }
    }

    /**
     * Tests whether a given object is contained in the Cache
     * <p>
     * The method checks every Entry object in the Cache until
     * it finds the object mapped to the url.
     *
     * @param  url  a unique url that is mapped to a specific object
     * @return      a boolean signifying whether the object was found
     */

    public boolean contains(String url) {
        int h = url.hashCode();
        int i = h & (startingSize - 1);

        return CacheRAF.entryRead(i, url).isPresent();
    }

    /**
     * Adds a json and its url to the Cache
     * <p>
     * The method adds a new Entry to the cache at the
     * specified index and writes it to the raf file
     *
     * @param  url  a unique url that is mapped to a specific object
     * @param  json  the json with all the Pokemon data
     */

    public void add(String url, String json) {
        int h = url.hashCode();
        int i = h & (startingSize - 1);
        CacheRAF.entryWrite(new Entry(url, json, h), i);
    }

    /**
     * Retrieves the json mapped to the specified url
     * <p>
     * The method checks every Entry object in the Cache until
     * it finds the object mapped to the url and returns the json
     * if found, otherwise it returns an empty Entry object
     *
     * @param  url  a unique url that is mapped to a specific object
     * @return      a json mapped to the supplied url
     */

    public String get(String url) {
        int h = url.hashCode();
        int i = h & (startingSize - 1);

        return CacheRAF.entryRead(i, url).orElse(defaultEntry).json;
    }
}
