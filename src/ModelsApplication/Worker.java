package ModelsApplication;

import java.util.ArrayList;

import ModelsDocument.Document;
import ModelsOther.*;
public class Worker extends Application {
    public Worker(String applicationId, String name, ArrayList<Document> documents, FinancialStatus financialStatus,
            Passport passport, Photo photo, String visaDuration, String status) {
        super(applicationId, name, documents, financialStatus, passport, photo, visaDuration, status);
    }

    public Worker(String applicationId) {
        super(applicationId);
    }

    public boolean financialStatusCheck() {
        if (getFinancialStatus() == null) {
            return false;
        }
        return getFinancialStatus().getSavings() >= 2000;
    }

    public int visaDurationInMonths() {
        int expirationDurationInMonths = super.getPassport().getExpirationDurationInMonths();

        if (getPassport().validExpirationDurationCheck() || expirationDurationInMonths < 12) {
            return 0;
        } else if (expirationDurationInMonths < 24) {
            return 12;
        } else if (expirationDurationInMonths < 60) {
            return 24;
        } else {
            return 60;
        }
    }
}
