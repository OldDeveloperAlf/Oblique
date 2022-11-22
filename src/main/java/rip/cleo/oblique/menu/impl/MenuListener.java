package rip.cleo.oblique.menu.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import rip.cleo.oblique.menu.Menu;
import rip.cleo.oblique.menu.MenuService;

public final class MenuListener implements Listener {

    private MenuService menuService;

    private MenuListener() {
    }

    protected MenuListener(MenuService menuService) {
        this.menuService = menuService;
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Menu menu = menuService.getOpenMenu(player);

        /* Check if the player currently has a menu referenced */
        if (menu != null) {
            boolean[] shouldReturn = {false};

            /* Get the menus item-provider and look if the item clicked
             is in the table */
            menu.itemProvider().get(player).cellSet().forEach(cell -> {
                if (cell.getRowKey().equals(event.getSlot())) {
                    /* Set the boolean whether to cancel this event based on the
                     invocation of the menu-actions onclick method return value */
                    shouldReturn[0] = cell.getValue().onClick(event.getClick());
                }
            });

            event.setCancelled(shouldReturn[0]);
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        close((Player) event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        close(event.getPlayer());
    }

    private void close(Player player) {
        CommonMenuService commonMenuService = (CommonMenuService) menuService;
        commonMenuService.getReferenceMap().remove(player.getUniqueId());
    }
}
