package api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in test resources");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    //URL
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    //User UUIDs
    public static String getValidUserUuid() {
        return properties.getProperty("valid.user.uuid");
    }

    public static String getValidSourceUuid() {
        return properties.getProperty("valid.source.uuid");
    }

    public static String getAnotherUserUUid() {
        return properties.getProperty("another.user.uuid");
    }

    public static String getNonExistentUuid() {
        return properties.getProperty("non.existent.uuid");
    }

    public static String getInvalidUuid() {
        return properties.getProperty("invalid.uuid");
    }

    //Tokens
    public static String getValidToken() {
        return properties.getProperty("valid.token");
    }

    public static String getExpiredToken() {
        return properties.getProperty("expired.token");
    }

    //Payment
    public static String getPaymentMethod() {
        return properties.getProperty("payment.method");
    }

    public static String getPaymentAsset() {
        return properties.getProperty("payment.asset");
    }

    public static String getPaymentMethodCrypto() {
        return properties.getProperty("payment.method.crypto");
    }

    public static String getPaymentMethodUnsupported() {
        return properties.getProperty("payment.method.unsupported");
    }
}
