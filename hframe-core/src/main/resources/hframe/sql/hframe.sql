create table hfcfg_program_skin(
   hfcfg_program_skin_id bigint(20) primary key auto_increment comment '项目皮肤ID',
   program_skin_name varchar(64) comment '项目皮肤名称',
   program_skin_code varchar(64) comment '项目皮肤编码',
   snapshot_url varchar(128) comment '快照URL',
   program_template_id bigint(20) comment '项目模板ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目皮肤';


create table hfpm_page_event(
   hfpm_page_event_id bigint(12) primary key auto_increment not null comment '页面事件id',
   hfpm_page_event_name varchar(128) not null comment '页面事件名称',
   hfpm_event_name varchar(32) comment '事件名称',
   hfpm_page_event_code varchar(64) not null comment '页面事件编码',
   hfpm_page_id bigint(20) not null comment '页面ID',
   hfpm_event_monitor_object varchar(64) comment '事件监听对象',
   hfpm_event_monitor_object_type varchar(64) comment '事件监听对象类型',
   hfpm_page_component_id bigint(20) comment '页面组件ID',
   hfpm_event_type int(2) comment '事件类型',
   event_source tinyint comment '事件来源',
   hfpm_event_effect_object varchar(64) comment '事件作用对象',
   effect_field bigint(20) not null comment '作用域',
   from varchar(200) comment '条件',
   op_id bigint(20) comment '创建人',
   to varchar(200) comment '结果',
   modify_op_id bigint(20) comment '修改人',
   hfpm_page_event_type tinyint(4) comment '页面事件类型',
   target_hfpm_page_id bigint(20) comment '目标页面',
   del_flag int(2) comment '删除标识',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '页面事件';


create table hfpm_program_cfg(
   hfpm_program_cfg_id bigint(20) primary key auto_increment not null comment '项目配置ID',
   show_name varchar(128) comment '项目标题',
   hfcfg_program_template_id bigint(20) comment '项目模板ID',
   hfcfg_program_skin_id bigint(20) comment '项目皮肤ID',
   hfcfg_login_page_id bigint(20) comment '登陆页面ID',
   bg_img_url varchar(128) comment '背景图片URL',
   hfpm_program_id bigint(20) comment '项目ID',
   hfcfg_db_connect_id bigint(12) not null comment '数据库连接信息id',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目设置';


create table hfcfg_page_template_elements(
   hfcfg_page_template_elements_id bigint(12) primary key auto_increment not null comment '页面模板元素id',
   hfcfg_page_template_id bigint(20) comment '页面模板ID',
   hfcfg_page_template_elements_type tinyint(4) comment '页面模板元素类型',
   event_extend tinyint(4) comment '事件继承',
   hfcfg_component_template_id bigint(12) not null comment '组件模板id',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '页面模板元素';


create table hfcfg_component_template(
   hfcfg_component_template_id bigint(12) primary key auto_increment not null comment '组件模板id',
   hfcfg_component_template_code varchar(64) not null comment '组件模板编码',
   hfcfg_component_template_name varchar(128) not null comment '组件模板名称',
   hfcfg_component_template_type tinyint(2) comment '组件模板类型',
   hfcfg_component_template_desc varchar(128) comment '组件模板描述',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(12) comment '修改人',
   modify_time datetime comment '修改时间') comment '组件模板';


create table hfsec_menu(
   hfsec_menu_id bigint(20) primary key auto_increment comment '菜单ID',
   hfsec_menu_code varchar(64) comment '菜单编码',
   hfsec_menu_name varchar(128) comment '菜单名称',
   hfsec_menu_desc varchar(128) comment '菜单描述',
   menu_level int(2) comment '菜单级别',
   icon varchar(64) not null comment '图标',
   url varchar(128) not null comment '地址',
   parent_hfsec_menu_id int(2) comment '父级菜单ID',
   modify_time datetime not null comment '修改时间',
   del_flag int(2) comment '删除标识') comment '菜单';


create table hfpm_page_event_attr(
   hfpm_page_event_attr_id bigint(20) primary key auto_increment comment '页面事件属性ID',
   hfpm_page_event_id bigint(20) comment '页面事件ID',
   hfpm_page_event_attr_type int(2) comment '页面事件属性类型        ',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   value_type int(2) comment '值类型',
   value varchar(128) comment '值',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面事件属性';


create table hfcfg_page_template(
   hfcfg_page_template_id bigint(20) primary key auto_increment comment '页面模板ID',
   hfcfg_page_template_type int(2) comment '页面模板类型',
   hfcfg_page_template_name varchar(64) comment '页面模板名称',
   hfcfg_page_template_code varchar(128) comment '页面模板编码',
   hfcfg_page_template_desc varchar(128) comment '页面模板描述',
   snapshot_url varchar(128) comment '快照URL',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面模板';


create table hfpm_data_field(
   hfpm_data_field_id bigint(20) primary key auto_increment comment '数据列ID',
   hfpm_data_field_code varchar(64) comment '数据列编码',
   hfpm_field_show_type_id varchar(32) comment '列展示类型ID',
   field_show_code varchar(6) comment '列展示码            ',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   data_get_method int(2) comment '数据获取方式          ',
   hfpm_data_field_name varchar(64) comment '数据列名称',
   hfpm_data_set_id bigint(20) comment '数据集ID',
   pri numeric(6,2) comment '优先级',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '数据列';


create table hfsec_user(
   hfsec_user_id bigint(20) primary key auto_increment comment '用户ID',
   hfsec_user_name varchar(64) comment '用户名称',
   account varchar(64) comment '用户账号',
   password varchar(128) comment '用户密码',
   gender int(2) comment '性别',
   mobile varchar(6) comment '手机号',
   email int(2) comment '邮箱',
   addr int(2) comment '地址',
   last_login_time datetime comment '上次登录时间',
   status int(2) comment '状态',
   hfuc_org_id bigint(20) comment '归属组织ID',
   creator_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modifier_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '用户';


create table hfpm_test(
   hfpm_test_id bigint(12) primary key auto_increment not null comment 'hfpm_testid',
   hfpm_test_code varchar(64) not null comment 'hfpm_test编码',
   hfpm_program_cfg_id bigint(20) comment '项目配置ID',
   hfpm_test_name varchar(128) not null comment 'hfpm_test名称',
   hfcfg_program_template_id bigint(20) comment '项目模板ID') comment 'hfpm_test';


create table hfpm_module(
   hfpm_module_id bigint(20) primary key auto_increment comment '模块ID',
   hfpm_module_name varchar(64) comment '模块名称',
   hfpm_module_code varchar(64) comment '模块编码',
   hfpm_module_desc varchar(128) comment '模块描述',
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '模块';


create table hfcfg_program_template(
   hfcfg_program_template_id bigint(20) primary key auto_increment comment '项目模板ID',
   program_template_name varchar(64) comment '项目模板名称',
   program_template_code varchar(64) comment '项目模板编码',
   program_template_desc varchar(128) comment '项目模板描述',
   snapshot_url varchar(128) comment '快照URL',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目模板';


create table hfus_entity_attr(
   hfus_entity_attr_id bigint(20) primary key auto_increment comment '常用实体属性ID',
   hfus_entity_attr_name varchar(64) comment '实体属性名称',
   hfus_entity_attr_code varchar(64) comment '实体属性编码',
   hfus_entity_attr_desc varchar(128) comment '实体描述',
   attr_type int(2) comment '属性类型',
   size varchar(6) comment '大小',
   ispk int(2) comment '是否主键',
   nullable int(2) comment '是否可为空',
   is_busi_attr int(2) comment '是否业务属性',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '常用实体属性';


create table hfmd_entity(
   hfmd_entity_id bigint(20) primary key auto_increment comment '实体ID',
   hfmd_entity_name varchar(64) not null comment '实体名称',
   hfmd_entity_code varchar(64) not null comment '实体编码',
   hfmd_entity_type int(2) comment '实体类型            ',
   hfmd_entity_desc varchar(124) comment '实体描述',
   hfpm_program_id bigint(20) comment '项目ID',
   hfpm_module_id bigint(20) comment '模块ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体';


create table hfpm_page(
   hfpm_page_id bigint(20) primary key auto_increment not null comment '页面ID',
   hfpm_page_code varchar(64) comment '页面编码',
   hfpm_page_name varchar(128) comment '页面名称',
   hfpm_page_type int(2) comment '页面类型',
   hfpm_data_set_id bigint(20) comment '数据集ID',
   hfpm_page_desc varchar(128) comment '页面描述',
   pri numeric(6,2) comment '优先级',
   modify_time datetime comment '修改时间',
   modify_op_id bigint(20) comment '修改人',
   parent_hfpm_page_id bigint(20) comment '父页面ID',
   hfpm_program_id bigint(20) comment '项目ID',
   hfpm_module_id bigint(20) comment '模块ID',
   del_flag int(2) comment '删除标识',
   hfcfg_page_template_id bigint(20) comment '页面模板ID') comment '页面';


create table hfpm_page_component(
   hfpm_page_component_id bigint(20) primary key auto_increment comment '页面组件ID',
   hfpm_page_component_name varchar(64) comment '页面组件名称',
   hfpm_page_component_type int(2) comment '页面组件类型',
   hfpm_page_id bigint(20) not null comment '页面ID',
   hfpm_data_set_id bigint(20) comment '数据集ID',
   hfcfg_component_template_id bigint(12) not null comment '组件模板id',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面组件';


create table hfpm_data_set(
   hfpm_data_set_id bigint(20) primary key auto_increment comment '数据集ID',
   hfpm_data_set_name varchar(64) comment '数据集名称',
   hfpm_data_set_code varchar(64) comment '数据集编码',
   main_hfmd_entity_id bigint(20) comment '主实体ID',
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '数据集';


create table hfpm_program(
   hfpm_program_id bigint(20) primary key auto_increment comment '项目ID',
   hfpm_program_name varchar(64) comment '项目名称',
   hfpm_program_code varchar(64) comment '项目编码',
   hfpm_program_desc varchar(512) comment '项目描述',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目';


create table hfmd_entity_join_rule(
   hfmd_entity_join_rule_id bigint(20) primary key auto_increment not null comment '实体连带id',
   source_hfmd_entity_id bigint(20) comment '源实体id',
   source_hfmd_entity_attr_id bigint(20) comment '源实体属性id',
   source_hfmd_entity_attr_value varchar(64) comment '源实体属性值',
   join_type tinyint(2) comment '连带类型',
   target_hfmd_entity_id bigint(20) comment '目标实体id',
   target_hfmd_entity_attr_id bigint(20) comment '目标实体属性id',
   target_hfmd_entity_attr_value varchar(64) comment '目标实体属性值',
   editable tinyint(2) comment '是否可编辑') comment '实体连带关系';


create table hfpm_page_entity_rel(
   hfpm_page_entity_rel_id bigint(20) primary key auto_increment comment '页面关联实体ID',
   hfpm_page_id bigint(20) comment '页面ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   is_main_entity int(2) comment '是否为主实体',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '页面关联实体';


create table hfmd_entity_attr(
   hfmd_entity_attr_id bigint(20) primary key auto_increment comment '实体属性ID',
   hfmd_entity_attr_name varchar(64) not null comment '实体属性名称',
   hfmd_entity_attr_code varchar(64) not null comment '实体属性编码',
   hfmd_entity_attr_desc varchar(128) comment '实体属性描述',
   attr_type int(2) not null comment '属性类型',
   size varchar(6) comment '大小',
   ispk int(2) comment '是否主键',
   nullable int(2) not null comment '是否为空',
   is_busi_attr int(2) comment '是否业务属性',
   is_redundant_attr int(2) comment '是否冗余属性',
   rel_hfmd_entity_attr_id bigint(20) comment '关联属性ID',
   hfmd_enum_class_id bigint(20) comment '枚举类ID',
   pri numeric(2,2) comment '优先级',
   hfpm_program_id bigint(20) comment '项目ID',
   hfpm_module_id bigint(20) comment '模块ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体属性';


create table hfpm_page_cfg(
   hfpm_page_cfg_id bigint(20) primary key auto_increment comment '页面设置ID',
   hfcfg_page_template_id bigint(20) comment '页面模板ID',
   hfpm_program_cfg_id bigint(20) comment '项目配置ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目页面设置';


create table hfpm_field_show_type(
   hfpm_field_show_type_id bigint(20) primary key auto_increment comment '展示类型ID',
   hfpm_field_show_type_code varchar(32) comment '展示类型编码',
   hfpm_field_show_type_name varchar(32) comment '展示类型名称',
   pre_str varchar(256) comment '前缀',
   after_str varchar(256) comment '后缀',
   col_span int(2) comment '列数',
   row_span int(2) comment '行数',
   width int(11) comment '宽度',
   height int(11) comment '高度',
   param1 varchar(128) comment '参数1',
   param2 varchar(128) comment '参数2',
   param3 varchar(128) comment '参数3',
   param4 varchar(128) comment '参数4',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '展示类型';


create table hfus_page_event(
   hfus_page_event_id bigint(20) primary key auto_increment comment '常用页面事件ID',
   hfpm_event_name varchar(32) comment '事件名称',
   hfpm_event_type int(2) comment '事件类型',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '常用页面事件';


create table hfus_program_entity_attr(
   hfus_program_entity_attr_id bigint(20) primary key auto_increment comment '项目常用实体ID',
   hfpm_program_id bigint(20) comment '项目ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '项目常用实体属性';


create table hfus_word_store(
   hfus_word_store_id bigint(20) primary key auto_increment comment '单词库ID',
   chinese_chars varchar(64) comment '汉字名称',
   english_name varchar(64) comment '英语名称',
   english_short_name varchar(64) comment '英文简称',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '单词库';


create table hfmd_enum(
   hfmd_enum_id bigint(20) primary key auto_increment not null comment '枚举ID',
   hfmd_enum_value varchar(32) comment '枚举值',
   hfmd_enum_text varchar(32) comment '枚举文本',
   hfmd_enum_desc varchar(128) comment '枚举描述',
   is_default int(2) comment '是否默认',
   pri numeric(2,2) comment '优先级',
   ext1 varchar(128) comment '扩展字段1',
   ext2 varchar(128) comment '扩展字段2',
   hfmd_enum_class_id bigint(20) comment '枚举类目ID',
   hfmd_enum_class_code varchar(32),
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) not null comment '删除标识') comment '枚举';


create table hfmd_entity_rel(
   hfmd_entity_rel_id bigint(20) primary key auto_increment comment '实体关系ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_rel_type int(2) comment '实体关联类型          ',
   hfmd_entity_rel_level int(2) comment '实体关联级别          ',
   hfmd_entity_rel_desc varchar(128) comment '实体关联描述',
   rel_hfmd_entity_id bigint(20) comment '关联实体ID',
   rel_entity_attr_id bigint(20),
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体关系';


create table hfcfg_db_connect(
   hfcfg_db_connect_id bigint(12) primary key auto_increment not null comment '数据库连接信息id',
   hfcfg_db_connect_code varchar(64) not null comment '数据库连接信息编码',
   hfcfg_db_connect_name varchar(128) not null comment '数据库连接信息名称',
   url varchar(256) not null comment 'URL',
   user varchar(64) not null comment '用户',
   password varchar(64) not null comment '密码',
   creator_id bigint(12) comment '创建人',
   create_time datetime comment '创建时间') comment '数据库连接信息';


create table hfmd_enum_class(
   hfmd_enum_class_id bigint(20) primary key auto_increment comment '枚举类目ID',
   hfmd_enum_class_name varchar(32) comment '枚举类目名称',
   hfmd_enum_class_code varchar(64) comment '枚举类目编码',
   hfmd_enum_class_desc varchar(128) comment '枚举类目描述',
   ext1 varchar(128) comment '扩展字段1',
   ext2 varchar(128) comment '扩展字段2',
   hfpm_program_id bigint(20) comment '项目ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '枚举类目';


create table hfcfg_login_page(
   hfcfg_login_page_id bigint(20) primary key auto_increment comment '登陆页面ID',
   hfcfg_login_page_name varchar(64) comment '登陆页面名称',
   hfcfg_login_page_code varchar(64) comment '登陆页面编码',
   snapshot_url varchar(128) comment '页面快照URL',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '登陆页面';


create table hfpm_entity_permission(
   hfpm_entity_permission_id bigint(20) primary key auto_increment comment '页面事件属性ID',
   hfmd_entity_id bigint(20) comment '实体ID',
   hfmd_entity_attr_id bigint(20) comment '实体属性ID',
   value_type int(2) comment '值类型',
   value varchar(128) comment '值',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体权限';


create table hfpm_entity_bind_rule(
   hfpm_entity_bind_rule_id bigint(20) primary key auto_increment comment '实体捆绑规则ID',
   bind_type int(2) comment '捆绑类型',
   src_hfmd_entity_id bigint(20) comment '源实体ID',
   src_hfmd_entity_attr_id bigint(20) comment '源实体属性ID',
   dest_hfmd_entity_id bigint(20) comment '目标实体ID',
   dest_hfmd_entity_attr_id bigint(20) comment '目标实体属性ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体捆绑规则';


create table hfus_entity_type_relat_entity_attr(
   hfus_entity_type_relat_entity_attr_id bigint(20) primary key auto_increment comment '关系ID',
   entity_type int(2) comment '实体类型',
   hfus_entity_attr_id bigint(20) comment '常用实体属性ID',
   op_id bigint(20) comment '创建人',
   create_time datetime comment '创建时间',
   modify_op_id bigint(20) comment '修改人',
   modify_time datetime comment '修改时间',
   del_flag int(2) comment '删除标识') comment '实体类型关联实体属性';
