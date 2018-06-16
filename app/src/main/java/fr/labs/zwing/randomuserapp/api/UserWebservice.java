package fr.labs.zwing.randomuserapp.api;

import fr.labs.zwing.randomuserapp.database.entity.Result;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ludov on 12,juin,2018
 */
public interface UserWebservice {

    @GET(UrlManager.END_POINT)
    Call<Result> getResults();

}
