package com.anbn.ipcalculatorforandroid;

public class Data {
    private static String ipByte3 = "";
    private static String ipByte2 = "";
    private static String ipByte1 = "";
    private static String ipByte0 = "";
    private static String strCidr = "";
    private static String maskByte3 = "";
    private static String maskByte2 = "";
    private static String maskByte1 = "";
    private static String maskByte0 = "";
    private static String fullMask = "";

    // переменные назначены и именованы в соответствии с расположением полей в layout
    private static String decIPAddress;
    private static String decCIDR;
    private static String decNetMask;

    private static String decNetwork;
    private static String decFirstAddress;
    private static String decLastAddress;
    private static String decBroadcast;
    private static String decNumberHosts;

    private static String strIpAddressBin;
    private static String strNetworkBin;
    private static String strNetmaskBin;
    private static String strFirstAddressBin;
    private static String strLastAddressBin;
    private static String strBroadcastBin;

    private static boolean[] binIPAddressArray = new boolean[32]; //
    private static boolean[] binNetmaskArray = new boolean[32]; //
    private static boolean[] binNetworkArray = new boolean[32]; //
    private static boolean[] binBroadcast = new boolean[32]; //
    private static boolean[] binNetmask = new boolean[32]; //
    private static boolean[] binFirstAddress = new boolean[32];
    private static boolean[] binLastAddress = new boolean[32];

    /**
     * Устанавливает значение определенного бита.
     *
     * @param index Индекс бита (0–31).
     * @param value Значение, которое нужно установить (true или false).
     * @throws IndexOutOfBoundsException Если индекс находится вне диапазона массива.
     */
    public static void setBinLastAddress(int index, boolean value) {
        if (index >= 0 && index < binLastAddress.length) {
            binLastAddress[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    /**
     * Устанавливает значение определенного бита.
     *
     * @param index Индекс бита (0–31).
     * @param value Значение, которое нужно установить (true или false).
     * @throws IndexOutOfBoundsException Если индекс находится вне диапазона массива.
     */
    public static void setBinFirstAddress(int index, boolean value) {
        if (index >= 0 && index < binFirstAddress.length) {
            binFirstAddress[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    /**
     * Устанавливает значение определенного бита.
     *
     * @param index Индекс бита (0–31).
     * @param value Значение, которое нужно установить (true или false).
     * @throws IndexOutOfBoundsException Если индекс находится вне диапазона массива.
     */
    public static void setBinNetmask(int index, boolean value) {
        if (index >= 0 && index < binNetmask.length) {
            binNetmask[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    /**
     * Устанавливает значение определенного бита.
     *
     * @param index Индекс бита (0–31).
     * @param value Значение, которое нужно установить (true или false).
     * @throws IndexOutOfBoundsException Если индекс находится вне диапазона массива.
     */
    public static void setBinBroadcast(int index, boolean value) {
        if (index >= 0 && index < binBroadcast.length) {
            binBroadcast[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    /**
     * Устанавливает значение определенного бита.
     *
     * @param index Индекс бита (0–31).
     * @param value Значение, которое нужно установить (true или false).
     * @throws IndexOutOfBoundsException Если индекс находится вне диапазона массива.
     */
    public static void setBinNetworkArray(int index, boolean value) {
        if (index >= 0 && index < binNetworkArray.length) {
            binNetworkArray[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    /**
     * Устанавливает значение определенного бита.
     *
     * @param index Индекс бита (0–31).
     * @param value Значение, которое нужно установить (true или false).
     * @throws IndexOutOfBoundsException Если индекс находится вне диапазона массива.
     */
    public static void setBinNetmaskArray(int index, boolean value) {
        if (index >= 0 && index < binNetmaskArray.length) {
            binNetmaskArray[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    /**
     * Устанавливает значение определенного бита.
     *
     * @param index Индекс бита (0–31).
     * @param value Значение, которое нужно установить (true или false).
     * @throws IndexOutOfBoundsException Если индекс находится вне диапазона массива.
     */
    public static void setBinIPAddressArray(int index, boolean value) {
        if (index >= 0 && index < binIPAddressArray.length) {
            binIPAddressArray[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
    }

    public static String getDecIPAddress() {
        return decIPAddress;
    }

    public static void setDecIPAddress(String decIPAddress) {
        Data.decIPAddress = decIPAddress;
    }

    public static String getDecCIDR() {
        return decCIDR;
    }

    public static void setDecCIDR(String decCIDR) {
        Data.decCIDR = decCIDR;
    }

    public static String getDecNetMask() {
        return decNetMask;
    }

    public static void setDecNetMask(String decNetMask) {
        Data.decNetMask = decNetMask;
    }

    public static String getDecNetwork() {
        return decNetwork;
    }

    public static void setDecNetwork(String decNetwork) {
        Data.decNetwork = decNetwork;
    }

    public static String getDecFirstAddress() {
        return decFirstAddress;
    }

    public static void setDecFirstAddress(String decFirstAddress) {
        Data.decFirstAddress = decFirstAddress;
    }

    public static String getDecLastAddress() {
        return decLastAddress;
    }

    public static void setDecLastAddress(String decLastAddress) {
        Data.decLastAddress = decLastAddress;
    }

    public static String getDecBroadcast() {
        return decBroadcast;
    }

    public static void setDecBroadcast(String decBroadcast) {
        Data.decBroadcast = decBroadcast;
    }

    public static String getDecNumberHosts() {
        return decNumberHosts;
    }

    public static void setDecNumberHosts(String decNumberHosts) {
        Data.decNumberHosts = decNumberHosts;
    }

    public static String getStrIpAddressBin() {
        return strIpAddressBin;
    }

    public static void setStrIpAddressBin(String strIpAddressBin) {
        Data.strIpAddressBin = strIpAddressBin;
    }

    public static String getStrNetworkBin() {
        return strNetworkBin;
    }

    public static void setStrNetworkBin(String strNetworkBin) {
        Data.strNetworkBin = strNetworkBin;
    }

    public static String getStrNetmaskBin() {
        return strNetmaskBin;
    }

    public static void setStrNetmaskBin(String strNetmaskBin) {
        Data.strNetmaskBin = strNetmaskBin;
    }

    public static String getStrFirstAddressBin() {
        return strFirstAddressBin;
    }

    public static void setStrFirstAddressBin(String strFirstAddressBin) {
        Data.strFirstAddressBin = strFirstAddressBin;
    }

    public static String getStrLastAddressBin() {
        return strLastAddressBin;
    }

    public static void setStrLastAddressBin(String strLastAddressBin) {
        Data.strLastAddressBin = strLastAddressBin;
    }

    public static String getStrBroadcastBin() {
        return strBroadcastBin;
    }

    public static void setStrBroadcastBin(String strBroadcastBin) {
        Data.strBroadcastBin = strBroadcastBin;
    }

    public static boolean[] getBinIPAddressArray() {
        return binIPAddressArray;
    }

    public static void setBinIPAddressArray(boolean[] binIPAddressArray) {
        Data.binIPAddressArray = binIPAddressArray;
    }

    public static boolean[] getBinNetmaskArray() {
        return binNetmaskArray;
    }

    public static void setBinNetmaskArray(boolean[] binNetmaskArray) {
        Data.binNetmaskArray = binNetmaskArray;
    }

    public static boolean[] getBinNetworkArray() {
        return binNetworkArray;
    }

    public static void setBinNetworkArray(boolean[] binNetworkArray) {
        Data.binNetworkArray = binNetworkArray;
    }

    public static boolean[] getBinBroadcast() {
        return binBroadcast;
    }

    public static void setBinBroadcast(boolean[] binBroadcast) {
        Data.binBroadcast = binBroadcast;
    }

    public static boolean[] getBinNetmask() {
        return binNetmask;
    }

    public static void setBinNetmask(boolean[] binNetmask) {
        Data.binNetmask = binNetmask;
    }

    public static boolean[] getBinFirstAddress() {
        return binFirstAddress;
    }

    public static void setBinFirstAddress(boolean[] binFirstAddress) {
        Data.binFirstAddress = binFirstAddress;
    }

    public static boolean[] getBinLastAddress() {
        return binLastAddress;
    }

    public static void setBinLastAddress(boolean[] binLastAddress) {
        Data.binLastAddress = binLastAddress;
    }

    public static String getFullMask() {
        return fullMask;
    }

    public static void setFullMask(String fullMask) {
        Data.fullMask = fullMask;
    }

    public static String getIpByte3() {
        return ipByte3;
    }

    public static void setIpByte3(String ipByte3) {
        Data.ipByte3 = ipByte3;
    }

    public static String getIpByte2() {
        return ipByte2;
    }

    public static void setIpByte2(String ipByte2) {
        Data.ipByte2 = ipByte2;
    }

    public static String getIpByte1() {
        return ipByte1;
    }

    public static void setIpByte1(String ipByte1) {
        Data.ipByte1 = ipByte1;
    }

    public static String getIpByte0() {
        return ipByte0;
    }

    public static void setIpByte0(String ipByte0) {
        Data.ipByte0 = ipByte0;
    }

    public static String getStrCidr() {
        return strCidr;
    }

    public static void setStrCidr(String strCidr) {
        Data.strCidr = strCidr;
    }

    public static String getMaskByte3() {
        return maskByte3;
    }

    public static void setMaskByte3(String maskByte3) {
        Data.maskByte3 = maskByte3;
    }

    public static String getMaskByte2() {
        return maskByte2;
    }

    public static void setMaskByte2(String maskByte2) {
        Data.maskByte2 = maskByte2;
    }

    public static String getMaskByte1() {
        return maskByte1;
    }

    public static void setMaskByte1(String maskByte1) {
        Data.maskByte1 = maskByte1;
    }

    public static String getMaskByte0() {
        return maskByte0;
    }

    public static void setMaskByte0(String maskByte0) {
        Data.maskByte0 = maskByte0;
    }
}