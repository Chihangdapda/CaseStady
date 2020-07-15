package sample;

import sample.FileManager.FileManager;

import java.util.ArrayList;
import java.util.List;

public class SpendManager {
    private static List<Spend> spendList = new ArrayList<>();

    public static Spend addSpend(Spend spends){
        spendList.add(spends);
        return spends;
    }

    public static void deleteSpend(Integer index){
        spendList.remove(index);
    }

    public static Spend edit(Integer index, Spend spend){
        spendList.set(index, spend);
        return spend;
    }

    public static List<Spend> init(){
        spendList = FileManager.readFile();
       return spendList;
    }

    public static List<Spend> getSpendList() {
        return spendList;
    }

    public static void setSpendList(List<Spend> spendList) {
        SpendManager.spendList = spendList;
    }

}
