package com.ashkiano.tpaplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPADenyCommand implements CommandExecutor {
    private TPAHandler tpaHandler;

    public TPADenyCommand(TPAHandler tpaHandler) {
        this.tpaHandler = tpaHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        if (tpaHandler.getRequest(player) == null) {
            player.sendMessage("You have no pending teleport requests to deny.");
            return true;
        }

        Player requester = player.getServer().getPlayer(tpaHandler.getRequest(player));
        if (requester != null) {
            requester.sendMessage(player.getName() + " has denied your teleport request.");
        }
        tpaHandler.removeRequest(player);
        player.sendMessage("Teleport request denied.");
        return true;
    }
}