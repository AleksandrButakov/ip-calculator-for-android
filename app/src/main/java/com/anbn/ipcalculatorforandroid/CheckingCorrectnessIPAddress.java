package com.anbn.ipcalculatorforandroid;

import static com.anbn.ipcalculatorforandroid.MainActivity.*;

public class CheckingCorrectnessIPAddress {
    public static boolean checkingCorrectnessIPAddress(String sIP) {
        String[] sByte = {"", "", "", ""};

        sIPCorrectlyB3 = "";
        sIPCorrectlyB2 = "";
        sIPCorrectlyB1 = "";
        sIPCorrectlyB0 = "";

        int iByte;

        int numberByte = 3;
        int length = 0;
        int numberDot = 0;

        char ch;

        length = sIP.length();
        for (int i = 0; i < length; i++) {

            // проверим что символ является цифрой или точкой
            ch = sIP.charAt(i);
            if (Character.isDigit(ch)) {
                sByte[numberByte] += Character.toString(ch);


                // проверим что символ не является вторым подряд нулем
                if (i >= 2) {
                    if (sIP.charAt(i - 1) == '0' &&
                            sIP.charAt(i - 2) == '.') return false;
                }

                // проверим что значения байт IP адреса лежат в требуемых диапазонах
                iByte = Integer.valueOf(sByte[numberByte]);
                switch (numberByte) {
                    case (3):
                        if (iByte < 1 || iByte > 255) return false;

                        break;
                    case (2):
                    case (1):
                        if (iByte < 0 || iByte > 255) return false;
                        break;
                    case (0):
                        if (iByte < 1 || iByte > 254) return false;
                        sIPCorrectlyB3 = sByte[3];
                        sIPCorrectlyB2 = sByte[2];
                        sIPCorrectlyB1 = sByte[1];
                        sIPCorrectlyB0 = sByte[0];
                        break;
                }

            } else {
                if (String.valueOf(ch).equals(".")) {
                    // проверим что адрес не начинается с точки
                    if (i == 0) {
                        if (sIP.charAt(i) == '.') return false;
                    }

                    // проверим что символ не является второй чередующийся точкой
                    if (i > 0) {
                        if (sIP.charAt(i - 1) == ch) return false;
                    }

                    numberByte--;
                    numberDot++;
                    if (numberDot > 3) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}