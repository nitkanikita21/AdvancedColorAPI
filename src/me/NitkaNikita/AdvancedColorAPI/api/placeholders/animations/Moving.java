package me.NitkaNikita.AdvancedColorAPI.api.placeholders.animations;

import me.NitkaNikita.AdvancedColorAPI.api.SpigotMain;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.Animation;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.GradientedText;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Collections;

public class Moving implements Animation {
    String text;
    ArrayList<AdvancedColor> colors;

    ArrayList<AdvancedColor> generated_colors = new ArrayList<>();

    double x;

    public Moving(String text, int speed, double x, ArrayList<AdvancedColor> colors) {
        this.text = text;
        this.colors = colors;
        this.x = x;

        for(int i = 0; i < text.length(); i++){
            float procent = ((float) i/(float)text.length())*100f;

            int pr = Math.round(procent);

            AdvancedColor ic = GradientedText.InterpolateColor(colors,(double) pr/100, x);
            generated_colors.add(ic);
        }
        ArrayList list = (ArrayList) generated_colors.clone();
        Collections.reverse(list);
        generated_colors.addAll(list);

        SpigotMain.i.getServer().getScheduler().scheduleSyncRepeatingTask(SpigotMain.i, new Runnable() {

            @Override
            public void run() {
                AdvancedColor c = generated_colors.get(0);
                generated_colors.remove(0);
                generated_colors.add(c);
            }
        }, 0, speed);
    }

    @Override
    public String getText() {
        //return GradientedText.generateGradient(text,colors,x).getFullText().toLegacyText();

        TextComponent txt = new TextComponent();

        for(int i = 0; i < text.length(); i++){
            String s = text.split("")[i];


            int r = generated_colors.get(i).color.getRed();
            int g = generated_colors.get(i).color.getGreen();
            int b = generated_colors.get(i).color.getBlue();

            TextComponent comp = new TextComponent(s);
            comp.setColor(ChatColor.of("#"+AdvancedColor.rgb2Hex(r,g,b)));
            txt.addExtra(comp);
        }

        return txt.toLegacyText();
    }
}
