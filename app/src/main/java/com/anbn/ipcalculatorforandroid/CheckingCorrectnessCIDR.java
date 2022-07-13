package com.anbn.ipcalculatorforandroid;

public class CheckingCorrectnessCIDR {
    public static boolean checkingCorrectnessCIDR(String sCIDR) {
        boolean result = false;
        if (sCIDR.equals("")) {
            result = true;
        } else {
            int iCIDR = Integer.parseInt(sCIDR);
            if (iCIDR == 0 || iCIDR >= 32) result = false;
            if (iCIDR > 0 && iCIDR < 32) result = true;
        }
        return result;
    }

}
