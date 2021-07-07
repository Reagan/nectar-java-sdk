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
//            test.createUser();
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
        params.put("password", "password");
        params.put("phone_no", "0700100100");
        params.put("image_url", "https://image.url");
        params.put("email", "user@email.com");
        params.put("activated", true);
        nectar.getUsersFactory().updateUser(params);
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
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Public Key");
        params.put("key", "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgZqr+BXGwQWe5UMY5CLM6a+XbFIZT0CAy/hx8Adhlb0PrwiQ9w4NNy9YMGTyvVTRyKBRgEjFNTJKBBDPFpWJyMa5BmL3JKmGZIYyWaggAILC2QbnEo2GqKbGfys3kiD/HfKCbxwohhNLieI+ULXw46IIriUEQCtx+AZyYTr620E26u1ANMvKzJLZQxTawUDNgy9S/YHSpMMftTF3LbEK5F2J33tLEbRBOVY4fvPL8w3YCx1Wu911+xz7UyVjdLDn26YoSl7+Fz5zZdwdhRMr+hDF8CInhbtAb1/cptFW4VBFVjDmHWn61bHUITbLWK5WRUzYoFWso4yOFYuq7JSMVYBKJE+27aMKZgPWiVrYaZVROxWoge7H//O+/NpWhyj9/K2Mzo6QzcLPTmw/1KN7CvIFIXDo+5wNZ+XFHuDeOaWrd2sMKvqXpEusdZYiuxy0e7Sze8/O5ada3BgFiM50DR1AIjZGONKEfAi2cGRXpBfCBUAU64RMeevobkrDzOSXCDy19o9wTfk4eRiWsuPIGm6zsJqA73+dW0KcSylBF5eaoPQbw/8WJjWClqlpQLfiKwnL2mjk6oFDAtVBfeRNjwd7Dyy1TvdbRJ5QwkfSHuwU2TphwPu/uMRJPOxvtMwgC3LXKnFEB2O9EzEDCrPmv6rOJn1i0tByDcNT0gL49MMCAwEAAQ==");
        params.put("activated", true);
        PublicKey publicKey = nectar.getPublicKeysFactory().createPublicKey(params);
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
        Configuration configuration = nectar.getConfigurationsFactory().getConfigurations("47693f75-b77f-4280-b00f-9c0d90111a63", true);
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
        String privateKey = "\"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\";";

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
