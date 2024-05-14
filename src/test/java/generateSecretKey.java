import java.security.SecureRandom;
import java.util.Base64;

public class generateSecretKey {
    public static void main(String[] args) {
        byte[] randomKey = new byte[64]; // 512 bits
        new SecureRandom().nextBytes(randomKey);
        String base64Secret = Base64.getEncoder().encodeToString(randomKey);
        System.out.println("Generated Base64 Secret: " + base64Secret);
    }
}
