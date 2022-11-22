package rip.cleo.oblique;

import org.bukkit.plugin.Plugin;
import rip.cleo.oblique.menu.MenuService;
import rip.cleo.oblique.menu.impl.CommonMenuService;

/**
 * Oblique Menu API
 */
public final class Oblique {

    private static Oblique instance;
    private MenuService menuService;

    /**
     * Empty constructor for security
     */
    private Oblique() {
    }

    /**
     * Sets the static {@link Oblique} instance and instantiates the {@link MenuService}
     *
     * @param plugin the {@link Plugin}
     */
    private Oblique(Plugin plugin) {
        instance = this;
        this.menuService = new CommonMenuService(plugin);
    }

    /**
     * Get the {@link Oblique} instance, will create a new one if necessary
     *
     * @param plugin the {@link Plugin}
     * @return oblique
     */
    public static Oblique getInstance(Plugin plugin) {
        return instance == null ? new Oblique(plugin) : instance;
    }

    /**
     * Get the {@link MenuService}
     *
     * @return menuService
     */
    public MenuService getMenuService() {
        return menuService;
    }
}
