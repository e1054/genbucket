package com.firecraftbucket.Hook;

import com.firecraftbucket.Genbucket;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault {
    Genbucket instance;
    RegisteredServiceProvider<Economy> rsp;
    private Economy economy = null;


    public Vault(Genbucket instance) {
        this.instance = instance;
        rsp = instance.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null)
            economy = rsp.getProvider();
    }

    public double getBalance(Player p) {
        if(economy.hasAccount(p)){
            return economy.getBalance(p);
        }
        return 0.0;
    }
    public boolean removeBalance(Player p, double amount) {
        if(economy.hasAccount(p)){
            if (economy.getBalance(p) >= amount) {
                economy.withdrawPlayer(p, amount);
                return true;
            }
        }
        return false;
    }
}
