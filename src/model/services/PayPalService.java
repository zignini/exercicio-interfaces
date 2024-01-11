package model.services;

import model.entities.PaymentService;

public class PayPalService implements PaymentService {

    private double interestRate;
    private double paymentRate;

    public PayPalService(double interestRate, double paymentRate) {
        this.interestRate = interestRate;
        this.paymentRate = paymentRate;
    }

    @Override
    public double calculateMonthlyFee(double installmentValue, int installmentNumber) {
        installmentValue += installmentValue * interestRate * installmentNumber;
        installmentValue += installmentValue * paymentRate;
        return installmentValue;
    }
}
