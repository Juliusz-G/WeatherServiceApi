package model.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Main{

	@SerializedName("temp")
	private double temp;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("pressure")
	private int pressure;
}