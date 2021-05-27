package com.ipiecoles.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ipiecoles.Model.*;
import com.ipiecoles.Model.Error;
import org.apache.commons.lang3.EnumUtils;
import org.joda.time.DateTime;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class MeteoService {

    final static String MSG_ERROR_CURRENCY_METEO = "La ville n'existe pas";
    public final static String MSG_ERROR_FROM_API_METEO = "Le service Meteo est indisponible";
    ApiService apiService;
    Gson gson = new Gson();

    public MeteoService(ApiService apiService) {
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
    public String getMeteoCurrency(String currency) {
        Meteo meteoObject = new Meteo();
        // Vérification si les currency sont présences dans notre liste
            if ( currency==null ) {
                // Retourne un JSON d'erreur
                return gson.toJson(new Error(MSG_ERROR_CURRENCY_METEO));
            }
        // Appel à l'api : https://api.openweathermap.org/data/2.5/weather?q=Paris&appid=5dfc2a06c8157403e9107053a73aca92&lang=fr&main.temp=Celsius
        /**
         * Retour de l'api :
         * {
         *     "coucher":"15:53",
         *     "humidite":70,
         *     "icon":803,
         *     "lever":"07:31",
         *     "temp":8.13,
         *     "temps":"nuageux"
         * }
         *
         * Si pas de retour ou erreur dans le retour : Récupération du message d'erreur
         */
        String result = this.apiService.getMeteoFromApi("https://api.openweathermap.org/data/2.5/weather?q=".concat(URLEncoder.encode(currency, StandardCharsets.UTF_8)).concat("&appid=5dfc2a06c8157403e9107053a73aca92&lang=fr&main.temp=Celsius"));
        // Si la méthode retourne une erreur on stop le process
        if (result.equals(MSG_ERROR_FROM_API_METEO)) {
            // Retourne un JSON d'erreur
            return gson.toJson(new Error(MSG_ERROR_FROM_API_METEO));
        } else {
            // Récupération des éléments renvoyé par l'api
            JsonObject convertedObject = new Gson().fromJson(result, JsonObject.class);
            // Création de la liste Currency
            this.getCurrentMeteo(convertedObject, meteoObject);
            // Attribution à l'object Bitcoin
        }
        return gson.toJson(meteoObject);
    }

    /**
     * Méthode qui attribue aux variables de CurrencyPrice la valeur du cours du Bitcoin * bitcoinAmount
     * @param convertedObject
     * @param meteo
     * @return L'objet CurrencyPrice avec les attributs nécessaires
     */
    public void getCurrentMeteo(JsonObject convertedObject, Meteo meteo) {
            // En fonction de chaque devise initialize la valeur

        Long sunset = (convertedObject.getAsJsonObject("sys").get("sunset").getAsLong());
        String sunsetDate = Date.from(Instant.ofEpochSecond(sunset)).getHours() + ":" + Date.from(Instant.ofEpochSecond(sunset)).getMinutes();
        meteo.setCoucher(sunsetDate);
       meteo.setHumidite(convertedObject.getAsJsonObject("main").get("humidity").getAsInt());
        meteo.setIcon(convertedObject.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString());
        Long sunrise = (convertedObject.getAsJsonObject("sys").get("sunrise").getAsLong());
        String sunriseDate = Date.from(Instant.ofEpochSecond(sunrise)).getHours() + ":" + Date.from(Instant.ofEpochSecond(sunrise)).getMinutes();
        meteo.setLever(sunriseDate);
        meteo.setTemp(Math.round((convertedObject.getAsJsonObject("main").get("temp").getAsDouble() - 273d) * 100d)/100d);
        meteo.setTemps(convertedObject.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString());
    }
}
