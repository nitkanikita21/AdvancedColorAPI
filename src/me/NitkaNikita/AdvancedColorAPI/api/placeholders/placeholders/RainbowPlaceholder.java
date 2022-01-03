package me.NitkaNikita.AdvancedColorAPI.api.placeholders.placeholders;

import me.NitkaNikita.AdvancedColorAPI.api.SpigotMain;
import me.NitkaNikita.AdvancedColorAPI.api.placeholders.Placeholder;
import me.NitkaNikita.AdvancedColorAPI.api.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.api.types.сomponents.GradientedText;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class RainbowPlaceholder extends Placeholder {
    private final float[] h = {0f};
    private float s = 1f;
    private float l = 1f;

    private final float[] h2 = {0.1f};

    private final float[] i = {0};
    private int id = 0;

    private int finalId = id;

    private ArrayList<AdvancedColor> colors = new ArrayList<>();

    public RainbowPlaceholder() {
        super("rainbow");

        Runnable rn = new Runnable() {
            @Override
            public void run() {
                if(i[0] > 4f){
                    Bukkit.getScheduler().cancelTask(finalId);
                }

                h[0] += 0.02f;
                h2[0] += 0.02f;

                colors = new ArrayList<>();
                colors.add(new AdvancedColor(AdvancedColor.hsl2rgb(h[0],s,l)));
                colors.add(new AdvancedColor(AdvancedColor.hsl2rgb(h2[0],s,l)));

                i[0] += 0.02;
            }
        };

        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpigotMain.i, rn,0,1);
    }

    @Override
    public TextComponent render(String[] args) {
        StringBuilder text = new StringBuilder(args[1]);
        for(int i = 2; i < args.length; i++){
            text.append("_").append(args[i]);
        }

        return new GradientedText(text.toString(), colors, 0.4d).renderComponent();
    }
}
