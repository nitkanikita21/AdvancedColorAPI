package me.NitkaNikita.AdvancedColorAPI.api.types;

import me.NitkaNikita.AdvancedColorAPI.api.utils.Debug;
import me.NitkaNikita.AdvancedColorAPI.api.utils.RegExpUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class GradientedText {
    private ArrayList<TextComponent> components = new ArrayList<>();

    public TextComponent getFullText(){
        TextComponent m = new TextComponent();

        for (TextComponent c : components) {
            m.addExtra(c);
        }

        return m;
    }

    public static GradientedText generateGradient(
            String text,
            ArrayList<AdvancedColor> colors,
            double X
    ){

        //String[] split = text.split("&\\w|.");

        List<String> split = new ArrayList<String>();

        for (MatchResult match : RegExpUtils.allMatches(Pattern.compile("&\\w|."), text)) {
            split.add(match.group());
        }

        boolean frm_l = false;
        boolean frm_m = false;
        boolean frm_n = false;
        boolean frm_o = false;

        GradientedText gradient = new GradientedText();

        for (int i = 0; i < split.size(); i++) {
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
                float procent = ((float) i/(float)split.size())*100f;

                int pr = Math.round(procent);

                AdvancedColor ic = InterpolateColor(colors,(double) pr/100, X);
                int r = ic.color.getRed();
                int g = ic.color.getGreen();
                int b = ic.color.getBlue();

                TextComponent comp = new TextComponent(split.get(i));

                //formatting
                comp.setBold(frm_l);
                comp.setUnderlined(frm_n);
                comp.setStrikethrough(frm_m);
                comp.setItalic(frm_o);

                comp.setColor(ChatColor.of("#"+AdvancedColor.rgb2Hex(r,g,b)));

                gradient.components.add(comp);
            }

        }

        return gradient;
    }

    public static AdvancedColor InterpolateColor(ArrayList<AdvancedColor> colors, double x, double c)
    {
        double r = 0.0, g = 0.0, b = 0.0;
        double total = 0.0;
        double step = 1.0 / (double)(colors.size() - 1);
        double mu = 0.0;
        double sigma_2 = c;

        for (AdvancedColor color : colors)
        {
            total += Math.exp(-(x - mu) * (x - mu) / (2.0 * sigma_2)) / Math.sqrt(2.0 * Math.PI * sigma_2);
            mu += step;
        }

        mu = 0.0;
        for(AdvancedColor color : colors)
        {
            double percent = Math.exp(-(x - mu) * (x - mu) / (2.0 * sigma_2)) / Math.sqrt(2.0 * Math.PI * sigma_2);
            mu += step;

            r += color.color.getRed() * percent / total;
            g += color.color.getGreen() * percent / total;
            b += color.color.getBlue() * percent / total;
        }

        return new AdvancedColor( (int)r, (int)g, (int)b);
    }
}
