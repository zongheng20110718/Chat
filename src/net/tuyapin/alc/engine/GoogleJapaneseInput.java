package net.tuyapin.alc.engine;

import java.util.Iterator;

import org.bukkit.craftbukkit.libs.com.google.gson.JsonArray;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonElement;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonParser;


public class GoogleJapaneseInput extends InputEngine{

    public GoogleJapaneseInput()
    {
        super("http://www.google.com/transliterate?langpair=ja-Hira|ja&text=");
    }

    @Override
    public String getText(String text) {

        text = this.getResponse(text);
        StringBuilder builder = new StringBuilder();

        try {
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(text);
            Iterator<JsonElement> iterator = rootElement.getAsJsonArray().iterator();

            while(iterator.hasNext())
            {
                JsonArray rootArray = iterator.next().getAsJsonArray();
                JsonArray jsonArray = rootArray.get(1).getAsJsonArray();
                builder.append(jsonArray.get(0).getAsString());
            }

            System.out.println(builder.toString().getBytes().length);
            return builder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String getEncode()
    {
        return "UTF-8";
    }

}
