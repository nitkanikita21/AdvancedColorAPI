package me.NitkaNikita.AdvancedColorAPI.api.types.builders;

import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.сomponents.GradientedText;

import java.util.ArrayList;

public class GradientTextBuilder{

    private ArrayList<AdvancedColor> colors = new ArrayList<>();
    private String text;
    private double blur;

    public GradientTextBuilder text(String text){
        this.text = text;
        return this;
    }
    public GradientTextBuilder blur(double x){
        blur = x;
        return this;
    }

    public GradientTextBuilder addColor(AdvancedColor color){
        colors.add(color);
        return this;
    }

    public GradientTextBuilder addColor(String hex){
        colors.add(new AdvancedColor(hex));
        return this;
    }

    public GradientedText build(){
        return new GradientedText(text,colors,blur);
    }
}
