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

    }
    public Object passTransaction(){
        XmlHandler.parseXml();
        XmlHandler m1 = new XmlHandler();
        List<Transaction> transList = m1.parseXml();
        //List <Transaction> temp = new ArrayList<>();
        //for (String[] s : transaction) {
           // System.out.println("Next item: " + s);
        for (Transaction transactionObject : transList);

        //(List<Transaction>)



        return null;
    }

}