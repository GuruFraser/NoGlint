package net.servonetwork.noglint;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.servonetwork.noglint.commands.GlintCommand;
import net.servonetwork.noglint.listeners.EquipmentListener;
import net.servonetwork.noglint.manager.GlintManager;
import net.servonetwork.noglint.util.GlintUtils;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ---------- NoGlint ----------
 * Created by Fraser.Cumming on 16/05/2015.
 * � 2015 Fraser Cumming All Rights Reserved
 */
public class NoGlintPlugin extends JavaPlugin {

    private ProtocolManager protocolManager;
    private GlintManager glintManager;

    @Override
    public void onLoad() {
        protocolManager = ProtocolLibrary.getProtocolManager();
        super.onLoad();
    }

    @Override
    public void onEnable() {
        glintManager = new GlintManager( this );
        protocolManager.addPacketListener( new EquipmentListener( this ) );
        new GlintUtils( this ); //Load Utils
        this.getCommand( "glint" ).setExecutor( new GlintCommand( this ) );
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public GlintManager getGlintManager() {
        return glintManager;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}
