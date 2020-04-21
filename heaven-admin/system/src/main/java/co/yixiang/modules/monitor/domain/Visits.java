package co.yixiang.modules.monitor.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * pv 与 ip 统计
 *
 * @author Zheng Jie
 * @date 2018-12-13
 */

@Data
public class Visits  implements Serializable {

    private Long id;

    private String date;

    private Long pvCounts;

    private Long ipCounts;

    private Timestamp createTime;

    private String weekDay;
}
