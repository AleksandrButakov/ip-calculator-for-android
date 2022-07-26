package com.anbn.ipcalculatorforandroid;

public class ClearingFragment1Fields {

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
