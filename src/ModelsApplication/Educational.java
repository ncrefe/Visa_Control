package ModelsApplication;

import java.util.ArrayList;

import ModelsDocument.Document;
import ModelsOther.*;

public class Educational extends Application {
    public Educational(String applicationId, String name, ArrayList<Document> documents, FinancialStatus financialStatus,
                       Passport passport, Photo photo, String visaDuration, String status) {
        super(applicationId, name, documents, financialStatus, passport, photo, visaDuration, status);
    }

    public Educational(String applicationId) {
        super(applicationId);
    }

    public boolean financialStatusCheck() {
        int discountWithDocument = 1;
        if (hasAInvitationLetter()) {
            discountWithDocument = 2;
        }

        int income = getFinancialStatus().getIncome();
        int savings = getFinancialStatus().getSavings();
        if (income < 1000 / discountWithDocument) {
            return false;
        } else if (income <= 2000 / discountWithDocument && savings < 6000 / discountWithDocument) {
            return false;
        } else
            return income <= 2000 / discountWithDocument || income > 3000 / discountWithDocument
                    || savings >= 3000 / discountWithDocument;
    }

    public int visaDurationInMonths() {
        int expirationDurationInMonths = super.getPassport().getExpirationDurationInMonths();

        if (getPassport().validExpirationDurationCheck() || expirationDurationInMonths < 12) {
            return 0;
        } else if (expirationDurationInMonths < 24) {
            return 12;
        } else if (expirationDurationInMonths < 48) {
            return 24;
        } else {
            return 48;
        }
    }
}
