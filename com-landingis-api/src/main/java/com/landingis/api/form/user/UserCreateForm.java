package com.landingis.api.form.user;

import io.swagger.annotations.ApiModelProperty;
import com.landingis.api.validation.GenderConstraint;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateForm {

    @ApiModelProperty(value = "User handle (username)", example = "johndoe", required = true)
    @NotEmpty(message = "User handle cannot be empty")
    private String userHandle;

    @ApiModelProperty(value = "User password", example = "Secure@123", required = true)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\W).{6,}$",
            message = "Password must have at least 6 characters, 1 uppercase letter, and 1 special character"
    )
    private String userPassword;

    @ApiModelProperty(value = "User full name", example = "John Doe", required = true)
    @NotEmpty(message = "User full name cannot be empty")
    private String userFullName;

    @ApiModelProperty(value = "Gender (1: Male, 2: Female, 3: Other)", example = "1", required = false)
    @GenderConstraint(allowNull = true)
    private Integer userGender;
}
