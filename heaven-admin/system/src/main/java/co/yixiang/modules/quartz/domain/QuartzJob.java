package co.yixiang.modules.quartz.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Getter
@Setter

public class QuartzJob  implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";


    private Long id;

    /** 定时器名称 */

    private String jobName;



    private String beanName;

    /** 方法名称 */

    private String methodName;

    /** 参数 */

    private String params;

    /** cron表达式 */

    private String cronExpression;

    /** 状态 */
    private Boolean isPause = false;

    /** 备注 */
    private String remark;

    private Timestamp createTime;

    public @interface Update {}
}