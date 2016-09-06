import java.math.BigDecimal;

public class Deposit {
    private String customer;
    private String id;
    private BigDecimal initialBalance;
    private BigDecimal upperBound;

    public Deposit(String customer, String id, BigDecimal initialBalance, BigDecimal upperBound) {
        this.customer = customer;
        this.id = id;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }

    public Deposit() {

    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    public String getCustomer() {
        return customer;
    }

    public String setCustomer(String customer) {
        this.customer = customer;
        return customer;
    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
        return null;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    @Override
    public String toString() {
        return customer + "#" + id + "#" + initialBalance + "#" + upperBound;
    }
}
