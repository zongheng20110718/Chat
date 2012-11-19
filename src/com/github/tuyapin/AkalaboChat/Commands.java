<<<<<<< HEAD
package com.github.tuyapin.AkalaboChat;

import java.io.*;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> Bukkit 1.3.2-R0.2 #2364

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor
{
<<<<<<< HEAD
	private ALCPlugin plugin;
	private PrintWriter pw;
	private File file;
	private FileOutputStream fos;
	private LoadFiles load;
	private String help = "";
	private String info = "";
	private ChatColor white = ChatColor.WHITE;
	private ChatColor gold = ChatColor.GOLD;
	private ChatColor aqua = ChatColor.AQUA;
	
	public Commands(ALCPlugin plugin)
	{
		this.plugin = plugin;
		this.load = new LoadFiles(plugin);
		help += aqua + "---------------ALC HELP---------------\n";
		help += gold + "/alc help" + white + ": Open the helps.\n";
		help += gold + "/alc add k [HIRAGANA]" + white + ": Add the words to list to convert of KATAKANA.\n";
		help += gold + "/alc add c [ROMAJI] [KANJI]" + white + ": Add the words to list to convert of KANJI.\n";
		help += gold + "/alc add e [ENGLISH]" + white + ": Add the words to list to not convert of ENGLISH.\n";
		help += gold + "/alc del k [HIRAGANA]" + white + ": Delete the words to list to convert of KATAKANA.\n";
		help += gold + "/alc del c [ROMAJI] [KANJI]" + white + ": Delete the words to list to convert of KANJI.\n";
		help += gold + "/alc del e [ENGLISH]" + white + ": Delete the words to list to notconvert of ENGLISH.\n";
		help += gold + "/alc info" + white + ": Open the this Plugins Information.";
		info += aqua + "--------------ALC INFO---------------\n";
		info += white + "AkalaboChat " + plugin.getVersion();
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		if(arg1.getName().equalsIgnoreCase("alc"))
		{	
			if(arg3[0].equalsIgnoreCase("help"))
			{
				arg0.sendMessage(help);
				return true;
			}
			if(arg3[0].equalsIgnoreCase("info"))
			{
				arg0.sendMessage(info);
				return true;
			}
			if(arg3[0].equalsIgnoreCase("add"))
			{
				// /alc k べっど
				if(arg3[0].equalsIgnoreCase("k"))
				{
					if(arg3.length >= 3)
					{
						arg0.sendMessage(ChatColor.RED + "Too many Arguments!!");
						return false;
					}
					try {
						file = new File(this.plugin.getDataFolder(), "kana_words.txt");
						fos = new FileOutputStream(file, true);
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
						pw.println(arg3[1]);
						pw.close();
						arg0.sendMessage(ChatColor.GREEN + "Success!");
						plugin.reload();
						return true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// /alc c ringo 林檎
				if(arg3[0].equalsIgnoreCase("c"))
				{
					if(arg3.length >= 4)
					{
						arg0.sendMessage(ChatColor.RED + "Too many Arguments!!");
						return false;
					}
					try {
						file = new File(this.plugin.getDataFolder(), "kanji_words.txt");
						fos = new FileOutputStream(file, true);
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
						pw.println(arg3[1] + " " + arg3[2]);
						pw.close();
						load.addKanji(arg3[1], arg3[2]);
						arg0.sendMessage(ChatColor.GREEN + "Success!");
						plugin.reload();
						return true;
					} catch (IOException e) {
						e.getStackTrace();
					}
				}
				// /alc e English
				if(arg3[0].equalsIgnoreCase("e"))
				{
					if(arg3.length >= 3)
					{
						arg0.sendMessage(ChatColor.RED + "Too many Arguments!!");
						return false;
					}
					try {					
						file = new File(this.plugin.getDataFolder(), "ignore_words.txt");
						fos = new FileOutputStream(file, true);
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
						pw.println(arg3[1]);
						pw.close();
						load.addIgnore(arg3[1]);
						arg0.sendMessage(ChatColor.GREEN + "Success!");
						this.plugin.reload();
						return true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}
=======
    private ALCPlugin plugin;
    private PrintWriter pw;
    private BufferedReader br;
    private File file;
    private FileOutputStream fos;
    private String help = "";
    private String info = "";
    private String clear = "";
    private String line = null;
    private String[] strList = new String[2];
    private List<String> list = new ArrayList<String>();
    private ChatColor white = ChatColor.WHITE;
    private ChatColor gold = ChatColor.GOLD;
    private ChatColor aqua = ChatColor.AQUA;
    
    
    public Commands(ALCPlugin plugin)
    {
        this.plugin = plugin;
        help += aqua + "---------------ALC HELP---------------\n";
        help += gold + "/alc help" + white + ": Open the helps.\n";
        help += gold + "/alc add k [HIRAGANA]" + white + ": Add the words to list to convert of KATAKANA.\n";
        help += gold + "/alc add c [ROMAJI] [KANJI]" + white + ": Add the words to list to convert of KANJI.\n";
        help += gold + "/alc add e [ENGLISH]" + white + ": Add the words to list to not convert of ENGLISH.\n";
        help += gold + "/alc del k [HIRAGANA]" + white + ": Delete the words to list to convert of KATAKANA.\n";
        help += gold + "/alc del c [ROMAJI] [KANJI]" + white + ": Delete the words to list to convert of KANJI.\n";
        help += gold + "/alc del e [ENGLISH]" + white + ": Delete the words to list to notconvert of ENGLISH.\n";
        help += gold + "/alc info" + white + ": Open the this Plugins Information.\n";
        help += gold + "/alc reload" + white + ": Reload to plugin.";
        info += aqua + "--------------ALC INFO---------------\n";
        info += white + "AkalaboChat " + plugin.getVersion();
        clear = ChatColor.GREEN + "Success!";
        strList[0] = "#FILE FORMAT: UTF-8N　";
        strList[1] = "#DON'T DELETE THE FIRST TWO LINES!";
    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
    {
        if(arg1.getName().equalsIgnoreCase("alc"))
        {    
            if(arg3[0].equalsIgnoreCase("help"))
            {
                arg0.sendMessage(help);
                return true;
            }
            if(arg3[0].equalsIgnoreCase("info"))
            {
                arg0.sendMessage(info);
                return true;
            }
            if(arg3[0].equalsIgnoreCase("reload"))
            {
                plugin.reload();
                return true;
            }
            
            if(arg3[0].equalsIgnoreCase("add"))
            {
                //Add a Japanese words.
                if(arg3[1].equalsIgnoreCase("k"))
                {
                    if(arg3.length != 3)
                    {
                        return false;
                    }
                    try
                    {
                        file = new File(plugin.getDataFolder(), "kana.txt");
                        fos = new FileOutputStream(file, true);
                        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                        pw.println(arg3[2]);
                        pw.close();
                        arg0.sendMessage(clear);
                        plugin.reload();
                        return true;
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                //Add a Chinese words.
                if(arg3[1].equalsIgnoreCase("c"))
                {
                    if(arg3.length != 4)
                    {
                        return false;
                    }
                    try
                    {
                        file = new File(plugin.getDataFolder(), "kanji.txt");
                        fos = new FileOutputStream(file, true);
                        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                        pw.println(arg3[2] + "　" + arg3[3]);
                        pw.close();
                        arg0.sendMessage(clear);
                        plugin.reload();
                        return true;
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                //Add a English words.
                if(arg3[1].equalsIgnoreCase("e"))
                {
                    if(arg3.length != 3)
                    {
                        return false;
                    }
                    try
                    {
                        file = new File(plugin.getDataFolder(), "english.txt");
                        fos = new FileOutputStream(file, true);
                        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                        pw.println(arg3[2]);
                        pw.close();
                        arg0.sendMessage(clear);
                        plugin.reload();
                        return true;
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            if(arg3[0].equalsIgnoreCase("del"))
            {
                //Delete a Japanese word.
                if(arg3[1].equalsIgnoreCase("k"))
                {
                    if(arg3.length != 3)
                    {
                        return false;
                    }
                    try
                    {
                        file = new File(plugin.getDataFolder(), "kana.txt");
                        br = new BufferedReader(new FileReader(file));
                        
                        while((line = br.readLine()) != null)
                        {
                            if(!arg3[2].equalsIgnoreCase(line))
                            {
                                list.add(line);
                            }
                        }
                        
                        list.set(0, strList[0]);
                        list.set(1, strList[1]);
                        
                        fos = new FileOutputStream(file, false);
                        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                        for(int j = 0; j < list.size(); j++)
                        {
                            pw.println(list.get(j));
                        }
                        arg0.sendMessage(clear);
                        plugin.reload();
                        br.close();
                        pw.close();
                        list.clear();
                        return true;
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                //Delete a Chinese word.
                if(arg3[1].equalsIgnoreCase("c"))
                {
                    if(arg3.length != 4)
                    {
                        return false;
                    }
                    try
                    {
                        file = new File(plugin.getDataFolder(), "kanji.txt");
                        br = new BufferedReader(new FileReader(file));
                        
                        while((line = br.readLine()) != null)
                        {
                            String[] str = line.split("　");
                            if(!arg3[2].equalsIgnoreCase(str[0]) && !arg3[3].equalsIgnoreCase(str[1]))
                            {
                                list.add(line);
                            }
                        }
                        
                        list.set(0, strList[0]);
                        list.set(1, strList[1]);
                        
                        fos = new FileOutputStream(file, false);
                        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                        for(int j = 0; j < list.size(); j++)
                        {
                            pw.println(list.get(j));
                        }
                        
                        arg0.sendMessage(clear);
                        plugin.reload();
                        br.close();
                        pw.close();
                        list.clear();
                        return true;
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                //Delete a English word.
                if(arg3[1].equalsIgnoreCase("e"))
                {
                    if(arg3.length != 3)
                    {
                        return false;
                    }
                    try
                    {
                        file = new File(plugin.getDataFolder(), "english.txt");
                        br = new BufferedReader(new FileReader(file));
                        while((line = br.readLine()) != null)
                        {
                            if(!arg3[2].equalsIgnoreCase(line))
                            {
                                list.add(line);
                            }
                        }
                        
                        list.set(0, strList[0]);
                        list.set(1, strList[1]);
                        
                        fos = new FileOutputStream(file, false);
                        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
                        for(int j = 0; j < list.size(); j++)
                        {
                            pw.println(list.get(j));
                        }
                        arg0.sendMessage(clear);
                        plugin.reload();
                        br.close();
                        pw.close();
                        list.clear();
                        return true;
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
>>>>>>> Bukkit 1.3.2-R0.2 #2364

}
=======
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
                            pw.println(arg3[2] + "　" + arg3[3]);
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
                                String[] str = line.split("　");
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
>>>>>>> Bukkit 1.4.2-R0.2
