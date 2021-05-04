package me.NitkaNikita.AdvancedColorAPI;

import me.NitkaNikita.AdvancedColorAPI.commands.gradient;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {



    @Override
    public void onEnable() {
        getCommand("gradient").setExecutor(new gradient());
        General.logger = getLogger();
        General.On(SessionType.SPIGOT);
    }
}
