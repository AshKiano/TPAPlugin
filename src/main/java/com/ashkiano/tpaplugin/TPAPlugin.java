package com.ashkiano.tpaplugin;

import com.ashkiano.ashlib.PluginStatistics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TPAPlugin extends JavaPlugin {
    private TPAHandler tpaHandler;

    @Override
    public void onEnable() {
        //TODO tuto chybu vypisovat i OP hráčům do chatu
        if (!isAshLibPresent()) {
            getLogger().severe("AshLib plugin is missing! Please download and install AshLib to run TPAPlugin. (can be downloaded from: https://www.spigotmc.org/resources/ashlib.118282/ )");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        new PluginStatistics(this);

        this.tpaHandler = new TPAHandler();
        this.getCommand("tpa").setExecutor(new TPACommand(this.tpaHandler));
        this.getCommand("tpaccept").setExecutor(new TPAAcceptCommand(this.tpaHandler));
        this.getCommand("tpdeny").setExecutor(new TPADenyCommand(this.tpaHandler));
        Metrics metrics = new Metrics(this, 21925);
        this.getLogger().info("Thank you for using the TPAPlugin plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    private boolean isAshLibPresent() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("AshLib");
        return plugin != null && plugin.isEnabled();
    }
}
