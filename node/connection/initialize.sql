DROP TABLE IF EXISTS body_regions;
DROP TABLE IF EXISTS specific_regions;
DROP TABLE IF EXISTS causes;

CREATE TABLE body_regions  (id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(250) NOT NULL);
CREATE TABLE specific_regions  (id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                body_region_id INT UNSIGNED NOT NULL,
                                name VARCHAR(250) NOT NULL,
                                FOREIGN KEY (body_region_id) REFERENCES body_regions(id) ON DELETE CASCADE);
CREATE TABLE causes    (id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        sbody_region_id INT UNSIGNED NOT NULL,
                        name VARCHAR(250) NOT NULL,
                        classification VARCHAR(50) NOT NULL,
                        FOREIGN KEY (sbody_region_id) REFERENCES specific_regions(id) ON DELETE CASCADE);


