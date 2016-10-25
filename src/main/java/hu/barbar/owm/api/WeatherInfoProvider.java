package hu.barbar.owm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WeatherInfoProvider {

	private static final String DEFAULT_CITY_ID = "7284830"; 	// Budapest XIII.
	
	private String cityId = DEFAULT_CITY_ID;
	
	private static final Gson gson = new GsonBuilder().create();
	
	
	public WeatherInfoProvider() {}
	
	public WeatherInfoProvider(String cityID) {
		this.cityId = cityID;
	}
	
	
	public WeatherResponse getCurrentInfos() {
		return getCurrentInfos(this.cityId);
	}
	
	public WeatherResponse getCurrentInfos(String cityID) {
		if(this.cityId == null){
			return null;
		}
		String weatherJson = Request.getRawWeather(cityID);
		WeatherResponse wr = gson.fromJson(weatherJson, WeatherResponse.class);
		return wr;
	}
	
	
	public ForecastResponse getForecast() {
		return getForecast(this.cityId);
	}
	
	public ForecastResponse getForecast(String cityID) {
		if(this.cityId == null){
			return null;
		}
		String weatherJson = getRawForecast(cityID);
		ForecastResponse fr = gson.fromJson(weatherJson, ForecastResponse.class);
		return fr;
	}
	
	
	public String getRawWeather() {
		return this.getRawWeather(this.cityId);
	}
	
	public String getRawWeather(String cityID) {
		if(this.cityId == null){
			return null;
		}
		return  Request.getRawWeather(cityID);
	}

	
	public String getRawForecast() {
		return getRawForecast(this.cityId);
	}
	
	public String getRawForecast(String cityID) {
		if(this.cityId == null){
			return null;
		}
		return Request.getRawForecast(cityID);
	}
	
}
