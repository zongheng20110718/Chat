package com.github.tuyapin.AkalaboChat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor
{
	private ALCPlugin plugin;
	private PrintWriter pw;
	private BufferedReader br;
	private LineNumberReader lnr;
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
		help += gold + "/alc info" + white + ": Open the this Plugins Information.";
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
						fos = new FileOutputStream(file, true);
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
						
						while((line = br.readLine()) != null)
						{
							if(line != arg3[2])
							{
								pw.print(line);
							}
						}
						plugin.reload();
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
						lnr = new LineNumberReader(br);
						fos = new FileOutputStream(file, true);
						pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos, "UTF-8")), true);
						plugin.getLogger().fine(br.readLine());
						
						while((line = br.readLine()) != null)
						{
							plugin.getLogger().log(Level.WARNING, "" + lnr.getLineNumber());
							if(line != (arg3[2] + "　" + arg3[3]))
							{
								pw.println(line);
							}
						}
						plugin.reload();
						br.close();
						pw.close();
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
						plugin.reload();
						//fos.close();
						br.close();
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

}
