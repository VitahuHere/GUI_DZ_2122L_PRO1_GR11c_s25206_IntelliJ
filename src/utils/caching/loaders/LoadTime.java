package utils.caching.loaders;

import main.TimeOperations;
import utils.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class LoadTime {
    public static void loadTime(){
        try(FileReader fileReader = new FileReader(Constants.CURRENT_TIME)){
            int c;
            StringBuilder sb = new StringBuilder();
            while((c = fileReader.read()) != -1){
                sb.append((char) c);
            }
            String[] ymd = sb.toString().split("-");
            TimeOperations.currentDate = LocalDate.of(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]));
        } catch (IOException ignore){}
    }
}
