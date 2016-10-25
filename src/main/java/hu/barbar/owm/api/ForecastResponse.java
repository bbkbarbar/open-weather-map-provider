package hu.barbar.owm.api;

import java.util.List;


/**
 * Forecast representation
 */
public class ForecastResponse {
	
	public class City {
		
		public class Coord {
			private float lon;
			private float lat;
			
			@Override
			public String toString(){
				return "Lon: " + lon + " lat: " + lat;
			}
		}
		
		public class Sys {
			private int population;
			
			@Override
			public String toString(){
				return "population: " + population;
			}
		}
		
		private int id;
		private String name;
		private Coord coord;
		private String country;
		private int population;
		private Sys sys;
		
		@Override
		public String toString() {
			return "id: " + id + "\n" +
					"name: " + name + "\n" +
					"coord: " + coord + "\n" +
					"country: " + country + "\n" +
					"population: " + population + "\n" +
					"sys: " + sys + "\n";
		}
		
	}

	public class Forecast {
		
		public class Main {
			private float temp;
			private float temp_min;
			private float temp_max;
			private float pressure;
			private float sea_level;
			private float grnd_level;
			private int humidity;
			private float temp_kf;
			
			@Override
			public String toString(){
				return "temp: " + temp + "\n" +
						"temp_min: " + temp_min + "\n" +
						"temp_max: " + temp_max + "\n" +
						"pressure: " + pressure + "\n" +
						"sea_level: " + sea_level + "\n" +
						"grnd_level: " + grnd_level + "\n" +
						"humidity: " + humidity + "\n" +
						"temp_kf: " + temp_kf + "\n";
			}
		}
		
		public class Weather {
			private int id;
			private String main;
			private String description;
			private String icon;
			
			@Override
			public String toString(){
				return "id: " + id + "\n" +
						"main: " + main + "\n" +
						"description: " + description + "\n" +
						"icon: " + icon + "\n";
			}
			
		}
		
		public class Clouds {
			private int all;
			
			@Override
			public String toString(){
				return "all: " + all;
			}
		}
		
		public class Wind {
			private float speed;
			private float deg;
			
			@Override
			public String toString(){
				return "speed: " + speed + " deg: " + deg;
			}
		}
		
		public class Rain {
			private float threeH;
			
			@Override
			public String toString(){
				return "3h: "  + threeH;
			}
		}
		
		public class Sys {
			private String pod;
			
			@Override
			public String toString(){
				return "pod: " + pod;
			}
		}
		
		
		private int dt;
		private Main main;
		private List<Weather> weather;
		private Clouds clouds;
		private Wind wind;
		private Rain Rain;
		private Sys sys;
		private String dt_txt;
		
		@Override
		public String toString(){
			String weatherListStr = "";
			for(int i=0; i<weather.size(); i++){
				weatherListStr += "\t" + weather.get(i) + "\n";
			}
			return  "dt: " + dt + "\n" +
					"main: " + main + "\n" +
					"WeatherList: " + weatherListStr +
					"clouds: " + clouds + "\n" +
					"wind: " + wind + "\n" +
					"Rain: " + Rain + "\n" +
					"sys: " + sys + "\n" +
					"dt_txt: " + dt_txt + "\n";
		}
		
	}
	
	private City city;
	private String cod;
	private float message;
	private int cnt;
	private List<Forecast> list;
	
	@Override
	public String toString() {
		
		return "city: " + this.city + "\n" +
				"cod: " + this.cod + "\n" +
				"message: " + this.message + "\n" +
				"cnt: " + this.cnt + "\n" +
				"list: " + this.list + "\n";
		
	}
	
}
