package com.firecraftbucket.Hook;

import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Relation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Siger tak til: https://github.com/biscuut/GenBucket/blob/master/GenBucketPlugin/src/main/java/codes/biscuit/genbucket/hooks/FactionsUUIDHook.java
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class SaberFaction {

    public boolean hasFaction(Player p) {
            FPlayer fPlayer = FPlayers.getInstance().getByPlayer(p);
            return fPlayer.hasFaction();
        }

    public boolean isNotWilderness(Location loc) {
            FLocation fLoc = new FLocation(loc);
            Faction fLocFaction = Board.getInstance().getFactionAt(fLoc);
            return !fLocFaction.isWilderness();
        }

    public boolean locationIsFactionClaim(Location loc, Player p) {
            Faction locFaction = Board.getInstance().getFactionAt(new FLocation(loc));
            Faction pFaction = FPlayers.getInstance().getByPlayer(p).getFaction();
            return locFaction.equals(pFaction);
        }

        public boolean isFriendlyPlayer(Player p, Player otherP) {
            FPlayer fPlayer = FPlayers.getInstance().getByPlayer(p);
            FPlayer otherFPlayer = FPlayers.getInstance().getByPlayer(otherP);
            Relation relation = fPlayer.getRelationTo(otherFPlayer);
            return relation == Relation.MEMBER || relation == Relation.ALLY || relation == Relation.TRUCE;
        }
}
