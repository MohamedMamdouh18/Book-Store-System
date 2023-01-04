package db.bookstore.Utils;

public class Helpers {

    public static boolean isInt(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String string) {
        float floatValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            floatValue = Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
