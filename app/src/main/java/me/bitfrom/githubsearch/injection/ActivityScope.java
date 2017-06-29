package me.bitfrom.githubsearch.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * <p>Scope for Activity-related objects.</p>
 *
 * @author const
 * @version 1
 * @since 29.06.2017
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {

}