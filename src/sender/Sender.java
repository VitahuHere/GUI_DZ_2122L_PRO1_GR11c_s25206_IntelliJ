package sender;

import utils.Evaluators;

import java.time.LocalDate;

public class Sender {
    public final String name;
    public final String surname;
    public final String PESEL;
    public final String address;

    private int strikes;

    public Sender(String name, String surname, String PESEL, String address) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.address = address;
        this.strikes = 0;
    }

    public Sender(){
        this.name = Evaluators.getStringFromInput("Name");
        this.surname = Evaluators.getStringFromInput("Surname");
        this.PESEL = Evaluators.getPeselFromInput("PESEL");
        this.address = Evaluators.getStringFromInput("Address");
        this.strikes = 0;
    }

    public LocalDate getBirthday() {
        int year, month, day;
        year = Integer.parseInt(PESEL.substring(0, 2));
        month = Integer.parseInt(PESEL.substring(2, 4));
        day = Integer.parseInt(PESEL.substring(4, 6));
        if(0 < month && month < 13) {
            year += 1900;
        }
        else if(month > 20) {
            year += 2000;
            month -= 20;
        }
        return LocalDate.of(year, month, day);
    }

    public static void main(String[] args) {
        Sender sender = new Sender();
        System.out.println(sender.getBirthday());
    }
}
