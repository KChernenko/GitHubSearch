package me.bitfrom.githubsearch.core.network;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * <p>Represents error response from the server.</p>
 *
 * @author const
 * @version 1
 * @since 01.07.2017
 */
@AutoValue
public abstract class ErrorResponse {

    @SerializedName(value = "message")
    public abstract String message();

    public static TypeAdapter<ErrorResponse> typeAdapter(Gson gson) {
        return new AutoValue_ErrorResponse.GsonTypeAdapter(gson);
    }
}