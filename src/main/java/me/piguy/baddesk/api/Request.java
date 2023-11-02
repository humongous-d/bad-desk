package me.piguy.baddesk.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Request {
    private String path;
    private Ctx context;
    private ApiAdapter api;
    private URL url;
    private HttpURLConnection connection;
    private static String BOUNDARY = "BAD_API_BOUNDARY";
    public Request(ApiAdapter api, String path) throws IOException {
        this.path = path;
        this.api = api;
        url = new URL(String.format(api.getUrl() + this.path));
    }

    public Request Get(){
        this.context = new Ctx(api.getUrl(), "GET");
        return this;
    }
    public Request Post(){
        this.context = new Ctx(api.getUrl(), "GET");
        return this;
    }

    public Request Do() throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        connectionRx();
        return this;
    }

    public Ctx Listen(){
        try {
            // Return code
            context.setReturnCode(connection.getResponseCode());
            // Body
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder body = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            context.setBody(body.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        System.out.printf("[%d] %s\n", context.getReturnCode(), url);

        return context;
    }

    public void setProperty(String key, String value) {
        context.setProperty(key, value);
    }

    public Request withAuth() throws ProtocolException {
        setProperty("Authorization", "Bearer " + api.getToken());
        return this;
    }
    public Request withAuth(String token) {
        setProperty("Authorization", "Bearer " + token);
        return this;
    }

    public Request DoWithMultipartForm(HashMap<String, String> formData) throws IOException {
        connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = getWriter(formData, outputStream);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception according to your application's needs
        }

        return this;
    }

    private static OutputStreamWriter getWriter(HashMap<String, String> formData, OutputStream outputStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        // Add form fields
        for (HashMap.Entry<String, String> entry : formData.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();

            writer.write("--" + BOUNDARY + "\r\n");
            writer.write("Content-Disposition: form-data; name=\"" + fieldName + "\"\r\n\r\n");
            writer.write(fieldValue + "\r\n");
        }

        writer.write("--" + BOUNDARY + "--\r\n");
        writer.flush();
        return writer;
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
