package me.NitkaNikita.AdvancedColorAPI.api.types.Components;

import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.BaseChatComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class SolidColor extends BaseChatComponent {
    private AdvancedColor _color;
    private String _text;
    @Override
    public TextComponent renderComponent() {
        TextComponent txt = new TextComponent(_text);
        txt.setColor(ChatColor.of("#"+_color.getHex()));

        txt.addExtra(super.renderComponent());
        return txt;
    }

    public SolidColor(String text, AdvancedColor color){
        _color = color;
        _text = text;
    }
}
