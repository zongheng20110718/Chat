package net.tuyapin.alc.engine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;

import net.tuyapin.alc.AkalaboChat;

@SuppressWarnings("all")
public abstract class InputEngine {

    private String endpoint;

    public String plainText;

    public InputEngine(String point)
    {
        this.endpoint = point;
        this.plainText = "";
    }

    public String getResponse(String text)
    {
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(this.endpoint + URLEncoder.encode(text, this.getEncode()));

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), this.getEncode()));
            String line = "";
            while((line = br.readLine()) != null)
            {
                builder.append(line);
	        }
            connection.disconnect();

            this.plainText = toEUC_JP(builder.toString());

        } catch (Exception e) {
        	e.printStackTrace();
            return "";
        }

        return this.plainText;
    }

    private String toEUC_JP(String t) throws Exception
    {
    	byte[] src = t.getBytes(this.getEncode());
    	byte[] des = (new String(src, this.getEncode())).getBytes("EUC-JP");
    	t = new String(des, "EUC-JP");
    	return t;
    }

    public abstract String getText(String text);

    public abstract String getEncode();
}
