package hu.barbar.owm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Object to get current weather details and forecasts 
 */
public class WeatherInfoProvider {

	/**
	 * Default city_id is: Budapest XIII.
	 */
	private static final String DEFAULT_CITY_ID = "7284830";

	private String cityId = DEFAULT_CITY_ID;
	
	private static final Gson gson = new GsonBuilder().create();
	
	
	/**
	 * Constructor with default city_id.
	 */
	public WeatherInfoProvider() {}
	
	
	/**
	 * Constructor with specified city_id. <br>
	 * City_ID -s can be found at <var><code>src/additional/city_list.txt</code></var>
	 * @param cityID
	 */
	public WeatherInfoProvider(String cityID) {
		this.cityId = cityID;
	}
	
	
	/**
	 * Method to get current weather for city_id of instance.
	 * @return {@link WeatherResponse} instance
	 */
	public WeatherResponse getCurrentWeather() {
		return getCurrentWeather(this.cityId);
	}
	
	/**
	 * Method to get current weather for specified city_id.
	 * @param cityID
	 * @return {@link WeatherResponse} instance
	 */
	public WeatherResponse getCurrentWeather(String cityID) {
		if(this.cityId == null){
			return null;
		}
		String weatherJson = Request.getRawWeather(cityID);
		WeatherResponse wr = gson.fromJson(weatherJson, WeatherResponse.class);
		return wr;
	}
	
	
	/**
	 * Method to get weather forecast for city_id of instance.
	 * @return {@link ForecastResponse} instance
	 */
	public ForecastResponse getForecast() {
		return getForecast(this.cityId);
	}
	
	/**
	 * Method to get weather forecast for specified city_id.
	 * @param cityID
	 * @return {@link ForecastResponse} instance
	 */
	public ForecastResponse getForecast(String cityID) {
		if(this.cityId == null){
			return null;
		}
		String weatherJson = getRawForecast(cityID);
		ForecastResponse fr = gson.fromJson(weatherJson, ForecastResponse.class);
		return fr;
	}
	
	
	/**
	 * @return a JSON-String
	 */
	public String getRawWeather() {
		return this.getRawWeather(this.cityId);
	}
	
	/**
	 * @param cityID
	 * @return a JSON-String
	 */
	public String getRawWeather(String cityID) {
		if(this.cityId == null){
			return null;
		}
		return  Request.getRawWeather(cityID);
	}

	
	/**
	 * @return a JSON-String
	 */
	public String getRawForecast() {
		return getRawForecast(this.cityId);
	}
	
	/**
	 * @param cityID
	 * @return a JSON-String
	 */
	public String getRawForecast(String cityID) {
		if(this.cityId == null){
			return null;
		}
		return Request.getRawForecast(cityID);
	}
	
}
