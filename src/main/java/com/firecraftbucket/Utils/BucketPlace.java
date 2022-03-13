package com.firecraftbucket.Utils;
import com.firecraftbucket.Genbucket;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BucketPlace {
    Genbucket instance;
    final private double maxBlocksy = 200.0;
    final private double maxBlocks = 50.0;

    public BucketPlace(Genbucket instance) {
        this.instance = instance;
    }

    public void BucketPlace(Location loc, BlockFace face, Material block, Player player) {
        //  Gør så den første block bliver placeret der rigtige sted
        switch (face) {
            case NORTH:
                loc.add(0, 0, -1.0);
                break;
            case SOUTH:
                loc.add(0, 0, 1.0);
                break;
            case EAST:
                loc.add(1.0, 0, 0);
                break;
            case WEST:
                loc.add(-1.0, 0, 0);
                break;
        }






        // Loop til at placere blocks
        new BukkitRunnable() {
            int blocks = 0;
            int blocksY = 0;
            @Override
            public void run() {
                switch (face) {
                    case UP:
                        if (loc.getY() > 253.0) {
                            cancel();
                        }
                        if (loc.getBlock().getType() == Material.AIR) {
                            cancel();
                        }
                        loc.add(0, 1.0, 0);
                        blocksY++;
                        break;

                    case DOWN:
                        if (loc.getY() < 2.0) {
                            cancel();
                        }
                        loc.add(0, -1.0, 0);
                        blocksY++;
                        break;
                    case EAST:
                        loc.add(1, 0, 0);
                        blocks++;
                        break;
                    case WEST:
                        loc.add(-1, 0, 0);
                        blocks++;
                        break;
                    case NORTH:
                        loc.add(0, 0, -1.0);
                        blocks++;
                        break;
                    case SOUTH:
                        loc.add(0, 0, 1.0);
                        blocks++;
                        break;
                }
                    //  Sikkre sig den ikke fjerner andet end AIR
                    if (loc.getBlock() == null || loc.getBlock().getType() != Material.AIR)
                        cancel();

                    //  Tjekker hvormange blocks der er placeret i muren
                    if (blocksY >= maxBlocksy || blocks >= maxBlocks)
                        cancel();

                    // Tjekker om blocken der bliver placeret er i spillerens ejet claim
                    if (!instance.getSaberFaction().locationIsFactionClaim(loc, player))
                        cancel();
                    else
                        if (loc.getBlock().getType() == Material.AIR) // Sikkerhed pga små fejl
                        loc.getBlock().setType(block);}
        }.runTaskTimer(instance, 100L, 20L);
    }
}
