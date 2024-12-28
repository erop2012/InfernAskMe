package org.infernworld.infernaskme.commands;

import com.sun.tools.javac.comp.Infer;
import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.infernworld.infernaskme.InfernAskMe;
import org.jetbrains.annotations.NotNull;

import static org.infernworld.infernaskme.gui.Inv.Inv;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        val text = InfernAskMe.getPlugin().getConfig().getString("setting.permission-reload");
        val message = InfernAskMe.getPlugin().getConfig().getString("message.config-reload");
        val msgNotPerms = InfernAskMe.getPlugin().getConfig().getString("message.config-reload-perms");

        if (command.getName().equalsIgnoreCase("ask")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Эта команда только для игроков!");
                return false;
            }

            val player = (Player) sender;
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (!player.hasPermission(text)) {
                    if (msgNotPerms != null) {
                        player.sendMessage(msgNotPerms);
                    }
                    return false;
                }

                InfernAskMe.reloadCfg();
                if (message != null) {
                    player.sendMessage(message);
                }
                return true;
            } else {
                Inv(player);
                return true;
            }
        }
        return false;
    }
}