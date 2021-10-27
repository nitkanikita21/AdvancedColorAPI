package me.NitkaNikita.AdvancedColorAPI.api.types;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class AdvancedColor {
    public Color color;
    public AdvancedColor(int r, int g, int b){
        color = hex2Rgb(rgb2Hex(r,g,b));
    }
    public AdvancedColor(Color color){
       this.color = color;
    }
    public AdvancedColor(String hex){
        color = hex2Rgb(hex);
    }

    public AdvancedColor invert() {
        int r = 255 - this.color.getRed();
        int g = 255 - this.color.getGreen();
        int b = 255 - this.color.getBlue();

        if ((r + g + b > 740) || (r + g + b < 20)) {
            return new AdvancedColor(new Color(255, 255, 40));
        } else {
            return new AdvancedColor(new Color(r, g, b));
        }
    }

    public String getHex(){
        return  AdvancedColor.rgb2Hex(color.getRed(),color.getGreen(),color.getBlue());
    }

    public static Color hsl2rgb(float h, float s, float l){



        return Color.getHSBColor(h,s,l);
    }
    public static String rgb2Hex(int r, int g, int b) {
        return String.format("%02x%02x%02x", r, g, b);
    }
    public static Color hex2Rgb(String colorStr) {
        return new Color(Integer.valueOf(colorStr, 16));
    }

    private static final char COLOR_CHAR = ChatColor.COLOR_CHAR;

}
