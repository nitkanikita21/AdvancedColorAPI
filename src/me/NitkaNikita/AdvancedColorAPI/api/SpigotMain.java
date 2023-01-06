package me.NitkaNikita.AdvancedColorAPI.api;

import me.NitkaNikita.AdvancedColorAPI.api.commands.CommandManager;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.Placeholder;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.PlaceholderRegister;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders.AnimationsPlaceholder;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders.GradientPlaceholder;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders.RainbowPlaceholder;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders.SolidColorPlaceholder;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.builders.GradientTextBuilder;
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
            PlaceholderRegister placeholderRegister = new PlaceholderRegister();

            placeholderRegister
                    .registerPlaceholder(new AnimationsPlaceholder())
                    .registerPlaceholder(new GradientPlaceholder())
                    .registerPlaceholder(new SolidColorPlaceholder())
                    .registerPlaceholder(new RainbowPlaceholder());

            placeholderRegister.register();
            Bukkit.getScheduler().runTaskTimer(this,()->{
              if(!placeholderRegister.isRegistered()) placeholderRegister.register();
            },20,100);
        }
    }
}
