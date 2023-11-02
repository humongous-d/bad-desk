package me.piguy.baddesk.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

public class Request {
    private String path;
    private Ctx context;
    private ApiAdapter api;
    private HttpURLConnection connection;
    public Request(ApiAdapter api, String path, String method) {
        this.path = path;
        this.context = new Ctx(api.getUrl(), method);
        this.api = api;
    }

    public Ctx Do() throws IOException {
        URL url = new URL(String.format(api.getUrl() + this.path));
        connection = (HttpURLConnection) url.openConnection();

        connectionRx();

        int retCode = -1;

        try {
            retCode = connection.getResponseCode();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        System.out.printf("[%d] %s\n", retCode, url);

        return context;
    }

    private void connectionRx() throws ProtocolException {
        connection.setRequestMethod(context.getMethod());
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        // Set properties
        for (HashMap.Entry<String, String> entry : context.getProperties().entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        connection.setConnectTimeout(context.getConTimeout());
        connection.setReadTimeout(context.getReadTimeout());
    }
}
