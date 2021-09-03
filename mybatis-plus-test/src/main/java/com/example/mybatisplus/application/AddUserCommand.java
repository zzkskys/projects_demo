package com.example.mybatisplus.application;

import com.example.mybatisplus.domain.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Created Date : 2021/09/01
 *
 * @author zzk
 */
@Data
@Accessors(chain = true)
public class AddUserCommand {

    @NotNull
    private String name;

    @NotNull
    private Role role;
}
