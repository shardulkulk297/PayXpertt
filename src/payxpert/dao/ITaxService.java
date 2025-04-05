package payxpert.dao;

import payxpert.model.Tax;

import java.time.Year;
import java.util.List;

public interface ITaxService {
    double CalculateTax(int employeeId, int taxYear);
    Tax GetTaxById(int taxId);
    List<Tax> GetTaxesForEmployee(int employeeId);
    List<Tax> GetTaxesForYear(int taxYear);
}
