package suai.ivanovdaniil.countries_api;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private List<Countries_API> countries;
    private Context mainActivity;

    DataRecyclerViewAdapter(Context context, List<Countries_API> countries)
    {
        this.countries = countries;
        this.inflater = LayoutInflater.from(context);
        this.mainActivity = context;
    }

    @NonNull
    @Override
    public DataRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataRecyclerViewAdapter.ViewHolder holder, int position)
    {
        Countries_API country = countries.get(position);

        Activity activity = (Activity) mainActivity;

        holder.CountryName_TextView.setText(country.getName());
        GlideToVectorYou.justLoadImage(activity, Uri.parse(country.getFlag()), holder.CountryBackground_ImageView);
        holder.CountryCapital_TextView.setText("Capital: " + country.getCapital());
        holder.CountryPopulation_TextView.setText("Population: " + country.getPopulation());
        holder.CountryArea_TextView.setText("Area: " + country.getArea() + " km²");
        holder.CountryRegion_TextView.setText("Region: " + country.getRegion());
        holder.CountrySubregion_TextView.setText("Subregion: " + country.getSubregion());
    }

    @Override
    public int getItemCount()
    {
        return countries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        final LinearLayout RecyclerView_Layout;
        final ImageView CountryBackground_ImageView;
        final TextView CountryName_TextView;
        final TextView CountryCapital_TextView;
        final TextView CountryPopulation_TextView;
        final TextView CountryArea_TextView;
        final TextView CountryRegion_TextView;
        final TextView CountrySubregion_TextView;

        ViewHolder(View view)
        {
            super(view);
            RecyclerView_Layout = (LinearLayout) view.findViewById(R.id.RecyclerView_Layout);
            CountryBackground_ImageView = (ImageView)view.findViewById(R.id.ItemBackground_ImageView);
            CountryName_TextView = (TextView)view.findViewById(R.id.CountryName_TextView);
            CountryCapital_TextView = (TextView)view.findViewById(R.id.CountryCapital_TextView);
            CountryPopulation_TextView = (TextView)view.findViewById(R.id.CountryPopulation_TextView);
            CountryArea_TextView = (TextView)view.findViewById(R.id.CountryArea_TextView);
            CountryRegion_TextView = (TextView)view.findViewById(R.id.CountryRegion_TextView);
            CountrySubregion_TextView = (TextView)view.findViewById(R.id.CountrySubregion_TextView);
        }
    }
}
