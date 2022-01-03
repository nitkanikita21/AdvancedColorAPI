package me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders;

import me.NitkaNikita.AdvancedColorAPI.api.placeholders.Placeholder;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.сomponents.SolidText;
import net.md_5.bungee.api.chat.TextComponent;

public class SolidColorPlaceholder extends Placeholder {
    public SolidColorPlaceholder() {
        super("color");
    }

    @Override
    public TextComponent render(String[] args) {
        return new SolidText(args[2],new AdvancedColor(args[1])).renderComponent();
    }
}
