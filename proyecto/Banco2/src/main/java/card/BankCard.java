package card;

import client.Client;

import java.time.LocalDate;

public abstract class BankCard {
    private static long cardCounter = 1000000000000000L;

    private String cardNumber;
    private String nip;
    private String dueDate;
    private String cvv;
    protected Client client;

    public BankCard(Client client) {
        this.cardNumber = generateCardNumber();
        this.nip = generateNip();
        this.dueDate = generateDueDate();
        this.cvv = generateCVV();
        this.client = client;
    }
    protected static String generateCardNumber(){
        cardCounter += 1;
        return Long.toString(cardCounter);

    }
    protected static String generateNip(){
        int nip = (int) (Math.random() * 9000) + 1000;
        return String.valueOf(nip);
    }
    protected  static String generateCVV(){
        int cvv = (int) (Math.random() * 900) + 100;
        return String.valueOf(cvv);
    }

    protected static String generateDueDate(){
        LocalDate date = LocalDate.now();
        LocalDate year4 =  date.plusYears(4);

        String year = String.valueOf(year4);
        String mothon = date.getMonth().toString();

        return (year + "/" + mothon);
    }

    public abstract void withdraw(double amount);

    public static long getCardCounter() {
        return cardCounter;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getNip() {
        return nip;
    }

    public String getCVV(){
        return cvv;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Nombre del cliente: " + client.getFullName() + "\n"
                + "Número de tarjeta: " + cardNumber + "\n" + "NIP: " + nip + "\n"+ "CVV: " + cvv;
    }
}
