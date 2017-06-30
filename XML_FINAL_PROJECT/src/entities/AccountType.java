package entities;

/**
 * Created by khang on 7/1/2017.
 */
public enum AccountType {
    NORMAL("Thường"), VIP("Vip"), PREMIUM("Bạch kim");
    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
