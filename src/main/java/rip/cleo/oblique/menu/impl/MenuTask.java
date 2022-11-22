package rip.cleo.oblique.menu.impl;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import rip.cleo.oblique.menu.MenuAction;
import rip.cleo.oblique.menu.MenuService;

import java.util.function.Predicate;

public final class MenuTask implements Runnable {

    private MenuService menuService;
    private Plugin plugin;

    private MenuTask() {
    }

    protected MenuTask(MenuService menuService, Plugin plugin) {
        this.menuService = menuService;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ImmutableSet.copyOf(plugin.getServer().getOnlinePlayers()).stream().filter(filter()).forEach(player -> {
            CommonMenu commonMenu = (CommonMenu) menuService.getOpenMenu(player);

            /* Get the inventory and table from the menu */
            Inventory inventory = commonMenu.getReferenceMap().get(player.getUniqueId());
            Table<Integer, ItemStack, MenuAction> table = commonMenu.itemProvider().get(player);

            assert inventory != null : "Inventory is null";

            /* Set each item from the tables cell-set */
            table.cellSet().forEach(cell -> {
                inventory.setItem(cell.getRowKey(), cell.getColumnKey());
            });

            player.updateInventory();
        });
    }

    private Predicate<Player> filter() {
        return player -> player.getOpenInventory() != null && menuService.getOpenMenu(player) != null;
    }
}
