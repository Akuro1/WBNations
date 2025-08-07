package com.akuro.wbnations.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.akuro.wbnations.WBNations;
import com.akuro.wbnations.data.Country;

public class AllInWBPlaceholder extends PlaceholderExpansion {

    private final WBNations plugin;

    public AllInWBPlaceholder(WBNations plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "wbn";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Akuro";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) {
            return "";
        }

        if (identifier.equals("country_chat")) {
            Country country = plugin.getCountryManager().getCountryByMember(player.getName());
            if (country != null) {
                return String.format("&f[%s%s&f] &7|&f ", country.getColor(), country.getName());
            }
        }

        if (identifier.equals("country")) {
            Country country = plugin.getCountryManager().getCountryByMember(player.getName());
            if (country != null) {
                return String.format("%s%s&f&f ", country.getColor(), country.getName());
            }
        }

        return "";
    }
}
