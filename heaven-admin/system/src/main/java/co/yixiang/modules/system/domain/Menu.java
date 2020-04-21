package co.yixiang.modules.system.domain;


import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-12-17
 */

@Getter
@Setter

public class Menu implements Serializable {

    private Long id;

    private String name;

    private Long sort = 999L;

    private String path;

    private String component;

    private Integer type;

    private String permission;

    private String componentName;

    private String icon;

    private Boolean cache;

    private Boolean hidden;

    /** 上级菜单ID */
    private Long pid;

    /** 是否为外链 true/false */
    private Boolean iFrame;

    private Set<Role> roles;

    private Timestamp createTime;

    public @interface Update {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
