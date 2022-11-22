package rip.cleo.oblique.example;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rip.cleo.oblique.menu.MenuAction;
import rip.cleo.oblique.menu.RowType;
import rip.cleo.oblique.menu.impl.CommonMenu;
import rip.cleo.oblique.provider.Provider;

public class ExampleMenu extends CommonMenu {

    public ExampleMenu() {
        super("Example", 3);
    }

    @Override
    public Provider<Player, Table<Integer, ItemStack, MenuAction>> itemProvider() {
        return player -> {
            Table<Integer, ItemStack, MenuAction> toReturn = HashBasedTable.create();

            /* Fill redstone blocks around the menu */
            fill(toReturn, new ItemStack(Material.REDSTONE_BLOCK), RowType.AROUND);

            /* Put an gold_axe in the middle of the menu */
            toReturn.put(13, new ItemStack(Material.GOLD_AXE), clickType -> {
                /* Send a message with a random chatcolor by the clickTypes enum-ordinal incremented by one */
                player.sendMessage(ChatColor.values()[clickType.ordinal() + 1] + "You clicked: " + clickType);
                player.closeInventory();
                return true;
            });

            return toReturn;
        };
    }
}
