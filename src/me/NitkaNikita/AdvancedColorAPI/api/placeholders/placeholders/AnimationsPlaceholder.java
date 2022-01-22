package me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders;

import me.NitkaNikita.AdvancedColorAPI.api.placeholders.Placeholder;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.animations.Moving;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AnimationsPlaceholder extends Placeholder {

    HashMap<Integer, Moving> animationHashMap = new HashMap<Integer, Moving>();

    public AnimationsPlaceholder() {
        super("an");
    }

    @Override
    public TextComponent render(String[] args, Player p) {

        StringBuilder textBuilder = new StringBuilder(args[5]);
        for(int i = 6; i < args.length; i++){
            textBuilder.append("_").append(args[i]);
        }
        String text = textBuilder.toString().replaceAll("\\$\\{nick}",p.getName());

        if(animationHashMap.containsKey(text.hashCode())){
            return animationHashMap.get(text.hashCode()).getText();
        }
        if(args[1].equals("move")){
            double x = Double.parseDouble(args[2]);
            int speed = Integer.parseInt(args[3]);

            ArrayList<AdvancedColor> colors_text = new ArrayList<>();
            for (String c : args[4].split(",")) {
                colors_text.add(new AdvancedColor(c));
            }

            Moving an = new Moving(text, speed,x,colors_text);

            animationHashMap.put(text.hashCode(),an);
            return an.getText();
        }else {
            return new TextComponent("undefined animation");
        }
    }
}
