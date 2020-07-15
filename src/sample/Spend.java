package sample;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ObservableArray;
import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Spend implements Serializable {
        int id;
        String name;
        String content;
        int money;
        String note;
        LocalDate date;
        boolean isIncome;


        public Spend(){
        }

        public LocalDate getDate() {
            return date;
        }



        public Spend(int id,String name, String content, int money, String note, LocalDate date) {
            this.id = id;
            this.name = name;
            this.content = content;
            this.money = money;
            this.note = note;
            this.date=date;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }



    }


