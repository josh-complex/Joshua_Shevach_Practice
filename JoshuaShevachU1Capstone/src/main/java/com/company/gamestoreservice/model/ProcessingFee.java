package com.company.gamestoreservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@UniqueElements
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="processingfee")
public class ProcessingFee {

    @Id
    private String productType;
    private BigDecimal fee;

}
