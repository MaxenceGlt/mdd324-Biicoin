package com.ipiecoles.Service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.ipiecoles.Model.BitcoinEntry;
import com.ipiecoles.Model.Error;
import com.ipiecoles.Model.GatewayRequest;
import com.ipiecoles.Model.GatewayResponse;


import java.util.HashMap;
import java.util.Map;

import static com.ipiecoles.Service.BitcoinService.MSG_ERROR_FROM_API;

public class Handler implements RequestHandler<GatewayRequest, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(GatewayRequest gatewayRequest, Context context) {
        BitcoinEntry bitcoinEntry = new Gson().fromJson(gatewayRequest.getBody(), BitcoinEntry.class);
        String body = "";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "https://pjvilloud.github.io");

        // Renvoie Erreur 500 si entrée null
        if (bitcoinEntry == null) {
            return new GatewayResponse(body, headers, 500);
        }
        BitcoinService bitcoinService = new BitcoinService(new ApiService());
        body = bitcoinService.getPriceForCurrency(bitcoinEntry.getBitcoinAmount(),bitcoinEntry.getCurrencyList());
        // Si le body est le message d'erreur "Service indisponible en provenance de l'api" on renvoie une erreur 500
        if ( body.equals(new Gson().toJson(new Error(MSG_ERROR_FROM_API)))) {
            return new GatewayResponse(body, headers, 500);
        }
        // Si la devise n'est pas reconnu on renvoie une erreur 200 avec dans le body l'objet erreurs
        return new GatewayResponse(body, headers, 200);
    }
}
