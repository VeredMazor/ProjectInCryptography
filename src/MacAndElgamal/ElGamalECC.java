package MacAndElgamal;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class ElGamalECC {
    private EllipticCurve ellipticCurve;
    private BigInteger privateKey;
    private Point publicKey;
    private Point basePoint;
    private int k = 30;

    public ElGamalECC(EllipticCurve ec, long privateKey) {
        this.ellipticCurve = ec;
        this.privateKey = BigInteger.valueOf(privateKey);
    }

    public void setBasePoint(Point p) {
        this.basePoint = p;
        Point pub = ellipticCurve.multiplyPoint(p, this.privateKey.longValue());
        this.publicKey = pub;
    }
    
    public Point getPublicKey() {
    	return publicKey;
    	
    }

    public List<EncryptedMessage> encrypt(String message) {
        List<EncryptedMessage> encryptedMessages = new ArrayList<>();

        for (int i=0; i<message.length(); i++) {
            BigInteger msg = BigInteger.valueOf(message.charAt(i));
            Point pointKey = ellipticCurve.multiplyPoint(basePoint, k);
            Point pointMessage = ellipticCurve.messageToPoint(msg, BigInteger.valueOf(k));
            Point encMessage = ellipticCurve.addPoint(pointMessage, ellipticCurve.multiplyPoint(publicKey, k));
            //System.out.println(pointMessage);

            EncryptedMessage em = new EncryptedMessage(pointKey, encMessage);
            encryptedMessages.add(em);
        }

        return encryptedMessages;
    }

    public String decrypt(List<EncryptedMessage> encryptedMessages) {
        StringBuilder message = new StringBuilder();

        for (EncryptedMessage em: encryptedMessages) {
            Point pointKey = ellipticCurve.multiplyPoint(em.getPointKey(), this.privateKey.longValue());
            Point pointMessage = ellipticCurve.minusPoint(em.getPointMessage(), pointKey);

            //System.out.println(pointMessage);
            BigInteger m = ellipticCurve.pointToMessage(pointMessage, BigInteger.valueOf(k));
            message.append((char) m.intValue());
        }

        return message.toString();
    }

    public static void main(String[] args) {
        // Test Encrypt Decrypt
        EllipticCurve ec = new EllipticCurve(-1, 188, 65537);
        ElGamalECC elGamal = new ElGamalECC(ec, 7);

        Point p = new Point(1, 6);

        elGamal.setBasePoint(p);

        String message = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        List<EncryptedMessage> encryptedMessages = elGamal.encrypt(message);

        //System.out.println(encryptedMessages);

        String m = elGamal.decrypt(encryptedMessages);

        //System.out.println(message);
        //System.out.println(m);
    }
}
