package sample.FileManager;

import sample.Spend;
import java.io.Serializable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String filePath = "D:\\Expense Management\\src\\sample\\FileManager\\fileManager.txt";

    public static void writeFile(List<Spend> spends) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(spends);
            oos.close();

        } catch (IOException e) {
            System.out.println("khong luu duoc file");
        }
    }


    public static List<Spend> readFile() {
        List<Spend> spends = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            spends = (List<Spend>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return spends;
    }

}

