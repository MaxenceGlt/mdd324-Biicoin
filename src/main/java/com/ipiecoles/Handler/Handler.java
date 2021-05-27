package com.ipiecoles.Handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.ipiecoles.Model.Bitcoin;
import com.ipiecoles.Service.ApiService;
import com.ipiecoles.Service.BitcoinService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ipiecoles.Service.BitcoinService.MSG_ERROR_FROM_API;


public class Handler implements RequestHandler<GatewayRequest, GatewayResponse> {
    @Override
    public GatewayResponse handleRequest(GatewayRequest o, Context context) {
        BitcoinInput bitcoinInput = new Gson().fromJson(o.getBody(),BitcoinInput.class);
        BitcoinService bitcoinService = new BitcoinService(new ApiService());
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        String body = "";
        if(bitcoinInput==null){
            return new GatewayResponse(body, headers, 500);
        }
        body = bitcoinService.getPriceForCurrency(bitcoinInput.getBitcoinAmount(),bitcoinInput.getCurrencyList());

        if(body.equals(new Gson().toJson(MSG_ERROR_FROM_API))) {
            return new GatewayResponse(body, headers, 500);
        }
        return new GatewayResponse(body, headers, 200);
    }
}
