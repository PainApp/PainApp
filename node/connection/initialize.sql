DROP DATABASE IF EXISTS pocket_doc ;
CREATE DATABASE pocket_doc;
USE pocket_doc;
DROP TABLE IF EXISTS body_regions;
DROP TABLE IF EXISTS specific_regions;
DROP TABLE IF EXISTS causes;

CREATE TABLE body_regions (id INT UNSIGNED NOT NULL PRIMARY KEY,
                           name VARCHAR(250) NOT NULL);

CREATE TABLE specific_regions (id INT UNSIGNED NOT NULL PRIMARY KEY,
                               body_region_id INT UNSIGNED NOT NULL,
                               name VARCHAR(250) NOT NULL,
                               FOREIGN KEY (body_region_id) REFERENCES body_regions(id) ON DELETE CASCADE);

CREATE TABLE causes (id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
                     sbody_region_id INT UNSIGNED NOT NULL,
                     name VARCHAR(250) NOT NULL,
                     classification VARCHAR(50) NOT NULL,
                     FOREIGN KEY (sbody_region_id) REFERENCES specific_regions(id) ON DELETE CASCADE);

INSERT INTO body_regions (id, name)
VALUES (1, "Front Hips"), 
       (2, "Back Hips"),
       (3, "Front Elbow"), 
       (4, "Back Elbow"),
       (5, "Front Feet"), 
       (6, "Back Feet"), 
       (7, "Front Hand"), 
       (8, "Back Hand"),  
       (9, "Front Upperarm"), 
       (10, "Back Upperarm"),  
       (11, "Front Forearm"), 
       (12, "Back Forearm"),
       (13, "Front Lowerlegs"), 
       (14, "Back Lowerlegs"), 
       (15, "Front Knees"), 
       (16, "Back Knees"), 
       (17, "Front Hips"), 
       (18, "Back Hips"), 
       (19, "Front Thighs"), 
       (20, "Back Thighs"), 
       (21, "Front Groin"), 
       (22, "Back Groin"),
       (23, "Front Shoulder"), 
       (24, "Back Shoulder"),  
       (25, "Front Chest"),
       (26, "Back Chest");
INSERT INTO specific_regions (id, body_region_id, name)
VALUES (1, 1, "Deep Hip"), 
       (2, 1, "Anterior"), 
       (3, 1, "Medial/Groin"), 
       (4, 2, "Anterolateral"), 
       (5, 2, "Lateral"), 
       (6, 2, "Posterior"), 
       
       (7, 3, "All"), 
       (8, 3, "Lateral"), 
       (9, 4, "Anterior"),
       (11, 4, "Medial"), 
       (12, 4, "Posterior"), 
       
       (13, 5, "Arch"), 
       (14, 5, "Sole"), 
       (15, 5, "Toes"), 
       (16, 6, "Lateral"), 
       (17, 6, "Medial"),  
       (18, 6, "Posterior"); 
       
       /*(7, 3, "All"), --front
       (8, 3, "Medial"),
       (9, 4, "Lateral"); --back
       
       
       (3, "Anterior"), 
       (3, "Front"), 
       (3, "Local"), 
       (3, "Posterior"), 
       (3, "Top"), 

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

       (15, "Ear"), 
       (15, "Eye"), 
       (15, "Frontal"), 
       (15, "Jaw/Cheek"), 
       (15, "Neck"), 
       (15, "Throat"), 
*/
INSERT INTO causes (sbody_region_id, name, classification)
VALUES (1, "Legg-Calves-Perthes", "extreme"), 
       (1, "Slipped Capital Femoral Epiphysis", "extreme"), 
       (1, "Avascular Necrosis of Femoral Head", "extreme"), 
       (1, "Osteoarthritis", "default"), 
       (1, "Adhesive Capsulitis", "default"), 
       (1, "Dysplasia", "default"), 
       (1, "Sacroiliac referred", "default"), 
       (1, "Synovitis", "default"), 

       (2, "Psoas Impingement", "default"), 
       (2, "Psoas Abscess", "default"), 
       (2, "AIIS Avulsion Fracture", "children"), 
       (2, "ASIS Avulsion Fracture", "children"), 

       (3, "Pubic Symphysis OA", "default"), 
       (3, "Adductor Enthesopathy", "default"), 
       (3, "Adductor Tendinitis" ,"default"), 
       (3, "Sports Hernia", "default"), 
       (3, "Incipient Hernia, Conjoint Tendon Tear", "default"), 
       (3, "Incipient Hernia, External Obliue Aponeurosis Tear", "default"), 
       (3, "Osteitis Pubis", "default"), 
       (3, "Athletic Osteitis Pubis", "default"), 
       (3, "Pubalgia", "default"), 
       (3, "Athletic Pubalgia", "default"), 
       (3, "Pelvic Instability", "default"), 
       (3, "Groin Disruption Injury", "default"), 
       (3, "Hip Antero-Superior Labral Tear with Avulsion of Rectus Femoris (HALTAR)", "default"), 
       (3, "Ramus Avulsion Fracture", "children"), 
       (3, "Funiculitis", "default"), 
       (3, "Orchitis", "default"), 
       (3, "Varicocele", "default"), 
       (3, "Hydrocele", "default"), 
       (3, "Urethritis", "default"), 
       (3, "Ovarian Cyst", "default"), 
       (3, "Endometriosis", "default"), 
       (3, "Round Ligament Entrapment", "default"), 
       (3, "Testicular/Ovarian Torsion", "extreme"), 
       (3, "Kidney Stone", "default"), 
       
       (4, "Subacute Strain at the Femoral Insertion of Anterior Hip Capsule", "default"), 

       (5, "OA", "default"), 
       (5, "Iliac Crest Contusion (Hip Pointer)", "default"), 
       (5, "Greater Trochanter Avulsion Fracture", "default"), 
       (5, "Lesser Trochanter Avulsion Fracture", "default"),

       (6, "Gluetus Maxiumus Bursitis", "default"), 
       (6, "Sacral Stress FX", "default"), 
       (6, "Coccygeal FX", "default"), 
       (6, "Coccydynia", "default"), 
       (6, "Ischial Tuberosity Avulsion Fracture", "children");
       
INSERT INTO causes (sbody_region_id, name, classification)
VALUES (7, "Interosseous BURSITIS of elbow", "default"),
       (7, "Interosseous membrane sprain", "default"),
       (7, "Compartment Syndrome", "extreme"),
       (7, "Fracture", "extreme"),
              
       (8, "Lateral Epincondylosis", "default"),
       (8, "Anterolateral capsule ganglion", "default"),
       (8, "Nursemaid’s Elbow", "children"),
       (8, "Bursitis of lateral epicondyle", "default"),
       (8, "Elbow Plica", "default"),
       (8, "The synovial fold of the humeroradial joint", "default"),
       (8, "Osteochondrosis of the capitellum", "default"),
       (8, "Lateral (Ulnar) Collateral Ligament Sprain", "default"),
       
       (9, "Bicipitioradial Bursitis", "default"),
       (9, "Biceps tendinosis", "default"),
       (9, "Anterior capsule strain", "default"),
       (9, "Brachialis tendonitis", "default"),
       
       (10, "Medial Epicondylosis-Golfer’s Elbow", "default"),
       (10, "Bursitis medial epicondyle", "default"),
       (10, "Medial Epicondylar Apophysitis/Little League Elbow", "default"),
       (10, "Medial (Radial) Collateral Ligament Sprain", "default"),
       (10, "Ulnar nerve dislocation", "default"),
       (10, "Heart Attack", "extreme"),
       
       (11, "BURSITIS triceps", "default"),
       (11, "Olecranon impingement", "default"),
       (11, "Posterior Elbow Impingement", "default"),
       (11, "Valgus Extension Overload", "default"),
       (11, "Triceps tendinosis", "default"),
       (11, "Posterolateral rotatory instability", "default"),
       (11, "Subluxating elbow", "default"),
       (11, "Snapping Triceps Syndrome", "default"),
       (11, "Gout", "default");
INSERT INTO causes (sbody_region_id, name, classification)
VALUES (12, "Subtalar OA", "default"),
       (12, "Lisfranc", "default"),
       (12, "Kohler disease / Avascular Necrosis Navicular", "default"),
       (12, "Inferior Extensor Retinaculum", "default"),
       (12, "Gruberi Bursa", "default"),
       (12, "Cuboid Syndrome", "default"),
       (12, "Talus/Talar Dome/Talar Neck fracture", "default"),
       (12, "Subtalar dislocation", "default"),
       (12, "Subcu bursa of calcaneal tendon", "default"),
       
       (13, "Plantar fasciitis", "default"),
       (13, "Sesamoiditis", "default"),
       (13, "Turf toe", "default"),
       (13, "Plantar plate rupture", "default"),
       
       (14, "Freiberg infraction", "default"),
       (14, "Plantar plate disruption at the second through fifth MTP joints", "default"),
       (14, "Ganglion", "default"),
       (14, "Morton’s Neuroma", "default"),
       (14, "Joplin’s Neuroma", "default"),
       (14, "First metatarsal bursa", "default"),
       (14, "Gout", "default"),
       (14, "Intermetatarsal bursa", "default"),
       (14, "Forefoot extensor bursa", "default"),
       
       (15, "ATFL Ligament", "default"),
       (15, "Sinus tarsi syndrome", "default"),
       (15, "Anterior tibiotalar impingement", "default"),
       (15, "Anterior tibiofular lig", "default"),
       (15, "lateral malleolus bursa", "default"),
       (15, "Inferior Peroneal Retinaculum", "default"),
       (15, "Anterior tibial fibular ligament", "default"),
       (15, "Maisonneuve fracture", "default"),
       (15, "High Ankle Sprain", "default"),
       (15, "Calcaneofibular ligament", "default"),
       (15, "Anteroinferior tibiofibular lig", "default"),
       
       (16, "medial malleolus bursa", "default"),
       (16, "Posterior tibial tendon dysfunction", "default"),
       (16, "Sustentaculum tali fracture", "default"),
       
       (17, "Haglunds triad", "default"),       
       (17, "Calcaneal stress fx", "default"),       
       (17, "Achilles enthesopathy", "default"),       
       (17, "Retro Achilles Bursa", "default"),       
       (17, "Achilles paratenonitis", "default"),       
       (17, "Calcaneal apophysisitis severs dx", "default"),
       (17, "Posterior Retinaculum", "default"),       
       (17, "Retrocalcaneal bursitis", "default"),       
       (17, "Subcu bursa of calcaneal tendon", "default");
       
       