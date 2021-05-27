package com.ipiecoles.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ipiecoles.Model.*;
import com.ipiecoles.Model.Error;
import org.apache.commons.lang3.EnumUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.ipiecoles.Service.BitcoinService.MSG_ERROR_FROM_API;

public class AnimeService {

    public final static String MSG_ERROR_ANIME_EMPTY = "Veuillez saisir une valeur à rechercher.";

    Gson gson = new Gson();
    ApiService apiService;

    public AnimeService(ApiService apiService) { this.apiService = apiService; }

    public String getAnime(String animeName) {
        new Anime();
        List<Anime> anime;

        if ( animeName.isEmpty() || animeName.isBlank()  ) {
            // Retourne un objet Error sous format JSON
            return gson.toJson(new Error(MSG_ERROR_ANIME_EMPTY));
        }
        // Appel à l'api : https://api.jikan.moe/v3/search/anime?q=
        /**
         * Retour de l'api :
         * {
         *   "request_hash": "request:search:1d2f3f8360e595f6853e0d00fca435dddf11b1e3",
         *   "request_cached": true,
         *   "request_cache_expiry": 432000,
         *   "results": [
         *     {
         *       "mal_id": 813,
         *       "url": "https://myanimelist.net/anime/813/Dragon_Ball_Z",
         *       "image_url": "https://cdn.myanimelist.net/images/anime/6/20936.jpg?s=0f99859bc8ded1ec9dbd92619b831561",
         *       "title": "Dragon Ball Z",
         *       "airing": false,
         *       "synopsis": "Five years after winning the World Martial Arts tournament, Gokuu is now living a peaceful life with his wife and son. This changes, however, with the arrival of a mysterious enemy named Raditz who pr...",
         *       "type": "TV",
         *       "episodes": 291,
         *       "score": 8.15,
         *       "start_date": "1989-04-26T00:00:00+00:00",
         *       "end_date": "1996-01-31T00:00:00+00:00",
         *       "members": 953027,
         *       "rated": "PG-13"
         *     },
         *     ...
         *     ]
         * }
         * Si pas de retour ou erreur dans le retour : Récupération du message d'erreur
         */
        String result = apiService.getHttpRequest("https://api.jikan.moe/v3/search/anime?q=".concat(URLEncoder.encode(animeName, StandardCharsets.UTF_8)));
        // Si la méthode retourne une erreur on stop le process
        if (result.equals(MSG_ERROR_FROM_API)) {
            // Retourne un JSON d'erreur
            return gson.toJson(new Error(MSG_ERROR_FROM_API));
        } else {
            // Récupération des éléments renvoyé par l'api
            JsonObject convertedObject = new Gson().fromJson(result, JsonObject.class);
            // Création de la liste Currency
            anime = this.createAnimeObject(convertedObject);
        }
        return gson.toJson(anime);
    }

    public List<Anime> createAnimeObject(JsonObject convertedObject) {
        List<Anime> animeList = new ArrayList<>();
        Anime anime = new Anime();
        for ( var result : convertedObject.get("results").getAsJsonArray()) {
            if ( result != null ) {
                anime.setId(result.getAsJsonObject().get("mal_id").getAsLong());
                anime.setUrl(result.getAsJsonObject().get("url").getAsString());
                anime.setImage_url(result.getAsJsonObject().get("image_url").getAsString());
                anime.setTitle(result.getAsJsonObject().get("title").getAsString());
                anime.setSynopsis(result.getAsJsonObject().get("synopsis").getAsString());
                anime.setEpisodes(result.getAsJsonObject().get("episodes").getAsInt());
                anime.setScore(result.getAsJsonObject().get("score").getAsDouble());
                if ( result.getAsJsonObject().get("start_date").isJsonNull() || result.getAsJsonObject().get("end_date").isJsonNull() ) {
                    anime.setStart_date("");
                    anime.setEnd_date("");
                } else {
                    anime.setStart_date(result.getAsJsonObject().get("start_date").getAsString());
                    anime.setEnd_date(result.getAsJsonObject().get("end_date").getAsString());
                }
                animeList.add(anime);
            }
        }
        return animeList;
    }
}
