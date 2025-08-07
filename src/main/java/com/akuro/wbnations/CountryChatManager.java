package com.akuro.wbnations;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.akuro.wbnations.data.Country;
import com.akuro.wbnations.data.CountryManager;

import java.util.HashMap;
import java.util.Map;

public class CountryChatManager implements Listener {
    private static Map<Player, Boolean> playerChatMode = new HashMap<>();
    private CountryManager countryManager;

    public CountryChatManager(CountryManager countryManager) {
        this.countryManager = countryManager;
    }

    public static boolean togglePlayerChatMode(Player player) {
        boolean currentMode = playerChatMode.getOrDefault(player, false);
        playerChatMode.put(player, !currentMode);
        return !currentMode;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (playerChatMode.getOrDefault(player, false)) {
            event.setCancelled(true);
            Country country = countryManager.getCountryByMember(player.getName());
            if (country != null) {
                String countryChatFormat = ChatColor.GRAY + "[Country] " + ChatColor.WHITE + "%s: %s";
                String formattedMessage = String.format(countryChatFormat, player.getName(), event.getMessage());

                for (String memberName : country.getMembers()) {
                    Player member = Bukkit.getPlayer(memberName);
                    if (member != null && member.getWorld().equals(player.getWorld())
                            && member.getLocation().distance(player.getLocation()) <= 60) {
                        member.sendMessage(formattedMessage);
                    }
                }
            }
        }
    }
}
