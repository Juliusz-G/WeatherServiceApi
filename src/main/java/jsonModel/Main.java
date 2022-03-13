package jsonModel;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Main {

    @SerializedName("temp")
    private double temp;

    @SerializedName("temp_min")
    private int tempMin;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("pressure")
    private int pressure;

    @SerializedName("feels_like")
    private double feelsLike;

    @SerializedName("temp_max")
    private double tempMax;
}
