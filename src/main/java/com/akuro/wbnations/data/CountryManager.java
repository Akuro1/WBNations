package com.akuro.wbnations.data;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryManager {
    private JavaPlugin plugin;
    private Map<String, Country> countries;
    private FileConfiguration countryData;
    private File dataFile;

    public CountryManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.countries = new HashMap<>();
        this.dataFile = new File(plugin.getDataFolder(), "countries.yml");

        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            plugin.saveResource("countries.yml", false);
        }

        this.countryData = YamlConfiguration.loadConfiguration(dataFile);
        loadCountries();
    }

    public void loadCountries() {
        if (countryData.contains("countries")) {
            for (String key : countryData.getConfigurationSection("countries").getKeys(false)) {
                String path = "countries." + key;
                String name = countryData.getString(path + ".name");
                String colorName = countryData.getString(path + ".color");
                String owner = countryData.getString(path + ".owner");

                if (name != null && colorName != null && owner != null) {
                    try {
                        ChatColor color = ChatColor.valueOf(colorName);
                        Country country = new Country(name, color, owner);
                        if (countryData.contains(path + ".members")) {
                            List<String> members = countryData.getStringList(path + ".members");
                            country.getMembers().addAll(members);
                        }
                        countries.put(name.toLowerCase(), country);
                    } catch (IllegalArgumentException e) {
                        plugin.getLogger().warning("Color no válido para el país " + name + ": " + colorName);
                    }
                }
            }
        }
    }

    public void saveCountries() {
        countryData.set("countries", null);
        for (Map.Entry<String, Country> entry : countries.entrySet()) {
            Country country = entry.getValue();
            String path = "countries." + entry.getKey();
            countryData.set(path + ".name", country.getName());
            countryData.set(path + ".color", country.getColor().name());
            countryData.set(path + ".owner", country.getOwner());
            countryData.set(path + ".members", country.getMembers());
        }
        try {
            countryData.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save countries data: " + e.getMessage());
        }
    }

    public boolean createCountry(String name, ChatColor color, String owner) {
        if (countries.containsKey(name.toLowerCase())) {
            return false;
        }
        Country country = new Country(name, color, owner);
        countries.put(name.toLowerCase(), country);
        saveCountries();
        return true;
    }

    public Country getCountry(String name) {
        return countries.get(name.toLowerCase());
    }

    public Country getCountryByOwner(String owner) {
        for (Country country : countries.values()) {
            if (country.getOwner().equals(owner)) {
                return country;
            }
        }
        return null;
    }

    public Country getCountryByMember(String member) {
        for (Country country : countries.values()) {
            if (country.getMembers().contains(member)) {
                return country;
            }
        }
        return null;
    }

    public boolean isInCountry(String playerName) {
        for (Country country : countries.values()) {
            if (country.getMembers().contains(playerName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOwner(String playerName) {
        for (Country country : countries.values()) {
            if (country.getOwner().equals(playerName)) {
                return true;
            }
        }
        return false;
    }

    public void removeCountry(String name) {
        countries.remove(name.toLowerCase());
        saveCountries();
    }
}
