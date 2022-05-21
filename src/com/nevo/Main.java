package com.nevo;

import java.util.*;

public class Main {
    private static final Locations locations = new Locations();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).description());
            if (loc == 0) {
                break;
            }
            Map<String, Integer> exits = locations.get(loc).exits();
            System.out.print("Available exits are:");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ",");
            }
            System.out.println();
            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) {
                String[] words = direction.split(" ");//split the string into words
                for (String word : words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }
            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
                if (loc == 0) {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("You have won!");
                            System.exit(0);
                        }
                    }, 1000);
                }
            }
        }
    }
}