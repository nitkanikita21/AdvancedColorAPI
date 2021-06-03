package me.NitkaNikita.AdvancedColorAPI.commands;

import me.NitkaNikita.AdvancedColorAPI.SpigotMain;
import me.NitkaNikita.AdvancedColorAPI.types.AdvancedColor;
import me.NitkaNikita.AdvancedColorAPI.types.GradientedText;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class rainbow implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String cmd, String[] args){
        final float[] h = {0f};
        float s = 1f;
        float l = 1f;

        final float[] h2 = {0.2f};

        if(!(commandSender instanceof Player))return false;
        Player p = (Player) commandSender;


        final float[] i = {0};
        int id = 0;

        int finalId = id;
        Runnable rn = new Runnable() {
            @Override
            public void run() {
                if(i[0] > 4f){
                    Bukkit.getScheduler().cancelTask(finalId);
                }

                if(h[0] +0.5f > 10){
                    h2[0] = 0.2f;
                }else {
                    h2[0] += 0.01f;
                }
                h[0] += 0.01;

                ArrayList<AdvancedColor> colors = new ArrayList<>();
                colors.add(new AdvancedColor(AdvancedColor.hsl2rgb(h[0],s,l)));
                colors.add(new AdvancedColor(AdvancedColor.hsl2rgb(h2[0],s,l)));

                GradientedText gr = GradientedText.generateGradient(args[1],colors,0.4d);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR,gr.getFullText());

                i[0] += 0.02;
            }
        };

        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(SpigotMain.i, rn,0,Integer.parseInt(args[0]));


        return true;
    }
}
