package com.weather;

public class Weather implements java.io.Serializable, Comparable<Weather> {
	/**
	 * Weather POJO class
	 */
	private static final long serialVersionUID = 1L;
	
	Location location;
	Current current;
	Astronomy astronomy;
	
	public Weather() {}
	
	public Weather(Location location) {
		this.location = location;
	}
	public Weather(Current current) {
		this.current = current;
	}
	
	public Weather(Location location, Current current) {
		this.location = location;
		this.current = current;
	}
	
	public Weather(Location location, Current current, Astronomy astronomy) {
		this.location = location;
		this.current = current;
		this.astronomy = astronomy;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Current getCurrent() {
		return current;
	}

	public void setCurrent(Current current) {
		this.current = current;
	}
	
	public Astronomy getAstronomy() {
		return astronomy;
	}

	public void setAstronomy(Astronomy astronomy) {
		this.astronomy = astronomy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((current == null) ? 0 : current.hashCode());
		result = prime * result + ((astronomy == null) ? 0 : astronomy.hashCode());
		return result;
	}
	
	@Override
	public int compareTo(Weather o) {
		return this.location.compareTo(o.location);
	}
	
	public static int getHashCode(Object o) {
		return Math.abs(o.hashCode());
	}

	public class Location {
		String name;
		String region;
		String country;
		double lat;
		double lon;
		String tz_id;
		int localtime_epoch;
		String localtime;
		
		public Location() {}

		public Location(String name, String region, 
				String country, double lat, double lon, 
				String tz_id, int localtime_epoch, 
				String localtime) {
			this.name = name;
			this.region = region;
			this.country = country;
			this.lat = lat;
			this.lon = lon;
			this.tz_id = tz_id;
			this.localtime_epoch = localtime_epoch;
			this.localtime = localtime;
			
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLon() {
			return lon;
		}

		public void setLon(double lon) {
			this.lon = lon;
		}

		public String getTz_id() {
			return tz_id;
		}

		public void setTz_id(String tz_id) {
			this.tz_id = tz_id;
		}

		public int getLocaltime_epoch() {
			return localtime_epoch;
		}

		public void setLocaltime_epoch(int localtime_epoch) {
			this.localtime_epoch = localtime_epoch;
		}

		public String getLocaltime() {
			return localtime;
		}

		public void setLocaltime(String localtime) {
			this.localtime = localtime;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((region == null) ? 0 : region.hashCode());
			result = prime * result + ((country == null) ? 0 : country.hashCode());
			long latLong = Double.doubleToLongBits(lat);
			result = 31 * result + (int) (latLong ^ (latLong >>> 32));
			long lonLong = Double.doubleToLongBits(lon);
			result = 31 * result + (int) (lonLong ^ (lonLong >>> 32));
			result = prime * result + ((tz_id == null) ? 0 : tz_id.hashCode());
			result = prime * result + ((localtime_epoch == 0) ? 0 : localtime_epoch);
			result = prime * result + ((localtime == null) ? 0 : localtime.hashCode());
			return result;
		}
		
		public int compareTo(Location l) {
			return this.name.compareTo(l.name);
		}
	}
	
	public class Current {
		int last_updated_epoch;
		String last_updated;
		double temp_c;
		double temp_f;
		int is_day;
		Condition condition;
		double wind_mph;
		double wind_kph;
		int wind_degree;
		String wind_dir;
		double pressure_mb;
		double pressure_in;
		double precip_mm;
		double precip_in;
		int humidity;
		int cloud;
		double feelslike_c;
		double feelslike_f;
		double vis_km;
		double vis_miles;
		double uv;
		double gust_mph;
		double gust_kph;
		
		public Current() {}
		
		public Current(int last_updated_epoch, String last_updated, 
				double temp_c, double temp_f, int is_day, Condition condition, 
				double wind_mph, double wind_kph, int wind_degree, 
				String wind_dir, double pressure_mb, double pressure_in, 
				double precip_mm, double precip_in, int humidity, 
				int cloud, double feelslike_c, double feelslike_f, 
				double vis_km, double vis_miles, double uv, double gust_mph, 
				double gust_kph) {
			this.last_updated_epoch = last_updated_epoch;
			this.last_updated = last_updated;
			this.temp_c = temp_c;
			this.temp_f = temp_f;
			this.is_day = is_day;
			this.condition = condition;
			this.wind_mph = wind_mph;
			this.wind_kph =  wind_kph;
			this.wind_degree = wind_degree;
			this.wind_dir = wind_dir;
			this.pressure_mb = pressure_mb;
			this.pressure_in = pressure_in;
			this.precip_mm = precip_mm;
			this.precip_in = precip_in;
			this.humidity = humidity;
			this.cloud = cloud;
			this.feelslike_c = feelslike_c;
			this.feelslike_f = feelslike_f;
			this.vis_km = vis_km;
			this.vis_miles = vis_miles;
			this.uv = uv;
			this.gust_mph = gust_mph;
			this.gust_kph = gust_kph;
			
		}
		
		public int getLast_updated_epoch() {
			return last_updated_epoch;
		}

		public void setLast_updated_epoch(int last_updated_epoch) {
			this.last_updated_epoch = last_updated_epoch;
		}

		public String getLast_updated() {
			return last_updated;
		}

		public void setLast_updated(String last_updated) {
			this.last_updated = last_updated;
		}

		public double getTemp_c() {
			return temp_c;
		}

		public void setTemp_c(double temp_c) {
			this.temp_c = temp_c;
		}

		public double getTemp_f() {
			return temp_f;
		}

		public void setTemp_f(double temp_f) {
			this.temp_f = temp_f;
		}

		public int getIs_day() {
			return is_day;
		}

		public void setIs_day(int is_day) {
			this.is_day = is_day;
		}

		public Condition getCondition() {
			return condition;
		}

		public void setCondition(Condition condition) {
			this.condition = condition;
		}

		public double getWind_mph() {
			return wind_mph;
		}

		public void setWind_mph(double wind_mph) {
			this.wind_mph = wind_mph;
		}

		public double getWind_kph() {
			return wind_kph;
		}

		public void setWind_kph(double wind_kph) {
			this.wind_kph = wind_kph;
		}

		public int getWind_degree() {
			return wind_degree;
		}

		public void setWind_degree(int wind_degree) {
			this.wind_degree = wind_degree;
		}

		public String getWind_dir() {
			return wind_dir;
		}

		public void setWind_dir(String wind_dir) {
			this.wind_dir = wind_dir;
		}

		public double getPressure_mb() {
			return pressure_mb;
		}

		public void setPressure_mb(double pressure_mb) {
			this.pressure_mb = pressure_mb;
		}

		public double getPressure_in() {
			return pressure_in;
		}

		public void setPressure_in(double pressure_in) {
			this.pressure_in = pressure_in;
		}

		public double getPrecip_mm() {
			return precip_mm;
		}

		public void setPrecip_mm(double precip_mm) {
			this.precip_mm = precip_mm;
		}

		public double getPrecip_in() {
			return precip_in;
		}

		public void setPrecip_in(double precip_in) {
			this.precip_in = precip_in;
		}

		public int getHumidity() {
			return humidity;
		}

		public void setHumidity(int humidity) {
			this.humidity = humidity;
		}

		public int getCloud() {
			return cloud;
		}

		public void setCloud(int cloud) {
			this.cloud = cloud;
		}

		public double getFeelslike_c() {
			return feelslike_c;
		}

		public void setFeelslike_c(double feelslike_c) {
			this.feelslike_c = feelslike_c;
		}

		public double getFeelslike_f() {
			return feelslike_f;
		}

		public void setFeelslike_f(double feelslike_f) {
			this.feelslike_f = feelslike_f;
		}

		public double getVis_km() {
			return vis_km;
		}

		public void setVis_km(double vis_km) {
			this.vis_km = vis_km;
		}

		public double getVis_miles() {
			return vis_miles;
		}

		public void setVis_miles(double vis_miles) {
			this.vis_miles = vis_miles;
		}

		public double getUv() {
			return uv;
		}

		public void setUv(double uv) {
			this.uv = uv;
		}

		public double getGust_mph() {
			return gust_mph;
		}

		public void setGust_mph(double gust_mph) {
			this.gust_mph = gust_mph;
		}

		public double getGust_kph() {
			return gust_kph;
		}

		public void setGust_kph(double gust_kph) {
			this.gust_kph = gust_kph;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + last_updated_epoch;
			result = prime * result + ((last_updated == null) ? 0 : last_updated.hashCode());
			long temp_cLong = Double.doubleToLongBits(temp_c);
			result = 31 * result + (int) (temp_cLong ^ (temp_cLong >>> 32));
			long temp_fLong = Double.doubleToLongBits(temp_f);
			result = 31 * result + (int) (temp_fLong ^ (temp_fLong >>> 32));
			result = prime * result + is_day;
			result = prime * result + ((condition == null) ? 0 : condition.hashCode());
			long wind_mphLong = Double.doubleToLongBits(wind_mph);
			result = 31 * result + (int) (wind_mphLong ^ (wind_mphLong >>> 32));
			long wind_kphLong = Double.doubleToLongBits(wind_kph);
			result = 31 * result + (int) (wind_kphLong ^ (wind_kphLong >>> 32));
			result = prime * result + wind_degree;
			result = prime * result + ((wind_dir == null) ? 0 : wind_dir.hashCode());
			long pressure_mbLong = Double.doubleToLongBits(pressure_mb);
			result = 31 * result + (int) (pressure_mbLong ^ (pressure_mbLong >>> 32));
			long pressure_inLong = Double.doubleToLongBits(pressure_in);
			result = 31 * result + (int) (pressure_inLong ^ (pressure_inLong >>> 32));	
			long precip_mmLong = Double.doubleToLongBits(precip_mm);
			result = 31 * result + (int) (precip_mmLong ^ (precip_mmLong >>> 32));
			long precip_inLong = Double.doubleToLongBits(precip_in);
			result = 31 * result + (int) (precip_inLong ^ (precip_inLong >>> 32));
			result = prime * result + humidity;
			result = prime * result + cloud;
			long feelslike_cLong = Double.doubleToLongBits(feelslike_c);
			result = 31 * result + (int) (feelslike_cLong ^ (feelslike_cLong >>> 32));
			long feelslike_fLong = Double.doubleToLongBits(feelslike_f);
			result = 31 * result + (int) (feelslike_fLong ^ (feelslike_fLong >>> 32));
			long vis_kmLong = Double.doubleToLongBits(vis_km);
			result = 31 * result + (int) (vis_kmLong ^ (vis_kmLong >>> 32));
			long vis_milesLong = Double.doubleToLongBits(vis_miles);
			result = 31 * result + (int) (vis_milesLong ^ (vis_milesLong >>> 32));
			long uvLong = Double.doubleToLongBits(uv);
			result = 31 * result + (int) (uvLong ^ (uvLong >>> 32));
			long gust_mphLong = Double.doubleToLongBits(gust_mph);
			result = 31 * result + (int) (gust_mphLong ^ (gust_mphLong >>> 32));
			long gust_kphLong = Double.doubleToLongBits(gust_kph);
			result = 31 * result + (int) (gust_kphLong ^ (gust_kphLong >>> 32));
			return result;
		}
		
		public int compareTo(Current c) {
			return this.last_updated.compareTo(c.last_updated);
		}

		public class Condition {
			String text;
			String icon;
			int code;
			
			public Condition() {}
			
			public Condition(String text, String icon, int code) {
				this.text = text;
				this.icon = icon;
				this.code = code;
			}
			
			public String getText() {
				return text;
			}

			public void setText(String text) {
				this.text = text;
			}

			public String getIcon() {
				return icon;
			}

			public void setIcon(String icon) {
				this.icon = icon;
			}

			public int getCode() {
				return code;
			}

			public void setCode(int code) {
				this.code = code;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((text == null) ? 0 : text.hashCode());
				result = prime * result + ((icon == null) ? 0 : icon.hashCode());
				result = prime * result + ((code == 0) ? 0 : code);
				return result;
			}
			
			public int compareTo(Condition cnd) {
				return this.text.compareTo(cnd.text);
			}
		}
	}
	
	public class Astronomy {
		String sunrise;
		String sunset;
		String moonrise;
		String moonset;
		String moon_phase;
		String moon_illumination;
		
		public Astronomy() {}
		
		public Astronomy(String sunrise, String sunset, 
				String moonrise, String moonset, 
				String moon_phase, String moon_illumination) {
			this.sunrise = sunrise;
			this.sunset = sunset;
			this.moonrise = moonrise;
			this.moonset = moonset;
			this.moon_phase = moon_phase;
			this.moon_illumination = moon_illumination;
		}
		
		public String getSunrise() {
			return sunrise;
		}

		public void setSunrise(String sunrise) {
			this.sunrise = sunrise;
		}

		public String getSunset() {
			return sunset;
		}

		public void setSunset(String sunset) {
			this.sunset = sunset;
		}

		public String getMoonrise() {
			return moonrise;
		}

		public void setMoonrise(String moonrise) {
			this.moonrise = moonrise;
		}

		public String getMoonset() {
			return moonset;
		}

		public void setMoonset(String moonset) {
			this.moonset = moonset;
		}

		public String getMoon_phase() {
			return moon_phase;
		}

		public void setMoon_phase(String moon_phase) {
			this.moon_phase = moon_phase;
		}

		public String getMoon_illumination() {
			return moon_illumination;
		}

		public void setMoon_illumination(String moon_illumination) {
			this.moon_illumination = moon_illumination;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((sunrise == null) ? 0 : sunrise.hashCode());
			result = prime * result + ((sunset == null) ? 0 : sunset.hashCode());
			result = prime * result + ((moonrise == null) ? 0 : moonrise.hashCode());
			result = prime * result + ((moonset == null) ? 0 : moonset.hashCode());
			result = prime * result + ((moon_phase == null) ? 0 : moon_phase.hashCode());
			result = prime * result + ((moon_illumination == null) ? 0 : moon_illumination.hashCode());
			return result;
		}
		
		public int compareTo(Astronomy a) {
			return this.sunrise.compareTo(a.sunrise);
		}
	}
}