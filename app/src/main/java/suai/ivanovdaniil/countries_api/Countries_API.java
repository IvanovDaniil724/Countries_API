package suai.ivanovdaniil.countries_api;

import android.net.Uri;

import java.net.URL;
import java.util.ArrayList;

public class Countries_API
{
    private String name;
    private String flag;
    /*private String[] topLevelDomain;
    private int[] callingCodes;*/
    private String capital;
    private String region;
    private String subregion;
    private int population;
    private float area;
    /*private String[] timezones;
    private String[] borders;
    private String nativeName;
    private String[] currencies;
    private String[] languages;*/

    public Countries_API(String mName, String mFlag, String mCapital, int mPopulation, float mArea,
                         String mRegion, String mSubregion)
    {
        this.name = mName;
        this.flag = mFlag;
        this.capital = mCapital;
        this.population = mPopulation;
        this.area = mArea;
        this.region = mRegion;
        this.subregion = mSubregion;
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }

    /*public String[] getTopLevelDomain() {
        return topLevelDomain;
    }

    public int[] getCallingCodes() {
        return callingCodes;
    }*/

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public int getPopulation() {
        return population;
    }

    public float getArea() {
        return area;
    }

    /*public String[] getTimezones() {
        return timezones;
    }

    public String[] getBorders() {
        return borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public String[] getLanguages() {
        return languages;
    }*/
}
