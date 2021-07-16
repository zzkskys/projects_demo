package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public abstract class AbstractEntity {

    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Version
    private int version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        if (that.id == null) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
