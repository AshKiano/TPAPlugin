package com.ashkiano.tpaplugin;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPACommand implements CommandExecutor {
    private TPAHandler tpaHandler;

    public TPACommand(TPAHandler tpaHandler) {
        this.tpaHandler = tpaHandler;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /tpa <player>");
            return true;
        }

        Player player = (Player) sender;
        Player target = player.getServer().getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("Player not found.");
            return true;
        }

        tpaHandler.addRequest(player, target);
        target.sendMessage(player.getName() + " wants to teleport to you. Type /tpaccept or /tpdeny to respond.");
        player.sendMessage("Teleport request sent to " + target.getName() + ".");
        return true;
    }
}