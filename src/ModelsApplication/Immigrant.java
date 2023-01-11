package ModelsApplication;

import java.util.ArrayList;

import ModelsDocument.Document;
import ModelsOther.*;

public class Immigrant extends Application {
    public Immigrant(String applicationId, String name, ArrayList<Document> documents, FinancialStatus financialStatus,
            Passport passport, Photo photo, String visaDuration, String status) {
        super(applicationId, name, documents, financialStatus, passport, photo, visaDuration, status);
    }

    public Immigrant(String applicationId) {
        super(applicationId);
    }

    public boolean financialStatusCheck() {
        int savings = getFinancialStatus().getSavings();

        int discountWithDocument = 1;
        if (hasAInvitationLetter()) {
            discountWithDocument = 2;
        }

        if (hasAGreenCard() && savings >= 4000 / discountWithDocument) {
            return true;
        } else
            return !hasAGreenCard() && savings >= 50000 / discountWithDocument;
    }
}
