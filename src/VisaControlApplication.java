import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import FileIO.FileProcessor;
import ModelsApplication.Application;

public class VisaControlApplication {
    public static void main(String[] args) {

        FileProcessor fileProcessor = new FileProcessor();

        try {
            fileProcessor.scan("HW2_ApplicantsInfo.csv");

            ArrayList<Application> applications = fileProcessor.getApplication();
            Collections.sort(applications);
            Collections.reverse(applications);
            for (var application : applications) {
                application.visaApplicationResult();

                System.out.println();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
