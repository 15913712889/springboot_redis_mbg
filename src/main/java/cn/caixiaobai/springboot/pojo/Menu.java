package cn.caixiaobai.springboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author caixiaobai
 * @since 2020-11-24
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;

    /**
     * 菜单名
     */
    @TableField("menuName")
    private String menuName;

    /**
     * 访问路径
     */
    @TableField("accessPath")
    private String accessPath;

    /**
     * 菜单级别
     */
    private Integer level;

    /**
     * 创建时间
     */
    @TableField("creationTime")
    private Date creationTime;

    /**
     * 修改时间
     */
    @TableField("modificationTime")
    private Date modificationTime;

    /**
     * 是否删除,0表示已删除，1表示未删除
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Menu{" +
        "id=" + id +
        ", menuName=" + menuName +
        ", accessPath=" + accessPath +
        ", level=" + level +
        ", creationTime=" + creationTime +
        ", modificationTime=" + modificationTime +
        ", status=" + status +
        "}";
    }
}
