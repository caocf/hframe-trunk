create table hfsec_menu(
   hfsec_menu_id bigint(20) primary key auto_increment comment '菜单ID',
   hfsec_menu_code varchar(64) comment '菜单编码',
   hfsec_menu_name varchar(128) comment '菜单名称',
   hfsec_menu_desc varchar(128) comment '菜单描述',
   menu_level int(2) comment '菜单级别',
   icon varchar(64) not null comment '图标',
   url varchar(128) not null comment '地址',
   parent_hfsec_menu_id bigint(20) not null comment '父级菜单ID',
   pri numeric(4,2) comment '优先级',
   creator_id bigint comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint comment '修改人',
   modify_time datetime not null comment '修改时间',
   del_flag int(2) comment '删除标识') comment '菜单';

alter table hfsec_menu comment '菜单';


create table hfsec_user(
   hfsec_user_id bigint primary key auto_increment comment '用户ID',
   hfsec_user_name varchar(64) comment '用户名称',
   account varchar(64) comment '用户账号',
   password varchar(128) comment '用户密码',
   gender int(2) comment '性别',
   mobile varchar(6) comment '手机号',
   email int(2) comment '邮箱',
   addr int(2) comment '地址',
   avatar varchar(512) comment '头像',
   last_login_time datetime comment '上次登录时间',
   status int(2) comment '状态',
   hfuc_org_id bigint comment '归属组织ID',
   creator_id bigint comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '用户';

alter table hfsec_user comment '用户';


create table hfmd_enum_class(
   hfmd_enum_class_id bigint(20) primary key auto_increment comment '字典ID',
   hfmd_enum_class_name varchar(32) comment '字典名称',
   hfmd_enum_class_code varchar(64) comment '字典编码',
   hfmd_enum_class_desc varchar(128) comment '字典描述',
   ext1 varchar(128) comment '扩展字段1',
   ext2 varchar(128) comment '扩展字段2',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '字典';

alter table hfmd_enum_class comment '字典';

create table hfmd_enum(
   hfmd_enum_id bigint(20) primary key auto_increment comment '字典项ID',
   hfmd_enum_value varchar(32) comment '字典项值',
   hfmd_enum_text varchar(32) comment '字典项文本',
   hfmd_enum_desc varchar(128) comment '字典项描述',
   is_default int(2) comment '是否默认',
   pri numeric(4,2) comment '优先级',
   ext1 varchar(128) comment '扩展字段1',
   ext2 varchar(128) comment '扩展字段2',
   hfmd_enum_class_id bigint(20) comment '字典ID',
   hfmd_enum_class_code varchar(32),
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) not null comment '删除标识') comment '字典项';

alter table hfmd_enum comment '字典项';