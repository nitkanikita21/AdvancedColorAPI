package me.NitkaNikita.AdvancedColorAPI.api.placeholders;

import me.NitkaNikita.AdvancedColorAPI.api.SpigotMain;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.animations.Moving;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.GradientedText;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Placeholder extends PlaceholderExpansion {

    final float[] h = {0f};
    float s = 1f;
    float l = 1f;

    final float[] h2 = {0.1f};

    final float[] i = {0};
    int id = 0;

    int finalId = id;

    //======[Animations]=======

    HashMap<Integer, Moving> animationHashMap = new HashMap<Integer, Moving>();


    ArrayList<AdvancedColor> colors = new ArrayList<>();

    public Placeholder() {
        Runnable rn = new Runnable() {
            @Override
            public void run() {
                if(i[0] > 4f){
                    Bukkit.getScheduler().cancelTask(finalId);
                }

                h[0] += 0.02f;
                h2[0] += 0.02f;

                colors = new ArrayList<>();
                colors.add(new AdvancedColor(AdvancedColor.hsl2rgb(h[0],s,l)));
                colors.add(new AdvancedColor(AdvancedColor.hsl2rgb(h2[0],s,l)));

                i[0] += 0.02;
            }
        };

        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpigotMain.i, rn,0,1);
    }

    @Override
    public String getIdentifier() {
        return "ac";
    }

    @Override
    public String getAuthor() {
        return "NitkaNikita";
    }

    @Override
    public String getVersion() {
        return "1.4";
    }

    //ac_gradient_ff0000,00ff00,0000ff_0.8_dradient
    //ac_rainbow_rainbow text
    //ac_color_464745_one color
    //ac_an_move_0.8_1_ff0000,00ff00,0000ff_moving gradient!
    @Override
    public String onPlaceholderRequest(Player p, String params) {
        String[] args = params.split("_");
        String result = "";

        if (args[0].equals("gradient")) {
            if (
                    args.length < 4
            ) return "args error";

            double x = Double.parseDouble(args[2]);
            ArrayList<AdvancedColor> colors_text = new ArrayList<>();
            for (String c : args[1].split(",")) {
                colors_text.add(new AdvancedColor(c));
            }

            StringBuilder text = new StringBuilder(args[3]);
            for(int i = 4; i < args.length; i++){
                text.append("_").append(args[i]);
            }

            result = GradientedText.generateGradient(text.toString(), colors_text, x).getFullText().toLegacyText();
        } else if (args[0].equals("rainbow")) {

            StringBuilder text = new StringBuilder(args[1]);
            for(int i = 2; i < args.length; i++){
                text.append("_").append(args[i]);
            }

            result = GradientedText.generateGradient(text.toString(), colors, 0.4d).getFullText().toLegacyText();
        }else if (args[0].equals("color")) {
            ArrayList<AdvancedColor> c = new ArrayList<AdvancedColor>();
            c.add(new AdvancedColor(args[1]));

            StringBuilder text = new StringBuilder(args[2]);
            for(int i = 3; i < args.length; i++){
                text.append("_").append(args[i]);
            }

            result = GradientedText.generateGradient(text.toString(), c, 0.4d).getFullText().toLegacyText();
        } else if (args[0].equals("an")){
            if(animationHashMap.containsKey(params.hashCode())){
                return animationHashMap.get(params.hashCode()).getText();
            }
            if(args[1].equals("move")){
                double x = Double.parseDouble(args[2]);
                int speed = Integer.parseInt(args[3]);

                ArrayList<AdvancedColor> colors_text = new ArrayList<>();
                for (String c : args[4].split(",")) {
                    colors_text.add(new AdvancedColor(c));
                }

                StringBuilder text = new StringBuilder(args[5]);
                for(int i = 6; i < args.length; i++){
                    text.append("_").append(args[i]);
                }

                Moving an = new Moving(text.toString(),speed,x,colors_text);

                animationHashMap.put(params.hashCode(),an);
                return an.getText();
            }

        }

        return result;
    }
}
