package sender;

import main.App;
import utils.ConsoleColors;
import utils.Evaluators;

import java.time.LocalDate;

public class Sender {
    public final String name;
    public final String surname;
    public final String PESEL;
    public final String address;

    public int strikes;

    public Sender(String name, String surname, String PESEL, String address) {
        this.name = name;
        this.surname = surname;
        this.PESEL = Evaluators.validatepesel(PESEL) ? PESEL : "-1";
        this.address = address;
        this.strikes = 0;
        if(isUniquePESEL() && !this.PESEL.equals("-1")){
            App.senders.add(this);
        }
        else{
            ConsoleColors.printRed("Sender already exists with this PESEL or PESEL is invalid.");
        }
    }

    public Sender(){
        this.name = Evaluators.getStringFromInput("Name");
        this.surname = Evaluators.getStringFromInput("Surname");
        this.PESEL = Evaluators.getPeselFromInput("PESEL");
        this.address = Evaluators.getStringFromInput("Address");
        this.strikes = 0;
        if(isUniquePESEL()){
            ConsoleColors.printGreen("Sender successfully created");
            App.senders.add(this);
        }
        else{
            ConsoleColors.printRed("Sender already exists with this PESEL.");
        }
    }

    private boolean isUniquePESEL(){
        for(Sender s : App.senders){
            if(s.PESEL.equals(this.PESEL)){
                return false;
            }
        }
        return true;
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

    @Override
    public String toString() {
        return "Sender " +
                "name: '" + name + '\'' +
                ", surname: '" + surname + '\'' +
                ", PESEL: " + PESEL +
                ", address: '" + address + '\'' +
                ", birthday: " + getBirthday() +
                ", strikes: " + strikes;
    }
}
