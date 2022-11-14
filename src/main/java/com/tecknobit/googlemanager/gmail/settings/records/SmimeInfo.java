package com.tecknobit.googlemanager.gmail.settings.records;

import com.tecknobit.apimanager.formatters.JsonHelper;
import org.json.JSONObject;

import java.util.Base64;

import static com.google.common.io.BaseEncoding.base64Url;

/**
 * The {@code SmimeInfo} class is useful to format a Gmail's smime-info
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at: <a href="https://developers.google.com/gmail/api/reference/rest/v1/users.settings.sendAs#SendAs">SendAs</a>
 **/
public class SmimeInfo {

    /**
     * {@code id} the immutable {@code "ID"} for the SmimeInfo
     **/
    private final String id;

    /**
     * {@code issuerCn} the {@code "S/MIME"} certificate issuer's common name
     **/
    private final String issuerCn;

    /**
     * {@code isDefault} whether this SmimeInfo is the default one for this user's send-as address
     **/
    private final boolean isDefault;

    /**
     * {@code expiration} when the certificate expires (in milliseconds since epoch)
     **/
    private final long expiration;

    /**
     * {@code encryptedKeyPassword} encrypted key password, when key is encrypted
     **/
    private final String encryptedKeyPassword;

    /*
      Union field key. The S/MIME "key", which may consist of various combinations of the public key, private key,
      and certificate chain depending on the format expected and used. key can be only one of the following
      **/

    /**
     * {@code pem} {@code "PEM"} formatted {@code "X509"} concatenated certificate string (standard base64 encoding).
     * Format used for returning key, which includes public key as well as certificate chain (not private key)
     **/
    private String pem;

    /**
     * {@code pkcs12} {@code "PKCS#12"} format containing a single private/public key pair and certificate chain.
     * This format is only accepted from client for creating a new SmimeInfo and is never returned, because the private key
     * is not intended to be exported. {@code "PKCS#12"} may be encrypted, in which case {@code "encryptedKeyPassword"} should be set appropriately.
     * A base64-encoded string
     **/
    private String pkcs12;

    /**
     * Constructor to init a {@link SmimeInfo}
     *
     * @param issuerCn:             the {@code "S/MIME"} certificate issuer's common name
     * @param isDefault:            whether this SmimeInfo is the default one for this user's send-as address
     * @param expiration:           when the certificate expires (in milliseconds since epoch)
     * @param encryptedKeyPassword: encrypted key password, when key is encrypted
     * @param pem:                  {@code "PEM"} formatted {@code "X509"} concatenated certificate string (standard base64 encoding).
     *                              Format used for returning key, which includes public key as well as certificate chain (not private key)
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SmimeInfo}
     **/
    public SmimeInfo(String issuerCn, boolean isDefault, long expiration, String encryptedKeyPassword, String pem) {
        this(null, issuerCn, isDefault, expiration, encryptedKeyPassword, pem);
    }

    /**
     * Constructor to init a {@link SmimeInfo}
     *
     * @param pkcs12:               {@code "PKCS#12"} format containing a single private/public key pair and certificate chain.
     *                              This format is only accepted from client for creating a new SmimeInfo and is never returned, because the private key
     *                              is not intended to be exported. {@code "PKCS#12"} may be encrypted, in which case {@code "encryptedKeyPassword"} should be set appropriately.
     *                              A base64-encoded string
     * @param issuerCn:             the {@code "S/MIME"} certificate issuer's common name
     * @param isDefault:            whether this SmimeInfo is the default one for this user's send-as address
     * @param expiration:           when the certificate expires (in milliseconds since epoch)
     * @param encryptedKeyPassword: encrypted key password, when key is encrypted
     * @apiNote this constructor is useful in those cases if you want to create a new {@link SmimeInfo}
     **/
    public SmimeInfo(String pkcs12, String issuerCn, boolean isDefault, long expiration, String encryptedKeyPassword) {
        this(pkcs12, null, issuerCn, isDefault, expiration, encryptedKeyPassword);
    }

    /**
     * Constructor to init a {@link SmimeInfo}
     *
     * @param id:                   the immutable {@code "ID"} for the SmimeInfo
     * @param issuerCn:             the {@code "S/MIME"} certificate issuer's common name
     * @param isDefault:            whether this SmimeInfo is the default one for this user's send-as address
     * @param expiration:           when the certificate expires (in milliseconds since epoch)
     * @param encryptedKeyPassword: encrypted key password, when key is encrypted
     * @param pem:                  {@code "PEM"} formatted {@code "X509"} concatenated certificate string (standard base64 encoding).
     *                              Format used for returning key, which includes public key as well as certificate chain (not private key)
     **/
    public SmimeInfo(String id, String issuerCn, boolean isDefault, long expiration, String encryptedKeyPassword,
                     String pem) {
        this.id = id;
        this.issuerCn = issuerCn;
        this.isDefault = isDefault;
        this.expiration = expiration;
        this.encryptedKeyPassword = encryptedKeyPassword;
        this.pem = pem;
        pkcs12 = null;
    }

    /**
     * Constructor to init a {@link SmimeInfo}
     *
     * @param id:                   the immutable {@code "ID"} for the SmimeInfo
     * @param pkcs12:               {@code "PKCS#12"} format containing a single private/public key pair and certificate chain.
     *                              This format is only accepted from client for creating a new SmimeInfo and is never returned, because the private key
     *                              is not intended to be exported. {@code "PKCS#12"} may be encrypted, in which case {@code "encryptedKeyPassword"} should be set appropriately.
     *                              A base64-encoded string
     * @param issuerCn:             the {@code "S/MIME"} certificate issuer's common name
     * @param isDefault:            whether this SmimeInfo is the default one for this user's send-as address
     * @param expiration:           when the certificate expires (in milliseconds since epoch)
     * @param encryptedKeyPassword: encrypted key password, when key is encrypted
     **/
    public SmimeInfo(String pkcs12, String id, String issuerCn, boolean isDefault, long expiration,
                     String encryptedKeyPassword) {
        this.id = id;
        this.issuerCn = issuerCn;
        this.isDefault = isDefault;
        this.expiration = expiration;
        this.encryptedKeyPassword = encryptedKeyPassword;
        this.pkcs12 = pkcs12;
        pem = null;
    }

    /**
     * Constructor to init a {@link SmimeInfo}
     *
     * @param jSmimeInfo: {@code "smime-info"} details as {@link JSONObject}
     **/
    public SmimeInfo(JSONObject jSmimeInfo) {
        JsonHelper hSmimeInfo = new JsonHelper(jSmimeInfo);
        id = hSmimeInfo.getString("id");
        issuerCn = hSmimeInfo.getString("issuerCn");
        isDefault = hSmimeInfo.getBoolean("isDefault");
        expiration = hSmimeInfo.getLong("expiration");
        encryptedKeyPassword = hSmimeInfo.getString("encryptedKeyPassword");
        pem = hSmimeInfo.getString("pem");
        pkcs12 = hSmimeInfo.getString("pkcs12");
    }

    /**
     * Method to get {@link #id} instance <br>
     * Any params required
     *
     * @return {@link #id} instance as {@link String}
     **/
    public String getId() {
        return id;
    }

    /**
     * Method to get {@link #issuerCn} instance <br>
     * Any params required
     *
     * @return {@link #issuerCn} instance as {@link String}
     **/
    public String getIssuerCn() {
        return issuerCn;
    }

    /**
     * Method to get {@link #isDefault} instance <br>
     * Any params required
     *
     * @return {@link #isDefault} instance as boolean
     **/
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Method to get {@link #expiration} instance <br>
     * Any params required
     *
     * @return {@link #expiration} instance as long
     **/
    public long getExpiration() {
        return expiration;
    }

    /**
     * Method to get {@link #encryptedKeyPassword} instance <br>
     * Any params required
     *
     * @return {@link #encryptedKeyPassword} instance as {@link String}
     **/
    public String getEncryptedKeyPassword() {
        return encryptedKeyPassword;
    }

    /**
     * Method to get {@link #pem} instance <br>
     * Any params required
     *
     * @return {@link #pem} instance as {@link String}
     **/
    public String getPem() {
        return pem;
    }

    /**
     * Method to encode {@link #pem} in {@link Base64#getUrlDecoder()} format
     * Any params required
     *
     * @apiNote {@link #pem} will be encoded only if it is not encoded yet
     **/
    public void encodePem() {
        try {
            Base64.getUrlDecoder().decode(pem);
        } catch (IllegalArgumentException e) {
            pem = base64Url().omitPadding().encode(pem.getBytes());
        }
    }

    /**
     * Method to decode {@link #pem} in {@link Base64#getUrlDecoder()} format
     * Any params required
     *
     * @apiNote {@link #pem} will be decoded only if it is already encoded
     **/
    public void decodePem() {
        try {
            pem = new String(Base64.getUrlDecoder().decode(pem));
        } catch (IllegalArgumentException ignored) {
        }
    }

    public String getPkcs12() {
        return pkcs12;
    }

    /**
     * Method to encode {@link #pkcs12} in {@link Base64#getUrlDecoder()} format
     * Any params required
     *
     * @apiNote {@link #pkcs12} will be encoded only if it is not encoded yet
     **/
    public void encodePkcs12() {
        try {
            Base64.getUrlDecoder().decode(pkcs12);
        } catch (IllegalArgumentException e) {
            pkcs12 = base64Url().omitPadding().encode(pkcs12.getBytes());
        }
    }

    /**
     * Method to decode {@link #pkcs12} in {@link Base64#getUrlDecoder()} format
     * Any params required
     *
     * @apiNote {@link #pkcs12} will be decoded only if it is already encoded
     **/
    public void decodePkcs12() {
        try {
            pkcs12 = new String(Base64.getUrlDecoder().decode(pkcs12));
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Returns a string representation of the object <br>
     * Any params required
     *
     * @return a string representation of the object as {@link String}
     */
    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
