package com.genka.paymentservice.infra.usecases;

import com.genka.paymentservice.application.exceptions.EntityNotFoundException;
import com.genka.paymentservice.application.gateways.CompanyDatabaseGateway;
import com.genka.paymentservice.application.gateways.FeeDatabaseGateway;
import com.genka.paymentservice.application.usecases.AddTransactionFee;
import com.genka.paymentservice.application.usecases.inputs.AddTransactionFeeInput;
import com.genka.paymentservice.application.usecases.outputs.FeeOutput;
import com.genka.paymentservice.domain.entities.Company;
import com.genka.paymentservice.domain.entities.Fee;
import com.genka.paymentservice.domain.entities.enums.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class AddTransactionFeeImpl implements AddTransactionFee {

    private final FeeDatabaseGateway feeDatabaseGateway;
    private final CompanyDatabaseGateway companyDatabaseGateway;

    public AddTransactionFeeImpl(FeeDatabaseGateway feeDatabaseGateway, CompanyDatabaseGateway companyDatabaseGateway) {
        this.feeDatabaseGateway = feeDatabaseGateway;
        this.companyDatabaseGateway = companyDatabaseGateway;
    }

    @Override
    public FeeOutput execute(AddTransactionFeeInput addTransactionFeeInput) {
        Company company = this.companyDatabaseGateway.findCompanyById(addTransactionFeeInput.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company with id " + addTransactionFeeInput.getCompanyId() + " not found"));
        if(addTransactionFeeInput.getTransactionType() == (TransactionType.TOP_UP)) {
            throw new IllegalArgumentException("TOP_UP transaction type cannot incur fees");
        }
        Fee fee = Fee.builder()
                .company(company)
                .name(addTransactionFeeInput.getName())
                .value(addTransactionFeeInput.getValue())
                .transactionType(addTransactionFeeInput.getTransactionType())
                .build();
        Fee savedFee = this.feeDatabaseGateway.saveFee(fee);
        return FeeOutput.mapFromEntity(savedFee);
    }
}
