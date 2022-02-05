package me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders;

import me.NitkaNikita.AdvancedColorAPI.api.placeholders.Placeholder;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.сomponents.GradientedText;
import me.NitkaNikita.AdvancedColorAPI.api.utils.Debug;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GradientPlaceholder extends Placeholder {
    public GradientPlaceholder() {
        super("gradient");
    }

    @Override
    public TextComponent render(String[] args, Player p) {
        if (
                args.length < 4
        ) return new TextComponent("args error");

        double x = Double.parseDouble(args[2]);
        ArrayList<AdvancedColor> colors_text = new ArrayList<>();
        for (String c : args[1].split(",")) {
            colors_text.add(new AdvancedColor(c));
        }

        StringBuilder text = new StringBuilder(args[3]);
        for(int i = 4; i < args.length; i++){
            text.append("_").append(args[i]);
        }

        GradientedText gradientedText = new GradientedText(text.toString().replaceAll("\\$\\{nick}", p.getName()), colors_text, x);
        return gradientedText.renderComponent();
    }
}
