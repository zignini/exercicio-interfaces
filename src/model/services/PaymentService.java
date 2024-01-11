package model.services;

public interface PaymentService {

    public double calculateMonthlyFee(double installmentValue, int installmentNumber);
}