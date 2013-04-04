package com.github.tuyapin.AkalaboChat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadFiles {
    
    private AkalaboChat plugin;
    
    //ignore words
    private List<String> ignore = new ArrayList<String>();
    //convert Japanese(Katakana)
    private List<String> jpn = new ArrayList<String>();
    //convert Chinese(Kanji)
    private Map<String, String> chn = new HashMap<String, String>();
    //newline code
    @SuppressWarnings("unused")
    private String nl = System.getProperty("line.separator");
    
    public LoadFiles(AkalaboChat plugin)
    {
        this.plugin = plugin;
    }
    
    public void load()
    {
        //Load ignore.dfg
        File file = new File(plugin.getDataFolder(), "ignore.cfg");

        try 
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            InputStream is = new FileInputStream(file);
            //Mac OS Support
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            
            this.ignore.clear();
            while((line = br.readLine()) != null)
            {
                if(!line.startsWith("#") && !line.equals(""))
                {
                    this.ignore.add(line);
                }
            }
            
        } catch (Exception e) 
        {
            this.plugin.exception(e);
        }
        
        //Load kana.cfg
        file = new File(this.plugin.getDataFolder(), "kana.cfg");
        
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            InputStream is = new FileInputStream(file);
            //Mac OS Support
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            
            this.jpn.clear();
            while((line = br.readLine()) != null)
            {
                if(!line.startsWith("#") && !line.equals(""))
                {
                    this.jpn.add(line);
                }
            }
            
        } catch (Exception e)
        {
            this.plugin.exception(e);
        }
        
        //Load kanji.cfg
        file = new File(this.plugin.getDataFolder(), "kanji.cfg");
        
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            InputStream is = new FileInputStream(file);
            //Mac OS Support
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            int i = 1;
            
            this.chn.clear();
            while((line = br.readLine()) != null)
            {
                //" "を含まない場合はSyntaxエラーとして出力
                if(!line.startsWith("#") && !line.equals("") && line.contains(" "))
                {
                    String[] buf = line.split(" ");
                    String var10 = "";
                    for(int j = 1; j < buf.length; j++)
                    {
                        var10 += buf[j];
                        if(j != buf.length - 1)
                        {
                            var10 += " ";
                        }
                    }
                    this.chn.put(buf[0], var10);
                    
                } else {
                    if(!line.contains(" ") && !line.startsWith("#") && !line.equals(""))
                    {
                        //Syntaxエラー
                        this.plugin.logger.warning("[ALC]Error : Syntax Error 'kanji.cfg'. Line to " + i);
                    }
                }
                i++;
            }
            
        } catch (Exception e)
        {
            this.plugin.exception(e);
        }
        
        this.plugin.logger.info("[ALC]" + this.ignore.size() + " ignore words loaded!");
        this.plugin.logger.info("[ALC]" + this.jpn.size() + " kana words loaded!");
        this.plugin.logger.info("[ALC]" + this.chn.size() + " kanji words loaded!");
    }
    
    public List<String> getIgnoreWords()
    {
        return this.ignore;
    }
    
    public List<String> getKanaWords()
    {
        return this.jpn;
    }
    
    public Map<String, String> getKanjiWords()
    {
        return this.chn;
    }
}
