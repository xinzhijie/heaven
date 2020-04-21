package co.yixiang.modules.mnt.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author zhanghouying
* @date 2019-08-24
*/

@Data

public class Database implements Serializable {

	/**
	 * id
	 */

    private String id;

	/**
	 * 数据库名称
	 */

    private String name;

	/**
	 * 数据库连接地址
	 */

    private String jdbcUrl;

	/**
	 * 数据库密码
	 */

    private String pwd;

	/**
	 * 用户名
	 */

    private String userName;


	private Timestamp createTime;

    public void copy(Database source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
