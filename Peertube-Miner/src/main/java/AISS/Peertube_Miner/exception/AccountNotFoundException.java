package AISS.Peertube_Miner.exception;

public class AccountNotFoundException extends RuntimeException {
    private String accountName;
    public AccountNotFoundException(String accountName) {
        super("El account '" + accountName + "' no existe en PeerTube");
        this.accountName = accountName;
    }
    public String getAccountName() {
        return accountName;
    }
}