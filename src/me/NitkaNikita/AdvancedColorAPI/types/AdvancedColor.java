package me.NitkaNikita.AdvancedColorAPI.types;

import net.md_5.bungee.api.ChatColor;

import javax.management.DescriptorKey;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }
}
