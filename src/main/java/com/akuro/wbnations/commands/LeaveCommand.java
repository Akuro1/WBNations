package com.akuro.wbnations.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.akuro.wbnations.data.Country;
import com.akuro.wbnations.data.CountryManager;

public class LeaveCommand implements CommandExecutor {
    private CountryManager countryManager;

    public LeaveCommand(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (countryManager.isOwner(player.getName())) {
            player.sendMessage(ChatColor.RED
                    + "El dueño del país no puede salir. Usa /wbn desbandar si deseas deshacerte del país.");
            return true;
        }

        Country country = countryManager.getCountryByMember(player.getName());
        if (country == null) {
            player.sendMessage(ChatColor.RED + "No perteneces a ningún país.");
            return true;
        }

        country.removeMember(player.getName());
        countryManager.saveCountries();
        player.sendMessage(ChatColor.GREEN + "Has salido del país " + country.getName() + ".");
        return true;
    }
}
