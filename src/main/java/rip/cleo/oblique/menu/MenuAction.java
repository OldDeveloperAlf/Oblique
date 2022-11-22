package rip.cleo.oblique.menu;

import org.bukkit.event.inventory.ClickType;

/**
 * Simple MenuAction interface easily implemented using lambdas
 */
public interface MenuAction {

    /**
     * Called when a player clicks the {@link org.bukkit.inventory.ItemStack} associated with this MenuAction
     *
     * @param clickType the {@link ClickType}
     * @return boolean whether the {@link org.bukkit.event.inventory.InventoryClickEvent} should be cancelled or not
     */
    boolean onClick(ClickType clickType);

}
