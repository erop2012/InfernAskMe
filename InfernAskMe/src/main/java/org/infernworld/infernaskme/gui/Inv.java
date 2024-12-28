package org.infernworld.infernaskme.gui;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.infernworld.infernaskme.InfernAskMe;
import org.infernworld.infernaskme.util.RGBcolors;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Inv implements InventoryHolder {
    private static Inventory inventory = Bukkit.createInventory(null,
            InfernAskMe.plugin.getConfig().getInt("inventory.size"),
            Objects.requireNonNull(InfernAskMe.plugin.getConfig().getString(RGBcolors.translate("inventory.title"))));

    public static void Inv(Player player) {
        List<Integer> integers = InfernAskMe.plugin.getConfig().getIntegerList("inventory.item.for-items");
        for (int slot : integers) {
            val material = InfernAskMe.plugin.getConfig().getString(RGBcolors.translate("inventory.item.for-items-material"));
            ItemStack items = new ItemStack(Material.valueOf(material));

            val dekorMeta = items.getItemMeta();
            boolean ench = InfernAskMe.plugin.getConfig().getBoolean("enchant");
            if (ench) {
                dekorMeta.addEnchant(Enchantment.LUCK, 1, true);
                dekorMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }

            items.setItemMeta(dekorMeta);
            inventory.setItem(slot,items);
        }

        val material = InfernAskMe.plugin.getConfig().getString("inventory.item.material");
        ItemStack items = new ItemStack(Material.valueOf(material));
        val itemMeta = items.getItemMeta();
        val name = InfernAskMe.plugin.getConfig().getString(RGBcolors.translate("inventory.item.name"));
        itemMeta.setDisplayName(name);
        List<String> lore = InfernAskMe.plugin.getConfig().getStringList(RGBcolors.translate("inventory.item.lore"));
        itemMeta.setLore(lore);
        val slot = InfernAskMe.plugin.getConfig().getInt("inventory.item.slot");
        items.setItemMeta(itemMeta);
        inventory.setItem(slot,items);

        player.openInventory(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}
