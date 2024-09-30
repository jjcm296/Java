import UI.UIMenuAccount;
import card.CreditCard;
import card.DebitCard;
import client.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

import static UI.UIMenu.menu;
import static  UI.UIMenuAccount.*;

public class Main {
    public static void main(String[] args) throws Exception {

        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        Date date = s.parse("28/09/2001");

        Client client =  new Client("Jordan", "Cruz", "Mendoza", "j.jordan28@putlook.com",
                "CUMJ010928HVZRNRA4", date, "9221860109");

        DebitCard debitCardClient = new DebitCard(client);
        CreditCard creditCardClien = new CreditCard(client);

        UIMenuAccount.addDebitCard(debitCardClient);
        UIMenuAccount.addCreditCard(creditCardClien);

        System.out.println(debitCardClient.toString());
        System.out.println(creditCardClien.toString());
        menu();
    }
}
