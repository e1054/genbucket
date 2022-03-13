package com.firecraftbucket.Commands;

import com.firecraftbucket.Genbucket;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Gen implements CommandExecutor {
    final private Genbucket instance;

    public Gen(Genbucket instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (instance.getPerms().hasPerm(sender, "genbucket.admin")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    //
                    ItemStack bucket1 = instance.getCobblestoneBucket().getGenBucket();
                    player.getInventory().addItem(bucket1);
                    ItemStack bucket2 = instance.getObisidianBucket().getGenBucket();
                    player.getInventory().addItem(bucket2);
                    ItemStack bucket3 = instance.getSandBucket().getGenBucket();
                    player.getInventory().addItem(bucket3);
                    ItemStack bucket4 = instance.getNetherackBucket().getGenBucket();
                    player.getInventory().addItem(bucket4);
                    //
                }
            }
        }
        return true;
    }
}
