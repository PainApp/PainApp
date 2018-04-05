DROP DATABASE IF EXISTS pocket_doc ;
CREATE DATABASE pocket_doc;
USE pocket_doc;
DROP TABLE IF EXISTS body_regions;
DROP TABLE IF EXISTS specific_regions;
DROP TABLE IF EXISTS causes;

CREATE TABLE body_regions (id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(250) NOT NULL);

CREATE TABLE specific_regions (id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                               body_region_id INT UNSIGNED NOT NULL,
                               name VARCHAR(250) NOT NULL,
                               FOREIGN KEY (body_region_id) REFERENCES body_regions(id) ON DELETE CASCADE);

CREATE TABLE causes (id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     sbody_region_id INT UNSIGNED NOT NULL,
                     name VARCHAR(250) NOT NULL,
                     classification VARCHAR(50) NOT NULL,
                     FOREIGN KEY (sbody_region_id) REFERENCES specific_regions(id) ON DELETE CASCADE);

INSERT INTO body_regions (name)
VALUES ("Front Thighs"), 
       ("Front Right Upperarm"), 
       ("Front Right Shoulder"), 
       ("Front Right Hand"), 
       ("Front Right Forearm"), 
       ("Front Right Elbow"), 
       ("Front Lowerlegs"), 
       ("Front Left Upperarm"), 
       ("Front Left Shoulder"), 
       ("Front Left Hand"), 
       ("Front Left Forearm"), 
       ("Front Left Elbow"), 
       ("Front Knees"), 
       ("Front Hips"), 
       ("Front Head"), 
       ("Front Groin"), 
       ("Front Feet"), 
       ("Front Chest");
INSERT INTO specific_regions (body_region_id, name)
VALUES (3, "Anterior"), 
       (3, "Front"), 
       (3, "Local"), 
       (3, "Posterior"), 
       (3, "Top"), 

       (5, "Lateral"), 
       (5, "Medial"), 

       (6, "All"), 
       (6, "Anterior"), 
       (6, "Lateral"), 
       (6, "Local"), 
       (6, "Medial"), 
       (6, "Posterior"), 

       (9, "Anterior"), 
       (9, "Front"), 
       (9, "Local"), 
       (9, "Posterior"), 
       (9, "Top"),

       (11, "Lateral"), 
       (11, "Medial"), 

       (12, "All"), 
       (12, "Anterior"), 
       (12, "Lateral"), 
       (12, "Local"), 
       (12, "Medial"), 
       (12, "Posterior"), 

       (14, "Deep Hip"),
       (14, "Anterior"), 
       (14, "Anterolateral"), 
       (14, "Lateral"), 
       (14, "Medial/Groin"), 
       (14, "Posterior"), 

       (15, "Ear"), 
       (15, "Eye"), 
       (15, "Frontal"), 
       (15, "Jaw/Cheek"), 
       (15, "Neck"), 
       (15, "Throat"), 

       (17, "Arch"), 
       (17, "Lateral"), 
       (17, "Medial"), 
       (17, "Posterior"), 
       (17, "Sole"), 
       (17, "Toes");
INSERT INTO causes (sbody_region_id, name, classification)
VALUES (27, "Legg-Calves-Perthes", "extreme"), 
       (27, "Slipped Capital Femoral Epiphysis", "extreme"), 
       (27, "Avascular Necrosis of Femoral Head", "extreme"), 
       (27, "Osteoarthritis", "default"), 
       (27, "Adhesive Capsulitis", "default"), 
       (27, "Dysplasia", "default"), 
       (27, "Sacroiliac referred", "default"), 
       (27, "Synovitis", "default"), 

       (28, "Pasoas Impingement", "default"), 
       (28, "Psoas Abscess", "default"), 
       (28, "AIIS Avulsion Fracture", "children"), 
       (28, "ASIS Avulsion Fracture", "children"), 

       (29, "Subacute Strain at the Femoral Insertion of Anterior Hip Capsule", "default"), 

       (30, "OA", "default"), 
       (30, "Iliac Crest Contusion (Hip Pointer)", "default"), 
       (30, "Greater Trochanter Avulsion Fracture", "default"), 
       (30, "Lesser Trochanter Avulsion Fracture", "default"),

       (31, "Pubic Symphysis OA", "default"), 
       (31, "Adductor Enthesopathy", "default"), 
       (31, "Adductor Tendinitis" ,"default"), 
       (31, "Sports Hernia", "default"), 
       (31, "Incipient Hernia, Conjoint Tendon Tear", "default"), 
       (31, "Incipient Hernia, External Obliue Aponeurosis Tear", "default"), 
       (31, "Osteitis Pubis", "default"), 
       (31, "Athletic Osteitis Pubis", "default"), 
       (31, "Pubalgia", "default"), 
       (31, "Athletic Pubalgia", "default"), 
       (31, "Pelvic Instability", "default"), 
       (31, "Groin Disruption Injury", "default"), 
       (31, "Hip Antero-Superior Labral Tear with Avulsion of Rectus Femoris (HALTAR)", "default"), 
       (31, "Ramus Avulsion Fracture", "children"), 
       (31, "Funiculitis", "default"), 
       (31, "Orchitis", "default"), 
       (31, "Varicocele", "default"), 
       (31, "Hydrocele", "default"), 
       (31, "Urethritis", "default"), 
       (31, "Ovarian Cyst", "default"), 
       (31, "Endometriosis", "default"), 
       (31, "Round Ligament Entrapment", "default"), 
       (31, "Testicular/Ovarian Torsion", "extreme"), 
       (31, "Kidney Stone", "default"), 

       (32, "Gluetus Maxiumus Bursitis", "default"), 
       (32, "Sacral Stress FX", "default"), 
       (32, "Coccygeal FX", "default"), 
       (32, "Coccydynia", "default"), 
       (32, "Ischial Tuberosity Avulsion Fracture", "children");
