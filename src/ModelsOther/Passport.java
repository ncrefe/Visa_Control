package ModelsOther;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Passport {
    private final Date currentDate = new Date();
    private String passportNumber;
    private Date date;

    public Passport(String[] data) {
        this.passportNumber = data[2];
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(data[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Passport(Passport tempPassport) {
        this.passportNumber = tempPassport.getPassportNumber();
        this.date = tempPassport.getDate();
    }

    public boolean validPassportCheck() {

        String last3Characters = passportNumber.substring(passportNumber.length() - 3);
        char[] charsOflast3Characters = last3Characters.toCharArray();

        if (passportNumber.length() != 10) {
            return false;
        } else if (!passportNumber.startsWith("P")) {
            return false;
        }

        for (char character : charsOflast3Characters) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }
        return true;
    }

    public boolean validExpirationDurationCheck() {
        return getExpirationDurationInDays() <= 6 * 30;
    }

    public int getExpirationDurationInDays() {
        long diff = date.getTime() - currentDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public int getExpirationDurationInMonths() {
        return getExpirationDurationInDays() / 30;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Date getDate() {
        Date temporary = new Date(this.date.getTime());
        return temporary;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Passport [date=" + date + ", passportNumber=" + passportNumber + "]";
    }
}
