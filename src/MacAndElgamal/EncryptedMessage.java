package MacAndElgamal;



public class EncryptedMessage {
	private Point pointKey2;
    private Point pointMessage;
    
    public EncryptedMessage(Point pointKey2, Point encMessage) {
    	this.pointKey2 = pointKey2;
    	pointMessage = encMessage;
		
	}
    
    public Point getPointKey() {
    	
    	return pointKey2;
    }
	
    public Point getPointMessage(){
    	
    	return pointMessage;
    }
}
