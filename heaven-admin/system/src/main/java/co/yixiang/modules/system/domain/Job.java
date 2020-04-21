package co.yixiang.modules.system.domain;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author Zheng Jie
* @date 2019-03-29
*/

@Getter
@Setter

public class Job implements Serializable {


    private Long id;


    private String name;

    private Long sort;

    private Boolean enabled;

    private Dept dept;

    private Timestamp createTime;

    public @interface Update {}
}