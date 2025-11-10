import java.util.*;

public class CRC {
    static String xor(String a, String b) {
        String r = "";
        for (int i = 1; i < b.length(); i++)
            r += (a.charAt(i) == b.charAt(i)) ? '0' : '1';
        return r;
    }

    static String zeros(int n) {
        String z = "";
        for (int i = 0; i < n; i++) z += "0";
        return z;
    }

    static String mod2div(String divd, String divs) {
        int pick = divs.length();
        String tmp = divd.substring(0, pick);
        while (pick < divd.length()) {
            tmp = (tmp.charAt(0) == '1' ? xor(divs, tmp) : xor(zeros(pick), tmp)) + divd.charAt(pick++);
        }
        return (tmp.charAt(0) == '1') ? xor(divs, tmp) : xor(zeros(pick), tmp);
    }

    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Data: "); String data = s.next();
        System.out.print("Enter Generator: "); String key = s.next();

        String code = data + mod2div(data + zeros(key.length() - 1), key);
        System.out.println("Transmitted Codeword: " + code);

        System.out.print("Enter Received Codeword: "); String rec = s.next();
        String rem = mod2div(rec, key);
        System.out.println(Integer.parseInt(rem) == 0 ? "No error detected." : "Error detected in received codeword!");
    }
}
