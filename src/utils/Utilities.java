package utils;

public class Utilities {

    public static String truncateString(String stringToTruncate, int length){
        if (stringToTruncate == null) {
            return "";
        }
        if (stringToTruncate.length() <= length) {
            return stringToTruncate;
        }
        else{
            return stringToTruncate.substring(0, length);
        }
    }

    public static boolean validateStringLength(String strToCheck, int maxLength){
        if (strToCheck == null) {
            return false;
        }
        return strToCheck.length() <= maxLength;
    }

    public static boolean validRange(double numberToCheck, double min, double max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

    public static boolean validIntRange(int numberToCheck, int min, int max) {
        return numberToCheck >= min && numberToCheck <= max;
    }

    public static boolean validChar(char charToCheck, char... allowedChars) {
        for (char allowedChar : allowedChars) {
            if (charToCheck == allowedChar) {
                return true;
            }
        }
        return false;
    }

}
