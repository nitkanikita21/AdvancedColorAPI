package me.NitkaNikita.AdvancedColorAPI.types;

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


    public static String rgb2Hex(int r, int g, int b) {
        return String.format("%02x%02x%02x", r, g, b);
    }
    public static Color hex2Rgb(String colorStr) {
        return new Color(Integer.valueOf(colorStr, 16));
    }
}
