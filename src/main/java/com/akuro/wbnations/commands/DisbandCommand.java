package com.akuro.wbnations.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.akuro.wbnations.data.Country;
import com.akuro.wbnations.data.CountryManager;

public class DisbandCommand implements CommandExecutor {
    private CountryManager countryManager;

    public DisbandCommand(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (!countryManager.isOwner(player.getName())) {
            player.sendMessage(ChatColor.RED + "Solo el dueño de un país puede desbandarlo.");
            return true;
        }

        Country country = countryManager.getCountryByOwner(player.getName());
        if (country == null) {
            player.sendMessage(ChatColor.RED + "No eres dueño de ningún país.");
            return true;
        }

        countryManager.removeCountry(country.getName());
        countryManager.saveCountries();
        player.sendMessage(ChatColor.GREEN + "Has desbandado el país " + country.getName() + ".");
        return true;
    }
}
