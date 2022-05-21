package com.nevo;


import java.io.*;
import java.util.*;

public class Locations implements Map<Integer,Location> {
    private static final Map<Integer,Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        try  (ObjectOutputStream out = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
            for(Location location : locations.values()) {
                   out.writeObject(location);
                    }
                }
            }


    static {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;
            while (!eof) {
                try{
                    Location location = (Location)in.readObject();
                    System.out.println("read location: " + location+" : "+location.description());
                    System.out.println("Found "+location.exits().size()+" exits");
                    locations.put(location.locationId(),location);
                }catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
