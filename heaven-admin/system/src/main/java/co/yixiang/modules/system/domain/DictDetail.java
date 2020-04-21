package co.yixiang.modules.system.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author Zheng Jie
* @date 2019-04-10
*/

@Getter
@Setter

public class DictDetail implements Serializable {

    private Long id;

    /** 字典标签 */
    private String label;

    /** 字典值 */
    private String value;

    /** 排序 */
    private String sort = "999";

    private Dict dict;

    private Timestamp createTime;

    public @interface Update {}
}