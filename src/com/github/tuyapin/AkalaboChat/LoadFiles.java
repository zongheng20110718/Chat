<<<<<<< HEAD
package com.github.tuyapin.AkalaboChat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class LoadFiles
{
<<<<<<< HEAD
  private ALCPlugin a;
  private List<String> b;
  private List<String> c;
  private Map<String, String> d;
  private File e;
  private File f;
  private File g;

  public LoadFiles(ALCPlugin plugin)
  {
    this.a = plugin;
    this.b = new ArrayList<String>();
    this.c = new ArrayList<String>();
    this.d = new HashMap<String, String>();

    this.e = new File(plugin.getDataFolder(), "ignore_words.txt");
    this.f = new File(plugin.getDataFolder(), "kana_words.txt");
    this.g = new File(plugin.getDataFolder(), "kanji_words.txt");
  }

  public final void a() {
    if (!this.e.exists()) {
      a(this.e);
    }

    if (!this.f.exists()) {
      a(this.f);
    }

    if (!this.g.exists()) {
      a(this.g);
    }

    try
    {
      InputStream is = new FileInputStream(this.e);
      Scanner scanner = new Scanner(is, "UTF-8");
      this.b.clear();
      while (scanner.hasNextLine())
      {
        String line;
        if ((!(
          line = scanner.nextLine().trim())
          .startsWith("#")) && (!line.equals("")))
          this.b.add(line);
      }
      this.a.getLogger().info(String.valueOf(this.b.size()) + " ignore words loaded.");
    } catch (Exception e) {
      this.a.getLogger().severe("Could not load file: " + this.e.getName() + " " + e.getMessage());
    }

    try
    {
      InputStream is = new FileInputStream(this.f);
      Scanner scanner = new Scanner(is, "UTF-8");
      this.c.clear();
      while (scanner.hasNextLine())
      {
        String line;
        if ((!(
          line = scanner.nextLine().trim())
          .startsWith("#")) && (!line.equals("")))
          this.c.add(line);
      }
      this.a.getLogger().info(String.valueOf(this.c.size()) + " kana words loaded.");
    } catch (Exception e) {
      this.a.getLogger().severe("Could not load file: " + this.f.getName() + " " + e.getMessage());
    }

    try
    {
      InputStream is = new FileInputStream(this.g);
      Scanner scanner = new Scanner(is, "UTF-8");
      this.d.clear();
      while (scanner.hasNextLine())
      {
        String line;
        String[] words;
        if (((
          line = scanner.nextLine().trim())
          .startsWith("#")) || (line.equals("")) || 
          ((
          words = line.split("\\s+", 2)).length != 
          2)) continue;
        this.d.put(words[0].trim(), words[1].trim());
      }
      this.a.getLogger().info(String.valueOf(this.d.size()) + " kanji words loaded.");

      return;
    }
    catch (Exception e)
    {
      this.a.getLogger().severe("Could not load file: " + this.g.getName() + " " + e.getMessage());
    }
  }

  public final void b() {
    a();
  }

  private void a(File file)
  {
    InputStream is = null;
    OutputStream os = null;
    try {
      is = this.a.getClass().getResourceAsStream("/" + file.getName());
      os = new FileOutputStream(file);

      byte[] buf = new byte[1024];
      int len;
      while ((len = is.read(buf)) > 0) {
        os.write(buf, 0, len);
      }

      if (is != null)
        try {
          is.close();
        }
        catch (IOException localIOException1)
        {
          this.a.getLogger().severe("Could not load file: " + file.getName());
        }
      try
      {
        os.close();

        return;
      }
      catch (IOException localIOException2)
      {
        this.a.getLogger().severe("Could not load file: " + file.getName());
        return;
      }
    }
    catch (Exception e)
    {
      this.a.getLogger().severe("Could not load file: " + file.getName());

      if (is != null) {
        try {
          is.close();
        }
        catch (IOException localIOException3)
        {
          //IOException e;
          this.a.getLogger().severe("Could not load file: " + file.getName());
        }
      }

      if (os != null)
        try {
          os.close();

          return;
        }
        catch (IOException localIOException4)
        {
          this.a.getLogger().severe("Could not load file: " + file.getName());
          return;
        }
    }
    finally
    {
      if (is != null) {
        try {
          is.close();
        } catch (IOException localIOException5) {
          this.a.getLogger().severe("Could not load file: " + file.getName());
        }
      }

      if (os != null)
        try {
          os.close();
        } catch (IOException localIOException6) {
          this.a.getLogger().severe("Could not load file: " + file.getName());
        }
    }
  }

  public final List<String> c()
  {
    return this.b;
  }

  public final List<String> d() {
    return this.c;
  }

  public final Map<String, String> e() {
    return this.d;
  }
  
  public final void addIgnore(String par1)
  {
	  this.b.add(par1);
  }
  
  public final void addKana(String par1)
  {
	  this.c.add(par1);
  }
  
  public final void addKanji(String par1, String par2)
  {
	  this.d.put(par1, par2);
  }
=======
    private ALCPlugin plugin;
    private List<String> b;
    private List<String> c;
    private Map<String, String> d;
    private File e;
    private File f;
    private File g;
    
    public LoadFiles(ALCPlugin plugin)
    {
        this.plugin = plugin;
        this.b = new ArrayList<String>();
        this.c = new ArrayList<String>();
        this.d = new HashMap<String, String>();

        this.e = new File(plugin.getDataFolder(), "english.txt");
        this.f = new File(plugin.getDataFolder(), "kana.txt");
        this.g = new File(plugin.getDataFolder(), "kanji.txt");
    }

    public final void a() {
        if (!this.e.exists()) {
            a(this.e);
        }

        if (!this.f.exists()) {
            a(this.f);
        }

        if (!this.g.exists()) {
            a(this.g);
        }

        try
        {
            InputStream is = new FileInputStream(this.e);
            Scanner scanner = new Scanner(is, "UTF-8");
            this.b.clear();
            while (scanner.hasNextLine())
            {
                String line;
                if ((!(
                        line = scanner.nextLine().trim())
                        .startsWith("#")) && (!line.equals("")))
                    this.b.add(line);
            }
            this.plugin.getLogger().info(String.valueOf(this.b.size()) + " ignore words loaded.");
        } catch (Exception e) {
            this.plugin.getLogger().severe("Could not load file: " + this.e.getName() + " " + e.getMessage());
        }

        try
        {
            InputStream is = new FileInputStream(this.f);
            Scanner scanner = new Scanner(is, "UTF-8");
            this.c.clear();
            while (scanner.hasNextLine())
            {
                String line;
                if ((!(
                        line = scanner.nextLine().trim())
                        .startsWith("#")) && (!line.equals("")))
                    this.c.add(line);
            }
            this.plugin.getLogger().info(String.valueOf(this.c.size()) + " kana words loaded.");
        } catch (Exception e) {
            this.plugin.getLogger().severe("Could not load file: " + this.f.getName() + " " + e.getMessage());
        }

        try
        {
            InputStream is = new FileInputStream(this.g);
            Scanner scanner = new Scanner(is, "UTF-8");
            this.d.clear();
            while (scanner.hasNextLine())
            {
                String line;
                String[] words;
                if (((
                        line = scanner.nextLine().trim())
                        .startsWith("#")) || (line.equals("")) || 
                        ((
                                words = line.split("　", 2)).length != 
                                2)) continue;
                this.d.put(words[0].trim(), words[1].trim());
            }
            this.plugin.getLogger().info(String.valueOf(this.d.size()) + " kanji words loaded.");

            return;
        }
        catch (Exception e) {
            this.plugin.getLogger().severe("Could not load file: " + this.g.getName() + " " + e.getMessage());
        }
  }

    public final void b() {
      a();
    }

    private void a(File file)
    {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = this.plugin.getClass().getResourceAsStream("/" + file.getName());
            os = new FileOutputStream(file);

            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) > 0) {
                os.write(buf, 0, len);
            }
            
            if (is != null)
                try {
                    is.close();
                }
            catch (IOException localIOException1)
            {
                this.plugin.getLogger().severe("Could not load file: " + file.getName());
            }
            try
            {
                os.close();
                return;
            }
            catch (IOException localIOException2)
            {
                this.plugin.getLogger().severe("Could not load file: " + file.getName());
                return;
            }
            }
            catch (Exception e)
            {
                this.plugin.getLogger().severe("Could not load file: " + file.getName());

                if (is != null) {
                    try {
                        is.close();
                    }
                    catch (IOException localIOException3)
                    {
                        //IOException e;
                        this.plugin.getLogger().severe("Could not load file: " + file.getName());
                    }    
                }

                if (os != null)
                    try {
                        os.close();
                        return;
                    }
                catch (IOException localIOException4)
                {
                    this.plugin.getLogger().severe("Could not load file: " + file.getName());
                    return;
                }
            }
        finally
        {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException localIOException5) {
                    this.plugin.getLogger().severe("Could not load file: " + file.getName());
                }
            }

            if (os != null)
                try {
                    os.close();
                } catch (IOException localIOException6) {
                    this.plugin.getLogger().severe("Could not load file: " + file.getName());
                }
        }
    }

    public final List<String> c()
    {
        return this.b;
    }

    public final List<String> d() {
        return this.c;
    }

    public final Map<String, String> e() {
        return this.d;
    }
>>>>>>> Bukkit 1.3.2-R0.2 #2364
   
}
=======
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
>>>>>>> Bukkit 1.4.2-R0.2
