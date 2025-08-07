package com.akuro.wbnations.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.akuro.wbnations.CountryChatManager;
import com.akuro.wbnations.data.CountryManager;

public class ChatCommand implements CommandExecutor {
    private CountryManager countryManager;

    public ChatCommand(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo los jugadores pueden ejecutar este comando.");
            return true;
        }

        Player player = (Player) sender;
        boolean isChatModeEnabled = CountryChatManager.togglePlayerChatMode(player);

        if (isChatModeEnabled) {
            player.sendMessage(ChatColor.GREEN
                    + "Modo de chat de país activado. Ahora puedes hablar con los miembros de tu país.");
        } else {
            player.sendMessage(ChatColor.RED + "Modo de chat de país desactivado. Ahora estás en el chat normal.");
        }

        return true;
    }
}
