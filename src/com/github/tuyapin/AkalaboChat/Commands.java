package com.github.tuyapin.AkalaboChat;

import java.io.*;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor
{
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

}
