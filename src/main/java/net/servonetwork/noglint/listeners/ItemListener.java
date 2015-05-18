package net.servonetwork.noglint.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import net.servonetwork.noglint.NoGlintPlugin;
import net.servonetwork.noglint.util.GlintUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

/**
 * ---------- GuruCraft ----------
 * Created by Fraser.Cumming on 06/04/2015.
 * © 2015 Fraser Cumming All Rights Reserved
 */
public class ItemListener extends PacketAdapter {

    private NoGlintPlugin plugin;

    public ItemListener( NoGlintPlugin plugin ) {
        super( plugin, ListenerPriority.LOWEST, PacketType.Play.Server.SPAWN_ENTITY );
        this.plugin = plugin;
    }

    @Override
    public void onPacketSending( PacketEvent event ) {
        PacketContainer packet = event.getPacket();

        int entityID = packet.getIntegers().read( 0 );
        int type = packet.getIntegers().read( 9 );

        /*
        ItemStacks are ID'd as 2.
         */
        if( type == 2 ) {
            Entity entity = packet.getEntityModifier( event ).read( 0 );
            //Lets just make sure
            if( entity instanceof Item ) {
                ItemStack item = ( (Item) entity ).getItemStack();
                if( plugin.getGlintManager().isGlintDisabled( event.getPlayer().getUniqueId() ) ) {
                    GlintUtils.removeGlint( item );
                }
            }
        }
    }
}
