package me.NitkaNikita.AdvancedColorAPI.api.types.builders;

import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.сomponents.GradientedText;
import me.NitkaNikita.AdvancedColorAPI.api.types.сomponents.SolidText;

import java.util.ArrayList;

public class SolidTextBuilder{

    private AdvancedColor color;
    private String text;

    public SolidTextBuilder text(String text){
        this.text = text;
        return this;
    }

    public SolidTextBuilder color(AdvancedColor color){
        this.color = color;
        return this;
    }

    public SolidTextBuilder color(String color){
        this.color = new AdvancedColor(color);
        return this;
    }

    public SolidText build(){
        return new SolidText(text,color);
    }
}
