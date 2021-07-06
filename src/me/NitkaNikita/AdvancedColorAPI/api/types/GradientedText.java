package me.NitkaNikita.AdvancedColorAPI.api.types;

import me.NitkaNikita.AdvancedColorAPI.api.SpigotMain;
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

        String[] split = text.split("");
        //ArrayList<String> TextElements = new ArrayList<>();
        //int counter = 0;
        //while (counter < text.length()){
        //    String s = split[counter];
        //    if(s.equals("§")){
        //        String adding = s;
        //        adding += split[counter+1];
        //        if(counter+2 < split.length){
        //            adding += split[counter+2];
        //        }
        //        TextElements.add(adding);
        //    }else {
        //        TextElements.add(s);
        //    }
        //}

        List<String> allMatches = new ArrayList<String>();

        //"(&[0-9a-fA-Fk-orK-OR])?."

        for (MatchResult match : RegExpUtils.allMatches(Pattern.compile("(§[0-9a-fA-Fk-orK-OR])?."), text)) {
            allMatches.add(match.group());
        }



        GradientedText gradient = new GradientedText();


        for (int i = 0; i < text.length(); i++) {
            float procent = ((float) i/(float)text.length())*100f;

            int pr = Math.round(procent);

            AdvancedColor ic = InterpolateColor(colors,(double) pr/100, X);
            int r = ic.color.getRed();
            int g = ic.color.getGreen();
            int b = ic.color.getBlue();

            TextComponent comp = new TextComponent(split[i]);

            comp.setColor(ChatColor.of("#"+AdvancedColor.rgb2Hex(r,g,b)));

            gradient.components.add(comp);
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
