package MacAndElgamal;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.hash;

public class Mac {

	static int counter = 0;
    static List<String> listOfMacs = new ArrayList<>();
   
    // Add a message authentication code to the message using a specific key.
    // You can use a counter to make MACs unique for the same input
    public static String addMac(String message, String key, int messagelength) {
        // TODO
    	System.out.println(message);
        int mac = hash(message + counter);
        String result = message + counter + key + message + mac;
        counter++;
        return result;
    }

    // Verify a message authentication code to the message given a specific key.
    // When message is replayed against system, the function should label it as not correct & return false.
  
	public static boolean checkMac(String message, String key, int messagelength) {
        String[] msg = message.split(key);
        String re = " ";
        
        if (msg.length != 2) {
            return false;
        }
        String counterStr = msg[0].substring(messagelength);
        int counterInt = Integer.parseInt(counterStr);
        String messageWithMac = msg[1];

        String messageWithoutMac = messageWithMac.substring(0, messagelength);
        String newMessage = messageWithoutMac + hash(messageWithoutMac + counterInt);

        if (messageWithMac.contains(newMessage) && !listOfMacs.contains(messageWithMac)) {
            listOfMacs.add(messageWithMac);
            System.out.println("----\n"+ messageWithoutMac);
            return  true;
        }
        return false;
    }
	
}
