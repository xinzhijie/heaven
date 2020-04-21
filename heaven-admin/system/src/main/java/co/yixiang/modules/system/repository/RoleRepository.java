package co.yixiang.modules.system.repository;

import co.yixiang.modules.system.domain.Role;

import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-12-03
 */
@SuppressWarnings("all")
public interface RoleRepository {

    /**
     * 根据名称查询
     * @param name /
     * @return /
     */
    Role findByName(String name);

    /**
     * 根据用户ID查询
     * @param id 用户ID
     * @return
     */
    Set<Role> findByUsers_Id(Long id);

    /**
     * 解绑角色菜单
     * @param id 菜单ID
     */
    //@Query(value = "delete from roles_menus where menu_id = ?1",nativeQuery = true)
    void untiedMenu(Long id);

    /**
     * 根据角色权限查询
     * @param permission /
     * @return /
     */
    Role findByPermission(String permission);
}
