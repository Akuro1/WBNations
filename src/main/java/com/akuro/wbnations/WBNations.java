package com.akuro.wbnations;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.akuro.wbnations.commands.MainCommandExecutor;
import com.akuro.wbnations.data.CountryManager;
import com.akuro.wbnations.placeholder.AllInWBPlaceholder;

public class WBNations extends JavaPlugin {
    private CountryManager countryManager;

    @Override
    public void onEnable() {
        this.countryManager = new CountryManager(this);
        getLogger().info("WBNations ha sido habilitado!");

        getCommand("wbn").setExecutor(new MainCommandExecutor(countryManager));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new AllInWBPlaceholder(this).register();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("WBNations ha sido deshabilitado!");
    }

    public CountryManager getCountryManager() {
        return countryManager;
    }
}
