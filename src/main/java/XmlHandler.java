import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XmlHandler {

    public  List<Transaction> parseXml() {
        String terminalId;
        String terminalType;
        String serverIp;
        String port;
        String outLogPath;
        String transactionId = null;
        String type;
        BigDecimal amount;
        String deposit;
        List<Transaction> transactionsList= new ArrayList<>();
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(new File("Terminal.xml"));
            NodeList base = doc.getElementsByTagName("transactions");
            Node basenode = base.item(0);
            System.out.println(basenode.getNodeName() + getAttributesAsString(basenode.getAttributes()));
            NodeList children = basenode.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                //System.out.println(children.getLength() + " length");
                Node item = children.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(item.getNodeName() + getAttributesAsString(item.getAttributes()));
                    Node idNode = item.getAttributes().getNamedItem("id");
                    transactionId  = idNode.getNodeValue();
                    //System.out.println(idStr);
                    Node typeNode = item.getAttributes().getNamedItem("type");
                    type = typeNode.getNodeValue();
                    Node amountNode = item.getAttributes().getNamedItem("amount");
                    String amountStr = amountNode.getNodeValue();
                    amount=new BigDecimal(amountStr);
                    Node depositNode=item.getAttributes().getNamedItem("deposit");
                    deposit= depositNode.getNodeValue();
                    Transaction transaction = new Transaction(transactionId, type, amount, deposit);
                    transactionsList.add(transaction);

                }
                //return transactionsList;
            }


            /////sort list
                /*
            Collections.sort(depositList);
            List<String> customerNumPaidInterestList = new ArrayList<>();

            for (int i = 0; i < depositList.size(); i++) {

                Deposit deposit = depositList.get(i);
                System.out.println(deposit.getCustomerNumber() + "#" + deposit.getPayedInterest());
                customerNumPaidInterestList.add("\n" + deposit.getCustomerNumber() + "#" + deposit.getPayedInterest());
                */
            ////write on xml
                /*
                try {

                    FileOutputStream outputFile = new FileOutputStream("Output.txt");
                    ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
                    outputStream.writeUnshared(customerNumPaidInterestList);
                    outputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                */

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return transactionsList;
    }
    /*
    public static List<Object> getList(){
        List<Object> transactionsList = null;
        return transactionsList;}
*/
    private static String getAttributesAsString(NamedNodeMap attributes) {
        StringBuilder sb = new StringBuilder("\n");
        for (int j = 0; j < attributes.getLength(); j++) {
            sb.append("\t- ").append(attributes.item(j).getNodeName()).append(": ").append(attributes.item(j).getNodeValue()).append("\n");
        }
        return sb.toString();

    }


    public static void main(String[] args)
    {
        //parseXml();
    }
}