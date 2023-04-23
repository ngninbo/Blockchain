package blockchain.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceProperties {

    private static final ResourceProperties INSTANCE = new ResourceProperties();

    private final ResourceBundle resourceBundle;

    public ResourceProperties() {
        resourceBundle = ResourceBundle.getBundle("messages_".concat(Locale.getDefault().getLanguage()));
    }

    public static ResourceProperties getInstance() {
        return INSTANCE;
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }


}
