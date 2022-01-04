package me.NitkaNikita.AdvancedColorAPI.api.placeholders;

import me.NitkaNikita.AdvancedColorAPI.api.General;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class PlaceholderRegister extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "ac";
    }

    @Override
    public String getAuthor() {
        return "NitkaNikita";
    }

    @Override
    public String getVersion() {
        return "1.4";
    }

    //ac_gradient_ff0000,00ff00,0000ff_0.8_dradient
    //ac_rainbow_rainbow text
    //ac_color_464745_one color
    //ac_an_move_0.8_1_ff0000,00ff00,0000ff_moving gradient!

    private HashMap<String,Placeholder> placeholders = new HashMap<String, Placeholder>();

    /**
     * Register your custom sub-placeholder
     * @param pl
     */
    public PlaceholderRegister registerPlaceholder(Placeholder pl){
        General.logger.info("Successful register "+pl.getName()+" placeholder");
        placeholders.put(pl.getName(),pl);
        return this;
    }


    @Override
    public String onPlaceholderRequest(Player p, String params) {
        String[] args = params.split("_");
        String result = "";

        //General.logger.info(args.toString());

        if(placeholders.containsKey(args[0])){
            result = placeholders.get(args[0]).render(args, p).toLegacyText();
        }else{
            result = "placeholder undefined";
        }

        return result;
    }
}
