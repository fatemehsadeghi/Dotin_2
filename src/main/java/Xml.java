import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.ObjectOutputStream;
        import java.lang.*;
        import java.lang.Class;
        import java.math.BigDecimal;
        import java.util.*;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.DocumentBuilder;

        import org.w3c.dom.Document;
        import org.w3c.dom.NodeList;
        import org.w3c.dom.Node;
        import org.w3c.dom.Element;

public class Xml {

    public static void parseXml() {
        String terminalId;
        String terminalType;
        String serverIp;
        String port;
        String outLogPath;
        Node transactionId = null;
        String type;
        BigDecimal amount;
        String deposit;
        List<Transaction> transactionsList = new ArrayList<>();
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(new File("Terminal.xml"));
            NodeList base = doc.getElementsByTagName("transactions");
            Node basenode = base.item(0);
            System.out.println(basenode.getNodeName() + getAttributesAsString(basenode.getAttributes()));
            NodeList children = basenode.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                System.out.println(children.getLength()+"lentgh");
                Node item = children.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(item.getNodeName()+getAttributesAsString(item.getAttributes()));
                    transactionId=item.getAttributes().getNamedItem("id");
                    String idStr = String.valueOf(transactionId);
                    System.out.println( idStr);
                }
            }

                /*
            //File InputFile = new File("Terminal.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("Terminal.xml");
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("transactions");
            //List<Transaction> transactionsList = new ArrayList<>();
            for(int temp=0 ; temp < nodeList.getLength();temp++) {
                if (temp < nodeList.getLength()) {
                    Node nNode = nodeList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        //Element e = (Element)node;
                        String name = eElement.getAttribute("id");
                        System.out.println("aaa"+name);

                                        */
                        // String tem =  eElement.getElementsByTagName("tem").item(0).getNodeValue();
                       // transactionId = NodeList.item(0).getAttributes().getNamedItem("id").getNodeValue();
                        //String.valueOf(eElement.getElementsByTagName("transaction id").item(0).getAttributes());
                      //  type = String.valueOf(eElement.getElementsByTagName("type").item(0).getAttributes());
                        //String amountStr = String.valueOf(eElement.getElementsByTagName("type").item(0).getAttributes());
                        //amount = new BigDecimal(amountStr);
                        //deposit = String.valueOf(eElement.getElementsByTagName("deposit").item(0).getAttributes());
                        //Transaction transaction = new Transaction(transactionId, type, amount, deposit);
                       // transactionsList.add(transaction);
                    //}
                //}

            //}


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
    }
    private static String getAttributesAsString(NamedNodeMap attributes) {
        StringBuilder sb = new StringBuilder("\n");
        for (int j = 0; j < attributes.getLength(); j++) {
            sb.append("\t- ").append(attributes.item(j).getNodeName()).append(": ").append(attributes.item(j).getNodeValue()).append("\n");
        }
        return sb.toString();

    }


    public static void main(String[] args) {
        parseXml();
    }
}