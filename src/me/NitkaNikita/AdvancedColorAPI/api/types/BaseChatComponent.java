package me.NitkaNikita.AdvancedColorAPI.api.types;

import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public class BaseChatComponent implements Cloneable {
    protected List<BaseChatComponent> _components;

    public TextComponent renderComponent() {
        TextComponent txt = new TextComponent();
        for (BaseChatComponent component : _components) {
            txt.addExtra(component.renderComponent());
        }
        return txt;
    }

    public void addComponent(BaseChatComponent component){
        _components.add(component);
    }


    @Override
    public Object clone() {
        try {
            return (BaseChatComponent) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return new BaseChatComponent();
        }
    }
}
