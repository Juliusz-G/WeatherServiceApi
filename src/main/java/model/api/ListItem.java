package model.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListItem {

    @SerializedName("dt")
    private long dt;

    @SerializedName("main")
    private Main main;

    @SerializedName("wind")
    private Wind wind;
}