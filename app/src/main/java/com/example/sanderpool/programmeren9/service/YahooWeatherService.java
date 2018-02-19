package com.example.sanderpool.programmeren9.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sanderpool.programmeren9.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YahooWeatherService {

    private com.example.sanderpool.programmeren9.service.WeatherServiceCallBack callback;
    private String location;
    private Exception error;

    public YahooWeatherService(com.example.sanderpool.programmeren9.service.WeatherServiceCallBack callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    @SuppressLint("StaticFieldLeak")
    public void refreshWeather(String l) {
        this.location = l;

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", strings[0]);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));
                Log.d("doInBackgroud", "endpoint: "+ endpoint);

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return result.toString();

                } catch (Exception e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if(s == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if(count == 0) {
                        callback.serviceFailure(new locationWeatherException("No weather information found for "+location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    callback.serviceSucces(channel);

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }
            }
        }.execute(location);
    }

    public class locationWeatherException extends Exception {
        public locationWeatherException(String message) {
            super(message);
        }
    }
}
