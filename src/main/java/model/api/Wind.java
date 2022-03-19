package model.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wind{

	@SerializedName("deg")
	private int deg;

	@SerializedName("speed")
	private double speed;
}