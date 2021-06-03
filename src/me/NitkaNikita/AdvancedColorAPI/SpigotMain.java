package me.NitkaNikita.AdvancedColorAPI;

import me.NitkaNikita.AdvancedColorAPI.commands.gradient;
import me.NitkaNikita.AdvancedColorAPI.commands.iterating;
import me.NitkaNikita.AdvancedColorAPI.commands.rainbow;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotMain extends JavaPlugin {

    public static SpigotMain i;

    @Override
    public void onEnable() {
        getCommand("gradient").setExecutor(new gradient());
        getCommand("iterator").setExecutor(new iterating());
        getCommand("rainbow").setExecutor(new rainbow());
        General.logger = getLogger();
        General.On(SessionType.SPIGOT);
        i = this;

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            new PapiHook(this,"ac").hook();
        }
    }
}
