import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkClient {

    public static void main(String args[]) throws IOException{


        InetAddress address=InetAddress.getLocalHost();
        Socket clientSocket=null;
        String line=null;
        BufferedReader inputBufferReader=null;
        BufferedReader bufferedReader=null;
        PrintWriter printWriter=null;

        try {
            clientSocket=new Socket(address, 8080); // You can use static final constant PORT_NUM
            inputBufferReader= new BufferedReader(new InputStreamReader(System.in));
            bufferedReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printWriter= new PrintWriter(clientSocket.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Client Address : "+address);
        System.out.println("Enter Data to echo Server ( Enter QUIT to end):");

        String response=null;
        try{
            line=inputBufferReader.readLine();
            while(line.compareTo("QUIT")!=0){
                printWriter.println(line);
                printWriter.flush();
                response=bufferedReader.readLine();
                System.out.println("Server Response : "+response);
                line=inputBufferReader.readLine();

            }



        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("Socket read Error");
        }
        finally{

            bufferedReader.close();printWriter.close();inputBufferReader.close();clientSocket.close();
            System.out.println("Connection Closed");

        }

    }
}