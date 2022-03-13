package com.firecraftbucket.Bucket;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private String name;
    private String type;
    private String pris;

    public Bucket(String name, String type, String pris) {
        this.name = name;
        this.type = type;
        this.pris = pris;
    }
    public ItemStack getGenBucket() {
        ItemStack bucket = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemMeta bucketMeta = bucket.getItemMeta();
        bucketMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lore = new ArrayList<String>();
        lore.add(setColor("&6&lType: &f" + type));
        lore.add(setColor("&6&lPris: &f" + pris));
        bucketMeta.setLore(lore);

        bucket.setItemMeta(bucketMeta);
        return bucket;
    }


    private String setColor(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
