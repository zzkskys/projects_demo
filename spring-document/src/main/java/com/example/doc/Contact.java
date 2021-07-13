package com.example.doc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "联系方式")
@Data
public class Contact {

    @ApiModelProperty(value = "电话号码", required = true)
    private String phone;


    @ApiModelProperty("电子邮件")
    private String email;

}
