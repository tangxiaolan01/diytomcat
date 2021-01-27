package adapter.vo;



public class OrderMq {
    private String uid;           // 用户ID
    private String sku;           // 商品
    private String orderId;       // 订单ID
    private String createOrderTime; // 下单时间

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(String createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    @Override
    public String toString() {
        return "OrderMq{" +
                "uid='" + uid + '\'' +
                ", sku='" + sku + '\'' +
                ", orderId='" + orderId + '\'' +
                ", createOrderTime=" + createOrderTime +
                '}';
    }
}
