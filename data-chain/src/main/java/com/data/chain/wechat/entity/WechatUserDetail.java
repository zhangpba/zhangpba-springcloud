package com.data.chain.wechat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取访问用户身份
 * 网页登录
 *
 * @author: wx
 * @date: 2022/2/23
 */
@NoArgsConstructor
@Data
public class WechatUserDetail {
    private Integer errcode;
    private String errmsg;
    private String userid;
    private String name;
    private List<String> department;
    private String position;
    private Integer status;
    private Integer isleader;
    private Extattr extattr;
    private String telephone;
    private Integer enable;
    private Integer hide_mobile;
    private List<Integer> order;
    private Integer main_department;
    private String alias;
    private List<Integer> is_leader_in_dept;
    private List<String> direct_leader;
    private List<WechatDepartment> depts;
    private String mobile;
    private String avatar;
    private String thumb_avatar;

    public class Extattr{
        private List<String> attrs;

        public List<String> getAttrs() {
            return attrs;
        }

        public void setAttrs(List<String> attrs) {
            this.attrs = attrs;
        }
    }

}
