package ua.com.alevel;

import ua.com.alevel.service.DateService;
import ua.com.alevel.service.GraphService;
import ua.com.alevel.service.UniqueNameService;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        UniqueNameService taskService = new UniqueNameService();
        DateService dateService = new DateService();
        GraphService graphService = new GraphService();
        String uniqueName = taskService.getFirstUniqueName("unique-name.txt");
        String correctDates = dateService.getDatesWithCorrectFormat("date.txt");
        String result = graphService.getShortestPathForCities("cities.txt");
        Main main = new Main();
        main.writeResultFile("unique-name.txt", uniqueName);
        main.writeResultFile("date.txt", correctDates);
        main.writeResultFile("cities.txt", result);
    }

    public void writeResultFile(String fileName, String result) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("module_2/src/main/resources/output/" + fileName), StandardCharsets.UTF_8))) {
            writer.write(result);
        } catch (FileNotFoundException e) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/output/" + fileName), StandardCharsets.UTF_8))) {
                writer.write(result);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
