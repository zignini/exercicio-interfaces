package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ContractService {

    private final OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract (Contract contract, int months) {

        double installmentBaseAmount = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            LocalDate installmentDueDate = contract.getDate().plusMonths(i);
            double installmentAmount = installmentBaseAmount + onlinePaymentService.interest(installmentBaseAmount,
                    i) + onlinePaymentService.paymentFee(installmentBaseAmount + onlinePaymentService
                    .interest(installmentBaseAmount, i));
            Installment installment = new Installment (installmentDueDate, installmentAmount);
            contract.getInstallments().add(installment);
        }

    }
}
