package com.anbn.ipcalculatorforandroid;

public class CalculationAddresses {
    // переменные назначины и именованы в соответствии с расположением полей в layout
    String ipAddress;
    String cIDR;
    String networkMask;

    String decNetwork;
    String decBroadcast;
    String decNetMask;
    String decFirstAddress;
    String decLastAddress;
    String decUsable;

    String binNetwork;
    String binBroadcast;
    String binNetmask;
    String binFirstAddress;
    String binLastAddress;
    String binUsable;

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
}
