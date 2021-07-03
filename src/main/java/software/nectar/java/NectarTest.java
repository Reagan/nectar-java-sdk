package software.nectar.java;

import software.nectar.java.models.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NectarTest {

    private final String KEY = "4d49b676-5aab-48ac-b6c3-3ff4c82bb229";
    private final String SECRET = "8d313aa9-6cb3-4c0a-a547-f905f5a952e0";

    private Nectar nectar = new Nectar(KEY, SECRET);

    public static void main(String[] args) {
        try {
            NectarTest test = new NectarTest();
//            test.getToken();
//            test.generateToken();

//            test.getUser();
            test.createUser();
//            test.updateUser();
//            test.deleteUser();
//
//            test.getPublicKeys();
//            test.createPublicKey();
//            test.activatePublicKey();
//            test.deactivatePublicKey();
//
//            test.getNotifications();
//            test.setNotificationsReadStatus();
//
//            test.getCredits();
//            test.getTransactions();
//
//            test.getCredentials();
//            test.activateCredentials();
//            test.deactivateCredentials();
//
//            test.getConfiguration();
//            test.createConfiguration();
//            test.activateConfiguration();
//            test.deactivateConfiguration();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getToken() throws Exception {
        Token generatedToken = nectar.getTokenFactory().getToken("590e9044-823f-4904-8c9e-4028b4b0116e");
        System.out.println(String.format("Get Token\n====================\n%s\n", generatedToken));
    }

    private void generateToken() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("class", "2");
        params.put("subclass", "7");
        params.put("token_id", "2018-05-16T07:29");
        params.put("wm_factor", "10");
        params.put("random_no", "9");
        params.put("is_stid", "false");
        params.put("drn", "47500150231");
        params.put("key_type", "0");
        params.put("supply_group_code", "000046");
        params.put("tariff_index", "01");
        params.put("key_revision_no", "1");
        params.put("issuer_identification_no", "600727");
        params.put("base_date", "1993");
        params.put("key_expiry_no", "255");
        params.put("encryption_algorithm", "sta");
        params.put("decoder_key_generation_algorithm", "02");
        params.put("vending_key", "0abc12def3456789");
        params.put("debug", "false");

        Token generatedToken = nectar.getTokenFactory()
                .generateToken(params);
        System.out.println(String.format("Generate Token\n====================\n%s\n", generatedToken));
    }

    private void getUser() throws Exception {
        User user = nectar.getUsersFactory().getUser();
        System.out.println(String.format("Get User\n====================\n%s\n", user));
    }

    private void createUser() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", "first_name");
        params.put("last_name", "last_name");
        params.put("username", "username");
        params.put("password", "password");
        params.put("phone_no", "0700100100");
        params.put("image_url", "https://image.url");
        params.put("email", "user@email.com");
        params.put("activated", true);
        String createdUserRef = nectar.getUsersFactory().createUser(params);
        System.out.println(String.format("Create User\n====================\n%s\n", createdUserRef));
    }

    private void updateUser() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", "first_name");
        params.put("last_name", "last_name");
        params.put("username", "username");
        params.put("phone_no", "0700100100");
        params.put("image_url", "https://image.url");
        params.put("email", "user@email.com");
        params.put("activated", true);
        params.put("ref", "ref");
        User user = nectar.getUsersFactory().updateUser(params);
        System.out.println(String.format("Update User\n====================\n%s\n", user));
    }

    private void deleteUser() throws Exception {
        nectar.getUsersFactory().deleteUser();
        System.out.println("Delete User\n====================\n%s\n");
    }

    private void getPublicKeys() throws Exception {
        final boolean activated = false;
        List<PublicKey> publicKeys = nectar.getPublicKeysFactory().getPublicKeys(activated);
        System.out.println(String.format("Get Public Keys\n====================\n%s\n", publicKeys));
    }

    private void createPublicKey() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Public Key");
        params.put("public_key", "public_key");
        params.put("activated", true);
        PublicKey publicKey = nectar.getPublicKeysFactory().createPublicKey(params);
        System.out.println(String.format("Create Public Key\n====================\n%s\n", publicKey));
    }

    private void activatePublicKey() throws Exception {
        nectar.getPublicKeysFactory().activatePublicKey("ref");
        System.out.println("Activate Public Key\n====================\n");
    }

    private void deactivatePublicKey() throws Exception {
        nectar.getPublicKeysFactory().deactivatePublicKey("ref");
        System.out.println("Deactivate Public Key\n====================\n");
    }

    private void getNotifications() throws Exception {
        List<Notification> notifications = nectar.getNotificationsFactory().getNotifications();
        System.out.println(String.format("Get Notifications\n====================\n%s\n", notifications));
    }

    private void setNotificationsReadStatus() throws Exception {
        System.out.println("Set Notification Read Status\n====================\n%s\n");
        nectar.getNotificationsFactory().setNotificationReadStatus("ref", true, Instant.now().toEpochMilli());
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
        System.out.println("Activate Credentials\n====================\n%s\n");
        nectar.getCredentialsFactory().activateCredentials("ref");
    }

    private void deactivateCredentials() throws Exception {
        System.out.println("Deactivate Credentials\n====================\n%s\n");
        nectar.getCredentialsFactory().deactivateCredentials("ref");
    }

    private void getConfiguration() throws Exception {
        Configuration configuration = nectar.getConfigurationsFactory().getConfigurations("47693f75-b77f-4280-b00f-9c0d90111a63", true);
        System.out.println(String.format("Get Configurations\n====================\n%s\n", configuration));
    }

    public void createConfiguration() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Config");
        params.put("data", "data");
        params.put("digest", "digest");
        params.put("key", "key");
        Configuration configuration = nectar.getConfigurationsFactory().createConfiguration(params);
        System.out.println(String.format("Create Configuration\n====================\n%s\n", configuration));
    }

    public void activateConfiguration() throws Exception {
        System.out.println("Activate Configuration\n====================\n");
        nectar.getConfigurationsFactory().activateConfiguration("ref");

    }

    public void deactivateConfiguration() throws Exception {
        System.out.println("Deactivate Configuration\n====================\n");
        nectar.getConfigurationsFactory().deactivateConfiguration("ref");
    }
}
