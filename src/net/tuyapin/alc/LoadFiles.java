package net.tuyapin.alc;

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
<<<<<<< HEAD

    private AkalaboChat plugin;

=======
    
    private AkalaboChat plugin;
    
>>>>>>> 172
    //ignore words
    private List<String> ignore = new ArrayList<String>();
    //convert Japanese(Katakana)
    private List<String> jpn = new ArrayList<String>();
    //convert Chinese(Kanji)
    private Map<String, String> chn = new HashMap<String, String>();
    //newline code
    @SuppressWarnings("unused")
    private String nl = System.getProperty("line.separator");
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public LoadFiles(AkalaboChat plugin)
    {
        this.plugin = plugin;
    }
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public void load()
    {
        //Load ignore.dfg
        File file = new File(plugin.getDataFolder(), "ignore.cfg");

<<<<<<< HEAD
        try
=======
        try 
>>>>>>> 172
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
<<<<<<< HEAD

=======
            
>>>>>>> 172
            InputStream is = new FileInputStream(file);
            //Mac OS Support
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
<<<<<<< HEAD

=======
            
>>>>>>> 172
            this.ignore.clear();
            while((line = br.readLine()) != null)
            {
                if(!line.startsWith("#") && !line.equals(""))
                {
                    this.ignore.add(line);
                }
            }
<<<<<<< HEAD
            br.close();

        } catch (Exception e)
        {
            this.plugin.exception(e);
        }

        //Load kana.cfg
        file = new File(this.plugin.getDataFolder(), "kana.cfg");

=======
            
        } catch (Exception e) 
        {
            this.plugin.exception(e);
        }
        
        //Load kana.cfg
        file = new File(this.plugin.getDataFolder(), "kana.cfg");
        
>>>>>>> 172
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
<<<<<<< HEAD

=======
            
>>>>>>> 172
            InputStream is = new FileInputStream(file);
            //Mac OS Support
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
<<<<<<< HEAD

=======
            
>>>>>>> 172
            this.jpn.clear();
            while((line = br.readLine()) != null)
            {
                if(!line.startsWith("#") && !line.equals(""))
                {
                    this.jpn.add(line);
                }
            }
<<<<<<< HEAD
            br.close();

=======
            
>>>>>>> 172
        } catch (Exception e)
        {
            this.plugin.exception(e);
        }
<<<<<<< HEAD

        //Load kanji.cfg
        file = new File(this.plugin.getDataFolder(), "kanji.cfg");

=======
        
        //Load kanji.cfg
        file = new File(this.plugin.getDataFolder(), "kanji.cfg");
        
>>>>>>> 172
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
<<<<<<< HEAD

=======
            
>>>>>>> 172
            InputStream is = new FileInputStream(file);
            //Mac OS Support
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            int i = 1;
<<<<<<< HEAD

=======
            
>>>>>>> 172
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
<<<<<<< HEAD

=======
                    
>>>>>>> 172
                } else {
                    if(!line.contains(" ") && !line.startsWith("#") && !line.equals(""))
                    {
                        //Syntaxエラー
                        this.plugin.logger.warning("[ALC]Error : Syntax Error 'kanji.cfg'. Line to " + i);
                    }
                }
                i++;
            }
<<<<<<< HEAD
            br.close();

=======
            
>>>>>>> 172
        } catch (Exception e)
        {
            this.plugin.exception(e);
        }
<<<<<<< HEAD

=======
        
>>>>>>> 172
        this.plugin.logger.info("[ALC]" + this.ignore.size() + " ignore words loaded!");
        this.plugin.logger.info("[ALC]" + this.jpn.size() + " kana words loaded!");
        this.plugin.logger.info("[ALC]" + this.chn.size() + " kanji words loaded!");
    }
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public List<String> getIgnoreWords()
    {
        return this.ignore;
    }
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public List<String> getKanaWords()
    {
        return this.jpn;
    }
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public Map<String, String> getKanjiWords()
    {
        return this.chn;
    }
}
