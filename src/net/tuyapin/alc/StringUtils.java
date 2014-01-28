package net.tuyapin.alc;

import java.util.List;

public class StringUtils {
    
    public static String stringBuild(String[] s)
    {
        String a = "";
        for(int i = 0; i < s.length; i++)
        {
            a += s[i] + "\n";
        }
        
        return a;
    }
    
    public static String stringBuild(List<String> list, String text)
    {
        String res = "";
        for(String buf : list)
        {
            if(res.length() >= 0) res += text;
            res += buf;
        }
        
        return res;
    }
    
    public static String stringBuild(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        
        for(int i = 0; i < sb.length(); i++)
        {
            char c = sb.charAt(i);
            if((c >= 'ぁ') && (c <= 'ん'))
            {
                sb.setCharAt(i, (char)(c - 'ぁ' + 12449));
            }
        }
        
        return sb.toString();
    }

}
