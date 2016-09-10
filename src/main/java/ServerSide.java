import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.String;

public class ServerSide {
    private ServerSocket serverSocket;
    private Socket serverSideSocket;
    String message;
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
                //int sizeTR = Integer.parseInt(message);
                System.out.println("le system est pret pour accepter les connexions");
                ObjectInputStream inputStream = new ObjectInputStream(serverSideSocket.getInputStream());
                Transaction transactionObject;
                List<Transaction> transactionList = new ArrayList<>();
                for (int i = 0; i < 7; i++) {
                    //ObjectOutputStream os = new ObjectOutputStream(serverSideSocket.getOutputStream());
                    // Message m = (Message) is.readObject();
                    transactionObject = (Transaction) inputStream.readObject();
                    System.out.println(transactionObject);
                    transactionList.add(transactionObject);
                    validate(transactionObject);
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

    public void validate(Transaction transaction) {
        //try {
        Deposit deposit = JsonHandler.depositMap.get(transaction.getDeposit());
        if (transaction.getType().equals("withdraw")) {
            int compareResult = transaction.getAmount().compareTo(deposit.getInitialBalance());
            if (compareResult == 0 || compareResult == -1) {
                deposit.setInitialBalance(deposit.getInitialBalance().subtract(transaction.getAmount()));
            } else if (compareResult == 1) {
                System.out.println("amount is greater ");
            }
            //else if (compareResult == -1)
            //  System.out.println("Second value is greater");
        }
        if (transaction.getType().equals("deposit")) {
            BigDecimal addValue = deposit.getInitialBalance().add(transaction.getAmount());
            int compareResult = addValue.compareTo(deposit.getUpperBound());
            if (compareResult == 0 || compareResult == -1) {
                deposit.setInitialBalance(addValue);
            }
            //}
            //catch(Exception message)
            //      {
            //        message.isEmpty();
            //          System.out.println("NO Such Deposit");

            //}

        }
    }
}
















