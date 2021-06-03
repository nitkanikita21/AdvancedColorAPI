package me.NitkaNikita.AdvancedColorAPI.commands;

import me.NitkaNikita.AdvancedColorAPI.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.types.GradientedText;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class gradient implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        ArrayList<AdvancedColor> l = new ArrayList<>();
        for (int i = 2; i < args.length; i++) {
            l.add(new AdvancedColor(args[i]));
        }
        commandSender.spigot().sendMessage(GradientedText.generateGradient(
                args[1],l,Double.parseDouble(args[0])
        ).getFullText());

        return true;
    }
}
