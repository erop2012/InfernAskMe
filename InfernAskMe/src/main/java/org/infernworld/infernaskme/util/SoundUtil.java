package org.infernworld.infernaskme.util;

import lombok.val;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.infernworld.infernaskme.InfernAskMe;

public class SoundUtil {
    public static void sound(Player player, String sou) {
        String name = InfernAskMe.plugin.getConfig().getString(sou);
        if (name != null) {
            val sound = Sound.valueOf(name);
            player.playSound(player.getLocation(), sound,1.0F,1.0F);
        }
    }
}
