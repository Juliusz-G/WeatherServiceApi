package model.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class City{

	@SerializedName("country")
	private String country;

	@SerializedName("coord")
	private Coord coord;

	@SerializedName("sunrise")
	private int sunrise;

	@SerializedName("timezone")
	private int timezone;

	@SerializedName("sunset")
	private int sunset;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("population")
	private int population;
}