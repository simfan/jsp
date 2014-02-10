package data;

import java.io.*;
import java.util.*;
import business.*;

public class ProductIO{

    public synchronized static boolean addRecord(Product product, String filename) throws IOException{
        File file = new File(filename);
        PrintWriter out = new PrintWriter(
                          new BufferedWriter(
                          new FileWriter(file, true)));
        out.println(product.getCode() + "|" +
                    product.getTitle() + "|" +
                    product.getArtist() + "|" +
                    product.getCategory() + "|" +
                    product.getDescription() + "|" +
                    product.getPrice());
        out.close();
        return true;
    }

    public synchronized static Product readRecord(String code, String filename) throws IOException{
        File file = new File(filename);
        BufferedReader in = new BufferedReader(
                        new FileReader(file));
        String line = in.readLine();
        while (line != null){
            StringTokenizer t = new StringTokenizer(line, "|");
            String productCode = t.nextToken();
            if (code.equalsIgnoreCase(productCode)){
                String title = t.nextToken();
                String artist = t.nextToken();
                String category = t.nextToken();
                String description = t.nextToken();
                double price = Double.parseDouble(t.nextToken());
                Product product = new Product(code, title, artist, category,
                                              description, price);
                in.close();
                return product;
            }
            line = in.readLine();
        }
        in.close();
        return null;
    }

    public synchronized static boolean updateRecord(Product newProduct, String filename) throws IOException{
        Vector products = readRecords(filename);
        for (int i = 0; i<products.size(); i++){
            Product product = (Product)products.get(i);
            if (product.getCode().equalsIgnoreCase(newProduct.getCode())){
                products.remove(i);
                i = products.size();
            }
        }
        products.add(newProduct);
        overwriteFile(products, filename);
        return true;
    }

    public synchronized static boolean overwriteFile(Vector products, String filename) throws IOException{
        File file = new File(filename);
        PrintWriter out = new PrintWriter(
                          new BufferedWriter(
                          new FileWriter(file)));
        for (int i = 0; i<products.size(); i++){
            Product product = (Product)products.get(i);
            out.println(product.getCode() + "|" +
                   product.getTitle() + "|" +
                   product.getArtist() + "|" +
                   product.getCategory() + "|" +
                   product.getDescription() + "|" +
                   product.getPrice());
        }
        out.close();
        return true;
    }

    public synchronized static boolean deleteRecord(String productCode, String filename) throws IOException{
        Vector products = readRecords(filename);
        for (int i = 0; i<products.size(); i++){
            Product product = (Product)products.get(i);
            if (product.getCode().equalsIgnoreCase(productCode)){
                products.remove(i);
                i = products.size();
            }
        }
        overwriteFile(products, filename);
        return true;

    }
    public synchronized static Vector readRecords(String fileName) throws IOException{
        Vector products = new Vector();
        File file = new File(fileName);
        BufferedReader in = new BufferedReader(
                            new FileReader(file));
        String line = in.readLine();
        while (line != null){
            StringTokenizer t = new StringTokenizer(line, "|");
            String code = t.nextToken();
            String title = t.nextToken();
            String artist = t.nextToken();
            String category = t.nextToken();
            String description = t.nextToken();
            double price = Double.parseDouble(t.nextToken());
            Product product = new Product(code, title, artist, category,
                                          description, price);
            products.add(product);
            line = in.readLine();
        }
        in.close();
        return products;
    }

}