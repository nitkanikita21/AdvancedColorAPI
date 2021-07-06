package me.NitkaNikita.AdvancedColorAPI;

import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.GradientedText;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;

public class AdvancedColorAPI {
    public TextComponent gradient(
            String text,
            ArrayList<AdvancedColor> colors,
            double X
    ){
        return GradientedText.generateGradient(text,colors,X).getFullText();
    }


}
