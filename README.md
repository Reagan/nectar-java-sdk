# Nectar Java SDK

Nectar Java SDK is a wrapper that allows generation of STS compliant prepaid tokens using the Nectar API.

## Installation

Use the package manager [gradle](https://gradle.org) to install this package by adding the following line to your *build.gradle*.

```bash
implementation 'software.nectar.java:nectar-java-sdk:0.9.2-alpha'
```

## Usage

```java
package software.nectar.java;

import software.nectar.java.models.*;

import java.time.Instant;
import java.util.List;

public class NectarTest {

    // Get from the Nectar API Portal at https://portal.nectar.software/credentials
    private final String KEY = "";
    private final String SECRET = "";

    private Nectar nectar = new Nectar(KEY, SECRET);

    public static void main(String[] args) {
        try {
            NectarTest test = new NectarTest();
            test.getToken();
            test.generateToken();

            test.getUser();
            test.createUser();
            test.updateUser();
            test.deleteUser();

            test.getPublicKeys();
            test.createPublicKey();
            test.activatePublicKey();
            test.deactivatePublicKey();

            test.getNotifications();
            test.setNotificationsReadStatus();

            test.getCredits();
            test.getTransactions();

            test.getCredentials();
            test.activateCredentials();
            test.deactivateCredentials();

            test.getConfiguration();
            test.createConfiguration();
            test.activateConfiguration();
            test.deactivateConfiguration();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getToken() throws Exception {
        Token generatedToken = nectar.getTokenFactory().getToken("590e9044-823f-4904-8c9e-4028b4b0116e");
        System.out.println(String.format("Get Token\n====================\n%s\n", generatedToken));
    }

    private void generateToken() throws Exception {
        Instant generationTime = Instant.parse("2018-05-16T07:29:00.00Z");
        int wmFactor = 10, randomNo = 5;
        boolean isStid = false, debug = false;
        String drn = "47500150231", configRef = "cbf43d1f-8c2d-44a0-95a9-9c3c13ec846c";

        Token generatedToken = nectar.getTokenFactory()
                .generateSetWaterMeterFactorToken(generationTime, wmFactor, randomNo,
                                                    isStid, drn, configRef, debug);
        System.out.println(String.format("Generate Token\n====================\n%s\n", generatedToken));
    }

    private void getUser() throws Exception {
        User user = nectar.getUsersFactory().getUser();
        System.out.println(String.format("Get User\n====================\n%s\n", user));
    }

    private void createUser() throws Exception {
        String firstName = "first_name";
        String lastName = "last_name";
        String username = "username";
        String password = "password";
        String phoneNo = "0700100100";
        String imageUrl = "https://image.url";
        String email = "user@email.com";

        String createdUserRef = nectar.getUsersFactory()
                .createUser(firstName, lastName, username, password,
                            phoneNo, imageUrl, email, true);
        System.out.println(String.format("Create User\n====================\n%s\n", createdUserRef));
    }

    private void updateUser() throws Exception {
        String firstName = "first_name";
        String lastName = "last_name";
        String username = "username";
        String password = "password";
        String phoneNo = "0700100100";
        String imageUrl = "https://image.url";
        String email = "user@email.com";

        nectar.getUsersFactory().updateUser(firstName, lastName, username, password,
                                            phoneNo, imageUrl, email, true);
        System.out.println(String.format("Update User\n====================\n"));
    }

    private void deleteUser() throws Exception {
        nectar.getUsersFactory().deleteUser();
        System.out.println("Delete User\n====================\n");
    }

    private void getPublicKeys() throws Exception {
        final boolean activated = false;
        List<PublicKey> publicKeys = nectar.getPublicKeysFactory().getPublicKeys(activated);
        System.out.println(String.format("Get Public Keys\n====================\n%s\n", publicKeys));
    }

    private void createPublicKey() throws Exception {
        String name = "Public Key";
        String key = "MIICIjANBgkqhki...L49MMCAwEAAQ==";
        boolean activated = true;
        PublicKey publicKey = nectar.getPublicKeysFactory()
                                    .createPublicKey(name, key, activated);
        System.out.println(String.format("Create Public Key\n====================\n%s\n", publicKey));
    }

    private void activatePublicKey() throws Exception {
        nectar.getPublicKeysFactory().activatePublicKey("d97bf39e-94ce-4fe9-8057-02d97eb3cbca");
        System.out.println("Activate Public Key\n====================\n");
    }

    private void deactivatePublicKey() throws Exception {
        nectar.getPublicKeysFactory().deactivatePublicKey("d97bf39e-94ce-4fe9-8057-02d97eb3cbca");
        System.out.println("Deactivate Public Key\n====================\n");
    }

    private void getNotifications() throws Exception {
        List<Notification> notifications = nectar.getNotificationsFactory().getNotifications();
        System.out.println(String.format("Get Notifications\n====================\n%s\n", notifications));
    }

    private void setNotificationsReadStatus() throws Exception {
        System.out.println("Set Notification Read Status\n====================\n");
        nectar.getNotificationsFactory().setNotificationReadStatus("931ef4e4-b375-40d8-b58e-c1874792ce64", true, Instant.now().toEpochMilli());
    }

    private void getCredits() throws Exception {
        Credits credits = nectar.getCreditsFactory().getCredits();
        System.out.println(String.format("Get Credits\n====================\n%s\n", credits));
    }

    private void getTransactions() throws Exception {
        Credits transactions = nectar.getCreditsFactory().getTransactions();
        System.out.println(String.format("Get Credits\n====================\n%s\n", transactions));
    }

    private void getCredentials() throws Exception {
        Credentials credentials = nectar.getCredentialsFactory().getCredentials("d41879ff-cb85-4bef-89a1-6c3cd7e2dd58");
        System.out.println(String.format("Get Credentials\n====================\n%s\n", credentials));
    }

    private void activateCredentials() throws Exception {
        System.out.println("Activate Credentials\n====================\n");
        nectar.getCredentialsFactory().activateCredentials("d1978978-5535-4c37-b4de-b05dbe2f09d4");
    }

    private void deactivateCredentials() throws Exception {
        System.out.println("Deactivate Credentials\n====================\n");
        nectar.getCredentialsFactory().deactivateCredentials("d1978978-5535-4c37-b4de-b05dbe2f09d4");
    }

    private void getConfiguration() throws Exception {
        Configuration configuration = nectar.getConfigurationsFactory().getConfiguration("47693f75-b77f-4280-b00f-9c0d90111a63", true);
        System.out.println(String.format("Get Configurations\n====================\n%s\n", configuration));
    }

    public void createConfiguration() throws Exception {
        String yamlConfig = "---\n" +
                "name: example_config\n" +
                "key_expiry_no: 255\n" +
                "encryption_algorithm: sta\n" +
                "token_carrier_type: numeric\n" +
                "decoder_key_generation_algorithm: 04\n" +
                "tariff_index: 01\n" +
                "key_revision_no: 1\n" +
                "vending_key: 0123456789abcdef\n" +
                "supply_group_code: 123456\n" +
                "key_type: 3\n" +
                "base_date: 2035\n" +
                "issuer_identification_no: 600727";
        String symmetricKey = "v8y/B?E(H+MbQeTh";
        String privateKey = "\"MIIJQgIBADANBgkqhkiEG9w0BAQEFAASC...kTRMP4Regv6aYfWAMQo0Q==\";";

        Configuration configuration = nectar.getConfigurationsFactory()
                .createConfiguration(yamlConfig, symmetricKey, privateKey);
        System.out.println(String.format("Create Configuration\n====================\n%s\n", configuration));
    }

    public void activateConfiguration() throws Exception {
        System.out.println("Activate Configuration\n====================\n");
        nectar.getConfigurationsFactory().activateConfiguration("ac3380d8-5d85-4161-92e5-03c1dc62de3d");

    }

    public void deactivateConfiguration() throws Exception {
        System.out.println("Deactivate Configuration\n====================\n");
        nectar.getConfigurationsFactory().deactivateConfiguration("ac3380d8-5d85-4161-92e5-03c1dc62de3d");
    }
}

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
