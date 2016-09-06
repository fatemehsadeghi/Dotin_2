import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Terminal {
    public static void main(String[] args) throws IOException {
        Terminal terminal = new Terminal();
        terminal.run();

    }
    public void run() throws IOException {
        Socket terminalSocket =new Socket("localhost",8080);
        PrintStream printStream =new PrintStream(terminalSocket.getOutputStream());
        printStream.println("azi");
        InputStreamReader serverStreamReader = new InputStreamReader(terminalSocket.getInputStream());
        BufferedReader terminalBufferReader = new BufferedReader(serverStreamReader);
        String message = terminalBufferReader.readLine();
        System.out.println(message);
        XmlHandler xmlHandler = new XmlHandler();
        List<Transaction> transactionList = xmlHandler.parseXml();
        ObjectOutputStream os = new ObjectOutputStream(terminalSocket.getOutputStream());
        for (int i=0 ; i<transactionList.size() ; i++) {
            //Transaction transactionObject : transactionList);
            os.writeObject(transactionList.indexOf(i));

        }



    }
    /*
    public Object passTransaction(){
        XmlHandler xmlHandler = new XmlHandler();
        List<Transaction> transactionList = xmlHandler.parseXml();
        ObjectOutputStream os = new ObjectOutputStream(ter.getOutputStream());
        for (Transaction transactionObject : transactionList);
        objectOutput.writeObject();




        return null;
    }
*/
}