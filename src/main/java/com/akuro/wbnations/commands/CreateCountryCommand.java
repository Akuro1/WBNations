package com.akuro.wbnations.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.akuro.wbnations.data.CountryManager;

public class CreateCountryCommand implements CommandExecutor {
    private CountryManager countryManager;

    public CreateCountryCommand(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length < 3) {
            player.sendMessage(ChatColor.RED + "Uso: /wbn crear <Nombre de país> <Color>");
            return true;
        }

        String countryName = args[1];
        ChatColor color;
        try {
            color = ChatColor.valueOf(args[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage(
                    ChatColor.RED + "Color no válido. Usa colores como YELLOW, RED, PURPLE, WHITE, BLACK, etc.");
            return true;
        }

        if (countryManager.isInCountry(player.getName()) || countryManager.isOwner(player.getName())) {
            player.sendMessage(ChatColor.RED + "Ya perteneces a un país o eres dueño de uno.");
            return true;
        }

        if (countryManager.createCountry(countryName, color, player.getName())) {
            player.sendMessage(
                    ChatColor.GREEN + "País " + countryName + " creado con éxito con el color " + color.name() + ".");
        } else {
            player.sendMessage(ChatColor.RED + "No se pudo crear el país. Puede que ya exista un país con ese nombre.");
        }

        return true;
    }
}
