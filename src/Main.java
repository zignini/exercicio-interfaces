import model.entities.Contract;
import model.entities.PayPalService;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter contract information: ");
        boolean numberisValid = false;
        int number = 0;
        do {
            System.out.print("Number: ");
            try {
                number = sc.nextInt();
                sc.nextLine();
                numberisValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer number.");
                sc.next();
            }
        } while (!numberisValid);

        boolean dateIsValid = false;
        LocalDate date = LocalDate.now();
        do {
            System.out.print("Date (dd/MM/yyyy): ");
            try {
                String strDate = sc.nextLine();
                date = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                dateIsValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid date.");
                sc.nextLine();
            }
        } while (!dateIsValid);

        boolean valueIsValid = false;
        double value = 0;
        do {
            System.out.print("Value: ");
            try {
                value = sc.nextDouble();
                sc.nextLine();
                valueIsValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid value.");
                sc.next();
            }
        } while (!valueIsValid);

        boolean installmentsIsValid = false;
        int installments = 0;
        do {
            System.out.print("Installments: ");
            try {
                installments = sc.nextInt();
                sc.nextLine();
                installmentsIsValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer number.");
                sc.next();
            }
        } while (!installmentsIsValid);

        PayPalService payPalService = new PayPalService(0.01, 0.02);

        Contract contract = new Contract(number, date, value, installments, payPalService);

        contract.printMonthlyFee();
    }

}