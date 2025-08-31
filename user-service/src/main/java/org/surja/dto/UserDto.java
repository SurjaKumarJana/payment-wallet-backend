package org.surja.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@ToString
public class UserDto {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String kycNumber;

}