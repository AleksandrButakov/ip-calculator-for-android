package com.anbn.ipcalculatorforandroid;

public class ClearingFragment1Fields {

    // обнулим значение переменных используемых для расчета при нажатии кнопки Clear
    public static void clearingFragment1Fields() {
        /*
        CalculationAddresses ftab1 = new CalculationAddresses();
        ftab1.ipAddress = "";
        ftab1.cIDR = "";
        ftab1.networkMask = "";

        ftab1.decNetwork = "";
        ftab1.decBroadcast = "";
        ftab1.decNetMask = "";
        ftab1.decFirstAddress = "";
        ftab1.decLastAddress = "";
        ftab1.decUsable = "";

        ftab1.binNetwork = "";
        ftab1.binBroadcast = "";
        ftab1.binNetmask = "";
        ftab1.binFirstAddress = "";
        ftab1.binLastAddress = "";
        ftab1.binUsable = "";

        CalculationAddresses ftab2 = new CalculationAddresses();
        ftab2.ipAddress = "";
        ftab2.cIDR = "";
        ftab2.networkMask = "";

        ftab2.decNetwork = "";
        ftab2.decBroadcast = "";
        ftab2.decNetMask = "";
        ftab2.decFirstAddress = "";
        ftab2.decLastAddress = "";
        ftab2.decUsable = "";

        ftab2.binNetwork = "";
        ftab2.binBroadcast = "";
        ftab2.binNetmask = "";
        ftab2.binFirstAddress = "";
        ftab2.binLastAddress = "";
        ftab2.binUsable = "";

        CalculationAddresses ftab3 = new CalculationAddresses();
        ftab3.ipAddress = "";
        ftab3.cIDR = "";
        ftab3.networkMask = "";

        ftab3.decNetwork = "";
        ftab3.decBroadcast = "";
        ftab3.decNetMask = "";
        ftab3.decFirstAddress = "";
        ftab3.decLastAddress = "";
        ftab3.decUsable = "";

        ftab3.binNetwork = "";
        ftab3.binBroadcast = "";
        ftab3.binNetmask = "";
        ftab3.binFirstAddress = "";
        ftab3.binLastAddress = "";
        ftab3.binUsable = "";
         */

    }

    /* Присвоили пустые значения переменным при запуске приложения.
    Необходимо для исключения дальнейшей проверки на соответствия null
     */
    public static void clearingVariablesIPAddressTab1(CalculationAddresses ipAddressTab1) {
        // переменные для хранения байтов IP адреса
        ipAddressTab1.ipAddressB3 = "";
        ipAddressTab1.ipAddressB2 = "";
        ipAddressTab1.ipAddressB1 = "";
        ipAddressTab1.ipAddressB0 = "";
    }

    /* Присвоили пустые значения переменным при запуске приложения.
    Необходимо для исключения дальнейшей проверки на соответствия null
     */
    public static void clearingVariablesCidrTab1(CalculationAddresses cidrTab1) {
        // переменная для хранения количества бит маски подсети
        cidrTab1.cidr = "";
    }

    /* Присвоили пустые значения переменным при запуске приложения.
    Необходимо для исключения дальнейшей проверки на соответствия null
     */
    public static void clearingVariablesNetmaskTab1(CalculationAddresses netmaskTab1) {
        // переменные для хранения байтов маски подсети
        netmaskTab1.netmaskB3 = "";
        netmaskTab1.netmaskB2 = "";
        netmaskTab1.netmaskB1 = "";
        netmaskTab1.netmaskB0 = "";
    }

}
