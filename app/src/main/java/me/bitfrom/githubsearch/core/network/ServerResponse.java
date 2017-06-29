package me.bitfrom.githubsearch.core.network;

import android.support.annotation.Nullable;

/**
 * <p>Represents general type for response from the server.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
public final class ServerResponse<T> {

    @Nullable
    private String errorMessage;
    @Nullable
    private T data;

    public void setErrorMessage(@Nullable String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    @Nullable
    public T getData() {
        return this.data;
    }
}