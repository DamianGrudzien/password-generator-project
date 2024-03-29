package com.passwordgenerator.damiangrudzien.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PasswordRequestDTO {

	Long wordAmount = 0L;
	Integer charAmount = 0;
	Integer numberAmount = 0;
	Boolean upperFirst = false;
}
