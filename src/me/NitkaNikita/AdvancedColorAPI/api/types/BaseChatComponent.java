package me.NitkaNikita.AdvancedColorAPI.api.types;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseChatComponent implements Cloneable {
    protected List<BaseChatComponent> _components = new ArrayList<>();

    public TextComponent renderComponent() {
        TextComponent txt = new TextComponent();
        for (BaseChatComponent component : _components) {
            txt.addExtra(component.renderComponent());
        }
        return txt;
    }

    public String getJsonText(){
        return renderComponent().toLegacyText();
    }

    public void addComponent(BaseChatComponent component){
        _components.add(component);
    }


    @Override
    public  Object clone() {
        try {
            return (BaseComponent) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return this;
        }
    }
}
