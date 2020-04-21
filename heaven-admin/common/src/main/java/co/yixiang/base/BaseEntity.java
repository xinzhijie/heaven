package co.yixiang.base;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.sql.Timestamp;
import java.lang.reflect.Field;
/**
 * @author Zheng Jie
 * @Date 2019年10月24日20:46:32
 */
@Getter
@Setter

public class BaseEntity implements Serializable {


    private Boolean isDelete = false;


    private Timestamp createTime;

    private Timestamp updateTime;

    public @interface Update {}

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
