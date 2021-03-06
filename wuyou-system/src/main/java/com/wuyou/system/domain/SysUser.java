package com.wuyou.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.wuyou.common.annotation.Excel;
import com.wuyou.common.annotation.Excel.ColumnType;
import com.wuyou.common.annotation.Excel.Type;
import com.wuyou.common.annotation.Excels;
import com.wuyou.common.core.domain.BaseEntity;
import com.wuyou.system.domain.validation.SaveUserGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author wuyou
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 用户ID
   */
  @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
  @TableId
  private Long userId;
  /**
   * 部门ID
   */
  @Excel(name = "部门编号", type = Type.IMPORT)
  private Long deptId;
  /**
   * 部门父ID
   */
  @TableField(exist = false)
  private Long parentId;
  /**
   * 角色ID
   */
  @TableField(exist = false)
  private Long roleId;
  /**
   * 登录名称
   */
  @Excel(name = "登录名称")
  @Size(max = 30, message = "登录账号长度不能超过30个字符")
  @NotBlank(message = "登录账号不能为空", groups = SaveUserGroup.class)
  private String loginName;
  /**
   * 用户名称
   */
  @Excel(name = "用户名称")
  @Size(max = 30, message = "用户昵称长度不能超过30个字符")
  private String userName;
  /**
   * 用户类型
   */
  private String userType;
  /**
   * 用户邮箱
   */
  @Excel(name = "用户邮箱")
  @Email(message = "邮箱格式不正确")
  @Size(max = 50, message = "邮箱长度不能超过50个字符")
  private String email;
  /**
   * 手机号码
   */
  @Excel(name = "手机号码")
  @Size(max = 11, message = "手机号码长度不能超过11个字符")
  private String phonenumber;
  /**
   * 用户性别
   */
  @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
  private String sex;
  /**
   * 用户头像
   */
  private String avatar;
  /**
   * 密码
   */
  private String password;
  /**
   * 盐加密
   */
  private String salt;
  /**
   * 帐号状态（0正常 1停用）
   */
  @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
  private Integer status;
  /**
   * 删除标志（0代表存在 2代表删除）
   */
  @TableLogic
  private Boolean delFlag;
  /**
   * 最后登录IP
   */
  @Excel(name = "最后登录IP", type = Type.EXPORT)
  private String loginIp;
  /**
   * 最后登录时间
   */
  @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
  private Date loginDate;
  /**
   * 部门对象
   */
  @Excels({
    @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
    @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
  })
  @TableField(exist = false)
  private SysDept dept;
  /**
   * 角色
   */
  @TableField(exist = false)
  private List<SysRole> roles;
  /**
   * 角色组
   */
  @TableField(exist = false)
  private Long[] roleIds;
  /**
   * 岗位组
   */
  @TableField(exist = false)
  private Long[] postIds;

  public SysUser() {
  }

  public SysUser(Long userId) {
    this.userId = userId;
  }

  public SysDept getDept() {
    if (dept == null) {
      dept = new SysDept();
    }
    return dept;
  }

  public boolean isAdmin() {
    return isAdmin(this.userId);
  }

  public static boolean isAdmin(Long userId) {
    return userId != null && 1L == userId;
  }
}
