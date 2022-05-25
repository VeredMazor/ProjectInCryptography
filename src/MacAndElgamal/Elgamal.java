package MacAndElgamal;

public class Elgamal extends Kriptografi {
    private int privateKey;
    Titik publicKey;
    ECC ecc;

    public Elgamal(ECC ecc){
        this.ecc = ecc;
    }

    public Elgamal(ECC ecc, Titik pubKey){
        this.ecc = ecc;
        publicKey = pubKey;
    }

    public Elgamal(ECC ecc, int privateKey){
        this.ecc = ecc;
        this.privateKey = privateKey;
    }

    public boolean generateKey(int k){
        privateKey = k;
        publicKey = ecc.G.multiPoint(privateKey, ecc.a, ecc.p);
        return publicKey.y !=0;
    }
    
    public Titik getPublicKey() {
    	return publicKey;
    }

    public int getPrivateKey() {
        return privateKey;
    }

    public int[] encryption(int[][] plain, int r){
        int[] cipher = new int[18];
        Titik alpha;
        Titik betha;

        alpha = ecc.G.multiPoint(r, ecc.a, ecc.p);
        betha = publicKey.multiPoint(r, ecc.a, ecc.p);

        cipher[0] = alpha.x;
        cipher[1] = alpha.y;

        int temp = 2;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                cipher[temp] = betha.x ^ plain[i][j];
                temp++;
            }
        }
        return cipher;
    }

    public int[][] decryption(int[] cipher){
        int[][] plain = new int[4][4];
        Titik alpha = new Titik(cipher[0], cipher[1]);
        Titik betha;
        betha = alpha.multiPoint(privateKey, ecc.a, ecc.p);

        int temp = 2;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                plain[i][j] = betha.x ^ cipher[temp];
                temp++;
            }
        }
        return plain;
    }
}