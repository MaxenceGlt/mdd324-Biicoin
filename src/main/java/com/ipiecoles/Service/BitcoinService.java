package com.ipiecoles.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ipiecoles.Model.Bitcoin;
import com.ipiecoles.Model.Currency;
import com.ipiecoles.Model.CurrencyPrice;
import com.ipiecoles.Model.Error;
import org.apache.commons.lang3.EnumUtils;

public class BitcoinService {

    public final static String MSG_ERROR_FROM_API = "Le service est indisponible";
    final static String MSG_ERROR_CURRENCY = "La devise n'est pas reconnue";
    Gson gson = new Gson();
    ApiService apiService;

    public BitcoinService(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * Méthode qui récupère le prix actuel du bitcoin en fonction des devises et renvoie la somme totale pour x bitcoin
     * @return L'object Bitcoin
     * {
     *     "bitcoinAmount": 3.0,
     *     "currenciesEquivalent": {
     *         "EUR":8962.74,
     *         "USD":10225.14
     *     }
     * }
     */
    public String getPriceForCurrency(Integer nbBitcoin, String currency) {
        Bitcoin bitcoinObject = new Bitcoin();
        // Vérification si les currency sont présences dans notre liste
        String[] listCurrency = currency.split(",");
        for ( String cur : listCurrency) {
            if ( !EnumUtils.isValidEnum(Currency.class, cur) ) {
                // Retourne un JSON d'erreur
                return gson.toJson(new Error(MSG_ERROR_CURRENCY));
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
        String result = apiService.getHttpRequest("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=".concat(currency));
        // Si la méthode retourne une erreur on stop le process
        if (result.equals(MSG_ERROR_FROM_API)) {
            // Retourne un JSON d'erreur
            return gson.toJson(new Error(MSG_ERROR_FROM_API));
        } else {
            // Récupération des éléments renvoyé par l'api
            JsonObject convertedObject = new Gson().fromJson(result, JsonObject.class);
            // Création de la liste Currency
            CurrencyPrice currencyPrice = this.getCurrencyPrice(convertedObject, listCurrency, nbBitcoin);
            // Attribution à l'object Bitcoin
            bitcoinObject.setBitcoinAmount(nbBitcoin.doubleValue());
            bitcoinObject.setCurrenciesEquivalent(currencyPrice);
        }
        return gson.toJson(bitcoinObject);
    }


    /**
     * Méthode qui attribue aux variables de CurrencyPrice la valeur du cours du Bitcoin * bitcoinAmount
     * @param convertedObject
     * @param listCurrency
     * @param bitcoinAmount
     * @return L'objet CurrencyPrice avec les attributs nécessaires
     */
    public CurrencyPrice getCurrencyPrice(JsonObject convertedObject, String[] listCurrency, Integer bitcoinAmount) {
        CurrencyPrice currencyPrice = new CurrencyPrice();
        for (String cur: listCurrency) {
            // En fonction de chaque devise initialize la valeur
            switch (cur) {
                case "EUR":
                    currencyPrice.setEUR(Math.round( (convertedObject.get("EUR").getAsDouble() * bitcoinAmount) * 100d ) / 100d );
                    break;
                case "USD":
                    currencyPrice.setUSD(Math.round( (convertedObject.get("USD").getAsDouble() * bitcoinAmount)  * 100d ) / 100d );
                    break;
                case "GBP":
                    currencyPrice.setGBP(Math.round( (convertedObject.get("GBP").getAsDouble() * bitcoinAmount)  * 100d ) / 100d );
                    break;
                case "JPY":
                    currencyPrice.setJPY(Math.round( (convertedObject.get("JPY").getAsDouble() * bitcoinAmount)  * 100d ) / 100d );
                    break;
                default:
                    break;
            }
        }
        return currencyPrice;
    }

}
