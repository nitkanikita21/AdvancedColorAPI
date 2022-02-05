package me.NitkaNikita.AdvancedColorAPI.api;

import me.NitkaNikita.AdvancedColorAPI.api.placeholders.PlaceholderRegister;
import me.NitkaNikita.AdvancedColorAPI.api.utils.Debug;
import org.bukkit.ChatColor;

import java.util.logging.Logger;

public class General {

    public static SessionType session;
    public static Logger logger;

    public static final String vesrion = "1.6.2";

    public static void On(SessionType type){

        session = type;
        switch (type){
            case SPIGOT:
                logger.info(ChatColor.BLUE+"[AdvacedColorAPI] Spigot version");
                logger.info(ChatColor.AQUA+"[AdvacedColorAPI] Loaded!");
                break;
            case BUNGEE_CORD:
                logger.info(ChatColor.BLUE+"[AdvacedColorAPI] BungeeCord version");
                logger.info(ChatColor.AQUA+"[AdvacedColorAPI] Loaded!");
                break;
        }
        logger.info(ChatColor.DARK_RED+"==============================================");
        logger.info(ChatColor.RED+"This plugin is just a library for developing other plugins!\n");
        logger.info(ChatColor.RED+"\n" );
        logger.info(ChatColor.RED+"It contains commands for testing the plugin!");
        logger.info(ChatColor.RED+"(If you are using Spigot)");
        logger.info(ChatColor.DARK_RED+"==============================================");


    }
}
