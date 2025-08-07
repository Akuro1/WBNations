package com.akuro.wbnations.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.akuro.wbnations.data.Country;
import com.akuro.wbnations.data.CountryManager;

public class InviteCommand implements CommandExecutor {
    private CountryManager countryManager;

    public InviteCommand(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage(ChatColor.RED + "Uso: /wbn invitar <Nombre de usuario>");
            return true;
        }

        String invitedPlayerName = args[1];
        Player invitedPlayer = Bukkit.getPlayer(invitedPlayerName);

        if (invitedPlayer == null) {
            player.sendMessage(ChatColor.RED + "El jugador no está en línea.");
            return true;
        }

        if (!countryManager.isOwner(player.getName())) {
            player.sendMessage(ChatColor.RED + "Solo el dueño de un país puede invitar a otros jugadores.");
            return true;
        }

        if (countryManager.isInCountry(invitedPlayer.getName())) {
            player.sendMessage(ChatColor.RED + "El jugador ya pertenece a un país.");
            return true;
        }

        Country country = countryManager.getCountryByOwner(player.getName());
        if (country == null) {
            player.sendMessage(ChatColor.RED + "No eres dueño de ningún país.");
            return true;
        }

        country.addMember(invitedPlayer.getName());
        countryManager.saveCountries();
        player.sendMessage(ChatColor.GREEN + "Has invitado a " + invitedPlayerName + " a tu país.");
        invitedPlayer.sendMessage(ChatColor.GREEN + "Has sido invitado al país " + country.getName() + ".");
        return true;
    }
}
