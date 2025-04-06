package payxpert.dao;

import payxpert.exception.TaxCalculationException;
import payxpert.model.Tax;

import java.util.List;

public class TaxService implements ITaxService {
    TaxDAO taxDAO = new TaxDAO();
    @Override
    public double CalculateTax(int employeeId, int taxYear) throws TaxCalculationException {
        return taxDAO.CalculateTax(employeeId, taxYear);
    }

    @Override
    public Tax GetTaxById(int taxId) throws TaxCalculationException{
        return taxDAO.GetTaxById(taxId);

    }

    @Override
    public List<Tax> GetTaxesForEmployee(int employeeId) throws TaxCalculationException{
        return taxDAO.GetTaxesForEmployee(employeeId);

    }

    @Override
    public List<Tax> GetTaxesForYear(int taxYear) throws TaxCalculationException{
        return taxDAO.GetTaxesForYear(taxYear);

    }
}
