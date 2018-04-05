DROP DATABASE IF EXISTS pocketdoc ;
CREATE DATABASE pocketdoc;
USE pocketdoc;
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
INSERT INTO body_regions (name)
VALUES ("front_thighs"), ("front_right_upperarm"), ("front_right_shoulder"), ("front_right_hand"), ("front_right_forearm"), ("front_right_elbow"), ("front_lowerlegs"), ("front_left_upperarm"), ("front_left_shoulder"), ("front_left_hand"), ("front_left_forearm"), ("front_left_elbow"), ("front_knees"), ("front_hips"), ("front_head"), ("front_groin"), ("front_feet"), ("front_chest");
INSERT INTO specific_regions (body_region_id, name)
VALUES (14, "Deep Hip"), (14, "Anterolateral"), (14, "Posterior"), (14, "Anterior"), (14, "Medial/Groin"), (14, "Lateral"), (5, "Medial"), (11, "Medial"), (5, "Lateral"), (11, "Lateral"), (17, "Lateral"), (17, "Medial"), (17, "Arch"), (17, "Posterior"), (17, "Sole"), (17, "Toes"), (6, "Local"), (12, "Local"), (6, "Medial"), (12, "Medial"), (6, "Lateral"), (12, "Lateral"), (6, "Anterior"), (12, "Anterior"), (6, "Posterior"), (12, "Posterior"), (6, "All"), (12, "All"), (15, "Eye"), (15, "Neck"), (15, "Jaw/Cheek"), (15, "Ear"), (15, "Throat"), (15, "Frontal"), (3, "Local"), (9, "Local"), (3, "Anterior"), (9, "Anterior"), (3, "Posterior"), (9, "Posterior"), (3, "Front"), (9, "Front"), (3, "Top"), (9, "Top");
INSERT INTO causes (sbody_region_id, name, classification)
VALUES (1, "Legg-Calves-Perthes", "critical"), (1, "Slipped Capital Femoral Epiphysis", "critical"), (1, "Avascular Necrosis of Femoral Head", "critical"), (1, "Osteoarthritis", "none"), (1, "Adhesive Capsulitis", "none"), (1, "Dysplasia", "none"), (1, "Sacroiliac referred", "none"), (1, "Synovitis", "none"), (2, "Subacute strain at the femoral insertion of anterior hip capsule", "none"), (3, "Gluetus Maxiumus Bursitis", "none"), (3, "Sacral Stress FX", "none"), (3, "Sacral Stress FX", "none"), (3, "Coccygeal FX", "none"), (3, "Ischial Tuberosity Avulsion Fracture", "pediatric"), (4, "Pasoas Impingement", "none"), (4, "Psoas Abscess", "none"), (4, "AIIS Avulsion Fracture", "pediatric"), (4, "ASIS Avulsion Fracture", "pediatric"), (5, "Pubic Symphysis OA", "none"), (5, "Adductor Enthesopathy", "none"), (5, "Adductor Tendinitis" ,"none"), (5, "Sports Hernia", "none"), (5, "Incipient hernia, conjoint tendon tear", "none"), (5, "Incipient hernia, external obliue aponeurosis tear", "none"), (5, "Osteitis pubis", "none"), (5, "Athletic osteitis pubis", "none"), (5, "Pubalgia", "none"), (5, "Athletic pubalgia", "none"), (5, "Pelvic instability", "none"), (5, "Groin disruption injury", "none"), (5, "Hip Antero-superior Labral Tear with Avulsion of Rectus femoris (HALTAR)", "none"), (5, "Ramus Avulsion Fracture", "pediatric"), (5, "Funiculitis", "none"), (5, "Orchitis", "none"), (5, "Varicocele", "none"), (5, "Hydrocele", "none"), (5, "Urethritis", "none"), (5, "Ovarian Cyst", "none"), (5, "Endometriosis", "none"), (5, "Round Ligament Entrapment", "none"), (5, "Testicular/Ovarian Torsion", "critical"), (5, "Kidney Stone", "none"), (6, "OA", "none"), (6, "Iliac Crest Contusion (Hip Pointer)", "none"), (6, "Greater Trochanter Avulsion Fracture", "none"), (6, "Lesser Trochanter Avulsion Fracture", "none");


