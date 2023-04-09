package utc2.itk62.sneaker.until;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashedPassword {
    private static final int SALT_LENGTH = 16;

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        // Concatenate the password and salt
        byte[] passwordAndSalt = concatenateByteArrays(password.getBytes(StandardCharsets.UTF_8), salt);

        // Hash the concatenated password and salt using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(passwordAndSalt);

        // Combine the hash and salt into a single string
        byte[] hashAndSalt = concatenateByteArrays(hash, salt);
        String encodedHashAndSalt = Base64.getEncoder().encodeToString(hashAndSalt);

        return encodedHashAndSalt;
    }

    private static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
