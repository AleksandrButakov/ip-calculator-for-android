package com.anbn.ipcalculatorforandroid;

public class IpAddress {
    static int cidr;

    public static int getCidr() {
        return cidr;
    }

    public static void setCidr(int cidr) {
        IpAddress.cidr = cidr;
    }
}