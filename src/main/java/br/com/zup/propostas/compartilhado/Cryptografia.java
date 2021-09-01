package br.com.zup.propostas.compartilhado;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Component
public class Cryptografia {

    @Value( "${values.application.cryptosecret}")
    private String cryptosecret;
    private static SecretKeySpec secretKey;
    private static byte[] key;
    Logger log = LoggerFactory.getLogger(Cryptografia.class);

    public Cryptografia() { }

    public  void gerarAESKey(String cryptosecret)
    {
        try {
            key = cryptosecret.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (Exception e) {
            log.error("Falha ao gerar secret AES.");
            e.printStackTrace();gir
        }
    }

    public String cryptografar(String valor)
    {
        try
        {
            gerarAESKey(this.cryptosecret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(valor.getBytes(StandardCharsets.UTF_8)));
        }
        catch (Exception e)
        {
            log.error("Falha ao encryptar um recurso.");
            return null;
        }
    }

    public  String decryptografar(String valor)
    {
        try
        {
            gerarAESKey(this.cryptosecret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(valor)));
        }
        catch (Exception e)
        {
            log.error("Falha ao decryptar um recurso.");
            return null;
        }
    }
}
