package rip.cleo.oblique.example;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rip.cleo.oblique.Oblique;

import java.util.Arrays;

public class ExampleCommand implements CommandExecutor {

    private final ExamplePlugin examplePlugin;

    public ExampleCommand(ExamplePlugin examplePlugin) {
        this.examplePlugin = examplePlugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        ComponentBuilder builder = new ComponentBuilder("");

        /* Check if the commandSender has permission
         to execute this command*/
        if (!command.testPermission(commandSender)) {
            return true;
        }

        /* Check whether the commandSender is a
        player or not */
        if (!(commandSender instanceof Player)) {
            builder.append("This is only for players!").color(ChatColor.RED);
            sendComponent(commandSender, builder.create());
            return true;
        }

        builder.append("Opening the example menu...").color(ChatColor.GREEN);
        sendComponent(commandSender, builder.create());

        /* Get the player, oblique instance and
         open the example menu */
        Player player = (Player) commandSender;
        Oblique oblique = Oblique.getInstance(examplePlugin);
        oblique.getMenuService().openMenu(player, new ExampleMenu());
        return true;
    }

    private void sendComponent(CommandSender commandSender, BaseComponent... components) {
        StringBuilder stringBuilder = new StringBuilder();

        /* Convert the baseComponents into legacy */
        Arrays.stream(components).forEach(baseComponent -> {
            stringBuilder.append(baseComponent.toLegacyText());
        });
        commandSender.sendMessage(stringBuilder.toString());
    }
}
