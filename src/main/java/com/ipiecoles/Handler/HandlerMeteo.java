package com.ipiecoles.Handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.ipiecoles.Model.Meteo;
import com.ipiecoles.Model.MeteoEntry;
import com.ipiecoles.Service.ApiService;
import com.ipiecoles.Service.BitcoinService;
import com.ipiecoles.Service.MeteoService;

import java.util.HashMap;
import java.util.Map;

import static com.ipiecoles.Service.MeteoService.MSG_ERROR_FROM_API_METEO;

public class HandlerMeteo implements RequestHandler<GatewayRequest, GatewayResponse> {
    @Override
    public GatewayResponse handleRequest(GatewayRequest o, Context context) {
        MeteoEntry meteoEntry = new Gson().fromJson(o.getBody(), MeteoEntry.class);
        MeteoService meteoService = new MeteoService(new ApiService());
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        String body = "";
        if (meteoEntry == null) {
            return new GatewayResponse(body, headers, 500);
        }
        body = meteoService.getMeteoCurrency((meteoEntry.getCity()));

        if (body.equals(new Gson().toJson(MSG_ERROR_FROM_API_METEO))) {
            return new GatewayResponse(body, headers, 500);
        }
        return new GatewayResponse(body, headers, 200);
    }
}
