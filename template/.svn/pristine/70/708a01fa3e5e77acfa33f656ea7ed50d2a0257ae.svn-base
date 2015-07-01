package tv.samim.common.communication;

/**
 * @author mojtaba khallash
 */
public class WelcomeResponse {
    private String status;
    private String text;

    public WelcomeResponse(String status, String text) {
        this.status = status;
        this.text = text;
    }

    public WelcomeResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("successful"))
            this.text = "info";
        else
            this.text = "error";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {

        this.text = text;
    }
}