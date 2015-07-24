package com.raizlabs.datahub.sample.data;

import org.json.JSONException;
import org.json.JSONObject;

public interface JsonParser<Data> {
    Data parseJsonObject(JSONObject jsonObject) throws JSONException;
}