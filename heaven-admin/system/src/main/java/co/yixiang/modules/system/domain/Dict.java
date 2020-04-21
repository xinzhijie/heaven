package co.yixiang.modules.system.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Getter
@Setter
public class Dict implements Serializable {

    private Long id;

    private String name;

    private String remark;

    private Timestamp createTime;

    private List<DictDetail> dictDetails;

    public @interface Update {}
}