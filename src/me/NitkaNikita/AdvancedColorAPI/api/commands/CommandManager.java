package me.NitkaNikita.AdvancedColorAPI.api.commands;

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

        if(args.length == 0){
            commandSender.sendMessage(ChatColor.AQUA+"[AdvacedColorAPI]"+ChatColor.GRAY+" version: "+ChatColor.LIGHT_PURPLE+"1.4");
        }

        return true;
    }


}
