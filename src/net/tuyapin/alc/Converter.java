package net.tuyapin.alc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tuyapin.alc.engine.AkalaboInput;
import net.tuyapin.alc.engine.EnumEngine;
import net.tuyapin.alc.engine.GoogleJapaneseInput;
import net.tuyapin.alc.engine.InputEngine;
import net.tuyapin.alc.engine.SocialIMEInput;
import net.tuyapin.alc.engine.YahooConversionInput;

public class Converter {

	private static HashMap<String, String>map = new ConvertDefiner();

    public static String convert(String text)
    {
    	String result = "";

        if(AkalaboChat.chatcolor)
        {
            text = text.replace("$0", "\u00a70");
            text = text.replace("$1", "\u00a71");
            text = text.replace("$2", "\u00a72");
            text = text.replace("$3", "\u00a73");
            text = text.replace("$4", "\u00a74");
            text = text.replace("$5", "\u00a75");
            text = text.replace("$6", "\u00a76");
            text = text.replace("$7", "\u00a77");
            text = text.replace("$8", "\u00a78");
            text = text.replace("$9", "\u00a79");
            text = text.replace("$a", "\u00a7a");
            text = text.replace("$b", "\u00a7b");
            text = text.replace("$c", "\u00a7c");
            text = text.replace("$d", "\u00a7d");
            text = text.replace("$e", "\u00a7e");
            text = text.replace("$f", "\u00a7f");
            text = text.replace("$k", "\u00a7k");
            text = text.replace("$l", "\u00a7l");
            text = text.replace("$m", "\u00a7m");
            text = text.replace("$n", "\u00a7n");
            text = text.replace("$o", "\u00a7o");
            text = text.replace("$r", "\u00a7r");
        }

        InputEngine engine = null;

        switch (AkalaboChat.engine)
        {
            case ALC:
                engine = new AkalaboInput();
                break;

            case GOOGLE:
                engine = new GoogleJapaneseInput();
                break;

            case YAHOO:
                engine = new YahooConversionInput();
                break;

            case SOCIAL:
                engine = new SocialIMEInput();
                break;

            default:
                engine = new  AkalaboInput();
                break;
        }

        List<String>ignore = AkalaboChat.files.getIgnoreWords();

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
                convert.add(cvword);
            }
        }
        if(hh)
        {
            result = StringUtils.stringBuild(convert, "");
        } else {
			result = text;
		}
        if(AkalaboChat.engine != EnumEngine.ALC)
        {
        	return engine.getText(result);
        }
        return engine.getText(text);
    }
}
