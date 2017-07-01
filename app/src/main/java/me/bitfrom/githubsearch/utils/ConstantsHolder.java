package me.bitfrom.githubsearch.utils;

/**
 * <p>Access point for all application-related constants.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
public final class ConstantsHolder {

    //Network-related constants
    public static final String BASE_URL = "https://api.github.com/";
    public static final int CONNECTION_TIMEOUT = 60;
    public static final int READ_TIMEOUT = 60;
    public static final String CACHE_DIR_NAME = "github_http_cache";
    public static final int CACHE_SIZE = 2 * 1024 * 1024; //2 Mb
    public static final String CACHE_CONTROL_HEADER = "Cache-Control";
    public static final String SORT_PRINCIPE_QUERY_PARAM = "stars";
    public static final String ORDER_QUERY_PARAM = "desc";

    //Database-related constants
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "github_search_db";

    private ConstantsHolder() {
        throw new IllegalStateException("No instances, please!");
    }
}