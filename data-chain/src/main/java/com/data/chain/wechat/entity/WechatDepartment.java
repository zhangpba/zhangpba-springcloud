package com.data.chain.wechat.entity;

public class WechatDepartment{
        private Integer id;
        private String name;
        private String name_en;
        private String department_leader;
        private String parentid;
        private String order;
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getDepartment_leader() {
            return department_leader;
        }

        public void setDepartment_leader(String department_leader) {
            this.department_leader = department_leader;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }