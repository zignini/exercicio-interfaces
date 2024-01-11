package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Contract {

    private int number;
    private LocalDate date;
    private double value;
    private int installments;
    private PaymentService paymentService;

    public Contract(int number, LocalDate date, double value, int installments, PaymentService paymentService) {
        this.number = number;
        this.date = date;
        this.value = value;
        this.installments = installments;
        this.paymentService = paymentService;
    }

    public void printMonthlyFee () {
        double installmentValue = value / installments;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Installments: ");
        for (int i = 1; i <= installments; i++) {
            LocalDate due = date.plus(i, ChronoUnit.MONTHS);
            double monthlyFee = paymentService.calculateMonthlyFee(installmentValue, i);

            System.out.printf("%s - %.2f%n", fmt.format(due), monthlyFee);

        }
    }
}
