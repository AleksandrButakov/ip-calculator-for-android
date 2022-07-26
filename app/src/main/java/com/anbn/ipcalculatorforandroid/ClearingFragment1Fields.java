package com.anbn.ipcalculatorforandroid;

public class ClearingFragment1Fields {

    /* Присвоили пустые значения переменным при запуске приложения.
    Необходимо для исключения дальнейшей проверки на соответствия null
     */
    public static void clearingVariablesTab1(CalculationAddresses tab1) {
        // переменные для хранения байтов IP адреса
        tab1.ipAddressB3 = "";
        tab1.ipAddressB2 = "";
        tab1.ipAddressB1 = "";
        tab1.ipAddressB0 = "";

        // переменная для хранения количества бит маски подсети
        tab1.cidr = "";

        // переменные для хранения байтов маски подсети
        tab1.netmaskB3 = "";
        tab1.netmaskB2 = "";
        tab1.netmaskB1 = "";
        tab1.netmaskB0 = "";
    }

}
