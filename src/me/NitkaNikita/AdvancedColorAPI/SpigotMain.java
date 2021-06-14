package me.NitkaNikita.AdvancedColorAPI;

import me.NitkaNikita.AdvancedColorAPI.placeholders.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {

    public static SpigotMain i;

    @Override
    public void onEnable() {
        getCommand("ac").setExecutor(null);
        General.logger = getLogger();
        General.On(SessionType.SPIGOT);
        i = this;

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new Placeholder(this,"ac").hook();
        }
    }
}
