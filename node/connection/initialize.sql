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
       (10, 4, "Local"), 
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
       
       
       (10, "Compartment Syndrome", "extreme"),
       (10, "Fracture", "extreme"),
       
       (11, "Medial Epicondylosis-Golfer’s Elbow", "default"),
       (11, "Bursitis medial epicondyle", "default"),
       (11, "Medial Epicondylar Apophysitis/Little League Elbow", "default"),
       (11, "Medial (Radial) Collateral Ligament Sprain", "default"),
       (11, "Ulnar nerve dislocation", "default"),
       (11, "Heart Attack", "extreme"),
       
       (12, "BURSITIS triceps", "default"),
       (12, "Olecranon impingement", "default"),
       (12, "Posterior Elbow Impingement", "default"),
       (12, "Valgus Extension Overload", "default"),
       (12, "Triceps tendinosis", "default"),
       (12, "Posterolateral rotatory instability", "default"),
       (12, "Subluxating elbow", "default"),
       (12, "Snapping Triceps Syndrome", "default"),
       (12, "Gout", "default");
INSERT INTO causes (sbody_region_id, name, classification)
VALUES (13, "Subtalar OA", "default"),
       (13, "Lisfranc", "default"),
       (13, "Kohler disease / Avascular Necrosis Navicular", "default"),
       (13, "Inferior Extensor Retinaculum", "default"),
       (13, "Gruberi Bursa", "default"),
       (13, "Cuboid Syndrome", "default"),
       (13, "Talus/Talar Dome/Talar Neck fracture", "default"),
       (13, "Subtalar dislocation", "default"),
       (13, "Subcu bursa of calcaneal tendon", "default"),
       
       (14, "Plantar fasciitis", "default"),
       (14, "Sesamoiditis", "default"),
       (14, "Turf toe", "default"),
       (14, "Plantar plate rupture", "default"),
       
       
       (15, "Freiberg infraction", "default"),
       (15, "Plantar plate disruption at the second through fifth MTP joints", "default"),
       (15, "Ganglion", "default"),
       (15, "Morton’s Neuroma", "default"),
       (15, "Joplin’s Neuroma", "default"),
       (15, "First metatarsal bursa", "default"),
       (15, "Gout", "default"),
       (15, "Intermetatarsal bursa", "default"),
       (15, "Forefoot extensor bursa", "default"),
       
       (16, "ATFL Ligament", "default"),
       (16, "Sinus tarsi syndrome", "default"),
       (16, "Anterior tibiotalar impingement", "default"),
       (16, "Anterior tibiofular lig", "default"),
       (16, "lateral malleolus bursa", "default"),
       (16, "Inferior Peroneal Retinaculum", "default"),
       (16, "Anterior tibial fibular ligament", "default"),
       (16, "Maisonneuve fracture", "default"),
       (16, "High Ankle Sprain", "default"),
       (16, "Calcaneofibular ligament", "default"),
       (16, "Anteroinferior tibiofibular lig", "default"),
       
       (17, "medial malleolus bursa", "default"),
       (17, "Posterior tibial tendon dysfunction", "default"),
       (17, "Sustentaculum tali fracture", "default"),
       
       (18, "Haglunds triad", "default"),       
       (18, "Calcaneal stress fx", "default"),       
       (18, "Achilles enthesopathy", "default"),       
       (18, "Retro Achilles Bursa", "default"),       
       (18, "Achilles paratenonitis", "default"),       
       (18, "Calcaneal apophysisitis severs dx", "default"),
       (18, "Posterior Retinaculum", "default"),       
       (18, "Retrocalcaneal bursitis", "default"),       
       (18, "Subcu bursa of calcaneal tendon", "default");
       
       