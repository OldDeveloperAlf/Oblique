package rip.cleo.oblique.menu.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import rip.cleo.oblique.menu.Menu;
import rip.cleo.oblique.menu.MenuAction;
import rip.cleo.oblique.menu.RowType;
import rip.cleo.oblique.provider.Provider;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

public abstract class CommonMenu implements Menu {

    private Map<UUID, Inventory> referenceMap;
    private String title;
    private byte rows;

    public CommonMenu(String title, int rows) {
        this.referenceMap = Maps.newHashMap();
        this.title = title;
        this.rows = (byte) rows;
    }

    @Override
    public Provider<Player, Table<Integer, ItemStack, MenuAction>> itemProvider() {
        return player -> HashBasedTable.create();
    }

    protected void fill(Table<Integer, ItemStack, MenuAction> table, ItemStack itemStack, RowType... rowTypes) {
        Arrays.stream(rowTypes).forEach(rowType -> {
            /* Im not good w/ maths so.. this is ugly */
            switch (rowType) {
                case TOP: {
                    IntStream.range(0, 9).forEach(i -> {
                        table.put(i, itemStack, clickType -> true);
                    });
                }

                case BOTTOM: {
                    IntStream.range(rows * 9 - 9, rows * 9).forEach(i -> {
                        table.put(i, itemStack, clickType -> true);
                    });
                    break;
                }

                case RIGHT: {
                    for (int i = 8; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    break;
                }

                case LEFT: {
                    for (int i = 0; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    break;
                }

                case AROUND: {
                    IntStream.range(0, 9).forEach(i -> {
                        table.put(i, itemStack, clickType -> true);
                    });
                    IntStream.range(rows * 9 - 9, rows * 9).forEach(i -> {
                        table.put(i, itemStack, clickType -> true);
                    });
                    for (int i = 8; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    for (int i = 0; i < rows * 9; i += 9) {
                        table.put(i, itemStack, clickType -> true);
                    }
                    break;
                }
            }
        });

    }

    protected Inventory createInventory() {
        return Bukkit.createInventory(null, ((int) rows) * 9, title);
    }

    protected Map<UUID, Inventory> getReferenceMap() {
        return referenceMap;
    }
}
