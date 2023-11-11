package me.piguy.baddesk.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class CtxBase {
    private String url;
    private HashMap<String, String> properties;
    private HashMap<String, String> parameters;
    private String contentType = "application/json";
    private String method;
    private int conTimeout = 5000;
    private int readTimeout = 5000;
    private int returnCode;

    private String body;

    // Request holder
//    private Request request = null;
    // Response holder
//    private Response response = null;

    public CtxBase(String url, String method) {
        this.url = url;
        this.method = method;
        this.parameters = new HashMap<String, String>();
        this.properties = new HashMap<String, String>();
    }

    public void setParameter(String name, String value) {
        this.parameters.put(name, value);
    }
    public void setProperty(String name, String value) {
        this.properties.put(name, value);
    }

    public String buildParameters() {
        StringBuilder result = new StringBuilder();

        for (HashMap.Entry<String, String> entry : parameters.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }

        String resultString = result.toString();
        return !resultString.isEmpty()
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    // GTR/STR

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getConTimeout() {
        return conTimeout;
    }

    public void setConTimeout(int conTimeout) {
        this.conTimeout = conTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
