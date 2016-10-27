package hu.barbar.owm.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hu.barbar.owm.api.helpers.ResourceDownloader;
import hu.barbar.owm.api.util.Config;

/**
 * Weather representation for current conditions
 */
public class WeatherResponse {

    private int id;
    private long dt;
    private String name;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    
    

    public int getId() {
		return id;
	}

	public long getDt() {
		return dt;
	}
	
	public String getDtFormatted(){
		SimpleDateFormat formatter = new SimpleDateFormat(Config.DATE_FORMAT);
		return formatter.format(new Date(dt * 1000));
	}

	public String getName() {
		return name;
	}

	public Weather getWeather() {
		Weather w = weather.get(0);
		return w;
	}

	public Main getMain() {
		return main;
	}
	
	public Main getConditions() {
		return main;
	}

	public Wind getWind() {
		return wind;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public Sys getSys() {
		return sys;
	}

	@Override
    public String toString() {
    	
        StringBuilder sb = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        SimpleDateFormat formatter = new SimpleDateFormat(Config.DATE_FORMAT);
        
        sb.append(this.getClass().getSimpleName());
        sb.append(" {");
        sb.append(lineSeparator);

        sb.append("\tdt: ");
        sb.append(formatter.format(new Date(dt * 1000)));
        sb.append(lineSeparator);

        sb.append("\tid: ");
        sb.append(id);
        sb.append(lineSeparator);

        sb.append("\tname: ");
        sb.append(name);
        sb.append(lineSeparator);

        for (Weather w : weather) {
            sb.append("\tweather.main: ");
            sb.append(w.main);
            sb.append(lineSeparator);
            sb.append("\tweather.description: ");
            sb.append(w.description);
            sb.append(lineSeparator);
            sb.append("\tweather.icon: ");
            sb.append(w.icon);
            sb.append(lineSeparator);
            sb.append("\tweather.iconUrl: ");
            sb.append(w.getIconUrl());
            sb.append(lineSeparator);
        }

        sb.append("\tmain.temp: ");
        sb.append(main.temp);
        sb.append(lineSeparator);
        
        sb.append("\tmain.temp_min: ");
        sb.append(main.temp_min);
        sb.append(lineSeparator);
        
        sb.append("\tmain.temp_max: ");
        sb.append(main.temp_max);
        sb.append(lineSeparator);
        
        sb.append("\tmain.humidity: ");
        sb.append(main.humidity);
        sb.append(lineSeparator);
        
        sb.append("\tmain.pressure: ");
        sb.append(main.pressure);
        sb.append(lineSeparator);

        sb.append("\twind.speed: ");
        sb.append(wind.speed);
        sb.append(lineSeparator);
        
        sb.append("\twind.deg: ");
        sb.append(wind.deg);
        sb.append(lineSeparator);

        sb.append("\tclouds: ");
        sb.append(clouds.all);
        sb.append(lineSeparator);

        sb.append("\tsunrise: ");
        sb.append(formatter.format(new Date(sys.sunrise * 1000)));
        sb.append(lineSeparator);

        sb.append("\tsunset: ");
        sb.append(formatter.format(new Date(sys.sunset * 1000)));
        sb.append(lineSeparator);

        sb.append('}');

        return sb.toString();
    }

    public class Weather {
    	
    	public static final String ICON_URL_PREFIX = "http://openweathermap.org/img/w/";

        private String id;
        private String main;
        private String description;
        private String icon;

        @Override
        public String toString() {
            return "Weather{" + "id=" + id + ", main=" + main + ", description=" + description + ", icon=" + icon + '}';
        }

		public String getId() {
			return id;
		}

		public String getMain() {
			return main;
		}

		public String getDescription() {
			return description;
		}

		public String getIconName() {
			return icon;
		}
		
		public String getIconUrl(){
			return ICON_URL_PREFIX + getIconName() + ".png";
		}
        
		/**
		 * Download icon (if it is not downloaded yet) and <br>
		 * Get local path for it. 
		 * @return
		 */
		public String getIconPath(){
			return ResourceDownloader.getImageAsPath(getIconUrl());
		}
    }

    public class Main {

        private float temp;
        private float pressure;
        private float humidity;
        private float temp_min;
        private float temp_max;

        @Override
        public String toString() {
            return "Main{" + "temp=" + temp + ", pressure=" + pressure + ", humidity=" + humidity + ", temp_min=" + temp_min + ", temp_max=" + temp_max + '}';
        }

		public float getTemp() {
			return temp;
		}

		public float getPressure() {
			return pressure;
		}

		public float getHumidity() {
			return humidity;
		}

		public float getTemp_min() {
			return temp_min;
		}

		public float getTemp_max() {
			return temp_max;
		}
        
        
    }

    public class Wind {

        private float speed;
        private float deg;

        @Override
        public String toString() {
            return "Wind{" + "speed=" + speed + ", deg=" + deg + '}';
        }

        //TODO: determine measurement unit
        /**
		 * @return wind speed
		 */
		public float getSpeed() {
			return speed;
		}

		/**
		 * @return wind direction in degrees
		 */
		public float getDirection() {
			return deg;
		}
		
		public float getDeg() {
			return deg;
		}
        
        
        
    }

    public class Clouds {

        private float all;
        
        public float get(){
        	return all;
        }
        
    }

    public class Sys {

        private long sunrise;
        private long sunset;
        
		public long getSunrise() {
			return sunrise;
		}
		public long getSunset() {
			return sunset;
		}
        
		public String getSunriseFormatted() {
			return this.getSunriseFormatted(Config.DATE_FORMAT);
		}
		
		public String getSunriseFormatted(String formatpattern) {
			SimpleDateFormat formatter = new SimpleDateFormat(formatpattern);
			return formatter.format(new Date(sunrise * 1000));
		}
		
		public String getSunsetFormatted() {
			return this.getSunsetFormatted(Config.DATE_FORMAT);
		}
		
		public String getSunsetFormatted(String formatpattern) {
			SimpleDateFormat formatter = new SimpleDateFormat(formatpattern);
			return formatter.format(new Date(sunset * 1000));
		}
        
        
    }

}
