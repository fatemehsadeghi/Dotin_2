import java.io.*;
import java.net.Socket;
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
}