ALTER TABLE staff ADD status INT DEFAULT 0 NULL COMMENT '状态 0有效 1无效';

ALTER TABLE supermarket.goods MODIFY COLUMN gds_status int DEFAULT '1' NULL COMMENT '状态(1正常/0停产)';
