package software.nectar.java.factory;

import software.nectar.java.factory.base.BaseFactory;
import software.nectar.java.models.Credits;

import java.util.List;

public class CreditsFactory extends BaseFactory {

    public CreditsFactory(String key, String secret) {
        super(key, secret);
    }

    public Credits getCredits() {
        return null;
    }

    public List<Credits> getTransactions() {
        return null;
    }
}
