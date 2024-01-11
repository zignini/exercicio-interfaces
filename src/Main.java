/*
Uma empresa deseja automatizar o processamento de seus contratos. O processamento de
um contrato consiste em gerar as parcelas a serem pagas para aquele contrato, com base no
número de meses desejado.
A empresa utiliza um serviço de pagamento online para realizar o pagamento das parcelas.
Os serviços de pagamento online tipicamente cobram um juro mensal, bem como uma taxa
por pagamento. Por enquanto, o serviço contratado pela empresa é o do Paypal, que aplica
juros simples de 1% a cada parcela, mais uma taxa de pagamento de 2%.
Fazer um programa para ler os dados de um contrato (número do contrato, data do contrato,
e valor total do contrato). Em seguida, o programa deve ler o número de meses para
parcelamento do contrato, e daí gerar os registros de parcelas a serem pagas (data e valor),
sendo a primeira parcela a ser paga um mês após a data do contrato, a segunda parcela dois
meses após o contrato e assim por diante. Mostrar os dados das parcelas na tela.
Veja exemplo na próxima página.

Entre os dados do contrato:
Numero: 8028
Data (dd/MM/yyyy): 25/06/2018
Valor do contrato: 600.00
Entre com o numero de parcelas: 3
Parcelas:
25/07/2018 - 206.04
25/08/2018 - 208.08
25/09/2018 - 210.12
*/

import model.entities.Contract;
import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PayPalService;

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
            } catch (Exception e) {
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

        boolean monthsIsValid = false;
        int months = 0;
        do {
            System.out.print("Installments: ");
            try {
                months = sc.nextInt();
                sc.nextLine();
                monthsIsValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer number.");
                sc.next();
            }
        } while (!monthsIsValid);

        Contract contract = new Contract(number, date, value);

        OnlinePaymentService payPal = new PayPalService();

        ContractService contractService = new ContractService(payPal);

        contractService.processContract(contract, months);

        System.out.println("Installments: ");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }

        sc.close();
    }

}