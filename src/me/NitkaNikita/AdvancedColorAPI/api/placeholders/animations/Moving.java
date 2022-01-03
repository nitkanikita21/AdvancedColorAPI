package me.NitkaNikita.AdvancedColorAPI.api.placeholders.animations;

import me.NitkaNikita.AdvancedColorAPI.api.SpigotMain;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.IAnimation;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.сomponents.GradientedText;
import me.NitkaNikita.AdvancedColorAPI.api.utils.RegExpUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Moving implements IAnimation {
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
    public TextComponent getText() {
        TextComponent txt = new TextComponent();

        List<String> split = new ArrayList<String>();

        for (MatchResult match : RegExpUtils.allMatches(Pattern.compile("&\\w|."), text)) {
            split.add(match.group());
        }



        boolean frm_l = false;
        boolean frm_m = false;
        boolean frm_n = false;
        boolean frm_o = false;

        for(int i = 0; i < split.size(); i++){
            String s = split.get(i);

            if(split.get(i).startsWith("&")){
                switch (split.get(i).charAt(1)){
                    case 'l':
                        frm_l = true;
                        break;
                    case 'm':
                        frm_m = true;
                        break;
                    case 'n':
                        frm_n = true;
                        break;
                    case 'o':
                        frm_o = true;
                        break;
                    case 'r':
                        frm_l = false;
                        frm_m = false;
                        frm_n = false;
                        frm_o = false;
                        break;
                } //set formatting
            }else {
                int r = generated_colors.get(i).color.getRed();
                int g = generated_colors.get(i).color.getGreen();
                int b = generated_colors.get(i).color.getBlue();

                TextComponent comp = new TextComponent(s);
                comp.setColor(ChatColor.of("#"+AdvancedColor.rgb2Hex(r,g,b)));


                //formatting
                comp.setBold(frm_l);
                comp.setUnderlined(frm_n);
                comp.setStrikethrough(frm_m);
                comp.setItalic(frm_o);

                comp.setColor(ChatColor.of("#"+AdvancedColor.rgb2Hex(r,g,b)));

                txt.addExtra(comp);
            }
        }

        return txt;
    }
}
