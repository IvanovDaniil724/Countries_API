package suai.ivanovdaniil.countries_api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderAPI
{
    @GET("all")
    Call<List<Countries_API>> getPosts();
}
