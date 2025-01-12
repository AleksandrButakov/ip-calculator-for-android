package com.anbn.ipcalculatorforandroid;

public class CalculationAddresses {
    // вспомогательная переменная для перевода данных Dec to Bin
    public static boolean[] bitOrder = new boolean[8];

    // переменная для хранения количества бит маски подсети
    String cidr = "";

    // переменные для хранения байтов маски подсети
    String netmaskB3 = "";
    String netmaskB2 = "";
    String netmaskB1 = "";
    String netmaskB0 = "";

    public static void calculationNumberHosts() {
        switch (Data.getStrCidr()) {  // tab1.cidr) {
            case ("31"):
                Data.setDecNumberHosts("0"); // tab1.decNumberHosts = "0";
                break;
            case ("30"):
                Data.setDecNumberHosts("2"); // tab1.decNumberHosts = "2";
                break;
            case ("29"):
                Data.setDecNumberHosts("6"); // tab1.decNumberHosts = "6";
                break;
            case ("28"):
                Data.setDecNumberHosts("14"); // tab1.decNumberHosts = "14";
                break;
            case ("27"):
                Data.setDecNumberHosts("30"); // tab1.decNumberHosts = "30";
                break;
            case ("26"):
                Data.setDecNumberHosts("62"); // tab1.decNumberHosts = "62";
                break;
            case ("25"):
                Data.setDecNumberHosts("126"); // tab1.decNumberHosts = "126";
                break;

            case ("24"):
                Data.setDecNumberHosts("254"); // tab1.decNumberHosts = "254";
                break;
            case ("23"):
                Data.setDecNumberHosts("510"); // tab1.decNumberHosts = "510";
                break;
            case ("22"):
                Data.setDecNumberHosts("1022"); // tab1.decNumberHosts = "1022";
                break;
            case ("21"):
                Data.setDecNumberHosts("2046"); // tab1.decNumberHosts = "2046";
                break;
            case ("20"):
                Data.setDecNumberHosts("4094"); // tab1.decNumberHosts = "4094";
                break;
            case ("19"):
                Data.setDecNumberHosts("8190"); // tab1.decNumberHosts = "8190";
                break;
            case ("18"):
                Data.setDecNumberHosts("16382"); // tab1.decNumberHosts = "16382";
                break;
            case ("17"):
                Data.setDecNumberHosts("32766"); // tab1.decNumberHosts = "32766";
                break;

            case ("16"):
                Data.setDecNumberHosts("65534"); // tab1.decNumberHosts = "65534";
                break;
            case ("15"):
                Data.setDecNumberHosts("131070"); // tab1.decNumberHosts = "131070";
                break;
            case ("14"):
                Data.setDecNumberHosts("262142"); // tab1.decNumberHosts = "262142";
                break;
            case ("13"):
                Data.setDecNumberHosts("524286"); // tab1.decNumberHosts = "524286";
                break;
            case ("12"):
                Data.setDecNumberHosts("1048574"); // tab1.decNumberHosts = "1048574";
                break;
            case ("11"):
                Data.setDecNumberHosts("2097150"); // tab1.decNumberHosts = "2097150";
                break;
            case ("10"):
                Data.setDecNumberHosts("4194302"); // tab1.decNumberHosts = "4194302";
                break;
            case ("9"):
                Data.setDecNumberHosts("8388606"); // tab1.decNumberHosts = "8388606";
                break;

            case ("8"):
                Data.setDecNumberHosts("16777214"); // tab1.decNumberHosts = "16777214";
                break;
            case ("7"):
                Data.setDecNumberHosts("33554430"); // tab1.decNumberHosts = "33554430";
                break;
            case ("6"):
                Data.setDecNumberHosts("67108862"); // tab1.decNumberHosts = "67108862";
                break;
            case ("5"):
                Data.setDecNumberHosts("134217726"); // tab1.decNumberHosts = "134217726";
                break;
            case ("4"):
                Data.setDecNumberHosts("268435454"); // tab1.decNumberHosts = "268435454";
                break;
            case ("3"):
                Data.setDecNumberHosts("536870910"); // tab1.decNumberHosts = "536870910";
                break;
            case ("2"):
                Data.setDecNumberHosts("1073741822"); // tab1.decNumberHosts = "1073741822";
                break;
            case ("1"):
                Data.setDecNumberHosts("2147483646"); // tab1.decNumberHosts = "2147483646";
                break;
        }
    }

    // заполним массив boolean[] binIPAddressArray
    public static void fillingTheBinIPAddressArray() {
        Data.setStrIpAddressBin("");
        decToBin(Integer.parseInt(Data.getIpByte3()));
        for (int i = 31; i >= 24; i--) {
            Data.setBinIPAddressArray(i, bitOrder[i - 24]); // tab.binIPAddressArray[i] = bitOrder[i - 24];
            Data.setStrIpAddressBin(Data.getStrIpAddressBin() + fillingValuesIPAddressBits(bitOrder[i - 24])); // tab.sIPAddressBin += tab.fillingValuesIPAddressBits(bitOrder[i - 24]);
        }
        Data.setStrIpAddressBin(Data.getStrIpAddressBin() + " "); //        tab.sIPAddressBin += " ";

        decToBin(Integer.parseInt(Data.getIpByte2()));
        for (int i = 23; i >= 16; i--) {
            Data.setBinIPAddressArray(i, bitOrder[i - 16]);
            Data.setStrIpAddressBin(Data.getStrIpAddressBin() + fillingValuesIPAddressBits(bitOrder[i - 16]));

//            tab.binIPAddressArray[i] = bitOrder[i - 16];
//            tab.sIPAddressBin += tab.fillingValuesIPAddressBits(bitOrder[i - 16]);
        }
        Data.setStrIpAddressBin(Data.getStrIpAddressBin() + " ");
//        tab.sIPAddressBin += " ";

        decToBin(Integer.parseInt(Data.getIpByte1()));
        for (int i = 15; i >= 8; i--) {
            Data.setBinIPAddressArray(i, bitOrder[i - 8]);
            Data.setStrIpAddressBin(Data.getStrIpAddressBin() + fillingValuesIPAddressBits(bitOrder[i - 8]));

//            tab.binIPAddressArray[i] = bitOrder[i - 8];
//            tab.sIPAddressBin += tab.fillingValuesIPAddressBits(bitOrder[i - 8]);
        }
        Data.setStrIpAddressBin(Data.getStrIpAddressBin() + " ");
//        tab.sIPAddressBin += " ";

        decToBin(Integer.parseInt(Data.getIpByte0()));
        for (int i = 7; i >= 0; i--) {
            Data.setBinIPAddressArray(i, bitOrder[i]);
            Data.setStrIpAddressBin(Data.getStrIpAddressBin() + fillingValuesIPAddressBits(bitOrder[i]));
//            tab.binIPAddressArray[i] = bitOrder[i];
//            tab.sIPAddressBin += tab.fillingValuesIPAddressBits(bitOrder[i]);
        }
    }

    public static String fillingValuesIPAddressBits(boolean bit) {
        if (bit) {
            return "1";
        } else {
            return "0";
        }
    }

    // заполним массив boolean[] binNetmaskArray
    public static void fillingTheBinNetmaskArray() {
//        tab.sNetmaskBin = "";
        Data.setFullMask("");
        for (int i = 31; i >= 0; i--) {
            if (i >= 32 - Integer.parseInt(Data.getStrCidr())) { // tab.cidr)) {
                Data.setBinNetmaskArray(i, true);
//                tab.binNetmaskArray[i] = true;
                Data.setStrNetmaskBin(Data.getStrNetmaskBin() + "1");
//                tab.sNetmaskBin += "1";
            } else {
                Data.setBinNetmaskArray(i, false);
//                tab.binNetmaskArray[i] = false;
                Data.setStrNetmaskBin(Data.getStrNetmaskBin() + "0");
//                tab.sNetmaskBin += "0";
            }
            if (i == 24 || i == 16 || i == 8)
                Data.setStrNetmaskBin(Data.getStrNetmaskBin() + " "); // tab.sNetmaskBin += " ";
        }
    }

    // рассчитываем значение binNetwork[32] and decNetwork
    public static void fillingTheBinNetworkArray() {
//        tab.sNetworkBin = "";
        Data.setStrNetworkBin("");

//        tab.sFirstAddressBin = "";
        Data.setStrFirstAddressBin("");

//        tab.sLastAddressBin = "";
        Data.setStrLastAddressBin("");

//        tab.sBroadcastBin = "";
        Data.setStrBroadcastBin("");

        for (int i = 31; i >= 0; i--) {
//            if (tab.binNetmaskArray[i]) {
            if (Data.getBinNetmaskArray()[i]) {

//                tab.binNetworkArray[i] = tab.binIPAddressArray[i];
                Data.setBinNetworkArray(i, Data.getBinIPAddressArray()[i]);

//                if (tab.binIPAddressArray[i]) {
                if (Data.getBinIPAddressArray()[i]) {

//                    tab.sNetworkBin += "1";
                    Data.setStrNetworkBin(Data.getStrNetworkBin() + "1");
//                    tab.binFirstAddress[i] = true;
                    Data.setBinFirstAddress(i, true);
//                    tab.sFirstAddressBin += "1";
                    Data.setStrFirstAddressBin(Data.getStrFirstAddressBin() + "1");
//                    tab.binLastAddress[i] = true;
                    Data.setBinLastAddress(i, true);
//                    tab.sLastAddressBin += "1";
                    Data.setStrLastAddressBin(Data.getStrLastAddressBin() + "1");
//                    tab.binBroadcast[i] = true;
                    Data.setBinBroadcast(i, true);
//                    tab.sBroadcastBin += "1";
                    Data.setStrBroadcastBin(Data.getStrBroadcastBin() + "1");
                } else {
//                    tab.sNetworkBin += "0";
                    Data.setStrNetworkBin(Data.getStrNetworkBin() + "0");
//                    tab.binFirstAddress[i] = false;
                    Data.setBinFirstAddress(i, false);
//                    tab.sFirstAddressBin += "0";
                    Data.setStrFirstAddressBin(Data.getStrFirstAddressBin() + "0");
//                    tab.binLastAddress[i] = false;
                    Data.setBinLastAddress(i, false);
//                    tab.sLastAddressBin += "0";
                    Data.setStrLastAddressBin(Data.getStrLastAddressBin() + "0");
//                    tab.binBroadcast[i] = false;
                    Data.setBinBroadcast(i, false);
//                    tab.sBroadcastBin += "0";
                    Data.setStrBroadcastBin(Data.getStrBroadcastBin() + "0");
                }
            } else {
//                tab.binNetworkArray[i] = false;
                Data.setBinNetworkArray(i,false);
//                tab.sNetworkBin += "0";
                Data.setStrNetworkBin(Data.getStrNetworkBin() + "0");

                // заполним массив tab.binFirstAddress[] and tab.binLastAddress[] данными
                if (i == 0) {
//                    tab.binFirstAddress[i] = true;
                    Data.setBinFirstAddress(i,true);
//                    tab.sFirstAddressBin += "1";
                    Data.setStrFirstAddressBin(Data.getStrFirstAddressBin() + "1");
//                    tab.binLastAddress[i] = false;
                    Data.setBinLastAddress(i, true);
//                    tab.sLastAddressBin += "0";
                    Data.setStrLastAddressBin(Data.getStrLastAddressBin() + "0");
                } else {
//                    tab.binFirstAddress[i] = false;
                    Data.setBinFirstAddress(i, true);
//                    tab.sFirstAddressBin += "0";
                    Data.setStrFirstAddressBin(Data.getStrFirstAddressBin() + "0");
//                    tab.binLastAddress[i] = true;
                    Data.setBinLastAddress(i, true);
//                    tab.sLastAddressBin += "1";
                    Data.setStrLastAddressBin(Data.getStrLastAddressBin() + "1");
                }
//                tab.binBroadcast[i] = true;
                Data.setBinBroadcast(i, true);
//                tab.sBroadcastBin += "1";
                Data.setStrBroadcastBin(Data.getStrBroadcastBin() + "1");
            }

            if (i == 24 || i == 16 || i == 8) {
//                tab.sNetworkBin += " ";
                Data.setStrNetworkBin(Data.getStrNetworkBin() + " ");
//                tab.sFirstAddressBin += " ";
                Data.setStrFirstAddressBin(Data.getStrFirstAddressBin() + " ");
//                tab.sLastAddressBin += " ";
                Data.setStrLastAddressBin(Data.getStrLastAddressBin() + " ");
//                tab.sBroadcastBin += " ";
                Data.setStrBroadcastBin(Data.getStrBroadcastBin() + " ");
            }
        }
    }

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
    public static String binToDec(boolean[] Arr) {
        int AuxiliaryDecByte0 = 0;
        String sTemp = "";
        int[] Degree = new int[8];
        // заполним массив промежуточными данными для дальнейших вычислений
        for (int i = 7; i >= 0; i--) {
            Degree[i] = (int) Math.pow(2, i);
        }

        for (int i = 31; i >= 24; i--) {
            if (Arr[i]) {
                AuxiliaryDecByte0 += Degree[i - 24];
            }
        }
        sTemp = String.valueOf(AuxiliaryDecByte0) + ".";
        AuxiliaryDecByte0 = 0;

        for (int i = 23; i >= 16; i--) {
            if (Arr[i]) {
                AuxiliaryDecByte0 += Degree[i - 16];
            }
        }
        sTemp += String.valueOf(AuxiliaryDecByte0) + ".";
        AuxiliaryDecByte0 = 0;

        for (int i = 15; i >= 8; i--) {
            if (Arr[i]) {
                AuxiliaryDecByte0 += Degree[i - 8];
            }
        }
        sTemp += String.valueOf(AuxiliaryDecByte0) + ".";
        AuxiliaryDecByte0 = 0;

        for (int i = 7; i >= 0; i--) {
            if (Arr[i]) {
                AuxiliaryDecByte0 += Degree[i];
            }
        }
        sTemp += String.valueOf(AuxiliaryDecByte0);

        return sTemp;
    }


    // temp new
    public static String calculationNetMask(int cidr) {
        // Преобразуем CIDR в маску сети
        // temp origin
//        int netmask = ~((1 << (32 - cidr)) - 1);
        int netmask = -(1 << (32 - cidr));

        // Переводим результат в строку вида "255.255.255.0"
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(netmask >>> ((3 - i) * 8) & 0xFF);
            if (i < 3) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

}