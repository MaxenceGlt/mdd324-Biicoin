package com.ipiecoles;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BitcoinServiceTest {

    public String getPriceForCurrency(){
        String contenu = "{\n"+"\"bitcoinAmount\": 3,\n"+"\"currencyList\":\"EUR,USD\"\n"+"}";
        JsonObject jsonOBject = new JsonParser().parse(contenu).getAsJsonObject();
        JsonArray contents = jsonOBject.get("contents").getAsJsonObject();
        JsonArray quotes = contents.getAsJsonObject()



    }
}
