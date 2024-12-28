package org.infernworld.infernaskme.listener;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.infernworld.infernaskme.InfernAskMe;
import org.infernworld.infernaskme.util.RGBcolors;

import java.util.*;

import static org.infernworld.infernaskme.util.SoundUtil.sound;

public class Events implements Listener {
    private static final String title = InfernAskMe.plugin.getConfig().getString(RGBcolors.translate("inventory.title"));
    private static final Map<UUID, Long> playersSet = new HashMap<>();

    @EventHandler
    public static void onInvClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(title)) {
            e.setCancelled(true);

            val slot = e.getSlot();
            val slots = InfernAskMe.plugin.getConfig().getInt("inventory.item.slot");
            val askstart = InfernAskMe.plugin.getConfig().getString(RGBcolors.translate("message.ask-me"));
            val player = e.getWhoClicked();
            if (slot == slots) {
                if (askstart != null) {
                    player.closeInventory();
                    player.sendMessage(askstart);
                    playersSet.put(player.getUniqueId(), System.currentTimeMillis() + 10000L);
                }
            }
        }
    }

    @EventHandler
    public static void onChatEvent(AsyncPlayerChatEvent e) {
        val player = e.getPlayer();
        val message = e.getMessage();

        if (!playersSet.containsKey(player.getUniqueId())) {
            return;
        }

        long timeout = playersSet.get(player.getUniqueId());
        if (System.currentTimeMillis() > timeout) {
            playersSet.remove(player.getUniqueId());
            return;
        }

        List<Player> lists = new ArrayList<>();
        lists.add(player);
        sound(player, "setting.sound-me");
        player.sendMessage(Objects.requireNonNull(InfernAskMe.plugin.getConfig().getString(RGBcolors.translate("message.accept"))));
        e.setCancelled(true);

        List<String> playerCondition = new ArrayList<>();
        playerCondition.add(message);

        val perms = InfernAskMe.plugin.getConfig().getString("setting.permission");
        val messageRecive = InfernAskMe.plugin.getConfig().getString("message.recive")
                .replace("%player%", player.getName())
                .replace("%message", message);
        if (perms != null) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission(perms)) {
                    p.sendMessage(RGBcolors.translate(messageRecive));
                    sound(player, "setting.sound-to");
                }
            }
        }
        playersSet.remove(player.getUniqueId());
    }
}