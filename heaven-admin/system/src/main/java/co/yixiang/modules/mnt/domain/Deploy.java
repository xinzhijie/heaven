package co.yixiang.modules.mnt.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
* @author zhanghouying
* @date 2019-08-24
*/

@Data

public class Deploy implements Serializable {

	/**
	 * 部署编号
	 */

    private Long id;

	/**
	 * 应用编号
	 */

    private App app;

	/**
	 * 服务器
	 */
private Set<ServerDeploy> deploys;


	private Timestamp createTime;

    public void copy(Deploy source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
