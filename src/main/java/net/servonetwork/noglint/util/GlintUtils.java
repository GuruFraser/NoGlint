package net.servonetwork.noglint.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import net.servonetwork.noglint.NoGlintPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

/**
 * ---------- NoGlint ----------
 * Created by Fraser.Cumming on 16/05/2015.
 * � 2015 Fraser Cumming All Rights Reserved
 */
public class GlintUtils {

    private static NoGlintPlugin plugin;

    public GlintUtils( NoGlintPlugin plugin ) {
        this.plugin = plugin;
    }

    public static void removeGlint( ItemStack item ) {
        for( Enchantment enchantment : item.getEnchantments().keySet() ) {
            item.removeEnchantment( enchantment );
        }
    }

    public static Player getPlayerByEntityID( int entityID ) {
        for( World world : Bukkit.getWorlds() ) {
            for( Player player : world.getEntitiesByClass( Player.class ) ) {
                if( player.getEntityId() == entityID ) {
                    return player;
                }
            }
        }
        return null;
    }

    public static void sendEquipmentData( NoGlintPlugin plugin, Player data, Player sending, boolean glint ) {
        int slot = 1;
        for( ItemStack item : sending.getInventory().getArmorContents() ) {
            item = item.clone();
            handleGlint( item, data, sending, slot, glint );
            slot++;
        }
        handleGlint( data.getItemInHand(), data, sending, 0, glint );
    }

    public static void handleGlint( ItemStack item, Player data, Player sending, int slot, boolean glint ) {
        if( item != null ) {
            if( !glint ) {
                GlintUtils.removeGlint( item );
            }
            try {
                plugin.getProtocolManager().sendServerPacket( data, constructEquipmentPacket( sending, item, slot ) );
            } catch ( InvocationTargetException e ) {
                plugin.getLogger().log( Level.WARNING, "Failed to send equipment packet to player (" + slot + ")" );
                e.printStackTrace();
            }
        }
    }

    public static PacketContainer constructEquipmentPacket( Player sending, ItemStack item, int slot ) {
        PacketContainer packet = new PacketContainer( PacketType.Play.Server.ENTITY_EQUIPMENT );
        packet.getIntegers().write( 0, sending.getEntityId() ).write( 1, slot );
        packet.getItemModifier().write( 0, item );
        return packet;
    }
}
