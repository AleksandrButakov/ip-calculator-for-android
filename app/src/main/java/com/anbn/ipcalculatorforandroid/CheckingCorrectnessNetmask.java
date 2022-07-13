package com.anbn.ipcalculatorforandroid;

import static com.anbn.ipcalculatorforandroid.MainActivity.*;

public class CheckingCorrectnessNetmask {

    public static boolean checkingCorrectnessNetmask(String sNetmask) {
        String[] sByte = {"", "", "", ""};

        sNetmaskCorrectlyB3 = "";
        sNetmaskCorrectlyB2 = "";
        sNetmaskCorrectlyB1 = "";
        sNetmaskCorrectlyB0 = "";

        int iByte;
        int iByte3, iByte2, iByte1, iByte0;

        int numberByte = 3;
        int length = 0;
        int numberDot = 0;

        char ch;

        length = sNetmask.length();
        for (int i = 0; i < length; i++) {

            // проверим что символ является цифрой или точкой
            ch = sNetmask.charAt(i);
            if (Character.isDigit(ch)) {
                sByte[numberByte] += Character.toString(ch);

                // 255 254 252 248 240 224 192 128 0
                // 2 25 255 254 252 24 248 240 22 224 1 19 192 12 128 0

                // проверим что значения байт IP адреса лежат в требуемых диапазонах
                switch (numberByte) {
                    case (3):
                        if (sByte[3].equals("00")) return false;
                        iByte3 = Integer.valueOf(sByte[3]);
                        if (iByte3 == 2 || iByte3 == 25 || iByte3 == 255 || iByte3 == 254 ||
                                iByte3 == 252 || iByte3 == 24 || iByte3 == 248 ||
                                iByte3 == 240 || iByte3 == 22 || iByte3 == 224 ||
                                iByte3 == 1 || iByte3 == 19 || iByte3 == 192 ||
                                iByte3 == 12 || iByte3 == 128) {
                            //return true;
                        } else {
                            return false;
                        }
                        break;

                    case (2):
                        if (sByte[2].equals("00")) return false;
                        iByte3 = Integer.valueOf(sByte[3]);
                        iByte2 = Integer.valueOf(sByte[2]);
                        if (iByte3 == 255) {
                            if (iByte2 == 2 || iByte2 == 25 || iByte2 == 255 || iByte2 == 254 ||
                                    iByte2 == 252 || iByte2 == 24 || iByte2 == 248 ||
                                    iByte2 == 240 || iByte2 == 22 || iByte2 == 224 ||
                                    iByte2 == 1 || iByte2 == 19 || iByte2 == 192 ||
                                    iByte2 == 12 || iByte2 == 128 || iByte2 == 0) {
                                //return true;
                            } else {
                                return false;
                            }
                        } else {
                            if (iByte2 == 0) {
                            } else {
                                return false;
                            }
                        }
                        break;
                    case (1):
                        if (sByte[1].equals("00")) return false;
                        iByte2 = Integer.valueOf(sByte[2]);
                        iByte1 = Integer.valueOf(sByte[1]);

                        if (iByte2 == 255) {
                            if (iByte1 == 2 || iByte1 == 25 || iByte1 == 255 || iByte1 == 254 ||
                                    iByte1 == 252 || iByte1 == 24 || iByte1 == 248 ||
                                    iByte1 == 240 || iByte1 == 22 || iByte1 == 224 ||
                                    iByte1 == 1 || iByte1 == 19 || iByte1 == 192 ||
                                    iByte1 == 12 || iByte1 == 128 || iByte1 == 0) {
                            } else {
                                return false;
                            }
                        } else {
                            if (iByte1 == 0) {
                            } else {
                                return false;
                            }
                        }
                        break;
                    case (0):
                        if (sByte[0].equals("00")) return false;
                        iByte1 = Integer.valueOf(sByte[1]);
                        iByte0 = Integer.valueOf(sByte[0]);

                        if (iByte1 == 255) {
                            if (iByte0 == 2 || iByte0 == 25 || iByte0 == 255 || iByte0 == 254 ||
                                    iByte0 == 252 || iByte0 == 24 || iByte0 == 248 ||
                                    iByte0 == 240 || iByte0 == 22 || iByte0 == 224 ||
                                    iByte0 == 1 || iByte0 == 19 || iByte0 == 192 ||
                                    iByte0 == 12 || iByte0 == 128 || iByte0 == 0) {
                                //
                                savingNetmaskToVariables(sByte[3], sByte[2], sByte[1], sByte[0]);

                            } else {
                                return false;
                            }
                        } else {
                            if (iByte0 == 0) {
                                //
                                savingNetmaskToVariables(sByte[3], sByte[2], sByte[1], sByte[0]);
                            } else {
                                return false;
                            }
                        }
                        break;
                }

            } else {
                if (String.valueOf(ch).equals(".")) {
                    // проверим что адрес не начинается с точки
                    if (i == 0) {
                        if (sNetmask.charAt(i) == '.') return false;
                    }

                    // проверим что символ не является второй чередующийся точкой
                    if (i > 0) {
                        // i-1
                        if (sNetmask.charAt(i - 1) == ch) return false;
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

    // заполним значениями байтов маски подсети переменные
    public static void savingNetmaskToVariables(String sByte3, String sByte2,
                                                String sByte1, String sByte0) {
        sNetmaskCorrectlyB3 = sByte3;
        sNetmaskCorrectlyB2 = sByte2;
        sNetmaskCorrectlyB1 = sByte1;
        sNetmaskCorrectlyB0 = sByte0;
    }

}
