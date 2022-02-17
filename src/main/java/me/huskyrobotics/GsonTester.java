package me.huskyrobotics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Scanner;

public class GsonTester {
    public static void main(String[] args) {
        CorrectionData data = new CorrectionData("23324.23242");
        System.out.println(convertDataToJson(data));
    }

    private static String convertDataToJson(CorrectionData data) {
        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.create();
        return gson.toJson(data);
    }

    private static class CorrectionData {
        String payload;

        public CorrectionData() {
            this.payload = "";
        }

        public CorrectionData(String payload) {
            this.payload = payload;
        }
    }
}
