package software.nectar.java.factory;

import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.Configuration;

import java.util.List;

public class ConfigurationFactory extends BaseFactory {

    public ConfigurationFactory(String key, String secret) {
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
}
