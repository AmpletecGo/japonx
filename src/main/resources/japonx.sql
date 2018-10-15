DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL comment '名称',
  `designation` varchar(32) NOT NULL comment '番号',
  `runtime` int(10) comment '时长',
  `directed` varchar(32) DEFAULT NULL comment '演员',
  `description` varchar(512) comment '简介',
  `studio` varchar(32) COMMENT '片商',
  `subtitle_url` varchar(512) comment '字幕地址',
  `video_url` varchar(512) NOT NULL comment '影片链接',
  `index_url` varchar(512) NOT NULL comment '封面图',
  `item_url` varchar(512) comment '预览图',
  `create_time` date not null comment '影片发布时间',
  `update_time` timestamp not null default current_timestamp comment '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='视频表';