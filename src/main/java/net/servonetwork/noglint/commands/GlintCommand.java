package net.servonetwork.noglint.commands;

import net.md_5.bungee.api.ChatColor;
import net.servonetwork.noglint.NoGlintPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ---------- NoGlint ----------
 * Created by Fraser.Cumming on 16/05/2015.
 * © 2015 Fraser Cumming All Rights Reserved
 */
public class GlintCommand implements CommandExecutor {

    private NoGlintPlugin plugin;

    public GlintCommand( NoGlintPlugin plugin ) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender commandSender, Command command, String s, String[] strings ) {
        if( commandSender instanceof Player ) {
            Player player = (Player)commandSender;
            if ( plugin.getGlintManager().isGlintDisabled( player.getUniqueId() ) ) {
                plugin.getGlintManager().enableGlint( player );
                player.sendMessage( ChatColor.GREEN + "Enchantment glint has been " + ChatColor.YELLOW + "enabled" + ChatColor.GREEN + "!" );
            } else {
                plugin.getGlintManager().disableGlint( player );
                player.sendMessage( ChatColor.RED + "Enchantment glint has been " + ChatColor.YELLOW + "disabled" + ChatColor.RED + "!" );
            }
        }
        return true;
    }
}
