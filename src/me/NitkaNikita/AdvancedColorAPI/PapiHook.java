package me.NitkaNikita.AdvancedColorAPI;

import me.NitkaNikita.AdvancedColorAPI.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.types.GradientedText;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class PapiHook extends EZPlaceholderHook {
    public PapiHook(Plugin plugin, String identifier) {
        super(plugin, identifier);
    }

    //ac_gradient_ff0000,00ff00,0000ff_0.8_avokado
    @Override
    public String onPlaceholderRequest(Player p, String params) {
        String[] args = params.split("_");
        String result = "";

        if(args[0].equals("gradient")){
            if(
                    args[1] == null ||
                    args[2] == null ||
                    args[3] == null
            )return "args error";

            double x = Double.parseDouble(args[2]);
            ArrayList<AdvancedColor> colors = new ArrayList<>();
            for(String c : args[1].split(",")){
                colors.add(new AdvancedColor(c));
            }

            result = GradientedText.generateGradient(args[3],colors,x).getFullText().toLegacyText();
        }

        return result;
    }
}
