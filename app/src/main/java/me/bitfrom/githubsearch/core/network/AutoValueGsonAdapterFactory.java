package me.bitfrom.githubsearch.core.network;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * <p>Custom AdapterFactory for AutoValue types serialization.</p>
 *
 * @author const
 * @version 1
 * @since 30.06.2017
 */
@GsonTypeAdapterFactory
public abstract class AutoValueGsonAdapterFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueGsonAdapterFactory();
    }
}