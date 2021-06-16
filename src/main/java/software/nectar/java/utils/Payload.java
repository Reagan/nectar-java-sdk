package software.nectar.java.utils;

import org.json.JSONObject;

import java.util.Map;

public class Payload {

    private Map<String, String> params;

    public Payload(Map<String, String> params) {
        setParams(params);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        params.forEach(obj::put);
        return obj;
    }
}
