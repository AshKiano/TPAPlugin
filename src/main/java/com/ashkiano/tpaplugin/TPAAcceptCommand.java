package com.ashkiano.tpaplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TPAAcceptCommand implements CommandExecutor {
    private TPAHandler tpaHandler;

    public TPAAcceptCommand(TPAHandler tpaHandler) {
        this.tpaHandler = tpaHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        UUID requesterID = tpaHandler.getRequest(player);
        if (requesterID == null) {
            player.sendMessage("You have no pending teleport requests.");
            return true;
        }

        Player requester = player.getServer().getPlayer(requesterID);
        if (requester == null) {
            player.sendMessage("The player who sent the request is no longer online.");
            return true;
        }

        requester.teleport(player.getLocation());
        tpaHandler.removeRequest(player);
        requester.sendMessage("Teleport successful.");
        player.sendMessage("Teleport request accepted.");
        return true;
    }
}
