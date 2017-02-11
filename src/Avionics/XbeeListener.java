package Avionics;


import com.digi.xbee.api.XBeeDevice;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class XbeeListener {

    //private static final String PORT = "COM1";
    //private static final int BAUD_RATE = 9600;

    private XBeeDevice groundXbee;

    public XbeeListener(String PORT, int BAUD_RATE){
        groundXbee = new XBeeDevice(PORT, BAUD_RATE);
    }

    public XBeeDevice getDevice(){
        return groundXbee;
    }

    public String[] filterPacket(String p){

        // TODO filter the string
        String[] packet = p.split(",");
        return packet;
    }

    public void savePackets(String packet){

        // TODO save data packets into a text file
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("data_logs.txt", true));
            pw.write(packet+"\n");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//            if (xbeeMessage != null)
//                System.out.format("From %s >> %s | %s%n", xbeeMessage.getDevice().get64BitAddress(),
//                        HexUtils.prettyHexString(HexUtils.byteArrayToHexString(xbeeMessage.getData())),
//                        new String(xbeeMessage.getData()));