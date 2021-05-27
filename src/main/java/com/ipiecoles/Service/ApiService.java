package com.ipiecoles.Service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static com.ipiecoles.Service.BitcoinService.MSG_ERROR_FROM_API;

public class ApiService {

    /**
     * Execute un appel GET à l'api et récupère le résultat
     * @param url
     * @return Renvoie un STRING qui contient soit le résultat de l'api soit un msg d'erreur
     */
    public String getPriceCurrencyFromApi(String url) {
        String resultFromApi;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            resultFromApi = httpClient.execute(httpGet, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    return MSG_ERROR_FROM_API;
                }
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            });
        } catch (IOException e) {
            return MSG_ERROR_FROM_API;
        }
        return resultFromApi;
    }

}
