package com.github.tuyapin.AkalaboChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Converter
{
    private static HashMap<String, String> map = new ConvertDefiner();

    public static boolean a(String text)
    {
        return text.matches(".*[あ-んア-ン]+.*");
    }

    public static String b(String text) {
        List<String> ignoreWords = ALCPlugin.getPlugin().getRomaToHiraData().c();
        List<String> kanaWords = ALCPlugin.getPlugin().getRomaToHiraData().d();
        Map<String, String> kanjiWords = ALCPlugin.getPlugin().getRomaToHiraData().e();

        String[] words = text.trim().split("[\\s,\\.]+");

        Boolean.valueOf(false);
        ArrayList<String> convertedWords = new ArrayList<String>();
        String[] arr$;
        int len$ = (arr$ = words).length;
        String h;
        Boolean hh = false;
        for (int i$ = 0; i$ < len$; i$++)
        {
            String word = (
                    arr$[i$])
                    .replaceAll("w+", "w");
            if ((ignoreWords != null) && (ignoreWords.contains(word.toLowerCase()))) {
                convertedWords.add(word);
            }
            else
            {
                StringBuilder sb = new StringBuilder();
                int wordLen = word.length();
                for (int j = 0; j < wordLen; j++) {
                    Boolean match = Boolean.valueOf(false);

                    for (int k = 4; k > 0; k--) {
                        if (wordLen < j + k)
                            continue;
                        String s = word.substring(j, j + k).toLowerCase();

                        if (map.containsKey(s)) {
                            h = (String)map.get(s);
                            sb.append(h);
                            if (!h.equals("っ")) {
                                j += k - 1;
                            }
                            match = hh = Boolean.valueOf(true);
                            break;
                        }
                    }

                    if ((match.booleanValue()) || (wordLen <= j)) continue; sb.append(word.substring(j, j + 1));
                }

                String convertedWord = sb.toString();
                if (kanaWords.contains(convertedWord))
                    convertedWords.add(StringUtils.convertHiraToKana(convertedWord));
                else if (kanjiWords.containsKey(convertedWord))
                    convertedWords.add(kanjiWords.get(convertedWord));
                else {
                    convertedWords.add(convertedWord);
                }
            }
        }
        if (hh.booleanValue()) {
            return StringUtils.join(convertedWords, " ");
        }
        return text;
    }
}