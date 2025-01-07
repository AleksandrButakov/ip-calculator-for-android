package com.anbn.ipcalculatorforandroid;

public class ClearingFragment1Fields {
    /* Присвоили пустые значения переменным при запуске приложения.
    Необходимо для исключения дальнейшей проверки на соответствия null
     */
    public static void clearingVariablesTab(CalculationAddresses tab) {
        // переменные для хранения байтов IP адреса
        tab.ipAddressB3 = "";
        tab.ipAddressB2 = "";
        tab.ipAddressB1 = "";
        tab.ipAddressB0 = "";

        // переменная для хранения количества бит маски подсети
        tab.cidr = "";

        // переменные для хранения байтов маски подсети
        tab.netmaskB3 = "";
        tab.netmaskB2 = "";
        tab.netmaskB1 = "";
        tab.netmaskB0 = "";
    }
}