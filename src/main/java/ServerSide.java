import com.sun.corba.se.spi.activation.Server;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;

public class ServerSide {
    private ServerSocket serverSocket;
    private Socket serverSideSocket;
    private String message;

    public static void main(String[] args) throws IOException {
        ServerSide serverSide = new ServerSide();
        serverSide.run();


    }

    public void run() {
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.out.println("");
        }

        while (true) {
            try {
                serverSideSocket = serverSocket.accept();
                InputStreamReader serverStreamReader = new InputStreamReader(serverSideSocket.getInputStream());
                BufferedReader serverBufferReader = new BufferedReader(serverStreamReader);
                message = serverBufferReader.readLine();
                System.out.println(message);
                ////
                System.out.println("le system est pret pour accepter les connexions");
                //Socket socket = ss.accept();
                ObjectInputStream inputStream = new ObjectInputStream(serverSideSocket.getInputStream());
                Transaction transactionObject;
                List<Transaction> transactionList = (List<Transaction>) new Transaction();
                for (int i = 0; i < transactionList.size(); i++) {
                    //ObjectOutputStream os = new ObjectOutputStream(serverSideSocket.getOutputStream());
                    // Message m = (Message) is.readObject();
                    transactionObject = (Transaction) inputStream.readObject();
                    System.out.println(transactionObject);
                    transactionList.add(transactionObject);
                    //validation();
                }
                serverSideSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Connection Error");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            try {
                if (message != null) {
                    PrintStream printStream = new PrintStream(serverSideSocket.getOutputStream());
                    printStream.println("message receive");
                }
            } catch (IOException e) {
            }
        }

    }

    public void validation(Transaction transaction, HashMap map, String id, Deposit deposit) {
        try {
            deposit = (Deposit) map.get(transaction.getDeposit());
        } catch (Exception e) {
            System.out.println("NO Such Deposit");
        }

        if (transaction.getType() == "withdraw") {
            BigDecimal add = deposit.getUpperBound().add(deposit.getInitialBalance());
            int compareResult = transaction.getAmount().compareTo(add);
            if (compareResult == 0 || compareResult == -1) {
                //System.out.println("Both values are equal ");
                deposit.setUpperBound(deposit.getUpperBound().subtract(transaction.getAmount()));
                System.out.println(deposit.getUpperBound());
            } else if (compareResult == 1)
            {
                System.out.println("amount is greater ");
            }
            //else if (compareResult == -1)
            //  System.out.println("Second value is greater");


        }
        if (transaction.getType() == "Deposit") {
            deposit.setUpperBound(deposit.getUpperBound().add(transaction.getAmount()));
            System.out.println(deposit.getUpperBound());
        }
    }


}
