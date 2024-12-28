package org.infernworld.infernaskme;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.infernworld.infernaskme.commands.Commands;
import org.infernworld.infernaskme.listener.Events;
import org.infernworld.infernaskme.util.RGBcolors;

public final class InfernAskMe extends JavaPlugin {
    @Getter
    public static InfernAskMe plugin;
    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        Bukkit.getPluginCommand("ask").setExecutor(new Commands());
        Bukkit.getPluginManager().registerEvents(new Events(), this);

        getLogger().info("§F ");
        getLogger().info(" §x§F§B§0§8§D§7I§x§F§B§1§9§D§Bn§x§F§B§2§9§D§Ff§x§F§B§3§A§E§2e§x§F§B§4§A§E§6r§x§F§C§5§B§E§An§x§F§C§6§B§E§EA§x§F§C§7§C§F§2s§x§F§C§8§C§F§5k§x§F§C§9§D§F§9M§x§F§C§A§D§F§De");
        getLogger().info("§F ");
        getLogger().info("§F §fПлагин §x§0§8§F§B§0§Fвключился! ✔");
        getLogger().info("§F ");
        getLogger().info("§F Разработчик: §x§F§B§0§8§D§7Великий Егшот§F (Егор Татаркин : EG_SH_NOT) ");
        getLogger().info("§F Для связи: ");
        getLogger().info("§F §x§F§B§0§8§D§7§n§l‖§F Telegram: §x§F§B§0§8§D§7@theegorchik");
        getLogger().info("§F §x§F§B§0§8§D§7§n§l‖§F VK: §x§F§B§0§8§D§7vk.com/egortatarkin");
        getLogger().info("§F §x§F§B§0§8§D§7§n§l‖§F Мой телеграм канал: §x§F§B§0§8§D§7 t.me/egashotina");
        getLogger().info("§F ");
    }

    public static void reloadCfg() {
        InfernAskMe.getPlugin().reloadConfig();
        Bukkit.getLogger().info("§F Конфиг §x§0§8§F§B§0§Fперезагружен! ✔");
    }
    @Override
    public void onDisable() {
        getLogger().info(RGBcolors.translate("§F "));
        getLogger().info(RGBcolors.translate(" §x§F§B§0§8§D§7I§x§F§B§1§9§D§Bn§x§F§B§2§9§D§Ff§x§F§B§3§A§E§2e§x§F§B§4§A§E§6r§x§F§C§5§B§E§An§x§F§C§6§B§E§EA§x§F§C§7§C§F§2s§x§F§C§8§C§F§5k§x§F§C§9§D§F§9M§x§F§C§A§D§F§De"));
        getLogger().info("§F ");
        getLogger().info("§F §fПлагин §x§f§d§0§0§0§0выключился! ✘");
        getLogger().info("§F ");
        getLogger().info("§F Разработчик: §x§F§B§0§8§D§7Великий Егшот§F (Егор Татаркин : EG_SH_NOT) ");
        getLogger().info("§F Для связи: ");
        getLogger().info("§F §x§F§B§0§8§D§7§n§l‖§F Telegram: §x§F§B§0§8§D§7@theegorchik");
        getLogger().info("§F §x§F§B§0§8§D§7§n§l‖§F VK: §x§F§B§0§8§D§7vk.com/egortatarkin");
        getLogger().info("§F §x§F§B§0§8§D§7§n§l‖§F Мой телеграм канал: §x§F§B§0§8§D§7 t.me/egashotina");
        getLogger().info("§F ");
    }
}
