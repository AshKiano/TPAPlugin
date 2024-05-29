package com.ashkiano.tpaplugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TPAHandler {
    private final HashMap<UUID, UUID> tpaRequests = new HashMap<>();

    public void addRequest(Player requester, Player target) {
        tpaRequests.put(target.getUniqueId(), requester.getUniqueId());
    }

    public UUID getRequest(Player target) {
        return tpaRequests.get(target.getUniqueId());
    }

    public void removeRequest(Player target) {
        tpaRequests.remove(target.getUniqueId());
    }
}