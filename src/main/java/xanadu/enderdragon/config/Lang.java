package xanadu.enderdragon.config;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xanadu.enderdragon.utils.SpecialColor;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import static xanadu.enderdragon.EnderDragon.*;
import static xanadu.enderdragon.EnderDragon.server;

public class Lang {
    public static String version;
    public static String plugin_prefix;
    public static String plugin_wrong_file_version;
    public static String plugin_read_file;
    public static String plugin_item_read_error;
    public static String plugin_checking_update;
    public static String plugin_check_update_fail;
    public static String plugin_out_of_date;
    public static String plugin_up_to_date;
    public static String plugin_disable;
    public static String plugin_file_save_error;
    public static String command_no_permission;
    public static String command_reload_config;
    public static String command_only_player;
    public static String command_drop_item_add_empty;
    public static String command_drop_item_add_invalid_chance;
    public static String command_drop_item_add_succeed;
    public static String command_drop_item_clear;


    public static String gui_default_title;
    public static String gui_not_found;
    public static String gui_item_lore;

    public static String dragon_damage_display;
    public static String dragon_player_inv_full;
    public static String dragon_no_killer;
    public static String dragon_auto_respawn;
    public static String dragon_not_found;


    public static String CommandTips1;
    public static String CommandTips2;
    public static String CommandTips3;
    public static String CommandTips4;
    public static void reload(FileConfiguration file){
        Field[] fields = Lang.class.getFields();
        for(Field field : fields){
            try{
                field.set(null,"");
            }catch (Exception ignored){}
        }
        Iterator<String> it = file.getKeys(true).iterator();
        while (it.hasNext()){
            String str = it.next();
            try{
                if(!file.isString(str)) continue;
                Lang.class.getField(str.replace(".","_")).set(null,SpecialColor.transGradient(file.getString(str)));
            }catch (Exception e){
                error("Language loading error! Key: "+str);
            }
        }
        CommandTips1 = SpecialColor.transGradient(lang.getString("CommandTips1","§e/ed reload §a- reload the config"));
        CommandTips2 = SpecialColor.transGradient(lang.getString("CommandTips2","§e/ed respawn §a- respawn a dragon"));
        CommandTips3 = SpecialColor.transGradient(lang.getString("CommandTips3","§e/ed drop gui §a- view the drop_item"));
        CommandTips4 = SpecialColor.transGradient(lang.getString("CommandTips4","§e/ed drop add <name> <chance> §a- add drop_item to one dragon"));
    }
    public static void sendMessage(String str) {
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage(Lang.plugin_prefix + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                sendMessage(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage(Lang.plugin_prefix + str);
    }
    public static void info(String str){
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage("§a[EnderDragon] " + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                info(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage("§a[EnderDragon] "+str);
    }
    public static void warn(String str){
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage("§e[EnderDragon] " + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                warn(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage("§e[EnderDragon] "+str);
    }
    public static void error(String str){
        if(str == null) {
            Bukkit.getConsoleSender().sendMessage("§c[EnderDragon] " + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                error(s);
            }
        }
        else Bukkit.getConsoleSender().sendMessage("§c[EnderDragon] "+str);
    }
    public static void sendFeedback(CommandSender sender,String str){
        if(str == null) {
            sender.sendMessage(Lang.plugin_prefix + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                sender.sendMessage(Lang.plugin_prefix + s);
            }
        }
        else sender.sendMessage(Lang.plugin_prefix + str);
    }
    public static void broadcastMSG(String str){
        if(str == null) {
            Bukkit.broadcastMessage(Lang.plugin_prefix + "null");
            return;
        }
        if(str.contains("\\n")){
            String[] strings = str.split("\\\\n");
            for(String s : strings){
                broadcastMSG(s);
            }
        }
        else Bukkit.broadcastMessage(Lang.plugin_prefix + str);
    }
    public static void broadcastMSG(List<String> list){
        if(list == null) return;
        for(String str : list){
            Bukkit.broadcastMessage(Lang.plugin_prefix + str);
        }
    }
    public static void runCommands(List<String> list, Player p){
        if(list == null) return;
        for (String cmd : list) {
            if(cmd.equals("")) continue;
            if(!cmd.contains("%player%")) server.dispatchCommand(server.getConsoleSender(),cmd);
            else{
                if(p != null) server.dispatchCommand(server.getConsoleSender(),cmd.replaceAll("%player%",p.getName()));
            }
        }
    }
    public static void runCommands(List<String> list){
        if(list == null) return;
        for(String cmd : list){
            if(cmd.equals("")) continue;
            server.dispatchCommand(server.getConsoleSender(),cmd);
        }
    }

}
