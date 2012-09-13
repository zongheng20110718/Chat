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
   
}