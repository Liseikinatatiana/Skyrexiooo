package api.dto;

public class CreateSubscriptionRequest {
    private String skyrexUserUuid;
    private String sourceUuid;
    private String paymentMethod;
    private String paymentAsset;

    public CreateSubscriptionRequest() {
    }

    public CreateSubscriptionRequest(String skyrexUserUuid, String sourceUuid,
                                     String paymentMethod, String paymentAsset) {
        this.skyrexUserUuid = skyrexUserUuid;
        this.sourceUuid = sourceUuid;
        this.paymentMethod = paymentMethod;
        this.paymentAsset = paymentAsset;
    }

    public String getSkyrexUserUuid() {
        return skyrexUserUuid;
    }

    public String getSourceUuid() {
        return sourceUuid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentAsset() {
        return paymentAsset;
    }

    public void setSkyrexUserUuid(String skyrexUserUuid) {
        this.skyrexUserUuid = skyrexUserUuid;
    }

    public void setSourceUuid(String sourceUuid) {
        this.sourceUuid = sourceUuid;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentAsset(String paymentAsset) {
        this.paymentAsset = paymentAsset;
    }
}
