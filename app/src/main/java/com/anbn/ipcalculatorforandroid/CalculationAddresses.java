package com.anbn.ipcalculatorforandroid;

import java.util.Arrays;

public class CalculationAddresses {

    // переменные назначины и именованы в соответствии с расположением полей в layout
    // String ipAddress;
    // String cIDR;
    // String networkMask;

    String decNetwork;
    String decBroadcast;
    String decNetMask;
    String decFirstAddress;
    String decLastAddress;
    String decNumberHosts;

    String sNetworkBin;
    //    String sBroadcastBin;
    String sNetmaskBin;
//    String sFirstAddressBin;
//    String sLastAddressBin;

    boolean[] binIPAddressArray = new boolean[32];
    boolean[] binNetmaskArray = new boolean[32];
    boolean[] binNetworkArray = new boolean[32];
    boolean[] binBroadcast = new boolean[32];
    boolean[] binNetmask = new boolean[32];
    boolean[] binFirstAddress = new boolean[32];
    boolean[] binLastAddress = new boolean[32];

    // вспомогательная переменная для перевода данных Dec to Bin
    public static boolean[] bitOrder = new boolean[8];

    // переменные для хранения байтов IP адреса
    String ipAddressB3;
    String ipAddressB2;
    String ipAddressB1;
    String ipAddressB0;

    // переменная для хранения количества бит маски подсети
    String cidr;

    // переменные для хранения байтов маски подсети
    String netmaskB3;
    String netmaskB2;
    String netmaskB1;
    String netmaskB0;

    public static void calculationNumberHosts(CalculationAddresses tab1) {
        switch (tab1.cidr) {
            case ("31"):
                tab1.decNumberHosts = "0";
                break;
            case ("30"):
                tab1.decNumberHosts = "2";
                break;
            case ("29"):
                tab1.decNumberHosts = "6";
                break;
            case ("28"):
                tab1.decNumberHosts = "14";
                break;
            case ("27"):
                tab1.decNumberHosts = "30";
                break;
            case ("26"):
                tab1.decNumberHosts = "62";
                break;
            case ("25"):
                tab1.decNumberHosts = "126";
                break;

            case ("24"):
                tab1.decNumberHosts = "254";
                break;
            case ("23"):
                tab1.decNumberHosts = "510";
                break;
            case ("22"):
                tab1.decNumberHosts = "1022";
                break;
            case ("21"):
                tab1.decNumberHosts = "2046";
                break;
            case ("20"):
                tab1.decNumberHosts = "4094";
                break;
            case ("19"):
                tab1.decNumberHosts = "8190";
                break;
            case ("18"):
                tab1.decNumberHosts = "16382";
                break;
            case ("17"):
                tab1.decNumberHosts = "32766";
                break;

            case ("16"):
                tab1.decNumberHosts = "65534";
                break;
            case ("15"):
                tab1.decNumberHosts = "131070";
                break;
            case ("14"):
                tab1.decNumberHosts = "262142";
                break;
            case ("13"):
                tab1.decNumberHosts = "524286";
                break;
            case ("12"):
                tab1.decNumberHosts = "1048574";
                break;
            case ("11"):
                tab1.decNumberHosts = "2097150";
                break;
            case ("10"):
                tab1.decNumberHosts = "4194302";
                break;
            case ("9"):
                tab1.decNumberHosts = "8388606";
                break;

            case ("8"):
                tab1.decNumberHosts = "16777214";
                break;
            case ("7"):
                tab1.decNumberHosts = "33554430";
                break;
            case ("6"):
                tab1.decNumberHosts = "67108862";
                break;
            case ("5"):
                tab1.decNumberHosts = "134217726";
                break;
            case ("4"):
                tab1.decNumberHosts = "268435454";
                break;
            case ("3"):
                tab1.decNumberHosts = "536870910";
                break;
            case ("2"):
                tab1.decNumberHosts = "1073741822";
                break;
            case ("1"):
                tab1.decNumberHosts = "2147483646";
                break;
        }
    }

    // заполним массив boolean[] binIPAddressArray
    public static void fillingTheBinIPAddressArray(CalculationAddresses tab) {
        decToBin(Integer.parseInt(tab.ipAddressB3));
        for (int i = 31; i >= 24; i--) {
            tab.binIPAddressArray[i] = bitOrder[i - 24];
        }
        decToBin(Integer.parseInt(tab.ipAddressB2));
        for (int i = 23; i >= 16; i--) {
            tab.binIPAddressArray[i] = bitOrder[i - 16];
        }
        decToBin(Integer.parseInt(tab.ipAddressB1));
        for (int i = 15; i >= 8; i--) {
            tab.binIPAddressArray[i] = bitOrder[i - 8];
        }
        decToBin(Integer.parseInt(tab.ipAddressB0));
        for (int i = 7; i >= 0; i--) {
            tab.binIPAddressArray[i] = bitOrder[i];
        }
    }

    // заполним массив boolean[] binNetmaskArray
    public static void fillingTheBinNetmaskArray(CalculationAddresses tab) {
        tab.sNetmaskBin = "";
        for (int i = 31; i >= 0; i--) {
            if (i >= 31 - Integer.parseInt(tab.cidr)) {
                tab.binNetmaskArray[i] = true;
                tab.sNetmaskBin += "1";
            } else {
                tab.binNetmaskArray[i] = false;
                tab.sNetmaskBin += "0";
            }
            if (i == 24 || i == 16 || i == 8) tab.sNetmaskBin += " ";
        }
    }

    // рассчитываем значение binNetwork[32] and decNetwork
    public static void fillingTheBinNetworkArray(CalculationAddresses tab) {
        tab.sNetworkBin = "";
        for (int i = 31; i >= 0; i--) {
            if (tab.binNetmaskArray[i] == true) {
                tab.binNetworkArray[i] = tab.binIPAddressArray[i];
                if (tab.binIPAddressArray[i] == true) {
                    tab.sNetworkBin += "1";
                } else {
                    tab.sNetworkBin += "0";
                }
            } else {
                tab.binNetworkArray[i] = false;
                tab.sNetworkBin += "0";
            }
            if (i == 24 || i == 16 || i == 8) tab.sNetworkBin += " ";
        }

    }


    /*
    boolean[] binIPAddressArray = new boolean[32];
    boolean[] binNetmaskArray = new boolean[32];
    boolean[] binNetworkArray = new boolean[32];
    boolean[] binBroadcast = new boolean[32];
    boolean[] binNetmask = new boolean[32];
    boolean[] binFirstAddress = new boolean[32];
    boolean[] binLastAddress = new boolean[32];
     */

    // получим двоичный массив BitOrder IP адреса
    public static void decToBin(int iByte0) {
        //
        //boolean[] BitOrder = new boolean[8];
        int[] Degree = new int[8];
        // заполним массив промежуточными данными для дальнейших вычислений
        for (int i = 7; i >= 0; i--) {
            Degree[i] = (int) Math.pow(2, i);
        }

        float Residue;
        for (int i = 7; i >= 0; i--) {
            Residue = (float) (iByte0 / Degree[i]);
            if (Residue >= 1) {
                bitOrder[i] = true;
                iByte0 -= Degree[i];
            } else {
                bitOrder[i] = false;
            }
        }
    }


    // полученный двумерный массив переведем в десятичный вид
    public static void binToDec(boolean[] Arr) {
        int AuxiliaryDecByte0 = 0;
        //boolean[] BitOrder = new boolean[8];
        int[] Degree = new int[8];
        // заполним массив промежуточными данными для дальнейших вычислений
        for (int i = 7; i >= 0; i--) {
            Degree[i] = (int) Math.pow(2, i);
        }

        for (int i = 7; i >= 0; i--) {
            if (Arr[i] == true) {
                AuxiliaryDecByte0 += Degree[i];
            }
        }
    }


}
