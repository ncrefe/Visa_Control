package ModelsApplication;

import java.util.ArrayList;

import Interfaces.IApplication;
import ModelsDocument.Document;
import ModelsOther.*;

public class Application implements Comparable, IApplication {
  private String applicationId;
  private String name;
  private ArrayList<Document> documents = new ArrayList<Document>();
  private FinancialStatus financialStatus;
  private Passport passport;
  private Photo photo;
  private String visaDuration;
  private String status;

  public Application(String applicationId) {
    this.applicationId = applicationId;
  }

  public Application(String applicationId, String name, ArrayList<Document> documents, FinancialStatus financialStatus,
      Passport passport, Photo photo, String visaDuration, String status) {
    this.applicationId = applicationId;
    this.name = name;
    this.documents = documents;
    this.financialStatus = financialStatus;
    this.passport = passport;
    this.photo = photo;
    this.visaDuration = visaDuration;
    this.status = status;
  }

  public String getApplicationId() {
    return applicationId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Document> getDocuments() {
    ArrayList<Document> tempDocuments = new ArrayList<Document>();
    for (Document doc : documents) {
      tempDocuments.add(new Document(doc));
    }
    return tempDocuments;
  }

  public void addDocument(Document document) {
    documents.add(document);
  }

  public FinancialStatus getFinancialStatus() {
    if (financialStatus == null) {
      return null;
    }
    return new FinancialStatus(this.financialStatus);
  }

  public void setFinancialStatus(FinancialStatus financialStatus) {
    this.financialStatus = financialStatus;
  }

  public Passport getPassport() {
    if (this.passport == null)
      return null;

    return new Passport(this.passport);
  }

  public void setPassport(Passport passport) {
    this.passport = passport;
  }

  public Photo getPhoto() {
    if (this.photo == null)
      return null;

    return new Photo(this.photo);
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public boolean financialStatusCheck() {
    return true;
  }

  private boolean hasADocument(String documentType) {
    if (getDocuments().size() == 0) {
      return false;
    }

    for (Document document : this.getDocuments()) {
      if (document.getDocumentType().equals(documentType)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasAInvitationLetter() {
    return hasADocument("IL");
  }

  public boolean hasALetterOfAcceptance() {
    return hasADocument("LA");
  }

  public boolean hasAGreenCard() {
    return hasADocument("GC");
  }

  public int visaDurationInMonths() {
    return 0;
  }

  public String rejectionReason() {
    if (getPassport() == null) {
      return "Applicant does not have a passport";
    } else if (!getPassport().validPassportCheck()) {
      return "Passport is not valid";
        } else if ((getClass().getSimpleName().equals("Educational") || getClass().getSimpleName().equals("Worker")) && visaDurationInMonths() == 0) {
      return "Passport expiration date is not valid";
    } else if (getClass().getSimpleName().equals("Tourist") && getPassport().validExpirationDurationCheck()) {
      return "Passport expiration date is not valid";
    } else if (getPhoto() == null) {
      return "Applicant does not have a photo";
    } else if (!getPhoto().validResolutionCheck()) {
      return "Resolution of photo is not valid";
    } else if (!getPhoto().validPositionCheck()) {
      return "Position in the photo is not valid";
    } else if (getFinancialStatus() == null) {
      return "Applicant does not have a financial status report";
    } else if (!financialStatusCheck()) {
      return "Applicant does not have a stable financial status";
        } else if (getClass().getSimpleName().equals("Educational") || getClass().getSimpleName().equals("Worker")) {
            if (!hasALetterOfAcceptance()) {
      return "Applicant does not have a letter of acceptance";
    }
        }
    return "Accepted";
  }

  public void statusUpdate() {
    if (rejectionReason().equals("Accepted")) {
      setStatus("Accepted");
    } else {
      setStatus("Rejected");
    }
  }

  public void visaApplicationResult() {
    statusUpdate();
    System.out.print("Applicant ID: " + getApplicationId());
    System.out.print(", Name: " + getName());
    System.out.print(", Visa Type: " + this.getClass().getSimpleName());
    System.out.print(", Status: " + status);
    if (status.equals("Accepted")) {
      System.out.print(", Visa Duration: " + visaDurationToString());
    } else {
      System.out.print(", Reason: " + rejectionReason());
    }
    System.out.println();
  }

  private String visaDurationToString() {
    return switch (visaDurationInMonths()) {
      case 0 -> "Permanent";
      case 6 -> "6 Months";
      case 12 -> "1 Years";
      case 24 -> "2 years";
      case 48 -> "4 Years";
      case 60 -> "5 Years";
      default -> "";
    };
  }

  @Override
  public String toString() {
    return "Application [applicationId=" + applicationId + ", documents=" + documents + ", financialStatus="
        + financialStatus + ", name=" + name + ", passport=" + passport + ", photo=" + photo + ", status=" + status
        + ", visaDuration=" + visaDuration + "]";
  }

  @Override
  public int compareTo(Object o) {
    if (o == null) {
      return 1;
    } else {
            return Integer.compare(Integer.parseInt(((Application) o).getApplicationId()), Integer.parseInt(getApplicationId()));
    }
  }
}
