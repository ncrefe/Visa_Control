package ModelsDocument;

import Interfaces.IDocument;

public class Document implements IDocument {
    private String documentType;
    private int durationMonths;

    public Document(String[] data) {
        documentType = data[2];
        if (data.length > 3) {
            durationMonths = Integer.parseInt(data[3]);
        }
    }

    public Document(Document document) {
        this.documentType = document.documentType;
        this.durationMonths = document.durationMonths;
    }

    public String getDocumentType() {
        return documentType;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    @Override
    public String toString() {
        return "Document [documentType=" + documentType + ", durationMonths=" + durationMonths + "]";
    }

}
