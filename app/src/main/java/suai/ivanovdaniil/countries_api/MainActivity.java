package suai.ivanovdaniil.countries_api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    List<Countries_API> countries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Objects.requireNonNull(getSupportActionBar()).hide();

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getWindow().getDecorView().setSystemUiVisibility(flags);

        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {

            @Override
            public void onSystemUiVisibilityChange(int visibility)
            {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) { decorView.setSystemUiVisibility(flags); }
            }
        });

        //RecyclerView CountriesAPI_RecyclerView = findViewById(R.id.DataRecyclerView);

        //DataRecyclerViewAdapter adapter = new DataRecyclerViewAdapter(MainActivity.this, countries);
        //CountriesAPI_RecyclerView.setAdapter(adapter);
        //CountriesAPI_RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call<List<Countries_API>> call = jsonPlaceholderAPI.getPosts();

        call.enqueue(new Callback<List<Countries_API>>()
        {
            @Override
            public void onResponse(@NonNull Call<List<Countries_API>> call, @NonNull Response<List<Countries_API>> response)
            {
                if (!response.isSuccessful())
                {
                    //Toast toast = Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG); toast.show();

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int id) { }
                    });
                    builder.setMessage(response.code()).setTitle("Ошибка");
                    AlertDialog dialog = builder.create(); dialog.show();
                    return;
                }

                List<Countries_API> posts = response.body();

                assert posts != null;
                for (Countries_API api : posts)
                {
                    countries.add(new Countries_API(api.getName(), api.getFlag(), api.getCapital(), api.getPopulation(),
                            api.getArea(), api.getRegion(), api.getSubregion()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Countries_API>> call, @NonNull Throwable t)
            {
                //Toast toast = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG); toast.show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id) { }
                });
                builder.setMessage(t.getMessage()).setTitle("Ошибка");
                AlertDialog dialog = builder.create(); dialog.show();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        RecyclerView CountriesAPI_RecyclerView = findViewById(R.id.DataRecyclerView);

        DataRecyclerViewAdapter adapter = new DataRecyclerViewAdapter(MainActivity.this, countries);
        CountriesAPI_RecyclerView.setAdapter(adapter);
        CountriesAPI_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}