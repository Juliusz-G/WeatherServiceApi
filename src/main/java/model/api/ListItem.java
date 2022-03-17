package model.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListItem{

	@SerializedName("dt")
	private int dt;

	@SerializedName("pop")
	private double pop;

	@SerializedName("visibility")
	private int visibility;

	@SerializedName("dt_txt")
	private String dtTxt;

	@SerializedName("weather")
	private List<WeatherItem> weather;

	@SerializedName("main")
	private Main main;

	@SerializedName("clouds")
	private Clouds clouds;

	@SerializedName("sys")
	private Sys sys;

	@SerializedName("wind")
	private Wind wind;

	@SerializedName("rain")
	private Rain rain;
}