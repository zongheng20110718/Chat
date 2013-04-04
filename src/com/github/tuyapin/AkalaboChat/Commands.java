package com.github.tuyapin.AkalaboChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    
    private String helpMessage;
    private String clear;
    private String info;
    private String line;
    private List<String>list = new ArrayList<String>();
    
    private File file;
    private FileOutputStream fos;
    private PrintWriter pw;
    private BufferedReader br;
    
    private ChatColor gold = ChatColor.GOLD;
    private ChatColor white = ChatColor.WHITE;
    
    public Commands()
    {
       
        String[] mes = new String[9];
        mes[0] = ChatColor.AQUA + "---------------ALC HELP---------------";
        mes[1] = gold + "/alc add k p1" + white + ": カタカナに変換する文字列を追加します。 ";
        mes[2] = gold + "/alc add c p1 p2" + white + ": 漢字に変換する文字列を追加します。";
        mes[3] = gold + "/alc add e p1" + white + ": 変換を行わない文字列を追加します。";
        mes[4] = gold + "/alc del k p1" + white + ": カタカナに変換する文字列を削除します。";
        mes[5] = gold + "/alc del c p1 p2" + white + ": 漢字に変換する文字列を削除します。";
        mes[6] = gold + "/alc del e p1" + white + ": 変換を行わない文字列を削除します。";
        mes[7] = gold + "/alc reload" + white + ": プラグインの設定を再読み込みします。";
        mes[8] = gold + "/alc info" + white + ": ALCの情報を表示します。";
        this.helpMessage = StringUtils.stringBuild(mes);
        this.clear = ChatColor.GREEN + "Success!";
        
        this.info = ChatColor.AQUA + "---------------ALC INFO ---------------\n";
        this.info += "AkalaboChat " + AkalaboChat.plugin.getVersion();
        
    }
    
    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        Player player = null;
        if(arg0 instanceof Player)
        {
            player = (Player) arg0;
        }
        
        if(arg1.getName().equalsIgnoreCase("alc"))
        {
            if(arg3[0].equalsIgnoreCase("help"))
            {
                if(player == null || player.hasPermission("alc.help") || player.isOp())
                {
                    arg0.sendMessage(helpMessage);
                    return true;
                }
            }
            
            if(arg3[0].equalsIgnoreCase("info"))
            {
                arg0.sendMessage(info);
                return true;
            }
            
            if(arg3[0].equalsIgnoreCase("reload"))
            {
                if(player == null ||  player.isOp())
                {
                    AkalaboChat.plugin.reload();
                    return true;
                }
            }
            
            if(arg3[0].equalsIgnoreCase("add"))
            {
                if(player == null || player.hasPermission("alc.add"))
                {
                    if(arg3[1].equalsIgnoreCase("k"))
                    {
                        if(arg3.length != 3)
                        {
                            return false;
                        }
                        try
                        {
                            file = new File(AkalaboChat.plugin.getDataFolder(), "kana.cfg");
                            fos = new FileOutputStream(file, true);
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                            pw.print(arg3[2]);
                            pw.close();
                            arg0.sendMessage(clear);
                            AkalaboChat.plugin.reload();
                            return true;
                            
                        } catch (Exception e)
                        {
                            AkalaboChat.plugin.exception(e);
                        }
                    }
                    
                    if(arg3[1].equalsIgnoreCase("c"))
                    {
                        if(arg3.length != 4)
                        {
                            return false;
                        }
                        try
                        {
                            file = new File(AkalaboChat.plugin.getDataFolder(), "kanji.cfg");
                            fos = new FileOutputStream(file, true);
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                            pw.println(arg3[2] + " " + arg3[3]);
                            pw.close();
                            arg0.sendMessage(clear);
                            AkalaboChat.plugin.reload();
                            return true;
                        }catch(Exception e)
                        {
                            AkalaboChat.plugin.exception(e);
                        }
                    }
                    
                    if(arg3[1].equalsIgnoreCase("e"))
                    {
                        if(arg3.length != 3)
                        {
                            return false;
                        }
                        try
                        {
                            file = new File(AkalaboChat.plugin.getDataFolder(), "ignore.cfg");
                            fos = new FileOutputStream(file, true);
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                            pw.println(arg3[2]);
                            pw.close();
                            arg0.sendMessage(clear);
                            AkalaboChat.plugin.reload();
                            return true;
                        }catch(Exception e)
                        {
                            AkalaboChat.plugin.exception(e);
                        }
                    }
                }
            }
            
            if(arg3[1].equalsIgnoreCase("del"))
            {
                if(player == null || player.hasPermission("alc.del"))
                {
                    if(arg3[1].equalsIgnoreCase("k"))
                    {
                        if(arg3.length != 3)
                        {
                            return false;
                        }
                        try
                        {
                            file = new File(AkalaboChat.plugin.getDataFolder(), "kana.cfg");
                            br = new BufferedReader(new FileReader(file));
                            
                            while((line = br.readLine()) != null)
                            {
                                if(!arg3[2].equalsIgnoreCase(line))
                                {
                                    list.add(line);
                                }
                            }
                            
                            fos = new FileOutputStream(file, false);
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                            for(int j = 0; j < list.size(); j++)
                            {
                                pw.println(list.get(j));
                            }
                            arg0.sendMessage(clear);
                            AkalaboChat.plugin.reload();
                            br.close();
                            pw.close();
                            list.clear();
                            return true;
                        }catch(Exception e)
                        {
                            AkalaboChat.plugin.exception(e);
                        }
                    }
                    
                    if(arg3[1].equalsIgnoreCase("c"))
                    {
                        if(arg3.length != 4)
                        {
                            return false;
                        }
                        try
                        {
                            file = new File(AkalaboChat.plugin.getDataFolder(), "kanji.cfg");
                            br = new BufferedReader(new FileReader(file));
                            
                            while((line = br.readLine()) != null)
                            {
                                String[] str = line.split(" ");
                                if(!arg3[2].equalsIgnoreCase(str[0]) && !arg3[3].equalsIgnoreCase(str[1]))
                                {
                                    list.add(line);
                                }
                            }
                            
                            fos = new FileOutputStream(file, false);
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                            for(int j = 0; j < list.size(); j++)
                            {
                                pw.println(list.get(j));
                            }
                            
                            arg0.sendMessage(clear);
                            AkalaboChat.plugin.reload();
                            br.close();
                            pw.close();
                            list.clear();
                            return true;
                        } catch (Exception e)
                        {
                            AkalaboChat.plugin.exception(e);
                        }
                    }
                    
                    if(arg3[1].equalsIgnoreCase("e"))
                    {
                        if(arg3.length != 3)
                        {
                            return false;
                        }
                        try
                        {
                            file = new File(AkalaboChat.plugin.getDataFolder(), "ignore.cfg");
                            br = new BufferedReader(new FileReader(file));
                            while((line = br.readLine()) != null)
                            {
                                if(!arg3[2].equalsIgnoreCase(line))
                                {
                                    list.add(line);
                                }
                            }
                            
                            fos = new FileOutputStream(file, false);
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                            for(int j = 0; j < list.size(); j++)
                            {
                                pw.println(list.get(j));
                            }
                            arg0.sendMessage(clear);
                            AkalaboChat.plugin.reload();
                            br.close();
                            pw.close();
                            list.clear();
                            return true;
                        } catch (Exception e)
                        {
                            AkalaboChat.plugin.exception(e);
                        }
                    }
                }
            }
        }
        return false;
    }
}
