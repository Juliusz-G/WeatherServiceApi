package model.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherApi {

	@SerializedName("city")
	private City city;

	@SerializedName("cnt")
	private int cnt;

	@SerializedName("cod")
	private String cod;

	@SerializedName("message")
	private int message;

	@SerializedName("list")
	private List<ListItem> list;
}