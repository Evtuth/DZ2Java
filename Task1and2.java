// Дана json строка { { "фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}} 
// Задача написать метод(ы), который распарсить строку и выдаст ответ вида: 
// Студент Иванов получил 5 по предмету Математика. 
// Студент Петрова получил 4 по предмету Информатика. 
// Студент Краснов получил 5 по предмету Физика. 
// Используйте StringBuilder для подготовки ответа

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
 
public class Task1and2 {
 
    public static void main(String[] args) {
 
        String json = "[{\"фамилия\":\" Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," + 
                        "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," + 
                        "{\" фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        
        createInfoFile(json);
    }

    static void createInfoFile(String json){

        String filePath = "StudentsInfo.txt";
        String s1 = parsing(json);
        writeToFile(s1, filePath);
    }

    static String parsing(String json){
        StringBuilder stringbuilder = new StringBuilder();
        json = json.substring(1, json.length() - 1);
        String [] objects = json.split("},");
        
        for (String object : objects) {
            object = object.replace("{", "").replace("}", "");
            String [] info = object.split(",");

            String surn = info[0].split(":")[1].replace("\"", "");
            String rate = info[1].split(":")[1].replace("\"", "");
            String sub = info[2].split(":")[1].replace("\"", "");

            stringbuilder = stringbuilder.append("Студент ").append(surn).append(" получил ").append(rate).append(" по предмету ").append(sub).append(".\n");
        }

        System.out.println(stringbuilder);
        return stringbuilder.toString();
    }

    static void writeToFile(String s, String filePath){
        try {
            Logger logger = Logger.getAnonymousLogger();
            FileHandler fileHandler = new FileHandler("log.txt", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            try (FileWriter writer = new FileWriter(filePath, false)) {
                writer.write(s);
                writer.flush();
                logger.log(Level.INFO, "Запись успешно создана");
            } catch (Exception e) {
                e.printStackTrace();
                logger.log(Level.WARNING, e.getMessage());
            }
            fileHandler.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

