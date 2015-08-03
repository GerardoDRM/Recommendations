package doge.thecraftcod.recommendations.api;


import doge.thecraftcod.recommendations.model.ActiveListings;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gerardo on 2/08/15.
 */
public interface Api {
    @GET("/listings/active")
    void activeListings(@Query("includes") String includes,
                        Callback<ActiveListings> callback);

}
