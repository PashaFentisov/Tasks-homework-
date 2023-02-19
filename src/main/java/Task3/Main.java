package Task3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<String> listOfCode = new ArrayList<>();
        listOfCode.add("RMuiRdf010160141151156164");
        listOfCode.add("lims8r3860lims1631411561441");
        listOfCode.add("GZQRyr6870GZQR+0041431501451451631455A");
        listOfCode.add("qkMfPjrd0561411551551651561511641511571567");
        listOfCode.add("EOcTkerf389-0201511431450551431621451411550");
        List<Type> list = new ArrayList<>();
        for (String crypt : listOfCode) {
            list.add(decryption(crypt));
        }
        writeToFile(list);
    }

    public static Type decryption(String crypt) {
        Type t = new Type();
        t.setType(crypt);
        t.setDriverCode(crypt.substring(0, 4));
        crypt = crypt.substring(4);
        for (int i = 0; i < 2; i++) {
            if (crypt.charAt(0) != 'r' && crypt.charAt(0) != 'R') {
                crypt = crypt.substring(1);
            }
        }
        crypt = letterCode(crypt, t);
        crypt = findTemp(crypt, t);
        t.setName(octalToString(crypt));
        return t;
    }

    private static String findTemp(String crypt, Type t) {
        if (crypt.charAt(0) == '+' || crypt.charAt(0) == '-') {
            t.setTemperature(Integer.parseInt(crypt.substring(0, 4)));
            crypt = crypt.substring(4);
        } else {
            t.setTemperature(Integer.MIN_VALUE);
        }
        return crypt;
    }


    private static String letterCode(String crypt, Type t) {
        String temp = "";
        temp += crypt.charAt(0);
        crypt = crypt.substring(1);
        if (crypt.charAt(0) == 'd') {
            t.setDanger(true);
            temp += crypt.charAt(0);
            crypt = crypt.substring(1);
        }
        if (crypt.charAt(0) == 'f') {
            t.setFragile(true);
            temp += crypt.charAt(0);
            crypt = crypt.substring(1);
        }
        if (t.isFragile() || t.isDanger()) {
            temp += crypt.substring(0, 3);
            crypt = crypt.substring(3);
        } else {
            temp += crypt.substring(0, 4);
            crypt = crypt.substring(4);
        }
        if (crypt.substring(0, t.getDriverCode().length()).equals(t.getDriverCode())) {
            crypt = crypt.substring(t.getDriverCode().length());
        }
        t.setLetterCode(temp);
        return crypt;
    }


    private static String octalToString(String crypt) {
        while (true) {
            if (crypt.length() % 3 != 0) {
                crypt = crypt.substring(0, crypt.length() - 1);
            } else {
                break;
            }
        }
        String result = "";

        Pattern p = Pattern.compile("\\d{3}");
        Matcher m = p.matcher(crypt);
        while (m.find()) {
            int decimal = Integer.valueOf(m.group(), 8);
            char tempChar = (char) decimal;
            result += tempChar;
        }
        return result;
    }

    public static void writeToFile(List<Type> list) {
        File file = new File("src/main/resources/crypt.csv");
        String caption = "шифр, код водителя, код путевого листа, опасный, хрупкий, температура, наименование";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            try {
                if (Files.readAllLines(file.toPath()).isEmpty()) {
                    writer.write(caption);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
