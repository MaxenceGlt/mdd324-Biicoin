package com.ipiecoles.Service;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.ipiecoles.Model.AnimeEntry;
import com.ipiecoles.Model.Error;
import com.ipiecoles.Model.GatewayRequest;
import com.ipiecoles.Model.GatewayResponse;

import java.util.HashMap;
import java.util.Map;

import static com.ipiecoles.Service.BitcoinService.MSG_ERROR_FROM_API;

public class HandlerAnime implements RequestHandler<GatewayRequest, GatewayResponse> {

    @Override
    public GatewayResponse handleRequest(GatewayRequest gatewayRequest, Context context) {
        AnimeEntry animeEntry = new Gson().fromJson(gatewayRequest.getBody(), AnimeEntry.class);
        var body = "";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // Renvoie Erreur 500 si entrée null
        if (animeEntry == null) {
            return new GatewayResponse(body, headers, 500);
        }
        AnimeService animeService = new AnimeService(new ApiService());
        body = animeService.getAnime(animeEntry.getSearch());
        // Si le body est le message d'erreur "Service indisponible en provenance de l'api" on renvoie une erreur 500
        if ( body.equals(new Gson().toJson(new Error(MSG_ERROR_FROM_API)))) {
            return new GatewayResponse(body, headers, 500);
        }
        return new GatewayResponse(body, headers, 200);
    }
}
