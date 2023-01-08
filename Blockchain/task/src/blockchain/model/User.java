package blockchain.model;

import blockchain.security.GenerateKeys;

import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class User {

    private final String name;
    private final String privateKeyFile;
    private final String publicKeyFile;
    private GenerateKeys generateKeys;
    private long balance;

    public User(String name) {
        this.name = name;
        this.privateKeyFile = String.format("KeyPair/%s/public/privateKey", name);
        this.publicKeyFile = String.format("KeyPair/%s//private/publicKey", name);
        generateKeys();
    }

    public String getName() {
        return name;
    }

    public void generateKeys() {
        try {
            generateKeys = new GenerateKeys(2048);
            generateKeys.createKeys();
            generateKeys.writeToFile(privateKeyFile, generateKeys.getPrivateKey().getEncoded());
            generateKeys.writeToFile(publicKeyFile, generateKeys.getPublicKey().getEncoded());
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sign(Transaction transaction) throws Exception{
        encode(transaction);
    }

    public PublicKey getPublicKey() {
        return generateKeys.getPublicKey();
    }

    public void encode(Transaction transaction)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(generateKeys.getPrivateKey());
        rsa.update(transaction.getBytes());
        String signature = Base64.getEncoder().encodeToString(rsa.sign());
        transaction.setSignature(signature);
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }
}
