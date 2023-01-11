package FileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import ModelsApplication.*;
import ModelsDocument.*;
import ModelsOther.*;

public class FileProcessor {
    private ArrayList<Application> applications = new ArrayList<Application>();

    public void scan(String file) throws ParseException {
        Scanner scanner;

        try {
            scanner = new Scanner(new File("src/assets/" + file));

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");

                switch (data[0]) {
                    case "D":
                        if (data[2].equals("IL")) {
                            getApplicationByApplicationId(data[1]).addDocument(new InvitationLetter(data));
                        } else if (data[2].equals("LA")) {
                            getApplicationByApplicationId(data[1]).addDocument(new LetterOfAcceptance(data));
                        } else if (data[2].equals("GC")) {
                            getApplicationByApplicationId(data[1]).addDocument(new GreenCard(data));
                        }
                        break;
                    case "P":
                        Photo photo = new Photo(data);
                        getApplicationByApplicationId(data[1]).setPhoto(photo);
                        break;
                    case "A":
                        getApplicationByApplicationId(data[1]).setName(data[2]);
                        break;
                    case "S":
                        Passport passport = new Passport(data);
                        getApplicationByApplicationId(data[1]).setPassport(passport);
                        break;
                    case "F":
                        FinancialStatus financialStatus = new FinancialStatus(data);
                        getApplicationByApplicationId(data[1]).setFinancialStatus(financialStatus);
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Application> getApplication() {
        return applications;
    }

    private Application getApplicationByApplicationId(String id) {
        for (Application application : applications) {
            if (application.getApplicationId().equals(id)) {
                return application;
            }
        }

        Application applicant = switch (id.substring(0, 2)) {
            case "11" -> new Tourist(id);
            case "23" -> new Worker(id);
            case "25" -> new Educational(id);
            case "30" -> new Immigrant(id);
            default -> new Application(id);
        };

        applications.add(applicant);
        return applicant;
    }

}
