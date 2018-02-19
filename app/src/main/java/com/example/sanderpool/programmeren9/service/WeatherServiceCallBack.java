package com.example.sanderpool.programmeren9.service;

import com.example.sanderpool.programmeren9.data.Channel;

public interface WeatherServiceCallBack {
    void serviceSucces(Channel channel);
    void serviceFailure(Exception exception);
}
