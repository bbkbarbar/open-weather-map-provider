package hu.barbar.owm.api.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.barbar.owm.api.ForecastResponse;
import hu.barbar.owm.api.WeatherInfoProvider;
import hu.barbar.owm.api.WeatherResponse;

public class WeatherAppSample {

	public static void main(String[] args) {
		
		final Gson gson = new GsonBuilder().create();
		
		String cityIdOfBudapestXIII = "7284830";
		
		WeatherInfoProvider wip = new WeatherInfoProvider("7284825");
		WeatherResponse wr = wip.getCurrentInfos();
		
		System.out.println(wr);
		
		String fp = "H:mm";
		System.out.println("Day: " + wr.getSys().getSunriseFormatted(fp) + " - " + wr.getSys().getSunsetFormatted(fp));

		
		String forecast = wip.getRawForecast(cityIdOfBudapestXIII);
		System.out.println("Forecast:\n" + forecast);
		
		ForecastResponse fr = wip.getForecast();
		//System.out.println("\n\n" + fr.toString());
		
	}
}
