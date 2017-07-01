package me.bitfrom.githubsearch.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * <p>Qualifier for marking Application's Context.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {

}