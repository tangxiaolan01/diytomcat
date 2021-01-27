package adapter.vo;



public class CreateAccount {
    private String number;      // 开户编号
    private String address;     // 开户地
    private String accountDate;   // 开户时间
    private String desc;        // 开户描述

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(String accountDate) {
        this.accountDate = accountDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CreateAccount{" +
                "number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", accountDate=" + accountDate +
                ", desc='" + desc + '\'' +
                '}';
    }
}
