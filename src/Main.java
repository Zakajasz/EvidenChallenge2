import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            String fileName = "harmonogram.txt";
            int workingDaysInMonth = 0;
            double maxWorkingHoursPerDay = 8.0;
            double totalHours = 0;
            double startTime = 8; // Początkowa godzina pracy
            double previousTime; // Czas zakończenia poprzedniego dnia

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            double sundayWorkHours = 0;
            double overtimeHours = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int day = Integer.parseInt(parts[0]);
                String dayOfWeek = parts[1];
                double hours = Integer.parseInt(parts[2]);
                previousTime = startTime + hours;

                //Przyjmuje że każdego dnia pracuje od 8 do 16 standardowo
                if (previousTime > 21) {
                    System.out.println("Przerwa między " + day + " a " + (day - 1) + " wynosi mniej niż 11 godzin.");

                }

                totalHours += hours;

                if ((!dayOfWeek.equals("sobota")) && (!dayOfWeek.equals("niedziela"))) {
                    workingDaysInMonth++;
                }

                if (dayOfWeek.equals("niedziela")) {
                    sundayWorkHours += hours;
                } else {
                    if (hours > maxWorkingHoursPerDay) {
                        overtimeHours += hours - maxWorkingHoursPerDay;
                    }
                }
            }

            reader.close();
            overtimeHours += sundayWorkHours;
            double maxTotalWorkingHours = maxWorkingHoursPerDay * workingDaysInMonth;

            if (totalHours > maxTotalWorkingHours) {
                System.out.println("Suma przepracowanych godzin w miesiącu przekracza ilość (8h * dni pracujące) " + maxTotalWorkingHours);
                System.out.println("Dni pracujące= " + workingDaysInMonth);
            }
            if (sundayWorkHours > 0) {
                System.out.println("Została zaplanowana praca na niedziele.");
            }
            System.out.println("Łącznie zostanie przepracowanych " + overtimeHours + " nadgodzin");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
