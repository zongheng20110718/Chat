package com.github.tuyapin.AkalaboChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {
    
    private static HashMap<String, String>map = new ConvertDefiner();

    public static String convert(String text)
    {
        List<String>ignore = AkalaboChat.files.getIgnoreWords();
        List<String>jpn = AkalaboChat.files.getKanaWords();
        Map<String, String>chn = AkalaboChat.files.getKanjiWords();
        
        String[] word = text.trim().split("[\\s,\\.]+");
        String a;
        
        ArrayList<String> convert = new ArrayList<String>();
        boolean hh = false;
        
        for(int i = 0; i < word.length; i++)
        {
            String w = word[i].replaceAll("w+", "w");
            if(ignore != null && ignore.contains(w.toLowerCase()))
            {
                convert.add(w);
            } else {
                StringBuilder sb = new StringBuilder();
                int len = w.length();
                
                for(int j = 0; j < len; j++)
                {
                    boolean  m = false;
                    
                    for(int k = 4; k > 0; k--)
                    {
                        if(len < k + j)
                        {
                            continue;
                        }
                        
                        String s = w.substring(j, j + k).toLowerCase();
                        
                        if(map.containsKey(s))
                        {
                            a = map.get(s);
                            sb.append(a);
                            if(!a.equals("っ"))
                            {
                                j += k - 1;
                            }
                            m = hh = true;
                            break;
                        }
                    }
                    
                    if(m || len <= j)
                    {
                        continue;
                    }
                    sb.append(w.substring(j, j + 1));
                }
                
                String cvword = sb.toString();
                if(jpn.contains(cvword))
                {
                    convert.add(StringUtils.stringBuild(cvword));//StringUtils
                }
                else if(chn.containsKey(cvword))
                {
                    convert.add(chn.get(cvword));
                }
                else
                {
                    convert.add(cvword);
                }
            }
        }
        if(hh)
        {
            return StringUtils.stringBuild(convert, " ");
        }
        
        return text;
    }
}
