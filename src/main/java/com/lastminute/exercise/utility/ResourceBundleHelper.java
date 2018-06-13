/**
 * 
 */
package com.lastminute.exercise.utility;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Gianluca Colaianni
 *
 */
public class ResourceBundleHelper {
    
    private static final String BUNDLE_NAME = "i18n/language";
    
    private static ResourceBundle bundle;

    /**
     * 
     */
    private ResourceBundleHelper() {}
    
    /**
     * @see {@link ResourceBundleHelper#getString(String, ResourceBundle, Object...)}.
     * @author Gianluca Colaianni
     */
    public static String getString(final String key, final Object... parameters) {
	if (bundle == null) {
	    bundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
	}
	return getString(key, bundle, parameters);
    }
    
    /**
     * Get a formatted string from the bundle key.
     * @param key the key to retrieve.
     * @param bundle the bundle to use to retireve the message.
     * @param parameters the values to format.
     * @return the formatted string from the bundle.
     * @author Gianluca Colaianni
     */
    public static String getString(final String key, final ResourceBundle bundle, final Object... parameters) {
	return MessageFormat.format(bundle.getString(key), parameters);
    }
    
    /**
     * Retrieves the application bundle.
     * @return the application bundle.
     * @author Gianluca Colaianni
     */
    public static ResourceBundle getBundle() {
	if (bundle == null) {
	    bundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.ENGLISH);
	}
	return bundle;
    }

}
