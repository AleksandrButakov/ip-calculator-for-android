package com.anbn.ipcalculatorforandroid;

public class CheckingCorrectnessCIDR {
    // при изменении поля CIDR корректными значениями заполним поле Netmask
    public static String searchForNetmaskByCIDR(String cIDR) {
        String netmask = "";
        switch (cIDR) {
            case ("0"):
                netmask = "0.0.0.0";
                break;
            case ("1"):
                netmask = "128.0.0.0";
                break;
            case ("2"):
                netmask = "192.0.0.0";
                break;
            case ("3"):
                netmask = "224.0.0.0";
                break;
            case ("4"):
                netmask = "240.0.0.0";
                break;
            case ("5"):
                netmask = "248.0.0.0";
                break;
            case ("6"):
                netmask = "252.0.0.0";
                break;
            case ("7"):
                netmask = "254.0.0.0";
                break;
            case ("8"):
                netmask = "255.0.0.0";
                break;

            case ("9"):
                netmask = "255.128.0.0";
                break;
            case ("10"):
                netmask = "255.192.0.0";
                break;
            case ("11"):
                netmask = "255.224.0.0";
                break;
            case ("12"):
                netmask = "255.240.0.0";
                break;
            case ("13"):
                netmask = "255.248.0.0";
                break;
            case ("14"):
                netmask = "255.252.0.0";
                break;
            case ("15"):
                netmask = "255.254.0.0";
                break;
            case ("16"):
                netmask = "255.255.0.0";
                break;

            case ("17"):
                netmask = "255.255.128.0";
                break;
            case ("18"):
                netmask = "255.255.192.0";
                break;
            case ("19"):
                netmask = "255.255.224.0";
                break;
            case ("20"):
                netmask = "255.255.240.0";
                break;
            case ("21"):
                netmask = "255.255.248.0";
                break;
            case ("22"):
                netmask = "255.255.252.0";
                break;
            case ("23"):
                netmask = "255.255.254.0";
                break;
            case ("24"):
                netmask = "255.255.255.0";
                break;

            case ("25"):
                netmask = "255.255.255.128";
                break;
            case ("26"):
                netmask = "255.255.255.192";
                break;
            case ("27"):
                netmask = "255.255.255.224";
                break;
            case ("28"):
                netmask = "255.255.255.240";
                break;
            case ("29"):
                netmask = "255.255.255.248";
                break;
            case ("30"):
                netmask = "255.255.255.252";
                break;
            case ("31"):
                netmask = "255.255.255.254";
                break;
            case ("32"):
                netmask = "255.255.255.255";
                break;
        }
        return netmask;
    }
}