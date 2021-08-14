package me.NitkaNikita.AdvancedColorAPI.api;

import me.NitkaNikita.AdvancedColorAPI.api.commands.CommandManager;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.Placeholder;
import me.NitkaNikita.AdvancedColorAPI.api.utils.Debug;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {

    public static SpigotMain i;

    @Override
    public void onEnable() {
        getCommand("ac").setExecutor(new CommandManager());
        General.logger = getLogger();
        Debug.setLogger(getLogger());
        General.On(SessionType.SPIGOT);
        i = this;

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new Placeholder().register();
        }
    }
}
