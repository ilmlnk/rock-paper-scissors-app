package service;

import java.util.Locale;
import java.util.Scanner;

public class LanguageDefinition {
    public static Locale chooseLanguage(int language) {
        switch (language) {
            case 1 -> { return new Locale("uk", "UA"); }
            case 2 -> { return new Locale("eng", "US"); }
            case 3 -> { return new Locale("de", "DE"); }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("This language is not available now. Try again. Choose language: ");
        int lang = Integer.parseInt(scanner.nextLine());
        return chooseLanguage(lang);
    }
}
