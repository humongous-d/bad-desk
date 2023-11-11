package me.piguy.baddesk.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JSONUtility {

    public static Map<String, Object> jsonToMap(Object json) throws JSONException {

        if(json instanceof JSONObject)
            return _jsonToMap_((JSONObject)json) ;

        else if (json instanceof String)
        {
            JSONObject jsonObject = new JSONObject((String)json) ;
            return _jsonToMap_(jsonObject) ;
        }
        return null ;
    }


    private static Map<String, Object> _jsonToMap_(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }


    private static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }


    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public static String hashMapToJson(Map<String, Object> map) {
        StringBuilder jsonString = new StringBuilder("{");

        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            jsonString.append("\"").append(entry.getKey()).append("\":");

            if (entry.getValue() instanceof Map) {
                jsonString.append(mapToJson((Map<String, Object>) entry.getValue()));
            } else if (entry.getValue() instanceof List) {
                jsonString.append(listToJson((List<Object>) entry.getValue()));
            } else if (entry.getValue() instanceof Integer) {
                jsonString.append(entry.getValue());
            } else {
                jsonString.append("\"").append(entry.getValue()).append("\"");
            }

            if (iterator.hasNext()) {
                jsonString.append(",");
            }
        }

        jsonString.append("}");
        return jsonString.toString();
    }

    private static String mapToJson(Map<String, Object> map) {
        return hashMapToJson(map);
    }

    private static String listToJson(List<Object> list) {
        StringBuilder jsonArray = new StringBuilder("[");

        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();

            if (element instanceof Map) {
                jsonArray.append(mapToJson((Map<String, Object>) element));
            } else if (element instanceof List) {
                jsonArray.append(listToJson((List<Object>) element));
            } else {
                jsonArray.append("\"").append(element).append("\"");
            }

            if (iterator.hasNext()) {
                jsonArray.append(",");
            }
        }

        jsonArray.append("]");
        return jsonArray.toString();
    }

    public static ArrayList<HashMap<String, Object>> jsonArrayToList(JSONArray jsonArray) throws JSONException {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            HashMap<String, Object> factual;

            if (value instanceof JSONObject) {
                factual = (HashMap<String, Object>)jsonToMap(value);
            } else {
                throw new JSONException("Invalid JSON array element: " + value);
            }

            list.add(factual);
        }

        return list;
    }

    public static ArrayList<HashMap<String, Object>> jsonToList(String jsonString) throws JSONException {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            return jsonArrayToList(jsonArray);
        } catch (JSONException e) {
            throw new JSONException("Invalid JSON array string: " + jsonString);
        }
    }

}