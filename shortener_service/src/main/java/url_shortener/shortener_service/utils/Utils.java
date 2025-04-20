package url_shortener.shortener_service.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String toBase62(byte[] input, int length) {
        BigInteger bigInt = new BigInteger(1, input);
        StringBuilder sb = new StringBuilder();

        while (bigInt.compareTo(BigInteger.ZERO) > 0 && sb.length() < length) {
            int remainder = bigInt.mod(BigInteger.valueOf(62)).intValue();
            sb.append(BASE62.charAt(remainder));
            bigInt = bigInt.divide(BigInteger.valueOf(62));
        }

        return sb.toString();
    }

    public static String generateRandomAlias(String url) throws NoSuchAlgorithmException {
        String urlWithTimestamp = url + System.currentTimeMillis();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(urlWithTimestamp.getBytes(StandardCharsets.UTF_8));

        return toBase62(hash, 6);
    }
}
