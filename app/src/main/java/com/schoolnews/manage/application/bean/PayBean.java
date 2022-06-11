package com.schoolnews.manage.application.bean;

import java.io.Serializable;


public class PayBean implements Serializable {
    private long employeesId;
    private double salaryAmount;

    public long getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(long employeesId) {
        this.employeesId = employeesId;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }
}
