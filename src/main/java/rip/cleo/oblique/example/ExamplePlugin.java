package rip.cleo.oblique.example;

import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        /* Register the example command */
        getCommand("example").setExecutor(new ExampleCommand(this));
    }
}
