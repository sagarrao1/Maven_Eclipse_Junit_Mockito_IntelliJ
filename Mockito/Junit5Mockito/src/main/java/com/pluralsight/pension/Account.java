package com.pluralsight.pension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Set;

public class Account {

    private String id;
    private String fistName;
    private String lastName;
    private LocalDate dob;
    private String taxId;
    private BigDecimal totalInvestmentValue;
    private Currency ccy;
    private Set<String> investments;
    private BigDecimal availableCash;
    private LocalDateTime expectedRetirement;
    private LocalDate openingDate;

    public LocalDateTime getExpectedRetirement() {
        return expectedRetirement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public BigDecimal getTotalInvestmentValue() {
        return totalInvestmentValue;
    }

    public void setTotalInvestmentValue(BigDecimal totalInvestmentValue) {
        this.totalInvestmentValue = totalInvestmentValue;
    }

    public Currency getCcy() {
        return ccy;
    }

    public void setCcy(Currency ccy) {
        this.ccy = ccy;
    }

    public Set<String> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<String> investments) {
        this.investments = investments;
    }

    public BigDecimal getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(BigDecimal availableCash) {
        this.availableCash = availableCash;
    }

    public void setExpectedRetirement(LocalDateTime expectedRetirement) {
        this.expectedRetirement = expectedRetirement;
    }

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}
    
    
}
