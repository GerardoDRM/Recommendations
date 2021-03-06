package doge.thecraftcod.recommendations.api;

import doge.thecraftcod.recommendations.model.ActiveListings;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by gerardo on 2/08/15.
 */
public class Etsy {
    private static final String API_KEY = "ipsfzb4ca29p56u7n4fp7jiq";


    private static RequestInterceptor getInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addEncodedQueryParam("api_key", API_KEY);
            }
        };
    }

    private static Api getApi() {
        return new RestAdapter.Builder()
                .setEndpoint("https://openapi.etsy.com/v2")
                .setRequestInterceptor(getInterceptor())
                .build()
                .create(Api.class);
    }


    public static void getActiveListings(Callback<ActiveListings> callback) {
        getApi().activeListings("Images,Shop",callback);

    }
}
