package software.nectar.java.factory;

import org.json.JSONObject;
import software.nectar.java.factory.base.ApiResponse;
import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.Configuration;

import java.util.List;

public class ConfigurationsFactory extends BaseFactory {

    public ConfigurationsFactory(String key, String secret) {
        super(key, secret);
    }

    public Configuration createConfiguration() {
        return null;
    }

    public List<Configuration> getConfigurations() {
        return null;
    }

    public void activateConfiguration() {

    }

    public void deactivateConfiguration() {

    }

    public ApiResponse extractFrom(JSONObject object) {
        return null;
    }
}
