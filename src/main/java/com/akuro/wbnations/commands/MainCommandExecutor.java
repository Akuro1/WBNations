package com.akuro.wbnations.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.akuro.wbnations.data.CountryManager;

public class MainCommandExecutor implements CommandExecutor {
    private CountryManager countryManager;

    public MainCommandExecutor(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo los jugadores pueden ejecutar este comando.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Uso: /wbn <crear|invitar|salir|desbandar|chat|menu>");
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "crear":
                return new CreateCountryCommand(countryManager).onCommand(player, cmd, label, args);
            case "invitar":
                return new InviteCommand(countryManager).onCommand(player, cmd, label, args);
            case "salir":
                return new LeaveCommand(countryManager).onCommand(player, cmd, label, args);
            case "desbandar":
                return new DisbandCommand(countryManager).onCommand(player, cmd, label, args);
            case "chat":
                return new ChatCommand(countryManager).onCommand(player, cmd, label, args);
            default:
                player.sendMessage(ChatColor.RED + "Subcomando no reconocido.");
                return true;
        }
    }
}
