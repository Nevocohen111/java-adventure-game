package com.nevo;


import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    private static final Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        Path locPath = FileSystems.getDefault().getPath("locations.dat");
        try(ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))) {
            for(Location loc : locations.values()) {
                os.writeObject(loc);
            }
        }
    }

    static {
            Path locPath = FileSystems.getDefault().getPath("locations.dat");
            try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(locPath)))) {
                boolean eof = false;
                while(!eof) {
                    try {
                        Location loc = (Location) in.readObject();
                        locations.put(loc.getLocationID(), loc);
                    } catch (EOFException e) {
                        eof = true;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Class not found exception");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
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
        return locations.put(key, value);
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
