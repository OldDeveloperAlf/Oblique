package rip.cleo.oblique.menu.impl;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import rip.cleo.oblique.menu.Menu;
import rip.cleo.oblique.menu.MenuService;

import java.util.Map;
import java.util.UUID;

public final class CommonMenuService implements MenuService {

    private Map<UUID, Menu> referenceMap;

    private CommonMenuService() {
    }

    public CommonMenuService(Plugin plugin) {
        this.referenceMap = Maps.newHashMap();

        /* Instantiate the listener and task */
        MenuListener listener = new MenuListener(this);
        MenuTask task = new MenuTask(this, plugin);

        /* Register the listener and schedule the task */
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, task, 2L, 2L);
    }

    @Override
    public void openMenu(Player player, Menu menu) {
        CommonMenu commonMenu = (CommonMenu) menu;
        UUID uuid = player.getUniqueId();
        Inventory inventory = commonMenu.getReferenceMap().computeIfAbsent(uuid, uuid1 -> commonMenu.createInventory());

        /* Open the inventory for the player and set
         their current menu in the reference map */
        player.openInventory(inventory);
        referenceMap.put(player.getUniqueId(), menu);
    }

    @Override
    public Menu getOpenMenu(Player player) {
        return referenceMap.get(player.getUniqueId());
    }

    protected Map<UUID, Menu> getReferenceMap() {
        return referenceMap;
    }
}
