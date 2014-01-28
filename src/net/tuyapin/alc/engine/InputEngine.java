package net.tuyapin.alc.engine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
            URL url = new URL(this.endpoint + URLEncoder.encode(text));

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), this.getEncode()));
            String line = "";
            while((line = br.readLine()) != null)
            {
                builder.append(line);
                builder.append("\n");
            }

            connection.disconnect();

        } catch (Exception e) {
            return "";
        }

        if(!this.getEncode().equals("UTF-8")) {
            try {
                this.plainText = new String(builder.toString().getBytes("UTF-8"), "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.plainText = builder.toString();
        }

        return this.plainText;
    }

    public abstract String getText(String text);

    public abstract String getEncode();
}
