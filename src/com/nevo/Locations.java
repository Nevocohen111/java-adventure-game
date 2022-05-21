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
     /*   try(BufferedReader reader = new BufferedReader (new FileReader("locations_big.txt"))) {
            String input;
            while((input = reader.readLine()) != null) {
                 String[] parts = input.split(", ");
                 int locationId = Integer.parseInt(parts[0]);
                 String description = parts[1];
                System.out.println("imported location " + locationId + ":" + description);
                 Map<String,Integer> exits = new HashMap<>();
                 locations.put(locationId, new Location(locationId,description,exits));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //now read the exists
        try (BufferedReader reader = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;
            while((input = reader.readLine()) != null) {
                String[] data = input.split(", ");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                System.out.println(loc+ ":" + direction + ":" + destination);
                Location location  = locations.get(loc);
                location.addExit(direction,destination);
         }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
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
