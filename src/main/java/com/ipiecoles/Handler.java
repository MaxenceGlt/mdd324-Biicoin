package com.ipiecoles;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.ipiecoles.Service.BitcoinService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Handler implements RequestHandler<Object, GatewayResponse> {
/*    @Override
    public GatewayResponse handleRequest(Object o, Context context) {
        BitcoinService citationService = new BitcoinService();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");
        Quote quote = null;
        try {
            quote = citationService.getQuoteOfTheDay();
        } catch (IOException e) {
            //Gestion d'erreur
            return new GatewayResponse("{\"error\":\"Problème lors de la récupération de la citation du jour\")", headers, 500);
        }
        String body = new Gson().toJson(quote);
        return new GatewayResponse(body, headers, 200);
    }
    */
 
}