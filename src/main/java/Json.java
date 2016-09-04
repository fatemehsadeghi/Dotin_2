import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Provider;
import java.util.*;

public class Json {
    public static List<Deposit> depositList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException,
            IOException, ParseException {

        parseJson();
     //   writeJson();
    }
    public static void parseJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("core.json"));
        String stringPort = (String) jsonObject.get("port");
        int port = Integer.parseInt(stringPort);
        System.out.println("port::" + stringPort);
        JSONArray deposits = (JSONArray) jsonObject.get("deposits");
        for (JSONObject c : (List<JSONObject>) deposits) {
            Deposit deposit = new Deposit();
            deposit.setCustomer((String) c.get("customer"));
            deposit.setId(Integer.parseInt((String) c.get("id")));
            deposit.setInitialBalance(new BigDecimal((String) c.get("initialBalance")));
            deposit.setUpperBound(new BigDecimal((String) c.get("upperBound")));
            depositList.add(deposit);
            System.out.println(deposit);
        }
    }
    /*
    for (int i = 0; i < serv.size(); i++) {
    JSONObject service = (JSONObject) serv.get(i);
    Service servec = new Service();
    servec.setServiceName((String) service.get("serviceName"));
    servec.setClassName((String) service.get("className"));
    servec.setIsEnabled((Boolean) service.get("isEnabled"));

    services.add(i, servec);
}
     */
///////////////////////////////////////////////
    /*
    public static void writeJson() {
        HashMap<String, String> depositMap = new HashMap<>();
        depositMap.put("customer", );
        depositMap.put("");
        JSONArray servicesJSON = new JSONArray();
        ArrayList<Provider.Service> servicesArray = this.getServices();
        for (int i = 0; i < servicesArray.size(); i++) {
            servicesJSON.add(servicesArray.get(i).toMap()); // use the toMap method here.
        }
        obj.put("services", servicesJSON);
        */
        /////////////////////////////

        //return depositMap;
        /*
        String content = json.toString();
FileWriter fw = new FileWriter("path");
BufferedWriter bw = new BufferedWriter(fw);
bw.write(content);
bw.close();
         */

    }