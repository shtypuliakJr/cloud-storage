--DROP TABLE device;
--DROP TABLE chunk;
--DROP TABLE file_object;
--DROP TABLE folder_object;
--DROP TABLE tag;
--DROP TABLE users;

CREATE TABLE users (
  id VARCHAR(255) NOT NULL,
  user_name VARCHAR(255) NOT NULL,
  user_email VARCHAR(255) NOT NULL,
  user_password VARCHAR(255) NOT NULL,
  last_login_at TIMESTAMP NULL,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE tag (
  id VARCHAR(255) NOT NULL,
  tag_name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  CONSTRAINT tag_pkey PRIMARY KEY (id),
  CONSTRAINT tag_user_fkey FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE device (
  id VARCHAR(255) NOT NULL,
  device_name VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  user_id VARCHAR(255) NULL,
  CONSTRAINT device_pkey PRIMARY KEY (id),
  CONSTRAINT device_user_fkey FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE folder_object (
	id VARCHAR(255) NOT NULL,
	folder_name VARCHAR(255) NOT NULL,
	folder_path VARCHAR(255) NULL,
	created_at TIMESTAMP NULL,
	updated_at TIMESTAMP NULL,
	user_id VARCHAR(255) NULL,
	tag_id VARCHAR(255) NULL,
	parent_folder_id VARCHAR(255) NULL,
	CONSTRAINT folder_object_pkey PRIMARY KEY (id),
	CONSTRAINT folder_parent_folder_fkey FOREIGN KEY (parent_folder_id) REFERENCES folder_object(id),
  CONSTRAINT folder_user_fkey FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT folder_tag_fkey FOREIGN KEY (tag_id) REFERENCES tag(id)
);

CREATE TABLE file_object (
	id VARCHAR(255) NOT NULL,
	file_name VARCHAR(255) NOT NULL,
  object_type VARCHAR(255) NOT NULL,
  file_path VARCHAR(255) NULL,
  s3_path VARCHAR(255) NOT NULL,
	created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  parent_folder_id VARCHAR(255) NULL,
  user_id VARCHAR(255) NULL,
  tag_id VARCHAR(255) NULL,
  CONSTRAINT file_object_pkey PRIMARY KEY (id),
  CONSTRAINT file_parent_folder_fkey FOREIGN KEY (parent_folder_id) REFERENCES folder_object(id),
  CONSTRAINT file_user_fkey FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT file_tag_fkey FOREIGN KEY (tag_id) REFERENCES tag(id)
);

CREATE TABLE chunk (
	id VARCHAR(255) NOT NULL,
	chunk_order BIGINT NOT NULL,
	chunk_size BIGINT NOT NULL,
  chunk_checksum BIGINT NOT NULL,
	s3_key VARCHAR(255) NOT NULL,
	created_at TIMESTAMP NULL,
	updated_at TIMESTAMP NULL,
	file_object_id VARCHAR(255) NULL,
	CONSTRAINT chunk_pkey PRIMARY KEY (id),
  CONSTRAINT chunk_file_object_fkey FOREIGN KEY (file_object_id) REFERENCES file_object(id)
);
