package com.github.tuyapin;

import java.util.List;

public class StringUtils
{
  public static String join(String[] array, String separator)
  {
    StringBuffer sb = new StringBuffer();
    for (String str : array) {
      if (sb.length() > 0) sb.append(separator);
      sb.append(str);
    }
    return sb.toString();
  }

  public static String join(List<String> list, String separator)
  {
    StringBuffer sb = new StringBuffer();
    for (String str : list) {
      if (sb.length() > 0) sb.append(separator);
      sb.append(str);
    }
    return sb.toString();
  }

  public static String convertHiraToKana(String text)
  {
    StringBuffer sb = new StringBuffer(text);
    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      if ((c >= 'ぁ') && (c <= 'ん')) {
        sb.setCharAt(i, (char)(c - 'ぁ' + 12449));
      }
    }
    return sb.toString();
  }

  public static String joinedArgs(String[] args, int initial)
  {
    StringBuffer sb = new StringBuffer();
    if (args.length <= initial) initial = 0;
    for (int i = initial; i < args.length; i++) {
      if (sb.length() > 0) sb.append(" ");
      sb.append(args[i]);
    }
    return sb.toString();
  }
}