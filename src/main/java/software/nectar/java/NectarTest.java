package software.nectar.java;

import software.nectar.java.models.Token;

import java.util.HashMap;
import java.util.Map;

public class NectarTest {

    public static void main(String[] args) {
        try {
            final String KEY = "4d49b676-5aab-48ac-b6c3-3ff4c82bb229";
            final String SECRET = "8d313aa9-6cb3-4c0a-a547-f905f5a952e0";

            Nectar nectar = new Nectar(KEY, SECRET);

            // Get Token
            Token generatedToken = nectar.getTokenFactory().getToken("590e9044-823f-4904-8c9e-4028b4b0116e");
            System.out.println(String.format("Get Token\n====================\n%s\n", generatedToken));

            // Generate token
            Map<String, String> params = new HashMap<>();
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
            generatedToken = nectar.getTokenFactory().generateToken(params);
            System.out.println(String.format("Generate Token\n====================\n%s\n", generatedToken));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
