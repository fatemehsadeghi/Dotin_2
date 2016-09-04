import com.sun.corba.se.spi.activation.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
public class ServerSide {
    private ServerSocket serverSocket;
    private Socket serverSideSocket;
    private String message;
    public static void main(String[] args) throws IOException {
        ServerSide serverSide =new ServerSide();
        serverSide.run();


    }

    public void run() {
        try {
            serverSocket = new ServerSocket(8080);
        }catch (IOException e)
        {
            System.out.println("");
        }

        while (true) {
            try {

                serverSideSocket = serverSocket.accept();
                InputStreamReader serverStreamReader = new InputStreamReader(serverSideSocket.getInputStream());
                BufferedReader serverBufferReader = new BufferedReader(serverStreamReader);
                message = serverBufferReader.readLine();
                System.out.println(message);
            }catch (IOException e)
            {  e.printStackTrace();
                System.out.println("Connection Error");

            }


            try {
                if (message != null) {
                    PrintStream printStream = new PrintStream(serverSideSocket.getOutputStream());
                    printStream.println("message receive");
                }
            }catch (IOException e){}}

    }


}
