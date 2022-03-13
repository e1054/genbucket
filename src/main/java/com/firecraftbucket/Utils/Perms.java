package com.firecraftbucket.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Perms {
    public boolean hasPerm(CommandSender sender, String permission) {
        if (sender instanceof Player) {
            if (sender.hasPermission("genbucket.*") || sender.hasPermission(permission)) {
                return true;
            } else {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6&lFIRE&f&lCRAFT&8] &cDu har ikke adgang til dette!"));
                return false;
            }
        }
        return true;
    }
}
