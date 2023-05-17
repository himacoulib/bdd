package ml.satgrie.pl.ue.utils;

public class XlsFile {
    private String id;
    private String name;

    
    public XlsFile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}