import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
class Result {
    public static String nimGame(List<Integer> pile) {
        int size = pile.size();
        String[] stringOfBits = new String[size];
        List<Integer> len = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stringOfBits[i] = Integer.toBinaryString(pile.get(i));
            len.add(stringOfBits[i].length());
        }
        String[] resultString = addZerosToTheLeftTillMaxLengthOfStringOfBits(stringOfBits, len);
        if (isOddConfiguration(resultString)) return "First";
        return "Second";
    }

    private static String[] addZerosToTheLeftTillMaxLengthOfStringOfBits(String[] stringOfBits, List<Integer> len) {
        String[] resultStrings = new String[stringOfBits.length];
        int maxLength = len.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
        for (int str = 0; str < stringOfBits.length; str++) {
            if (stringOfBits[str].length() < maxLength) {
                String sWithZeros = "";
                for (int i = 0; i < maxLength - stringOfBits[str].length(); i++)
                    sWithZeros += "0";
                resultStrings[str] = sWithZeros + stringOfBits[str];
            }
            else resultStrings[str] = stringOfBits[str];
        }
        return resultStrings;
    }

    private static boolean isOddConfiguration(String[] stringOfBits) {
        int rowsLength = stringOfBits[0].length();
        int[] rows = new int[rowsLength];
        for (int i = 0; i < stringOfBits.length; i++) {
            for (int j = 0; j < rowsLength; j++) {
                if (stringOfBits[i].charAt(j) == '1') rows[j]++;
            }
        }
        for (int i = 0; i < rowsLength; i++) if (rows[i] % 2 == 1) return true;
        return false;
    }
}