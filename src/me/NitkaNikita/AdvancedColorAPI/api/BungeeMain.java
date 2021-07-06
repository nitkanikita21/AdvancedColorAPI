package me.NitkaNikita.AdvancedColorAPI.api;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {

    @Override
    public void onEnable() {
        General.logger = getLogger();
        General.On(SessionType.BUNGEE_CORD);
    }

}
