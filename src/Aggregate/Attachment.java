package Aggregate;

public class Attachment {
    private final String id;
    private final String fileName;
    private final String fileType;

    public Attachment(String id, String fileName, String fileType) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }
}