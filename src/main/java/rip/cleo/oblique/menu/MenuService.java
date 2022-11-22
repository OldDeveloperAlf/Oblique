package rip.cleo.oblique.menu;

import org.bukkit.entity.Player;

/**
 * Simple MenuService interface to handle {@link Player} {@link Menu}'s
 */
public interface MenuService {

    /**
     * Called to open a {@link Menu} for a {@link Player}
     *
     * @param player the {@link Player}
     * @param menu   the {@link Menu}
     */
    void openMenu(Player player, Menu menu);

    /**
     * Get the {@link Player}'s current open {@link Menu}
     * Returns null if the player doesn't have a menu open.
     *
     * @param player the {@link Player}
     * @return menu
     */
    Menu getOpenMenu(Player player);

}
