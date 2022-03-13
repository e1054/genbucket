package com.firecraftbucket;

import com.firecraftbucket.Bucket.Type.CobblestoneBucket;
import com.firecraftbucket.Bucket.Type.NetherackBucket;
import com.firecraftbucket.Bucket.Type.ObisidianBucket;
import com.firecraftbucket.Bucket.Type.SandBucket;
import com.firecraftbucket.Commands.Gen;
import com.firecraftbucket.Hook.SaberFaction;
import com.firecraftbucket.Hook.Vault;
import com.firecraftbucket.Listener.PlayerListener;
import com.firecraftbucket.Utils.BucketPlace;
import com.firecraftbucket.Utils.Lag;
import com.firecraftbucket.Utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Genbucket extends JavaPlugin {

    protected Genbucket instance;

    protected SaberFaction saberFaction;
    protected Vault vault;
    protected BucketPlace bucketPlace;
    protected CobblestoneBucket cobblestoneBucket;
    protected ObisidianBucket obisidianBucket;
    protected SandBucket sandBucket;
    protected NetherackBucket netherackBucket;
    protected Lag lag;
    protected Perms perms;

    @Override
    public void onEnable() {

        instance = this;
        bucketPlace = new BucketPlace(this);
        cobblestoneBucket = new CobblestoneBucket();
        obisidianBucket = new ObisidianBucket();
        sandBucket = new SandBucket();
        netherackBucket = new NetherackBucket();
        lag = new Lag();
        perms = new Perms();
        saberFaction = new SaberFaction();
        vault = new Vault(this);

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);

        this.getCommand("Gen").setExecutor(new Gen(this));

    }

    public Genbucket getInstance() {
        return instance;
    }
    public BucketPlace getBucketPlace() {return bucketPlace;}
    public CobblestoneBucket getCobblestoneBucket() {return cobblestoneBucket;}
    public ObisidianBucket getObisidianBucket() {return obisidianBucket;}
    public SandBucket getSandBucket() {return sandBucket;}
    public NetherackBucket getNetherackBucket() {return netherackBucket;}
    public Lag getLag() {return lag;}
    public Perms getPerms() {return perms;}
    public SaberFaction getSaberFaction() {return saberFaction;}
    public Vault getVault() {return vault;}
}
