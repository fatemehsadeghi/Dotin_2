import java.math.BigDecimal;

/**
 * Created by fateme on 21/08/2016.
 */
public class Transaction {
   private int port;
    private String terminalId;
    private String terminalType;
    private String id;
    private String deposit;
    private String transactionType;
    private BigDecimal amount;

    public Transaction() {

    }

    public Transaction(String transactionId, String transactionType, BigDecimal amount, String deposit) {
        this.terminalId=transactionId;
        this.transactionType=transactionType;
        this.amount=amount;
        this.deposit=deposit;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return transactionType;
    }

    public void setType(String type) {
        this.transactionType = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
