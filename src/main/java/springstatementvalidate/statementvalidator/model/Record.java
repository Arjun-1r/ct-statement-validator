package springstatementvalidate.statementvalidator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private Long transactionReference;
	private String accountNumber;
	private String description;
	private Double startBalance;
	private Double mutation;
	private Double endBalance;
}
