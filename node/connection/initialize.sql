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
       (7, "Front Shoulder"), 
       (8, "Back Shoulder"), 
       (9, "Front Abdomen"),
       (10, "Back Abdomen"),  
       (11, "Front Forearm"), 
       (12, "Back Forearm"),
       (13, "Front Hand"), 
       (14, "Back Hand"), 
       (15, "Front Head"),
       (16, "Back Head"),
       (17, "Front Chest"),
       (18, "Back Chest"),
       (19, "Front Thighs"), 
       (20, "Back Thighs"), 
       (21, "Front Groin"), 
       (22, "Back Groin"), 
       (23, "Front Lowerlegs"), 
       (24, "Back Lowerlegs"), 
       (25, "Front Knees"), 
       (26, "Back Knees"), 
       (27, "Front Hips");
       (28, "Back Hips"), 
       (29, "Front Upperarm"), 
       (30, "Back Upperarm");
       
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
       (10, 4, "Medial"), 
       (11, 4, "Posterior"), 
       
       (12, 5, "Arch"), 
       (13, 5, "Sole"), 
       (14, 5, "Toes"), 
       (15, 6, "Lateral"), 
       (16, 6, "Medial"),  
       (17, 6, "Posterior"),
       
       (18, 7, "All"),
       (19, 7, "Front"),
       (20, 7, "Top"),
       (21, 8, "Anterior"),
       (22, 8, "Posterior"),
       
       (23, 9, "Top Left"),
       (24, 9, "Middle Left"),
       (25, 9, "Bottom Left"),
       (26, 9, "Top Middle"),
       (27, 9, "Middle Middle"),
       (28, 9, "Bottom middle"),
       (29, 9, "Top Right"),
       (30, 9, "Middle Right"),
       (31, 9, "Bottom Right"),
       
       (32, 11, "Lateral"),
       (33, 11, "Medial"),
       (34, 12, "Posterior"),
       (35, 12, "Anterior"),
       
       (36, 13, "Fingers"),
       (37, 14, "Fingers"),
       
       (38, 15, "Facial/Frontal"),
       (39, 15, "Temporal"),
       (40, 15, "Parietal"),
       (41, 15, "Neck"),
       (42, 16, "Occipital"),
       (43, 16, "Cervical Spine");
       


/* Hip */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 
       /* Deep Hip */
       (1, "Legg-Calves-Perthes", "extreme"), 
       (1, "Slipped Capital Femoral Epiphysis", "extreme"), 
       (1, "Avascular Necrosis of Femoral Head", "extreme"), 
       (1, "Osteoarthritis", "default"), 
       (1, "Adhesive Capsulitis", "default"), 
       (1, "Dysplasia", "default"), 
       (1, "Sacroiliac referred", "default"), 
       (1, "Synovitis", "default"), 

       /* Anterior */
       (2, "Psoas Impingement", "default"), 
       (2, "Psoas Abscess", "default"), 
       (2, "AIIS Avulsion Fracture", "children"), 
       (2, "ASIS Avulsion Fracture", "children"), 

       /* Medial/Groin */
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
       
       /* Anterolateral */
       (4, "Subacute Strain at the Femoral Insertion of Anterior Hip Capsule", "default"), 
       
       /* Lateral */
       (5, "OA", "default"), 
       (5, "Iliac Crest Contusion (Hip Pointer)", "default"), 
       (5, "Greater Trochanter Avulsion Fracture", "default"), 
       (5, "Lesser Trochanter Avulsion Fracture", "default"),
       
       /* Posterior */
       (6, "Gluetus Maxiumus Bursitis", "default"), 
       (6, "Sacral Stress FX", "default"), 
       (6, "Coccygeal FX", "default"), 
       (6, "Coccydynia", "default"), 
       (6, "Ischial Tuberosity Avulsion Fracture", "children");
     
/* Elbow */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 

       /* All */
       (7, "Interosseous BURSITIS of elbow", "default"),
       (7, "Interosseous membrane sprain", "default"),
       (7, "Compartment Syndrome", "extreme"),
       (7, "Fracture", "extreme"),
        
       /* Lateral */  
       (8, "Lateral Epincondylosis", "default"),
       (8, "Anterolateral capsule ganglion", "default"),
       (8, "Nursemaid’s Elbow", "children"),
       (8, "Bursitis of lateral epicondyle", "default"),
       (8, "Elbow Plica", "default"),
       (8, "The synovial fold of the humeroradial joint", "default"),
       (8, "Osteochondrosis of the capitellum", "default"),
       (8, "Lateral (Ulnar) Collateral Ligament Sprain", "default"),  
       (8, "Essex-Lopresti Injury", "default"),
       
       /* Anterior */
       (9, "Bicipitioradial Bursitis", "default"),
       (9, "Biceps tendinosis", "default"),
       (9, "Anterior capsule strain", "default"),
       (9, "Brachialis tendonitis", "default"),
       
       /* Medial */
       (10, "Medial Epicondylosis-Golfer’s Elbow", "default"),
       (10, "Bursitis medial epicondyle", "default"),
       (10, "Medial Epicondylar Apophysitis/Little League Elbow", "default"),
       (10, "Medial (Radial) Collateral Ligament Sprain", "default"),
       (10, "Ulnar nerve dislocation", "default"),
       (10, "Heart Attack", "extreme"),
       (10, "Cubital Tunnel Syndrome", "extreme"),
       
       /* Posterior */
       (11, "BURSITIS triceps", "default"),
       (11, "Olecranon impingement", "default"),
       (11, "Posterior Elbow Impingement", "default"),
       (11, "Valgus Extension Overload", "default"),
       (11, "Triceps tendinosis", "default"),
       (11, "Posterolateral rotatory instability", "default"),
       (11, "Subluxating elbow", "default"),
       (11, "Snapping Triceps Syndrome", "default"),
       (11, "Gout", "default");
 
/* Feet */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 
       /* Arch */
       (12, "Subtalar OA", "default"),
       (12, "Lisfranc", "default"),
       (12, "Kohler disease / Avascular Necrosis Navicular", "default"),
       (12, "Inferior Extensor Retinaculum", "default"),
       (12, "Gruberi Bursa", "default"),
       (12, "Cuboid Syndrome", "default"),
       (12, "Talus/Talar Dome/Talar Neck fracture", "default"),
       (12, "Subtalar dislocation", "default"),
       (12, "Subcu bursa of calcaneal tendon", "default"),
       
       /* Sole */
       (13, "Plantar fasciitis", "default"),
       (13, "Sesamoiditis", "default"),
       (13, "Turf toe", "default"),
       (13, "Plantar plate rupture", "default"),
       
       /* Toes */
       (14, "Freiberg infraction", "default"),
       (14, "Plantar plate disruption at the second through fifth MTP joints", "default"),
       (14, "Ganglion", "default"),
       (14, "Morton’s Neuroma", "default"),
       (14, "Joplin’s Neuroma", "default"),
       (14, "First metatarsal bursa", "default"),
       (14, "Gout", "default"),
       (14, "Intermetatarsal bursa", "default"),
       (14, "Forefoot extensor bursa", "default"),
       
       /* Lateral */
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
       
       /* Medial */
       (16, "medial malleolus bursa", "default"),
       (16, "Posterior tibial tendon dysfunction", "default"),
       (16, "Sustentaculum tali fracture", "default"),
       
       /* Posterior */
       (17, "Haglunds triad", "default"),       
       (17, "Calcaneal stress fx", "default"),       
       (17, "Achilles enthesopathy", "default"),       
       (17, "Retro Achilles Bursa", "default"),       
       (17, "Achilles paratenonitis", "default"),       
       (17, "Calcaneal apophysisitis severs dx", "default"),
       (17, "Posterior Retinaculum", "default"),       
       (17, "Retrocalcaneal bursitis", "default"),       
       (17, "Subcu bursa of calcaneal tendon", "default");

/* Shoulder */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 
       /* All */
       (18, "Heart Attack", "extreme"),
       (18, "Facet Pain", "default"),
       (18, "Coracobrachial bursa", "default"),
       (18, "Bursa of latissimus dorsi", "default"),
       (18, "Bursa of greater pec muscle", "default"),
       (18, "Trapezoid bursa", "default"),
       (18, "Subacromial bursa", "default"),
       (18, "Subdeltoid bursa", "default"),
       (18, "Coracobrachial bursa", "default"),
       (18, "Supraserratus bursa", "default"),
       (18, "Infraserratus bursa", "default"),
       (18, "Infraserratus bursa", "default"),
       (18, "Subserratus bursa", "default"),
       (18, "Acromioclavicular Ganglion", "default"),
       (18, "Acromioclavicular subluxation", "default"),
       (18, "Spinal Accessory Nerve", "default"),
       (18, "Bankart", "default"),
       (18, "Hill-Sachs", "default"),
       (18, "Sick Scapula", "default"),
       (18, "Long thoracic nerve", "default"),
       (18, "Shoulder Osteoarthrirtis", "default"),
       (18, "Axillary Nerve Compression", "default"),
       (18, "Supraclavicular nerve compression", "default"),
       
       /* Front */
       (19, "Biceps subluxation", "default"),
       (19, "Transverse humeral ligament sprain/rupture", "default"),
       (19, "Acromioclavicular joint sprain", "default"),
       
       /* Top */
       (20, "subacromial bursa", "default"),
       (20, "Acromial apophysiolysis", "default"),
       
       /* Anterior */
       (21, "Subcoracoid bursa", "default"),
       (21, "HAGL Lesion", "default"),
       (21, "Perthes lesion", "default"),
       (21, "ALPSA", "default"),
       
       /* Posterior */
       (22, "Labrum (SLAP) lesion", "default"),
       (22, "Posterior glenoid impingement", "default"),
       (22, "Shoulder capsule", "default"),
       (22, "Adhesive capsulitis", "default"),
       (22, "Internal Impingement", "default"),
       (22, "Supracapular nerve entrapment", "default"),
       (22, "Quadrilateral Space Syndrome", "default"),
       (22, "", "default");

/* Abdomen */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 

       /* Top Left */
       (23, "Biliary Tract Disorder", "default"),
       (23, "Hepatitis", "default"),
       (23, "Fitz-Hughes- Curtis", "default"),
       (23, "RUQ Syndrome", "default"),
       (23, "Budd-Chiari Syndrome", "default"),
       (23, "Basal Pneumonia", "default"),
       (23, "Retrocecal Appendicitis", "extreme"),
       
       /* Middle Left */
       (24, "Kidney Stones", "default"),
       (24, "Kidney Infection", "default"),
       
       /* Bottom Left */
       (25, "Appendicitis", "extreme"),
       (25, "Crohn’s Disease", "default"),
       (25, "Ovarian Cyst", "female"),
       (25, "Ovarian Torsion", "extreme"),
       (25, "Ovarian Remnant Syndrome", "female"),
       (25, "Pregnancy", "female"),
       (25, "Ectopic Pregnancy (Ruptured)", "female"),
       (25, "Pelvic Inflammatory Disease", "female"),
       (25, "Tubo-ovarian abscess", "female"),
       (25, "Mittelschmerz", "female"),
       (25, "Pelvic Congestion Syndrome", "female"),
       
       /* Top Middle */
       (26, "Ulcer Disease", "default"),
       (26, "Pancreatitis", "default"),
       (26, "Myocardial Ischemia", "extreme"),
       (26, "Boerhaave Syndrome", "default"),
       
       /* Middle Middle */
       (27, "Pancreatitis", "default"),
       (27, "Myocardial Ischemia", "extreme"),
       (27, "Early Appendicitis", "extreme"),
       (27, "Small Bowel Obstruction", "extreme"),
       (27, "Abdominal Aortic Aneurysm", "default"),
       
       /* Bottom Middle */
       (28, "Urinary Retention", "default"),
       (28, "Pregnancy", "default"),
       (28, "Cystitis", "default"),
       (28, "Fibroid", "female"),
       (28, "Adenomyosis", "female"),
       (28, "Allen-Masters Syndrome", "female"),
       
       /* Top Right */
       (29, "Splenic Disorder", "default"),
       (29, "Basal Pneumonia", "default"),
       
       /* Middle Right */
       (30, "Kidney Stones", "default"),
       (30, "Kidney Infection", "default"),
       (30, "Nutcracker Syndrome", "default"),
       
       /* Bottom Right */
       (31, "Diverticulitis/Diverticulosis", "default"),
       (31, "Ovarian Cyst", "female"),
       (31, "Ovarian Torsion", "extreme"),
       (31, "Ovarian Remnant Syndrome", "female"),
       (31, "Pregnancy", "female"),
       (31, "Ectopic Pregnancy", "default"),
       (31, "Pelvic Inflammatory Disease", "female"),
       (31, "Tubo-ovarian abscess", "female"),
       (31, "Mittelschmerz", "female"),
       (31, "Pelvic Congestion Syndrome", "female");

/* Forearm */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 
       /* Lateral */
       (32, "Avascular Necrosis of Scaphoid", "default"),
       (32, "Gymnast's Wrist / Distal Radial Physeal Stress Syndrome", "default"),
       (32, "Posterior Interosseous Nerve Syndrome", "default"),
       (32, "Carpal Tunnel Syndrome", "default"),
       (32, "Wartenberg syndrome", "default"),
       (32, "Radial Bursitis", "default"),
       (32, "Aseptic necrosis capitate", "default"),
       (32, "Tunnel of Guyon Syndrome", "default"),
       (32, "Intersection Syndrome", "default"),
       (32, "Dequervains Tenosynovitis", "default"),
       (32, "Median neuropathy", "default"),
       (32, "Fracture", "default"),
       (32, "Osteoarthritis", "default"),
       (32, "Rheumatoid Arthritis", "default"),
       (32, "Essex-Lopresti Injury", "default"),
       (32, "Essex-Lopresti Injury", "default"),
       (32, "Gamekeepers thumb", "default"),
       
       /* Medial */
       (33, "Snapping Extensor Carpi Ulnaris", "default"),
       (33, "Triangular Fibrocartilage (TFC) Injury", "default"),
       (33, "Ulnar styloid impaction syndrome", "default"),
       (33, "Ulnar impingement syndrome", "default"),
       (33, "Hamatolunate impingement syndrome", "default"),
       (33, "Ulnocarpal impaction syndrome", "default"),
       (33, "Ulnar bursitis", "default"),
       (33, "Kienboch disease", "default"),
       (33, "Ulnar impaction syndrome", "default"),
       (33, "Hypothenar Hammer Syndrome", "default"),
       (33, "Pisotriquetral arthritis", "default"),
       (33, "Lunate dislocation", "default"),
       (33, "Lunotriquetral injury", "default"),
       (33, "Cubital Tunnel Syndrome", "default"),
       (33, "Ulnar Bursitis", "default"),
       (33, "Fracture", "default"),
       (33, "Osteoarthritis", "default"),
       (33, "Rheumatoid Arthritis", "default"),
       
       /* Posterior */
       (34, "Carpal Instability", "default"),
       (34, "Extensor indicis proprius syndrome", "default"),
       (34, "Carpal boss", "default"),
       (34, "Wartenberg syndrome", "default"),
       (34, "Aseptic necrosis capitate", "default"),
       (34, "Distal Intersection Syndrome", "default"),
       (34, "Distal Radioulnar joint injury", "default"),
       (34, "Ganglion/Growths", "default"),
       (34, "Compartment Syndrome", "extreme"),
       (34, "Fracture", "default"),
       (34, "Osteoarthritis", "default"),
       (34, "Rheumatoid Arthritis", "default"),
       
       /* Anterior */
       (35, "Carpal Instability", "default"),
       (35, "Aseptic necrosis capitate", "default"),
       (35, "Pronator Syndrome", "default"),
       (35, "Ganglion/Growths", "default"),
       (35, "Compartment Syndrome", "extreme"),
       (35, "Fracture", "default"),
       (35, "Osteoarthritis", "default"),
       (35, "Rheumatoid Arthritis", "default");
       
/* Hand */       
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 
       /* Front Fingers */
       (36, "Gamekeepers thumb", "default"),
       (36, "Tunnel of Guyon Syndrome", "default"),
       (36, "Ulnar bursitis", "default"),
       
       /* Back Fingers */
       (37, "Tunnel of Guyon Syndrome", "default"),
       (37, "Gamekeepers thumb", "default"),
       (37, "Ulnar bursitis", "default");
       
/* Head */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES 
       /* Facial/Frontal */
       (38, "Buccinator Trigger Point", "default"),
       (38, "C1 radiculopathy", "default"),
       (38, "C3 radiculopathy", "default"),
       (38, "Cavernous sinus syndrome", "default"),
       (38, "Charlin Syndrome", "default"),
       (38, "Cluster headache", "default"),
       (38, "Cricoarytenoid arthritis", "default"),
       (38, "Digastric Muscle Trigger Point", "default"),
       (38, "Ernest Syndrome", "default"),
       (38, "External nasal neuralgia", "default"),
       (38, "Frontalis Trigger Point", "default"),
       (38, "Geniculate Neuralgia", "default"),
       (38, "Glossopharyngeal neuralgia", "default"),
       (38, "Levator Scapulae Trigger Point", "default"),
       (38, "Masseter Trigger Point", "default"),
       (38, "Medial Pterygoid Trigger", "default"),
       (38, "Middle cranial fossa syndrome", "default"),
       (38, "Middle turbinate squeeze syndrome", "default"),
       (38, "Migraine Headache", "default"),
       (38, "Nasociliaris Nerve Syndrome", "default"),
       (38, "Occipitalis Trigger Point", "default"),
       (38, "Orbital apex syndrome / Superior Orbital Fissure Syndrome", "default"),
       (38, "Orbital syndrome", "default"),
       (38, "Parasellar syndrome", "default"),
       (38, "Petrous apex syndrome", "default"),
       (38, "Platysma Trigger Point", "default"),
       (38, "Semispinalis Capitis Trigger Point", "default"),
       (38, "Sinus of Morgani Syndrome", "default"),
       (38, "Sphenoid sinus syndrome", "default"),
       (38, "Splenius Cervicis Trigger Point", "default"),
       (38, "Sternocleidomastoid Trigger Point", "default"),
       (38, "Suboccipital Trigger Point", "default"),
       (38, "Supraorbital neuralgia", "default"),
       (38, "Temporal arteritis", "extreme"),
       (38, "Temporalis Trigger Point", "default"),
       (38, "Tensor Veli Palatine Syndrome", "default"),
       (38, "Tolosa Hunt Syndrome", "default"),
       (38, "Trapezius Trigger Point", "default"),
       (38, "Zygomaticus Trigger Point", "default"),
       
       
       /* Temporal */
       (39, "Alderman's neuralgia", "default"),
       (39, "Atlantooccipital Arthropathy", "default"),
       (39, "C2 radiculopathy", "default"),
       (39, "C3 radiculopathy", "default"),
       (39, "Cricoarytenoid arthritis", "default"),
       (39, "Digastric Trigger Point", "default"),
       (39, "Eagle's syndrome", "default"),
       (39, "Ernest Syndrome", "default"),
       (39, "Greater Occipital and Lesser Occipital Nerve Neuralgia", "default"),
       (39, "Masseter myotendonitis", "default"),
       (39, "Migraine Headache", "default"),
       (39, "Myringitis", "default"),
       (39, "Occipitalis Trigger Point", "default"),
       (39, "Ramsey hunt Syndrome", "default"),
       (39, "Semispinalis Capitis Trigger Point", "default"),
       (39, "sinus syndrome pain", "default"),
       (39, "Splenius Capitis Trigger Point", "default"),
       (39, "Sternocleidomastoid Trigger Point", "default"),
       (39, "Suboccipital Trigger Point", "default"),
       (39, "Temporal arteritis", "default"),
       (39, "Temporal Tendinitis", "default"),
       (39, "Temporomandibular Joint Dysfunction", "default"),
       (39, "Tension Headache", "default"),
       (39, "Tonic tensor tympani syndrome", "default"),
       (39, "Trapezius Trigger Point", "default"),
       
       /* Parietal */
       (40, "Arnold - Chiari", "default"),
       (40, "C2 radiculopathy", "default"),
       (40, "Clivus syndrome", "default"),
       (40, "Frontalis Trigger Point", "default"),
       (40, "Greater Occipital and Lesser Occipital Nerve Neuralgia", "default"),
       (40, "Semispinalis Capitis Trigger Point", "default"),
       (40, "Splenius Capitis Trigger Point", "default"),
       (40, "Splenius Cervicis Trigger Point", "default"),
       (40, "Sternocleidomastoid Trigger Point", "default"),
       (40, "Suboccipital Trigger Point", "default"),
       (40, "Tension Headache", "default"),
       (40, "Trapezius Trigger Point", "default"),
       
       /* Neck */
       (41, "Acute calcific retropharyngeal tendinitis", "default"),  
       (41, "Cardiac Ischemia", "extreme"),  
       (41, "Carotid/Vertebral Artery Dissection", "extreme"),  
       (41, "Carotydynia", "default"),  
       (41, "Cervical Discopathy", "default"),  
       (41, "C3 radiculopathy", "default"),  
       (41, "C4 Radiculopathy", "default"),  
       (41, "Crowded dens", "default"),  
       (41, "C4-C5, C5-C6, C6-C7 Facet Joint Arthropathy", "default"),  
       (41, "Eagle's syndrome", "default"),  
       (41, "Foramen magnum syndrome", "default"),  
       (41, "Glossopharyngeal neuralgia", "default"),  
       (41, "Grisel syndrome", "default"),  
       (41, "Hamular bursitis", "default"),  
       (41, "Lateral Pterygoid Trigger Point", "default"),  
       (41, "Mumps", "extreme"),  
       (41, "Neck tongue Syndrome", "default"),  
       (41, "Occipitoatlas Joint Arthropathy", "default"),  
       (41, "Omohyoideus pain syndrome", "default"),  
       (41, "Ponticulus Posticus", "default"),  
       (41, "Ramsay Hunt Syndrome", "default"),  
       (41, "Retropharyngeal abscess", "default"),  
       (41, "Sialadenitis", "default"),  
       (41, "Splenius capitis trigger point", "default"),  
       (41, "Sternocleidomastoid trigger point", "default"),  
       (41, "Supraglottits / Epiglottitis", "extreme"),  
       (41, "Temporomandibular Joint Dysfunction", "default"),  
       (41, "Tensor Veli Palatine Syndrome", "default"),  
       (41, "Thyrohyoid syndrome/Hyoid syndrome", "default"),  
       (41, "Trapezius Trigger Point", "default"),  
       
       /* Occipital */
       (42, "Arnold-Chiari Malformation", "default"),
       (42, "Atlanto-occipital Arthropathy", "default"),
       (42, "C3 radiculopathy", "default"),
       (42, "C4 radiculopathy", "default"),
       (42, "Crowded dens", "default"),
       (42, "Digastric Trigger Point", "default"),
       (42, "Foramen magnum syndrome", "default"),
       (42, "Greater Occipital and Lesser Occipital Nerve Neuralgia", "default"),
       (42, "Migraine Headache", "default"),
       (42, "Neck tongue Syndrome", "default"),
       (42, "Occipital condyle syndrome", "default"),
       (42, "Occipital neuralgia", "default"),
       (42, "Occipitalis Trigger Point", "default"),
       (42, "Semispinalis Capitis Trigger Point", "default"),
       (42, "Sternocleidomastoid Trigger Point", "default"),
       (42, "Suboccipital Trigger Point", "default"),
       (42, "Tension Headache", "default");

/* Chest */
INSERT INTO causes (sbody_region_id, name, classification)
VALUES

       (42, "Suboccipital Trigger Point", "default"),