package com.nevo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public record Location(int locationId, String description, Map<String, Integer> exits) implements Serializable {
    private static final long serialVersionUID = 1L;
    public Location(int locationId, String description, Map<String, Integer> exits) {
        this.locationId = locationId;
        this.description = description;
        if (exits != null)
            this.exits = new LinkedHashMap<>(exits);
        else this.exits = new LinkedHashMap<>();
        this.exits.put("Q", 0);
    }

    @Override
    public int locationId() {
        return locationId;
    }



    @Override
    public Map<String, Integer> exits() {
        return new LinkedHashMap<>(exits);
    }

    protected void addExit(String direction, int location) {
        exits.put(direction, location);
    }
}
