package me.NitkaNikita.AdvancedColorAPI.types;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;

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
        GradientedText gradient = new GradientedText();


        for (int i = 0; i < text.length(); i++) {
            float procent = ((float) i/(float)text.length())*100f;

            int pr = Math.round(procent);

            AdvancedColor ic = InterpolateColor(colors,(double) pr/100, X);
            int r = ic.color.getRed();
            int g = ic.color.getGreen();
            int b = ic.color.getBlue();

            TextComponent comp = new TextComponent(text.split("")[i]);
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
