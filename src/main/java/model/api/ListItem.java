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

	@SerializedName("main")
	private Main main;

	@SerializedName("wind")
	private Wind wind;
}