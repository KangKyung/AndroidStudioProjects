package deepstreamhub.demo_chat_app;


public class Message {

    private String email;
    private String content;
    private String id;
    private String msgId;

    public Message(String email, String content, String id, String msgId) {
        this.email = email;
        this.content = content;
        this.id = id;
        this.msgId = msgId;
    }

    public String getWriterEmail() {
        return email;
    }

    public void setWriterEmail(String writerEmail) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriterId() {
        return id;
    }

    public void setWriterId(String id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
