package com.firecraftbucket.Listener;

import com.firecraftbucket.Genbucket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class PlayerListener implements Listener {
    final private Genbucket instance;

    private final HashMap<String, Long> cooldown = new HashMap<>();

    public PlayerListener(Genbucket instance) {
        this.instance = instance;
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBucketPlace(PlayerInteractEvent event) {
        if (event.isCancelled())
            return;

        if (event.getAction() == null &&!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getItem() == null &&
                (!event.getItem().getType().equals(Material.LAVA_BUCKET)))
            return;

        if (!event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8§l✯ §6§lGEN§f§lBUCKET §8§l✯"))
            return;

        event.setCancelled(true);


        // Slår Gen Buckets fra hvis server TPS'en er for lav.
        if (instance.getSaberFaction().hasFaction(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Du kan &nkun&f bruge Gen Buckets som medlem af en faction"));
            return;
        }
        // Tjekker serverens TPS
        if (instance.getLag().getTPS() < 18) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&cServeren lagger for meget. Vent Venligst!"));
            return;
        }

        // Tjekker Block siden
        if (event.getBlockFace() == null)
            return;

        //Tjekker Cooldown
        if (onCooldown(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Vent lidt med at gøre dette!"));
            return;
        }

        putCooldown(event.getPlayer());
        Location loc = event.getClickedBlock().getLocation();

        // Tjekker om spilleren prøver at placere i sit ejet claim
        if (!instance.getSaberFaction().locationIsFactionClaim(loc, event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Du kan &nkun&f bruge Gen Buckets i din factions claim!"));
            return;
        }

        // Tjekker item'et har en lore
        if (event.getItem().getItemMeta().getLore() == null)
            return;

        genSwitchStatement(event, loc);
    }


    /**
     * Takes a Gen Bucket event an determinds which Gen the player is using
     * Then it calls BucketPlace to make the wall
     * @param event
     * @param loc
     */
    private void genSwitchStatement(PlayerInteractEvent event, Location loc) {
        switch (event.getItem().getItemMeta().getLore().get(0).substring(12).toLowerCase()) {
            case "cobblestone":
                if (instance.getVault().removeBalance(event.getPlayer(), 250))
                    instance.getBucketPlace().BucketPlace(loc, event.getBlockFace(), Material.COBBLESTONE, event.getPlayer());
                else
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Du har &c&lIKKE &fnok penge til dette!"));
                break;
            case "obsidian":
                if (instance.getVault().removeBalance(event.getPlayer(), 5000))
                    instance.getBucketPlace().BucketPlace(loc, event.getBlockFace(), Material.OBSIDIAN, event.getPlayer());
                else
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Du har &c&lIKKE &fnok penge til dette!"));
                break;
            case "sand":
                if (event.getBlockFace() == BlockFace.UP) {
                    if (instance.getVault().removeBalance(event.getPlayer(), 7500))
                        instance.getBucketPlace().BucketPlace(loc, event.getBlockFace(), Material.SAND, event.getPlayer());
                    else
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Du har &c&lIKKE &fnok penge til dette!"));
                } else {
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Sand-Gens kan kun placeres på toppen af en block!"));
                    return;
                }

            break;
        case "netherrack":
            if (instance.getVault().removeBalance(event.getPlayer(), 100))
                instance.getBucketPlace().BucketPlace(loc, event.getBlockFace(), Material.NETHERRACK, event.getPlayer());
            else
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &c&lFIRE&f&lCRAFT &8|&f Du har &c&lIKKE &fnok penge til dette!"));
            break;
        default:
            break;
}
    }

    /**
     * Check if a player is on cooldown
     * @param player The player to check
     * @return if the user is on cooldown
     */
    private boolean onCooldown(Player player) {

        if(cooldown.containsKey(player.getName())) {
            long secondsLeft = (((cooldown.get(player.getName())/1000)+1) - (System.currentTimeMillis()/1000));
            if(secondsLeft>0)
                return true;

        } else {
            cooldown.put(player.getName(), System.currentTimeMillis());
        }
        return false;
    }

    /**
     * Put a Player on cooldown
     * @param player The player to put on cooldown
     */
    private void putCooldown(Player player) {
        if(!cooldown.containsKey(player.getName())) {
            cooldown.put(player.getName(), System.currentTimeMillis());
        } else {
            cooldown.replace(player.getName(), System.currentTimeMillis());
        }
    }
}
