package net.servonetwork.noglint.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import net.servonetwork.noglint.NoGlintPlugin;
import net.servonetwork.noglint.util.GlintUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * ---------- NoGlint ----------
 * Created by Fraser.Cumming on 16/05/2015.
 * © 2015 Fraser Cumming All Rights Reserved
 */
public class EquipmentListener extends PacketAdapter {

    private NoGlintPlugin plugin;

    public EquipmentListener( NoGlintPlugin plugin ) {
        super( plugin, ListenerPriority.LOWEST, PacketType.Play.Server.ENTITY_EQUIPMENT );
        this.plugin = plugin;
    }

    @Override
    public void onPacketSending( PacketEvent event ) {
        PacketContainer packet = event.getPacket();

        int entityID = packet.getIntegers().read( 0 );
        ItemStack item = packet.getItemModifier().read( 0 );

        //Run this check first to save on performance.
        if( plugin.getGlintManager().isGlintDisabled( event.getPlayer().getUniqueId() ) ) {
            Player player = GlintUtils.getPlayerByEntityID( entityID );

            if ( player != null && item != null && !item.getType().equals( Material.AIR )) {
                GlintUtils.removeGlint( item );
            }
        }

    }
}
