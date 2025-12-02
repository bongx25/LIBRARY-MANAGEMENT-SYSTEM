public class Item {
    private String serialNo;
    private String name;
    private String authorName;
    private String status;

    public Item(String serialNo, String name, String authorName, String status) {
        this.serialNo = serialNo;
        this.name = name;
        this.authorName = authorName;
        this.status = status;
    }

    public String getSerialNo() { return serialNo; }
    public String getName() { return name; }
    public String getAuthorName() { return authorName; }
    public String getStatus() { return status; }
    public boolean isAvailable() { return "Available".equalsIgnoreCase(status); }
}