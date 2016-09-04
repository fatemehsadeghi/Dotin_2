import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_X_Client {
    public static void main(String args[]) {


        Socket socket ;
        ServerSocket serverSocket = null;
        System.out.println("Server Listening......");
        try {
            serverSocket = new ServerSocket(8080); // can also use static final PORT_NUM , when defined

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");

        }

        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("connection Established");
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");

            }
        }

    }

}

class ServerThread extends Thread {

    String line = null;
    BufferedReader bufferedReader = null;
    PrintWriter printWriter = null;
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            line = bufferedReader.readLine();
            while (line.compareTo("QUIT") != 0) {

                printWriter.println(line);
                printWriter.flush();
                System.out.println("Response to Client  :  " + line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {

            line = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + line + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println("Socket Out Closed");
                }
                if (socket != null) {
                    socket.close();
                    System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }//end finally
    }
}
