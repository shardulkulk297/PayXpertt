package payxpert.dao;

import payxpert.exception.TaxCalculationException;
import payxpert.model.Tax;

import java.util.List;

public interface ITaxService {
    double CalculateTax(int employeeId, int taxYear) throws TaxCalculationException;
    Tax GetTaxById(int taxId)throws TaxCalculationException;
    List<Tax> GetTaxesForEmployee(int employeeId) throws TaxCalculationException;
    List<Tax> GetTaxesForYear(int taxYear) throws TaxCalculationException;
}
