package co.yixiang.modules.system.domain;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */

@Getter
@Setter
public class User implements Serializable {

    private Long id;

    private String username;

    private String nickName;

    private String sex;

    private UserAvatar userAvatar;

    private String email;

    private String phone;

    private Boolean enabled;

    private String password;

    private Timestamp createTime;

    private Date lastPasswordResetTime;

    private Set<Role> roles;

    private Job job;

    private Dept dept;

    public @interface Update {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}