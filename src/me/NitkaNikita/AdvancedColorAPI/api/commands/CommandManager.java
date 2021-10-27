package me.NitkaNikita.AdvancedColorAPI.api.commands;

import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.BaseChatComponent;
import me.NitkaNikita.AdvancedColorAPI.api.types.Components.SolidColor;
import me.NitkaNikita.AdvancedColorAPI.api.utils.RegExpUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(args.length <= 0){
            commandSender.sendMessage(ChatColor.AQUA+"[AdvacedColorAPI]"+ChatColor.GRAY+" version: "+ChatColor.LIGHT_PURPLE+"1.4");
        }else{
            AdvancedColor color = new AdvancedColor(args[0]);
            BaseChatComponent solid1 = new SolidColor(args[1],color);
            BaseChatComponent solid2 = new SolidColor(args[2],color);

            solid1.addComponent(solid2);
            commandSender.sendMessage(solid1.renderComponent().toLegacyText());
        }

        return true;
    }


}
