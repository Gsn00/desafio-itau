package app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estatistica {

	private Integer count;
	private Double sum;
	private Double avg;
	private Double min;
	private Double max;
	
}
