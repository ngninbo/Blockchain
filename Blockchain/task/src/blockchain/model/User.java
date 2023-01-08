package blockchain.model;

import blockchain.Blockchain;
import blockchain.security.GenerateKeys;

import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class User {

    private final String name;
    private final Blockchain blockchain = Blockchain.getInstance();
    private final String privateKeyFile;
    private final String publicKeyFile;
    private final GenerateKeys generateKeys;

    public User(String name) throws NoSuchAlgorithmException {
        this.name = name;
        this.privateKeyFile = String.format("KeyPair/%s/public/privateKey", name);
        this.publicKeyFile = String.format("KeyPair/%s//private/publicKey", name);
        generateKeys = new GenerateKeys(2048);
    }

    public String getName() {
        return name;
    }

    public void sendMessage(Message message) throws Exception {
        sign(message);
        blockchain.send(message);
    }

    public void generateKeys() {
        try {
            generateKeys.createKeys();
            generateKeys.writeToFile(privateKeyFile, generateKeys.getPrivateKey().getEncoded());
            generateKeys.writeToFile(publicKeyFile, generateKeys.getPublicKey().getEncoded());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void sign(Message message) throws Exception{
        encode(message);
    }

    public PublicKey getPublicKey() {
        return generateKeys.getPublicKey();
    }

    public void encode(Message message)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(generateKeys.getPrivateKey());
        rsa.update(message.getBytes());
        String signature = Base64.getEncoder().encodeToString(rsa.sign());
        message.setSignature(signature);
    }
}
