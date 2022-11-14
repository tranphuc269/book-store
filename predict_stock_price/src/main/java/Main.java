import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        callApi();
        System.out.println("Hello world");
    }

    private static void callApi() throws IOException {
        String url = "http://192.168.2.105:3004/api/StockExchange/price-history?stock=AAA";
        URL urlNet = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlNet.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String userInputLine;
        StringBuilder response = new StringBuilder();
        while ((userInputLine = reader.readLine()) != null) {
            response.append(userInputLine);
        }
        StockObjectList stockObjectList = new Gson().fromJson(response.toString(), StockObjectList.class);
        CSVStock[] stocks = new CSVStock[stockObjectList.data.size()];
        for(int i = 0; i < stockObjectList.data.size(); i++){
            stocks[i] = new CSVStock(stockObjectList.data.get(i).getNGD().split("T")[0], stockObjectList.data.get(i).getGDC());
        }
        generateCSV(new File("example_wp_log_peyton_manning.csv"), stocks);
        reader.close();
    }

    private static void CSVWrite() {

    }

    private static String produceCsvData(Object[] data) throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException {
        if (data.length == 0) {
            return "";
        }

        Class<?> classType = data[0].getClass();
        StringBuilder builder = new StringBuilder();

        Method[] methods = classType.getDeclaredMethods();

        for (Method m : methods) {
            if (m.getParameterTypes().length == 0) {
                if (m.getName().startsWith("get")) {
                    builder.append(m.getName().substring(3)).append(',');
                } else if (m.getName().startsWith("is")) {
                    builder.append(m.getName().substring(2)).append(',');
                }

            }

        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append('\n');
        for (Object d : data) {
            for (Method m : methods) {
                if (m.getParameterTypes().length == 0) {
                    if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                        System.out.println(m.invoke(d).toString());
                        builder.append(m.invoke(d).toString()).append(',');
                    }
                }
            }
            builder.append('\n');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static boolean generateCSV(File csvFileName, Object[] data) {
        try (FileWriter fw = new FileWriter(csvFileName)) {
            if (!csvFileName.exists())
                csvFileName.createNewFile();
            fw.write(produceCsvData(data));
            fw.flush();
        } catch (Exception e) {
            System.out.println("Error while generating csv from data. Error message : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
