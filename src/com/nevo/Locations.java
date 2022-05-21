package com.nevo;


import java.io.*;
import java.util.*;

public class Locations implements Map<Integer,Location> {
    private static final Map<Integer,Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.txt")))) {
            for (Location location : locations.values()) {
                out.writeInt(location.locationId());
                out.writeUTF(location.description());
                for (String direction : location.exits().keySet()) {
                    out.writeUTF(direction);
                    out.writeInt(location.exits().get(direction));
                    System.out.println("direction: " + direction + " location: " + location.exits().get(direction));
                }
            }

        }
    }

    static {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.txt")))) {
            boolean eof = false;
            while (!eof) {
                try{
                    Map<String, Integer> exits = new LinkedHashMap<>();
                    int loc = in.readInt();
                    String description = in.readUTF();
                    int numExits = in.readInt();
                    System.out.println("Read location " + loc + " : " + description);
                    System.out.println("Read exits " + numExits + " : " + " exits.");
                    for (int i = 0; i < numExits; i++) {
                        String direction = in.readUTF();
                        int location = in.readInt();
                        exits.put(direction, location);
                        System.out.println("\t\t" + direction + " : " + location);
                    }
                    locations.put(loc, new Location(loc, description, exits));
                }catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
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
