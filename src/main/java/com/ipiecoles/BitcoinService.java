package com.ipiecoles;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.EnumUtils;

import javax.swing.plaf.ColorUIResource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BitcoinService {

    /**
     * Méthode qui récupère le prix actuel du bitcoin en fonction des devises et renvoie la somme totale pour x bitcoin
     * @return La valeur de x bitcoin par rapport à son prix actuel
     * {
     *     "bitcoinAmount": 3.0,
     *     "currenciesEquivalent": {
     *         "EUR":8962.74,
     *         "USD":10225.14
     *     }
     * }
     */

    public String getPriceForCurrency(Integer nbBitcoin, String currency) {
        String msgError = "";
        Gson gson = new Gson();

        // Vérification si les currency sont présences dans notre liste
        String[] listCurrency = currency.split(",");
        for ( String cur : listCurrency) {
            if ( !EnumUtils.isValidEnum(Currency.class, cur) ) {
                msgError = "La devise n'est pas reconnue";
                return gson.toJson(msgError);
            }
        }

        // Appel à l'api : https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR,USD
        /**
         * Retour de l'api :
         * {
         *  "EUR":30520.11,
         *  "USD":37351.4
         * }
         *
         * Si pas de retour ou erreur dans le retour : Récupération du message d'erreur
         */
        gson.

        // Récupération des éléments renvoyé par l'api

        // Création de l'objet JSON de retour
                String contenu = "{\"success\": {\"total\": 1 }, \"contents\": {\"quotes\": [{\"quote\": \"Your success will not be determined by your gender or your ethnicity, but only on the scope of your dreams and your hard work to achieve them.\", \"length\": \"142\", \"author\": \"Zaha Hadid\", \"tags\": [\"dream\", \"hard-work\", \"inspire\"], \"category\": \"inspire\", \"language\": \"en\", \"date\": \"2021-05-25\", \"permalink\": \"https://theysaidso.com/quote/zaha-hadid-your-success-will-not-be-determined-by-your-gender-or-your-ethnicity\", \"id\": \"bjY_8j8kCupLt4VShbn9_QeF\", \"background\": \"https://theysaidso.com/img/qod/qod-inspire.jpg\", \"title\": \"Inspiring Quote of the day\"} ] }, \"baseurl\": \"https://theysaidso.com\", \"copyright\": {\"year\": 2023, \"url\": \"https://theysaidso.com\"} }";
        JsonObject jsonObject = new JsonParser().parse(contenu).getAsJsonObject();
        JsonObject contents = jsonObject.get("contents").getAsJsonObject();
        JsonArray quotes = contents.getAsJsonArray("quotes");
        String author = quotes.get(0).getAsJsonObject().get("author").getAsString();
        String quote = quotes.get(0).getAsJsonObject().get("quote").getAsString();
        //Je gère les erreurs...
        //Je forme l'objet de retour
        /*
            {
                "quote": "Your success will not be ...",
                "author": "Zaha Hadid",
            }
         */
//        return "{ \"quote\": " + quote + ", \"author\": \"" + quote + "\"}";
        return new Quote(author, quote);//serialisation en JSON + tard

    }

}
