package co.yixiang.modules.system.repository;

import co.yixiang.modules.system.domain.User;

import java.util.Date;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
public interface UserRepository  {

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return /
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查询
     * @param email 邮箱
     * @return /
     */
    User findByEmail(String email);

    /**
     * 修改密码
     * @param username 用户名
     * @param pass 密码
     * @param lastPasswordResetTime /
     */

    //@Query(value = "update user set password = ?2 , last_password_reset_time = ?3 where username = ?1",nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 修改邮箱
     * @param username 用户名
     * @param email 邮箱
     */

    //@Query(value = "update user set email = ?2 where username = ?1",nativeQuery = true)
    void updateEmail(String username, String email);
}
