package ModelsApplication;

import java.util.ArrayList;

import ModelsDocument.Document;
import ModelsOther.*;

public class Tourist extends Application {
    public Tourist(String applicationId, String name, ArrayList<Document> documents, FinancialStatus financialStatus,
            Passport passport, Photo photo, String visaDuration, String status) {
        super(applicationId, name, documents, financialStatus, passport, photo, visaDuration, status);
    }

    public Tourist(String applicationId) {
        super(applicationId);
    }

    public boolean financialStatusCheck() {
        if (getFinancialStatus() == null)
            return false;
        int discountWithDocument = 1;

        if (hasAInvitationLetter()) {
            discountWithDocument = 2;
        }

        int income = getFinancialStatus().getIncome();
        int savings = getFinancialStatus().getSavings();
        if (income < 2000 / discountWithDocument) {
            return false;
        } else if (income <= 3000 / discountWithDocument && savings < 12000 / discountWithDocument) {
            return false;
        } else
            return income <= 3000 / discountWithDocument || income > 4000 / discountWithDocument
                    || savings >= 6000 / discountWithDocument;
    }

    private int calculateDurationConstant() {
        if (hasAInvitationLetter()) {
            int durationConstant = ((getFinancialStatus().getIncome() - 2000) * 6 + getFinancialStatus().getSavings()) / 6000;
            return durationConstant;
        } else {
            int durationConstant = ((getFinancialStatus().getIncome() - 2000) * 6 + getFinancialStatus().getSavings()) / 12000;
            return durationConstant;
        }
    }

    public int visaDurationInMonths() {

        int expirationDurationInMonths = super.getPassport().getExpirationDurationInMonths();

        if (getPassport().validExpirationDurationCheck()) {
            return 0;
        } else if (calculateDurationConstant() >= 1 && calculateDurationConstant() < 2) {
            return 6;
        } else if (calculateDurationConstant() >= 2 && calculateDurationConstant() <= 4) {
            if (expirationDurationInMonths < 12) {
                return 6;
            }
            return 12;
        } else if (calculateDurationConstant() > 4) {
            if (expirationDurationInMonths < 60) {
                if (expirationDurationInMonths < 12) {
                    return 6;
                }
                return 12;
            }
            return 60;
        }
        return 0;
    }
}
