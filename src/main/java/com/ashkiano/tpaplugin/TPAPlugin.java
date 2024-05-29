package com.ashkiano.tpaplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TPAPlugin extends JavaPlugin {
    private TPAHandler tpaHandler;

    @Override
    public void onEnable() {
        this.tpaHandler = new TPAHandler();
        this.getCommand("tpa").setExecutor(new TPACommand(this.tpaHandler));
        this.getCommand("tpaccept").setExecutor(new TPAAcceptCommand(this.tpaHandler));
        this.getCommand("tpdeny").setExecutor(new TPADenyCommand(this.tpaHandler));
        Metrics metrics = new Metrics(this, 21925);
        this.getLogger().info("Thank you for using the TPAPlugin plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
