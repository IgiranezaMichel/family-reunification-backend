package com.familyreunificationbackend.restbodyinput;

import java.util.UUID;
import com.familyreunificationbackend.model.Beneficiary;
import lombok.Getter;
import lombok.Setter;

public class BeneficiaryInput extends Beneficiary{
@Getter @Setter
private UUID lostId;
@Getter  @Setter
private long organizationId;
}
