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
        XmlHandler xmlHandler = new XmlHandler();
        List<Transaction> transactionList = xmlHandler.parseXml();
        int size = transactionList.size();
        String sizi= String.valueOf(size);
        Socket terminalSocket =new Socket("localhost",8080);
        ObjectOutputStream outputStream = new ObjectOutputStream(terminalSocket.getOutputStream());
        PrintStream printStream =new PrintStream(terminalSocket.getOutputStream());
        printStream.println("++++++++");
        InputStreamReader serverStreamReader = new InputStreamReader(terminalSocket.getInputStream());
        BufferedReader terminalBufferReader = new BufferedReader(serverStreamReader);
        String message = terminalBufferReader.readLine();
        System.out.println(message);
        for (Transaction tx : transactionList) {
            //Transaction transactionObject : transactionList);
            outputStream.writeObject(tx);
        }
    }
}