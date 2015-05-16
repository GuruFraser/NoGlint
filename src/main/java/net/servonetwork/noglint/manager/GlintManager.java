package net.servonetwork.noglint.manager;

import net.servonetwork.noglint.NoGlintPlugin;
import net.servonetwork.noglint.util.GlintUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ---------- NoGlint ----------
 * Created by Fraser.Cumming on 16/05/2015.
 * © 2015 Fraser Cumming All Rights Reserved
 */
public class GlintManager {

    private NoGlintPlugin plugin;

    public GlintManager( NoGlintPlugin plugin ) {
        this.plugin = plugin;
    }

    private List<UUID> disabledGlintPlayers = new ArrayList<>(  );

    public void disableGlint( Player player ) {
        if( !disabledGlintPlayers.contains( player.getUniqueId() ) ) {
            disabledGlintPlayers.add( player.getUniqueId() );
            for( Player online : plugin.getServer().getOnlinePlayers() ) {
                GlintUtils.sendEquipmentData( plugin, player, online, false );
            }
        }
    }

    public void enableGlint( Player player ) {
        if( disabledGlintPlayers.contains( player.getUniqueId() ) ) {
            disabledGlintPlayers.remove( player.getUniqueId() );
            for( Player online : plugin.getServer().getOnlinePlayers() ) {
                GlintUtils.sendEquipmentData( plugin, player, online, true );
            }
        }

    }

    public boolean isGlintDisabled( UUID player ) {
        return disabledGlintPlayers.contains( player );
    }
}
