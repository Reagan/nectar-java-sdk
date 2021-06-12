package software.nectar.java;

import software.nectar.java.models.Token;

public class NectarTest {

    public static void main(String[] args) {
        try {
            final String KEY = "4d49b676-5aab-48ac-b6c3-3ff4c82bb229";
            final String SECRET = "8d313aa9-6cb3-4c0a-a547-f905f5a952e0";

            Nectar nectar = new Nectar(KEY, SECRET);
            Token token = nectar.getTokenFactory().getToken("590e9044-823f-4904-8c9e-4028b4b0116e");
            System.out.println("Token " + token);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
