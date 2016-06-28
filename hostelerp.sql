/*
SQLyog Ultimate v8.55 
MySQL - 5.5.5-10.1.9-MariaDB : Database - hostelerp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hostelerp` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hostelerp`;

/*Table structure for table `block` */

DROP TABLE IF EXISTS `block`;

CREATE TABLE `block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostelid` int(100) NOT NULL DEFAULT '0',
  `blockname` varchar(100) NOT NULL,
  `nooffloor` int(11) DEFAULT NULL,
  `status` enum('active','deactive') NOT NULL DEFAULT 'active',
  `createdat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hostelid`,`blockname`),
  UNIQUE KEY `NewIndex1` (`id`),
  KEY `NewIndex2` (`hostelid`,`blockname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `block` */

insert  into `block`(`id`,`hostelid`,`blockname`,`nooffloor`,`status`,`createdat`) values (6,19,'AB',4,'active','2016-06-25 16:13:07'),(7,19,'ABC',3,'active','2016-06-25 16:13:20'),(4,20,'A',4,'active','2016-06-25 16:12:08'),(5,20,'B',2,'active','2016-06-25 16:12:25'),(8,22,'STEP',4,'active','2016-06-28 17:52:20');

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `status` enum('active','deactive') DEFAULT 'active',
  PRIMARY KEY (`name`),
  UNIQUE KEY `NewIndex1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1218 DEFAULT CHARSET=latin1;

/*Data for the table `city` */

insert  into `city`(`id`,`name`,`status`) values (1217,'','active'),(293,'Achalpur','active'),(1094,'Achhnera','active'),(1203,'Adalaj','active'),(278,'Adilabad','active'),(192,'Adityapur','active'),(202,'Adoni','active'),(929,'Adoor','active'),(1200,'Adra','active'),(1211,'Adyar','active'),(977,'Afzalpur','active'),(97,'Agartala','active'),(23,'Agra','active'),(4,'Ahmedabad','active'),(106,'Ahmednagar','active'),(122,'Aizawl','active'),(74,'Ajmer','active'),(92,'Akola','active'),(355,'Akot','active'),(193,'Alappuzha','active'),(49,'Aligarh','active'),(485,'AlipurdUrban Agglomerationr','active'),(486,'Alirajpur','active'),(37,'Allahabad','active'),(112,'Alwar','active'),(585,'Amalapuram','active'),(345,'Amalner','active'),(424,'Ambejogai','active'),(286,'Ambikapur','active'),(62,'Amravati','active'),(313,'Amreli','active'),(35,'Amritsar','active'),(46,'Amroha','active'),(382,'Anakapalle','active'),(173,'Anand','active'),(136,'Anantapur','active'),(305,'Anantnag','active'),(553,'Anjangaon','active'),(379,'Anjar','active'),(425,'Ankleshwar','active'),(409,'Arakkonam','active'),(477,'Arambagh','active'),(405,'Araria','active'),(137,'Arrah','active'),(603,'Arsikere','active'),(374,'Aruppukkottai','active'),(698,'Arvi','active'),(598,'Arwal','active'),(72,'Asansol','active'),(1214,'Asarganj','active'),(395,'Ashok Nagar','active'),(665,'Athni','active'),(767,'Attingal','active'),(328,'Aurangabad','active'),(297,'Azamgarh','active'),(291,'Bagaha','active'),(1209,'Bageshwar','active'),(194,'Bahadurgarh','active'),(175,'Baharampur','active'),(179,'Bahraich','active'),(388,'Balaghat','active'),(340,'Balangir','active'),(275,'Baleshwar Town','active'),(96,'Ballari','active'),(225,'Balurghat','active'),(247,'Bankura','active'),(446,'Bapatla','active'),(201,'Baramula','active'),(474,'Barbil','active'),(398,'Bargarh','active'),(1205,'Barh','active'),(300,'Baripada Town','active'),(176,'Barmer','active'),(280,'Barnala','active'),(700,'Barpeta','active'),(214,'Batala','active'),(126,'Bathinda','active'),(142,'Begusarai','active'),(83,'Belagavi','active'),(574,'Bellampalle','active'),(1191,'Belonia','active'),(3,'Bengaluru','active'),(253,'Bettiah','active'),(613,'BhabUrban Agglomeration','active'),(617,'Bhadrachalam','active'),(308,'Bhadrak','active'),(98,'Bhagalpur','active'),(624,'Bhainsa','active'),(141,'Bharatpur','active'),(198,'Bharuch','active'),(543,'Bhatapara','active'),(70,'Bhavnagar','active'),(457,'Bhawanipatna','active'),(593,'Bheemunipatnam','active'),(66,'Bhilai Nagar','active'),(102,'Bhilwara','active'),(243,'Bhimavaram','active'),(59,'Bhiwandi','active'),(170,'Bhiwani','active'),(584,'Bhongir','active'),(17,'Bhopal','active'),(54,'Bhubaneswar','active'),(234,'Bhuj','active'),(63,'Bikaner','active'),(109,'Bilaspur','active'),(550,'Bobbili','active'),(412,'Bodhan','active'),(94,'Bokaro Steel City','active'),(465,'Bongaigaon City','active'),(103,'Brahmapur','active'),(320,'Buxar','active'),(635,'Byasanagar','active'),(454,'Chaibasa','active'),(627,'Chalakudy','active'),(288,'Chandausi','active'),(44,'Chandigarh','active'),(646,'Changanassery','active'),(618,'Charkhi Dadri','active'),(619,'Chatra','active'),(6,'Chennai','active'),(666,'Cherthala','active'),(1206,'Chhapra','active'),(273,'Chikkamagaluru','active'),(327,'Chilakaluripet','active'),(378,'Chirala','active'),(670,'Chirkunda','active'),(455,'Chirmiri','active'),(219,'Chittoor','active'),(867,'Chittur-Thathamangalam','active'),(38,'Coimbatore','active'),(67,'Cuttack','active'),(681,'Dalli-Rajhara','active'),(120,'Darbhanga','active'),(272,'Darjiling','active'),(90,'Davanagere','active'),(295,'Deesa','active'),(71,'Dehradun','active'),(249,'Dehri-on-Sone','active'),(2,'Delhi','active'),(166,'Deoghar','active'),(394,'Dhamtari','active'),(33,'Dhanbad','active'),(898,'Dharmanagar','active'),(269,'Dharmavaram','active'),(462,'Dhenkanal','active'),(386,'Dhoraji','active'),(492,'Dhubri','active'),(100,'Dhule','active'),(566,'Dhuri','active'),(245,'Dibrugarh','active'),(265,'Dimapur','active'),(507,'Diphu','active'),(648,'Dumka','active'),(578,'Dumraon','active'),(131,'Durg','active'),(161,'Eluru','active'),(160,'English Bazar','active'),(213,'Erode','active'),(140,'Etawah','active'),(26,'Faridabad','active'),(384,'Faridkot','active'),(668,'Farooqnagar','active'),(447,'Fatehabad','active'),(848,'Fatehpur Sikri','active'),(413,'Fazilka','active'),(20,'Firozabad','active'),(299,'Firozpur','active'),(586,'Firozpur Cantt.','active'),(611,'Forbesganj','active'),(542,'Gadwal','active'),(556,'Gangarampur','active'),(226,'Ganjbasoda','active'),(88,'Gaya','active'),(287,'Giridih','active'),(581,'Goalpara','active'),(523,'Gobichettipalayam','active'),(432,'Gobindgarh','active'),(241,'Godhra','active'),(478,'Gohana','active'),(403,'Gokak','active'),(637,'Gooty','active'),(464,'Gopalganj','active'),(274,'Gudivada','active'),(515,'Gudur','active'),(642,'Gumia','active'),(259,'Guntakal','active'),(61,'Guntur','active'),(418,'Gurdaspur','active'),(48,'Gurgaon','active'),(1176,'Guruvayoor','active'),(43,'Guwahati','active'),(40,'Gwalior','active'),(231,'Habra','active'),(233,'Hajipur','active'),(215,'Haldwani-cum-Kathgodam','active'),(381,'Hansi','active'),(134,'Hapur','active'),(292,'Hardoi ','active'),(151,'Hardwar','active'),(242,'Hazaribag','active'),(223,'Hindupur','active'),(117,'Hisar','active'),(200,'Hoshiarpur','active'),(45,'Hubli-Dharwad','active'),(189,'Hugli-Chinsurah','active'),(5,'Hyderabad','active'),(123,'Ichalkaranji','active'),(133,'Imphal','active'),(15,'Indore','active'),(338,'Itarsi','active'),(39,'Jabalpur','active'),(262,'Jagdalpur','active'),(580,'Jaggaiahpet','active'),(484,'Jagraon','active'),(343,'Jagtial','active'),(9,'Jaipur','active'),(52,'Jalandhar','active'),(644,'Jalandhar Cantt.','active'),(197,'Jalpaiguri','active'),(315,'Jamalpur','active'),(662,'Jammalamadugu','active'),(82,'Jammu','active'),(75,'Jamnagar','active'),(65,'Jamshedpur','active'),(376,'Jamui','active'),(592,'Jangaon','active'),(560,'Jatani','active'),(322,'Jehanabad','active'),(79,'Jhansi','active'),(508,'Jhargram','active'),(342,'Jharsuguda','active'),(373,'Jhumri Tilaiya','active'),(203,'Jind','active'),(34,'Jodhpur','active'),(441,'Jorhat','active'),(145,'Kadapa','active'),(430,'Kadi','active'),(365,'Kadiri','active'),(541,'Kagaznagar','active'),(1182,'Kailasahar','active'),(239,'Kaithal','active'),(113,'Kakinada','active'),(696,'Kalimpong','active'),(600,'Kalpi','active'),(29,'Kalyan-Dombivali','active'),(399,'Kamareddy','active'),(206,'Kancheepuram','active'),(544,'Kandukur','active'),(429,'Kanhangad','active'),(549,'Kannur','active'),(12,'Kanpur','active'),(629,'Kapadvanj','active'),(326,'Kapurthala','active'),(380,'Karaikal','active'),(548,'Karimganj','active'),(138,'Karimnagar','active'),(914,'Karjat','active'),(125,'Karnal','active'),(442,'Karur','active'),(301,'Karwar','active'),(568,'Kasaragod','active'),(270,'Kashipur','active'),(596,'KathUrban Agglomeration','active'),(150,'Katihar','active'),(393,'Kavali','active'),(458,'Kayamkulam','active'),(654,'Kendrapara','active'),(516,'Kendujhar','active'),(415,'Keshod','active'),(324,'Khair','active'),(390,'Khambhat','active'),(183,'Khammam','active'),(255,'Khanna','active'),(121,'Kharagpur','active'),(422,'Kharar','active'),(1196,'Khowai','active'),(309,'Kishanganj','active'),(68,'Kochi','active'),(827,'Kodungallur','active'),(339,'Kohima','active'),(246,'Kolar','active'),(7,'Kolkata','active'),(107,'Kollam','active'),(475,'Koratla','active'),(101,'Korba','active'),(358,'Kot Kapura','active'),(401,'Kothagudem','active'),(564,'Kottayam','active'),(738,'Kovvur','active'),(440,'Koyilandy','active'),(91,'Kozhikode','active'),(572,'Kunnamkulam','active'),(93,'Kurnool','active'),(863,'Kyathampalle','active'),(583,'Lachhmangarh','active'),(479,'Ladnu','active'),(933,'Ladwa','active'),(795,'Lahar','active'),(502,'Laharpur','active'),(918,'Lakheri','active'),(221,'Lakhimpur','active'),(335,'Lakhisarai','active'),(778,'Lakshmeshwar','active'),(943,'Lal Gopalganj Nindaura','active'),(1152,'Lalganj','active'),(1058,'Lalgudi','active'),(252,'Lalitpur','active'),(814,'Lalsot','active'),(775,'Lanka','active'),(942,'Lar','active'),(1151,'Lathi','active'),(99,'Latur','active'),(1091,'Lilong','active'),(699,'Limbdi','active'),(802,'Lingsugur','active'),(1188,'Loha','active'),(1049,'Lohardaga','active'),(1070,'Lonar','active'),(539,'Lonavla','active'),(1056,'Longowal','active'),(78,'Loni','active'),(936,'Losal','active'),(11,'Lucknow','active'),(21,'Ludhiana','active'),(888,'Lumding','active'),(772,'Lunawada','active'),(547,'Lunglei','active'),(630,'Macherla','active'),(195,'Machilipatnam','active'),(250,'Madanapalle','active'),(991,'Maddur','active'),(672,'Madhepura','active'),(476,'Madhubani','active'),(995,'Madhugiri','active'),(651,'Madhupur','active'),(859,'Madikeri','active'),(42,'Madurai','active'),(1028,'Magadi','active'),(1048,'Mahad','active'),(897,'Mahalingapura','active'),(1165,'Maharajganj','active'),(1143,'Maharajpur','active'),(383,'Mahasamund','active'),(210,'Mahbubnagar','active'),(774,'Mahe','active'),(899,'Mahemdabad','active'),(1045,'Mahendragarh','active'),(184,'Mahesana','active'),(923,'Mahidpur','active'),(766,'Mahnar Bazar','active'),(397,'Mahuva','active'),(815,'Maihar','active'),(976,'Mainaguri','active'),(905,'Makhdumpur','active'),(359,'Makrana','active'),(868,'Malaj Khand','active'),(329,'Malappuram','active'),(793,'Malavalli','active'),(1078,'Malda','active'),(87,'Malegaon','active'),(251,'Malerkotla','active'),(1082,'Malkangiri','active'),(513,'Malkapur','active'),(448,'Malout','active'),(971,'Malpura','active'),(956,'Malur','active'),(1005,'Manachanallur','active'),(1100,'Manasa','active'),(965,'Manavadar','active'),(1017,'Manawar','active'),(276,'Mancherial','active'),(1187,'Mandalgarh','active'),(472,'Mandamarri','active'),(647,'Mandapeta','active'),(1168,'Mandawa','active'),(518,'Mandi','active'),(576,'Mandi Dabwali','active'),(735,'Mandideep','active'),(517,'Mandla','active'),(244,'Mandsaur','active'),(705,'Mandvi','active'),(248,'Mandya','active'),(900,'Manendragarh','active'),(906,'Maner','active'),(1054,'Mangaldoi','active'),(85,'Mangaluru','active'),(1136,'Mangalvedhe','active'),(701,'Manglaur','active'),(1131,'Mangrol','active'),(957,'Mangrulpir','active'),(1134,'Manihari','active'),(688,'Manjlegaon','active'),(934,'Mankachar','active'),(436,'Manmad','active'),(951,'Mansa','active'),(849,'Manuguru','active'),(764,'Manvi','active'),(928,'Manwath','active'),(731,'Mapusa','active'),(354,'Margao','active'),(1051,'Margherita','active'),(1039,'Marhaura','active'),(1157,'Mariani','active'),(1169,'Marigaon','active'),(531,'Markapur','active'),(316,'Marmagao','active'),(493,'Masaurhi','active'),(1154,'Mathabhanga','active'),(684,'Mathura','active'),(683,'Mattannur','active'),(1084,'Mauganj','active'),(939,'Mavelikkara','active'),(953,'Mavoor','active'),(1174,'Mayang Imphal','active'),(713,'Medak','active'),(408,'Medininagar (Daltonganj)','active'),(230,'Medinipur','active'),(28,'Meerut','active'),(761,'Mehkar','active'),(787,'Memari','active'),(733,'Merta City','active'),(1177,'Mhaswad','active'),(357,'Mhow Cantonment','active'),(1175,'Mhowgaon','active'),(841,'Mihijam','active'),(57,'Mira-Bhayandar','active'),(1065,'Mirganj','active'),(361,'Miryalaguda','active'),(569,'Modasa','active'),(130,'Modinagar','active'),(227,'Moga','active'),(266,'Mohali','active'),(551,'Mokameh','active'),(892,'Mokokchung','active'),(1167,'Monoharpur','active'),(47,'Moradabad','active'),(169,'Morena','active'),(1099,'Morinda, India','active'),(836,'Morshi','active'),(177,'Morvi','active'),(263,'Motihari','active'),(1123,'Motipur','active'),(1117,'Mount Abu','active'),(1010,'Mudabidri','active'),(911,'Mudalagi','active'),(944,'Muddebihal','active'),(703,'Mudhol','active'),(1145,'Mukerian','active'),(1004,'Mukhed','active'),(391,'Muktsar','active'),(1110,'Mul','active'),(687,'Mulbagal','active'),(1144,'Multai','active'),(1,'Mumbai','active'),(1180,'Mundargi','active'),(909,'Mundi','active'),(882,'Mungeli','active'),(162,'Munger','active'),(1087,'Murliganj','active'),(773,'Murshidabad','active'),(748,'Murtijapur','active'),(155,'Murwara (Katni)','active'),(826,'Musabani','active'),(924,'Mussoorie','active'),(925,'Muvattupuzha','active'),(105,'Muzaffarpur','active'),(104,'Mysore','active'),(260,'Nabadwip','active'),(910,'Nabarangapur','active'),(460,'Nabha','active'),(992,'Nadbai','active'),(158,'Nadiad','active'),(281,'Nagaon','active'),(319,'Nagapattinam','active'),(1012,'Nagar','active'),(497,'Nagari','active'),(983,'Nagarkurnool','active'),(331,'Nagaur','active'),(333,'Nagda','active'),(153,'Nagercoil','active'),(349,'Nagina','active'),(1115,'Nagla','active'),(13,'Nagpur','active'),(932,'Nahan','active'),(789,'Naharlagun','active'),(488,'Naidupet','active'),(156,'Naihati','active'),(730,'Naila Janjgir','active'),(718,'Nainital','active'),(1101,'Nainpur','active'),(369,'Najibabad','active'),(771,'Nakodar','active'),(1097,'Nakur','active'),(954,'Nalbari','active'),(1149,'Namagiripettai','active'),(567,'Namakkal','active'),(73,'Nanded-Waghala','active'),(1204,'Nandgaon','active'),(686,'Nandivaram-Guduvancheri','active'),(708,'Nandura','active'),(296,'Nandurbar','active'),(168,'Nandyal','active'),(721,'Nangal','active'),(610,'Nanjangud','active'),(853,'Nanjikottai','active'),(639,'Nanpara','active'),(527,'Narasapuram','active'),(282,'Narasaraopet','active'),(1096,'Naraura','active'),(714,'Narayanpet','active'),(785,'Nargund','active'),(628,'Narkatiaganj','active'),(1153,'Narkhed','active'),(421,'Narnaul','active'),(1212,'Narsinghgarh','active'),(830,'Narsipatnam','active'),(498,'Narwana','active'),(25,'Nashik','active'),(607,'Nasirabad','active'),(1061,'Natham','active'),(710,'Nathdwara','active'),(633,'Naugachhia','active'),(847,'Naugawan Sadat','active'),(831,'Nautanwa','active'),(1037,'Navalgund','active'),(209,'Navsari','active'),(740,'Nawabganj','active'),(341,'Nawada','active'),(663,'Nawanshahr','active'),(817,'Nawapur','active'),(519,'Nedumangad','active'),(786,'Neem-Ka-Thana','active'),(256,'Neemuch','active'),(645,'Nehtaur','active'),(770,'Nelamangala','active'),(656,'Nellikuppam','active'),(81,'Nellore','active'),(913,'Nepanagar','active'),(143,'New Delhi','active'),(314,'Neyveli (TS)','active'),(445,'Neyyattinkara','active'),(691,'Nidadavole','active'),(659,'Nilambur','active'),(788,'Nilanga','active'),(504,'Nimbahera','active'),(370,'Nirmal','active'),(1208,'Niwai','active'),(1060,'Niwari','active'),(114,'Nizamabad','active'),(621,'Nohar','active'),(64,'Noida','active'),(974,'Nokha','active'),(935,'Nongstoin','active'),(746,'Noorpur','active'),(520,'North Lakhimpur','active'),(729,'Nowgong','active'),(1130,'Nowrozabad (Khodargama)','active'),(528,'Nuzvid','active'),(1124,'O\' Valley','active'),(1202,'Obra','active'),(907,'Oddanchatram','active'),(167,'Ongole','active'),(178,'Orai','active'),(294,'Osmanabad','active'),(577,'Ottappalam','active'),(604,'Ozar','active'),(1072,'P.N.Patti','active'),(671,'Pachora','active'),(1161,'Pachore','active'),(1095,'Pacode','active'),(1190,'Padmanabhapuram','active'),(741,'Padra','active'),(679,'Padrauna','active'),(810,'Paithan','active'),(790,'Pakaur','active'),(414,'Palacole','active'),(1098,'Palai','active'),(172,'Palakkad','active'),(1073,'Palampur','active'),(466,'Palani','active'),(258,'Palanpur','active'),(620,'Palasa Kasibugga','active'),(590,'Palghar','active'),(1160,'Pali','active'),(804,'Palia Kalan','active'),(597,'Palitana','active'),(908,'Palladam','active'),(1044,'Pallapatti','active'),(1170,'Pallikonda','active'),(257,'Palwal','active'),(456,'Palwancha','active'),(1022,'Panagar','active'),(1016,'Panagudi','active'),(336,'Panaji','active'),(1207,'Panamattom','active'),(163,'Panchkula','active'),(1120,'Panchla','active'),(989,'Pandharkaoda','active'),(360,'Pandharpur','active'),(724,'Pandhurna','active'),(975,'PandUrban Agglomeration','active'),(119,'Panipat','active'),(594,'Panna','active'),(1164,'Panniyannur','active'),(565,'Panruti','active'),(165,'Panvel','active'),(839,'Pappinisseri','active'),(427,'Paradip','active'),(387,'Paramakudi','active'),(1162,'Parangipettai','active'),(1150,'Parasi','active'),(747,'Paravoor','active'),(139,'Parbhani','active'),(1020,'Pardi','active'),(695,'Parlakhemundi','active'),(368,'Parli','active'),(931,'Partur','active'),(625,'Parvathipuram','active'),(919,'Pasan','active'),(893,'Paschim Punropara','active'),(1122,'Pasighat','active'),(290,'Patan','active'),(753,'Pathanamthitta','active'),(199,'Pathankot','active'),(1093,'Pathardi','active'),(878,'Pathri','active'),(27,'Patiala','active'),(14,'Patna','active'),(875,'Patratu','active'),(852,'Pattamundai','active'),(812,'Patti','active'),(1116,'Pattran','active'),(480,'Pattukkottai','active'),(1173,'Patur','active'),(1103,'Pauni','active'),(1035,'Pauri','active'),(949,'Pavagada','active'),(916,'Pedana','active'),(669,'Peddapuram','active'),(837,'Pehowa','active'),(904,'Pen','active'),(763,'Perambalur','active'),(1156,'Peravurani','active'),(762,'Peringathur','active'),(678,'Perinthalmanna','active'),(711,'Periyakulam','active'),(877,'Periyasemur','active'),(717,'Pernampattu','active'),(990,'Perumbavoor','active'),(606,'Petlad','active'),(323,'Phagwara','active'),(675,'Phalodi','active'),(608,'Phaltan','active'),(1112,'Phillaur','active'),(828,'Phulabani','active'),(1137,'Phulera','active'),(1158,'Phulpur','active'),(191,'Phusro','active'),(966,'Pihani','active'),(728,'Pilani','active'),(835,'Pilibanga','active'),(264,'Pilibhit','active'),(469,'Pilkhuwa','active'),(1171,'Pindwara','active'),(917,'Pinjore','active'),(851,'Pipar City','active'),(680,'Pipariya','active'),(1201,'Piriyapatna','active'),(1009,'Piro','active'),(459,'Pithampur','active'),(615,'Pithapuram','active'),(673,'Pithoragarh','active'),(254,'Pollachi','active'),(1015,'Polur','active'),(80,'Pondicherry','active'),(375,'Ponnani','active'),(1047,'Ponneri','active'),(540,'Ponnur','active'),(171,'Porbandar','active'),(846,'Porsa','active'),(334,'Port Blair','active'),(1071,'Powayan','active'),(1114,'Prantij','active'),(981,'Pratapgarh','active'),(1104,'Prithvipur','active'),(228,'Proddatur','active'),(304,'Pudukkottai','active'),(1163,'Pudupattinam','active'),(1189,'Pukhrayan','active'),(783,'Pulgaon','active'),(843,'Puliyankudi','active'),(653,'Punalur','active'),(723,'Punch','active'),(8,'Pune','active'),(682,'Punganur','active'),(1181,'Punjaipugalur','active'),(769,'Puranpur','active'),(211,'Puri','active'),(842,'Purna','active'),(174,'Purnia','active'),(1063,'PurqUrban Agglomerationzi','active'),(289,'Purulia','active'),(1147,'Purwa','active'),(467,'Pusad','active'),(1195,'Puthuppally','active'),(920,'Puttur','active'),(1127,'Qadian','active'),(164,'Raayachuru','active'),(452,'Rabkavi Banhatti','active'),(872,'Radhanpur','active'),(196,'Rae Bareli','active'),(1030,'Rafiganj','active'),(631,'Raghogarh-Vijaypur','active'),(108,'Raghunathganj','active'),(1125,'Raghunathpur','active'),(1021,'Rahatgarh','active'),(811,'Rahuri','active'),(190,'Raiganj','active'),(283,'Raigarh','active'),(1034,'Raikot','active'),(36,'Raipur','active'),(1128,'Rairangpur','active'),(794,'Raisen','active'),(961,'Raisinghnagar','active'),(693,'Rajagangapur','active'),(95,'Rajahmundry','active'),(941,'Rajakhera','active'),(1092,'Rajaldesar','active'),(1068,'Rajam','active'),(303,'Rajampet','active'),(268,'Rajapalayam','active'),(716,'Rajauri','active'),(1053,'Rajgarh','active'),(1029,'Rajgarh (Alwar)','active'),(601,'Rajgarh (Churu)','active'),(832,'Rajgir','active'),(22,'Rajkot','active'),(240,'Rajnandgaon','active'),(806,'Rajpipla','active'),(392,'Rajpura','active'),(562,'Rajsamand','active'),(862,'Rajula','active'),(1008,'Rajura','active'),(719,'Ramachandrapuram','active'),(146,'Ramagundam','active'),(402,'Ramanagaram','active'),(499,'Ramanathapuram','active'),(782,'Ramdurg','active'),(755,'Rameshwaram','active'),(895,'Ramganj Mandi','active'),(298,'Ramgarh','active'),(749,'Ramnagar','active'),(938,'Ramngarh','active'),(127,'Rampur','active'),(1032,'Rampur Maniharan','active'),(677,'Rampura Phul','active'),(609,'Rampurhat','active'),(1105,'Ramtek','active'),(236,'Ranaghat','active'),(915,'Ranavav','active'),(51,'Ranchi','active'),(312,'Ranebennuru','active'),(1025,'Rangia','active'),(1159,'Rania','active'),(364,'Ranibennur','active'),(135,'Ranipet','active'),(1083,'Rapar','active'),(660,'Rasipuram','active'),(926,'Rasra','active'),(491,'Ratangarh','active'),(559,'Rath','active'),(1057,'Ratia','active'),(129,'Ratlam','active'),(451,'Ratnagiri','active'),(1166,'Rau','active'),(84,'Raurkela','active'),(1001,'Raver','active'),(808,'Rawatbhata','active'),(940,'Rawatsar','active'),(715,'Raxaul Bazar','active'),(438,'Rayachoti','active'),(570,'Rayadurg','active'),(538,'Rayagada','active'),(1088,'Reengus','active'),(1006,'Rehli','active'),(1055,'Renigunta','active'),(471,'Renukoot','active'),(1118,'Reoti','active'),(702,'Repalle','active'),(822,'Revelganj','active'),(187,'Rewa','active'),(330,'Rewari','active'),(406,'Rishikesh','active'),(968,'Risod','active'),(870,'Robertsganj','active'),(212,'Robertson Pet','active'),(118,'Rohtak','active'),(1138,'Ron','active'),(284,'Roorkee','active'),(969,'Rosera','active'),(776,'Rudauli','active'),(986,'Rudrapur','active'),(632,'Rupnagar','active'),(823,'Sabalgarh','active'),(879,'Sadabad','active'),(1185,'Sadalagi','active'),(784,'Sadasivpet','active'),(1041,'Sadri','active'),(267,'Sadulpur','active'),(1111,'Sadulshahar','active'),(967,'Safidon','active'),(1108,'Safipur','active'),(115,'Sagar','active'),(614,'Sagara','active'),(894,'Sagwara','active'),(60,'Saharanpur','active'),(217,'Saharsa','active'),(1102,'Sahaspur','active'),(535,'Sahaswan','active'),(1178,'Sahawar','active'),(400,'Sahibganj','active'),(1026,'Sahjanwa','active'),(1140,'Saidpur','active'),(1192,'Saiha','active'),(736,'Sailu','active'),(742,'Sainthia','active'),(1080,'Sakaleshapura','active'),(1184,'Sakti','active'),(979,'Salaya','active'),(55,'Salem','active'),(638,'Salur','active'),(912,'Samalkha','active'),(579,'Samalkot','active'),(657,'Samana','active'),(501,'Samastipur','active'),(186,'Sambalpur','active'),(157,'Sambhal','active'),(1113,'Sambhar','active'),(1019,'Samdhan','active'),(1183,'Samthar','active'),(861,'Sanand','active'),(820,'Sanawad','active'),(1007,'Sanchore','active'),(1076,'Sandi','active'),(636,'Sandila','active'),(963,'Sanduru','active'),(503,'Sangamner','active'),(545,'Sangareddy','active'),(809,'Sangaria','active'),(77,'Sangli','active'),(948,'Sangole','active'),(411,'Sangrur','active'),(449,'Sankarankovil','active'),(970,'Sankari','active'),(858,'Sankeshwara','active'),(224,'Santipur','active'),(869,'Sarangpur','active'),(396,'Sardarshahar','active'),(640,'Sardhana','active'),(352,'Sarni','active'),(1215,'Sarsod','active'),(232,'Sasaram','active'),(985,'Sasvad','active'),(856,'Satana','active'),(307,'Satara','active'),(834,'Sathyamangalam','active'),(148,'Satna','active'),(602,'Sattenapalle','active'),(886,'Sattur','active'),(385,'Saunda','active'),(752,'Saundatti-Yellamma','active'),(1046,'Sausar','active'),(797,'Savanur','active'),(426,'Savarkundla','active'),(984,'Savner','active'),(325,'Sawai Madhopur','active'),(1089,'Sawantwadi','active'),(883,'Sedam','active'),(356,'Sehore','active'),(634,'Sendhwa','active'),(649,'Seohara','active'),(363,'Seoni','active'),(997,'Seoni-Malwa','active'),(473,'Shahabad','active'),(461,'Shahabad, Hardoi','active'),(864,'Shahabad, Rampur','active'),(626,'Shahade','active'),(768,'Shahbad','active'),(407,'Shahdol','active'),(1038,'Shahganj','active'),(110,'Shahjahanpur','active'),(744,'Shahpur','active'),(959,'Shahpura','active'),(537,'Shajapur','active'),(1141,'Shamgarh','active'),(362,'Shamli','active'),(973,'Shamsabad, Agra','active'),(1064,'Shamsabad, Farrukhabad','active'),(591,'Shegaon','active'),(694,'Sheikhpura','active'),(1155,'Shendurjana','active'),(980,'Shenkottai','active'),(1033,'Sheoganj','active'),(1148,'Sheohar','active'),(532,'Sheopur','active'),(857,'Sherghati','active'),(589,'Sherkot','active'),(1043,'Shiggaon','active'),(884,'Shikaripur','active'),(844,'Shikarpur, Bulandshahr','active'),(372,'Shikohabad','active'),(132,'Shillong','active'),(237,'Shimla','active'),(998,'Shirdi','active'),(509,'Shirpur-Warwade','active'),(978,'Shirur','active'),(1172,'Shishgarh','active'),(128,'Shivamogga','active'),(235,'Shivpuri','active'),(1139,'Sholavandan','active'),(988,'Sholingur','active'),(709,'Shoranur','active'),(994,'Shrigonda','active'),(366,'Shrirampur','active'),(1059,'Shrirangapattana','active'),(704,'Shujalpur','active'),(743,'Siana','active'),(575,'Sibsagar','active'),(505,'Siddipet','active'),(667,'Sidhi','active'),(534,'Sidhpur','active'),(722,'Sidlaghatta','active'),(655,'Sihor','active'),(758,'Sihora','active'),(1135,'Sikanderpur','active'),(757,'Sikandra Rao','active'),(453,'Sikandrabad','active'),(180,'Sikar','active'),(1186,'Silao','active'),(1106,'Silapathar','active'),(185,'Silchar','active'),(24,'Siliguri','active'),(690,'Sillod','active'),(1129,'Silvassa','active'),(825,'Simdega','active'),(1075,'Sindagi','active'),(962,'Sindhagi','active'),(511,'Sindhnur','active'),(182,'Singrauli','active'),(881,'Sinnar','active'),(616,'Sira','active'),(482,'Sircilla','active'),(623,'Sirhind Fatehgarh Sahib','active'),(871,'Sirkali','active'),(798,'Sirohi','active'),(707,'Sironj','active'),(208,'Sirsa','active'),(945,'Sirsaganj','active'),(1146,'Sirsi','active'),(697,'Siruguppa','active'),(377,'Sitamarhi','active'),(222,'Sitapur','active'),(1121,'Sitarganj','active'),(734,'Sivaganga','active'),(1179,'Sivagiri','active'),(271,'Sivakasi','active'),(302,'Siwan','active'),(1109,'Sohagpur','active'),(964,'Sohna','active'),(745,'Sojat','active'),(818,'Solan','active'),(50,'Solapur','active'),(972,'Sonamukhi','active'),(838,'Sonepur','active'),(1107,'Songadh','active'),(152,'Sonipat','active'),(468,'Sopore','active'),(958,'Soro','active'),(987,'Soron','active'),(1132,'Soyagaon','active'),(937,'Sri Madhopur','active'),(277,'Srikakulam','active'),(444,'Srikalahasti','active'),(229,'Srinagar','active'),(1086,'Srinivaspur','active'),(1193,'Srirampore','active'),(1074,'Srisailam Project (Right Flank Colony) Township','active'),(431,'Srivilliputhur','active'),(887,'Sugauli','active'),(389,'Sujangarh','active'),(1133,'Sujanpur','active'),(416,'Sullurpeta','active'),(712,'Sultanganj','active'),(332,'Sultanpur','active'),(1036,'Sumerpur','active'),(524,'Sunabeda','active'),(555,'Sunam','active'),(750,'Sundargarh','active'),(1052,'Sundarnagar','active'),(571,'Supaul','active'),(947,'Surandai','active'),(692,'Surapura','active'),(10,'Surat','active'),(536,'Suratgarh','active'),(999,'SUrban Agglomerationr','active'),(506,'Suri','active'),(1126,'Suriyampalayam','active'),(310,'Suryapet','active'),(318,'Tadepalligudem','active'),(306,'Tadpatri','active'),(1198,'Takhatgarh','active'),(751,'Taki','active'),(955,'Talaja','active'),(726,'Talcher','active'),(552,'Talegaon Dabhade','active'),(880,'Talikota','active'),(435,'Taliparamba','active'),(993,'Talode','active'),(1194,'Talwara','active'),(483,'Tamluk','active'),(347,'Tanda','active'),(487,'Tandur','active'),(437,'Tanuku','active'),(896,'Tarakeswar','active'),(1031,'Tarana','active'),(854,'Taranagar','active'),(1003,'Taraori','active'),(1210,'Tarbha','active'),(792,'Tarikere','active'),(470,'Tarn Taran','active'),(756,'Tasgaon','active'),(1018,'Tehri','active'),(996,'Tekkalakote','active'),(205,'Tenali','active'),(450,'Tenkasi','active'),(1119,'Tenu dam-cum-Kathhara','active'),(1000,'Terdal','active'),(529,'Tezpur','active'),(685,'Thakurdwara','active'),(1142,'Thammampatti','active'),(781,'Thana Bhawan','active'),(16,'Thane','active'),(218,'Thanesar','active'),(706,'Thangadh','active'),(154,'Thanjavur','active'),(950,'Tharad','active'),(902,'Tharamangalam','active'),(1079,'Tharangambadi','active'),(353,'Theni Allinagaram','active'),(605,'Thirumangalam','active'),(1199,'Thirupuvanam','active'),(1042,'Thiruthuraipoondi','active'),(588,'Thiruvalla','active'),(557,'Thiruvallur','active'),(58,'Thiruvananthapuram','active'),(533,'Thiruvarur','active'),(595,'Thodupuzha','active'),(664,'Thoubal','active'),(111,'Thrissur','active'),(860,'Thuraiyur','active'),(404,'Tikamgarh','active'),(865,'Tilda Newra','active'),(510,'Tilhar','active'),(433,'Tindivanam','active'),(337,'Tinsukia','active'),(522,'Tiptur','active'),(1024,'Tirora','active'),(873,'Tiruchendur','active'),(348,'Tiruchengode','active'),(53,'Tiruchirappalli','active'),(922,'Tirukalukundram','active'),(903,'Tirukkoyilur','active'),(86,'Tirunelveli','active'),(1002,'Tirupathur','active'),(124,'Tirupati','active'),(89,'Tiruppur','active'),(558,'Tirur','active'),(676,'Tiruttani','active'),(238,'Tiruvannamalai','active'),(759,'Tiruvethipuram','active'),(760,'Tiruvuru','active'),(1050,'Tirwaganj','active'),(890,'Titlagarh','active'),(1090,'Tittakudi','active'),(1085,'Todabhim','active'),(1066,'Todaraisingh','active'),(490,'Tohana','active'),(204,'Tonk','active'),(777,'Tuensang','active'),(824,'Tuljapur','active'),(1040,'Tulsipur','active'),(116,'Tumkur','active'),(674,'Tumsar','active'),(612,'Tundla','active'),(582,'Tuni','active'),(420,'Tura','active'),(891,'Uchgaon','active'),(850,'Udaipur','active'),(927,'Udaipurwati','active'),(317,'Udgir','active'),(371,'Udhagamandalam','active'),(799,'Udhampur','active'),(512,'Udumalaipettai','active'),(261,'Udupi','active'),(500,'Ujhani','active'),(76,'Ujjain','active'),(800,'Umarga','active'),(845,'Umaria','active'),(650,'Umarkhed','active'),(952,'Umbergaon','active'),(573,'Umred','active'),(829,'Umreth','active'),(530,'Una','active'),(546,'Unjha','active'),(1062,'Unnamalaikadai','active'),(188,'Unnao','active'),(526,'Upleta','active'),(901,'Uran','active'),(463,'Uran Islampur','active'),(796,'Uravakonda','active'),(1069,'Urmar Tanda','active'),(803,'Usilampatti','active'),(930,'Uthamapalayam','active'),(1023,'Uthiramerur','active'),(874,'Utraula','active'),(921,'Vadakkuvalliyur','active'),(739,'Vadalur','active'),(1011,'Vadgaon Kasba','active'),(982,'Vadipatti','active'),(960,'Vadnagar','active'),(19,'Vadodara','active'),(720,'Vaijapur','active'),(1077,'Vaikom','active'),(443,'Valparai','active'),(285,'Valsad','active'),(889,'Vandavasi','active'),(351,'Vaniyambadi','active'),(819,'Vapi','active'),(31,'Varanasi','active'),(727,'Varkala','active'),(30,'Vasai-Virar','active'),(419,'Vatakara','active'),(816,'Vedaranyam','active'),(732,'Vellakoil','active'),(181,'Vellore','active'),(599,'Venkatagiri','active'),(220,'Veraval','active'),(216,'Vidisha','active'),(876,'Vijainagar, Ajmer','active'),(1013,'Vijapur','active'),(807,'Vijayapura','active'),(41,'Vijayawada','active'),(1197,'Vijaypur','active'),(587,'Vikarabad','active'),(652,'Vikramasingapuram','active'),(344,'Viluppuram','active'),(521,'Vinukonda','active'),(561,'Viramgam','active'),(428,'Virudhachalam','active'),(439,'Virudhunagar','active'),(18,'Visakhapatnam','active'),(494,'Visnagar','active'),(1014,'Viswanatham','active'),(641,'Vita','active'),(149,'Vizianagaram','active'),(495,'Vrindavan','active'),(737,'Vyara','active'),(725,'Wadgaon Road','active'),(417,'Wadhwan','active'),(754,'Wadi','active'),(791,'Wai','active'),(514,'Wanaparthy','active'),(525,'Wani','active'),(689,'Wankaner','active'),(1027,'Wara Seoni','active'),(56,'Warangal','active'),(311,'Wardha','active'),(1067,'Warhapur','active'),(821,'Warisaliganj','active'),(658,'Warora','active'),(1213,'Warud','active'),(410,'Washim','active'),(805,'Wokha','active'),(423,'Yadgir','active'),(159,'Yamunanagar','active'),(563,'Yanam','active'),(279,'Yavatmal','active'),(780,'Yawal','active'),(833,'Yellandu','active'),(350,'Yemmiganur','active'),(855,'Yerraguntla','active'),(622,'Yevla','active'),(813,'Zaidpur','active'),(840,'Zamania','active'),(779,'Zira','active'),(346,'Zirakpur','active'),(765,'Zunheboto','active');

/*Table structure for table `college` */

DROP TABLE IF EXISTS `college`;

CREATE TABLE `college` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `image` varchar(50) DEFAULT '',
  `address` text,
  `state` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `mobileno` varchar(50) DEFAULT NULL,
  `status` enum('active','deactive') DEFAULT 'active',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`name`),
  UNIQUE KEY `NewIndex1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `college` */

insert  into `college`(`id`,`name`,`image`,`address`,`state`,`city`,`country`,`mobileno`,`status`,`created_at`) values (1,'Bishop heber college','','Namakkal, K.M.Devi(P.o), P.velur(T.K)\n','Kerala','Tadpatri','india','9976625471','active','2016-06-17 17:37:47'),(2,'KCE','','Namakkal-637213','Tamil Nadu','Coimbatore','india','9976625471','active','2016-06-22 17:04:06'),(4,'Kumara Guru','','Coimbatore','Tamil Nadu','Coimbatore','india','2001','active','2016-06-28 17:13:51'),(3,'PSG','','Coimbatore','Tamil Nadu','Coimbatore','india','9973325621','active','2016-06-28 17:01:18');

/*Table structure for table `hostel` */

DROP TABLE IF EXISTS `hostel`;

CREATE TABLE `hostel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `collegeid` int(100) NOT NULL,
  `image` varchar(100) DEFAULT '',
  `mobileno` varchar(50) DEFAULT NULL,
  `address` text,
  `state` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `status` enum('active','deactive') NOT NULL DEFAULT 'active',
  `country` enum('india') DEFAULT 'india',
  `createdat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`name`,`collegeid`),
  UNIQUE KEY `NewIndex1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `hostel` */

insert  into `hostel`(`id`,`name`,`collegeid`,`image`,`mobileno`,`address`,`state`,`city`,`status`,`country`,`createdat`) values (21,'Boys',4,'','9976625471','Coimbatore','Tamil Nadu','Coimbatore','active','india','2016-06-28 17:42:06'),(19,'Dod Son',2,'','9976625471','Trichy','Tamil Nadu','Coimbatore','active','india','2016-06-25 16:10:41'),(20,'Ladies',1,'','9976625471','Trichy','Tamil Nadu','Coimbatore','active','india','2016-06-25 16:11:01'),(22,'STEP HOSTEL',3,'','3425545','Coimbatore','Tamil Nadu','Coimbatore','active','india','2016-06-28 17:51:51');

/*Table structure for table `menu_mas` */

DROP TABLE IF EXISTS `menu_mas`;

CREATE TABLE `menu_mas` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(100) NOT NULL DEFAULT '',
  `menu_level` int(11) NOT NULL DEFAULT '1',
  `slno` int(11) NOT NULL DEFAULT '1',
  `is_parent` enum('yes','no') NOT NULL DEFAULT 'no',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `menu_url` varchar(200) NOT NULL DEFAULT '',
  `menu_icon` varchar(100) NOT NULL DEFAULT '',
  `is_projectmanager_menu` enum('yes','no') NOT NULL DEFAULT 'no',
  `status` enum('active','inactive') NOT NULL DEFAULT 'active',
  PRIMARY KEY (`menu_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `menu_mas` */

insert  into `menu_mas`(`menu_id`,`menu_name`,`menu_level`,`slno`,`is_parent`,`parent_id`,`menu_url`,`menu_icon`,`is_projectmanager_menu`,`status`) values (1,'College',1,1,'yes',0,'api/manager/get_college','fa fa-globe','no','active'),(2,'Hostel',1,2,'yes',0,'api/manager/get_hostel','fa fa-building-o','no','active'),(3,'Block',1,3,'yes',0,'api/manager/get_block','fa fa-flash','no','active'),(4,'Student',1,5,'yes',0,'api/manager/get_student','fa fa-user','no','active'),(5,'Users',1,7,'yes',0,'api/manager/users_index','fa fa-users','yes','active'),(6,'UserMenu',1,8,'yes',0,'api/manager/getUserMenus_index','fa fa-cog','yes','active'),(7,'Room',1,4,'yes',0,'api/manager/getRoom_index','fa fa-building-o','no','active'),(8,'RoomAllocation',1,6,'yes',0,'api/manager/getRoomAllocation_index','fa fa-cogs','no','active');

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomno` int(11) NOT NULL,
  `hostelid` int(11) NOT NULL,
  `blockid` int(100) DEFAULT NULL,
  `roomtype` enum('AC','nonAC') DEFAULT 'AC',
  `floorname` int(100) DEFAULT NULL,
  `noofperson` int(11) DEFAULT NULL,
  `status` enum('active','deactive') DEFAULT 'active',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`roomno`,`hostelid`),
  UNIQUE KEY `NewIndex1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `room` */

insert  into `room`(`id`,`roomno`,`hostelid`,`blockid`,`roomtype`,`floorname`,`noofperson`,`status`,`created_at`) values (10,1,19,7,'nonAC',1,3,'active','2016-06-25 16:14:44'),(9,4,20,5,'AC',2,2,'active','2016-06-25 16:14:00'),(12,23,20,4,'AC',1,3,'active','2016-06-28 18:04:18'),(11,101,20,5,'nonAC',1,1,'active','2016-06-26 11:07:10');

/*Table structure for table `room_allocation` */

DROP TABLE IF EXISTS `room_allocation`;

CREATE TABLE `room_allocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomid` int(11) NOT NULL DEFAULT '0',
  `studentid` int(11) NOT NULL DEFAULT '0',
  `entry_date` date NOT NULL DEFAULT '1990-01-01',
  `last_modified` date DEFAULT '1990-01-01',
  `status` enum('alloted','vacated','shifted') NOT NULL DEFAULT 'alloted',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `room_allocation` */

insert  into `room_allocation`(`id`,`roomid`,`studentid`,`entry_date`,`last_modified`,`status`,`created_at`) values (14,9,1,'2016-06-28','2016-06-28','shifted','2016-06-28 13:21:03'),(15,10,7,'2016-06-28','2016-06-28','shifted','2016-06-28 13:21:13'),(16,10,5,'2016-06-28','2016-06-28','shifted','2016-06-28 13:21:27'),(17,10,7,'2016-06-28','2016-06-28','shifted','2016-06-28 13:22:07'),(18,10,7,'2016-06-28','2016-06-28','shifted','2016-06-28 13:22:59'),(19,10,7,'2016-06-28','1990-01-01','alloted','2016-06-28 13:23:38'),(20,10,5,'2016-06-28','1990-01-01','alloted','2016-06-28 13:27:15');

/*Table structure for table `state` */

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `status` enum('active','deactive') DEFAULT 'active',
  PRIMARY KEY (`name`),
  UNIQUE KEY `NewIndex1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1218 DEFAULT CHARSET=latin1;

/*Data for the table `state` */

insert  into `state`(`id`,`name`,`status`) values (1217,'','active'),(334,'Andaman and Nicobar Islands','active'),(1074,'Andhra Pradesh','active'),(1122,'Arunachal Pradesh','active'),(1169,'Assam','active'),(1214,'Bihar','active'),(44,'Chandigarh','active'),(1184,'Chhattisgarh','active'),(1129,'Dadra and Nagar Haveli','active'),(143,'Delhi','active'),(731,'Goa','active'),(1206,'Gujarat','active'),(1215,'Haryana','active'),(1073,'Himachal Pradesh','active'),(799,'Jammu and Kashmir','active'),(1119,'Jharkhand','active'),(1211,'Karnataka','active'),(104,'Karnatka','active'),(1207,'Kerala','active'),(1212,'Madhya Pradesh','active'),(1213,'Maharashtra','active'),(1174,'Manipur','active'),(935,'Meghalaya','active'),(1192,'Mizoram','active'),(892,'Nagaland','active'),(1210,'Odisha','active'),(774,'Puducherry','active'),(1194,'Punjab','active'),(1198,'Rajasthan','active'),(1199,'Tamil Nadu','active'),(983,'Telangana','active'),(1196,'Tripura','active'),(1208,'Uttar Pradesh','active'),(1209,'Uttarakhand','active'),(1200,'West Bengal','active');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `collegeid` int(11) NOT NULL DEFAULT '0',
  `rollno` varchar(50) NOT NULL,
  `batch` varchar(50) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `messtype` enum('veg','nonveg') DEFAULT 'veg',
  `address` text,
  `state` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` enum('india') DEFAULT 'india' COMMENT 'We have only INDIA city and state records',
  `mobileno` varchar(50) DEFAULT NULL,
  `status` enum('active','deactive') DEFAULT 'active',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`rollno`),
  UNIQUE KEY `NewIndex1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`collegeid`,`rollno`,`batch`,`course`,`messtype`,`address`,`state`,`city`,`country`,`mobileno`,`status`,`created_at`) values (6,'Raja',2,'10z120','2013','mba','veg','Namakkal','Tamil Nadu','Coimbatore','india','9976625471','active','2016-06-26 21:22:12'),(1,'Nagarajan',1,'10z128','2013','mca','veg','Namakkal','Tamil Nadu','Coimbatore','india','9976625471','active','2016-06-25 16:17:20'),(5,'Sara',2,'10z129','2013','mca','veg','Namakkal','Tamil Nadu','Coimbatore','india','9976625471','active','2016-06-25 16:20:29'),(7,'Muthu',2,'10z145','2013','mba','veg','Namakkal','Tamil Nadu','Coimbatore','india','9976625471','active','2016-06-27 17:36:00');

/*Table structure for table `user_vs_menu` */

DROP TABLE IF EXISTS `user_vs_menu`;

CREATE TABLE `user_vs_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL DEFAULT '0',
  `menu_id` int(11) NOT NULL DEFAULT '0',
  `save_access` enum('yes','no') NOT NULL DEFAULT 'no',
  `edit_access` enum('yes','no') NOT NULL DEFAULT 'no',
  `delete_access` enum('yes','no') NOT NULL DEFAULT 'no',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `user_vs_menu` */

insert  into `user_vs_menu`(`id`,`userid`,`menu_id`,`save_access`,`edit_access`,`delete_access`) values (1,2,1,'yes','yes','yes'),(2,2,2,'yes','yes','yes'),(3,2,3,'yes','yes','yes'),(4,2,4,'yes','yes','yes'),(6,2,5,'yes','yes','yes'),(17,1,1,'yes','yes','no'),(15,1,2,'no','yes','no'),(16,1,5,'no','no','no');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL DEFAULT '',
  `password` varchar(250) NOT NULL DEFAULT '',
  `firstname` varchar(100) NOT NULL DEFAULT '',
  `usertype` enum('admin','projectmanager') NOT NULL DEFAULT 'admin',
  `status` enum('active','inactive') NOT NULL DEFAULT 'active',
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`),
  UNIQUE KEY `NewIndex1` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`userid`,`username`,`password`,`firstname`,`usertype`,`status`,`created_on`) values (1,'admin@gmail.com','pass','nagarajan','admin','active','2016-05-31 13:35:28'),(2,'projectmagager@gmail.com','pass','Manager','projectmanager','active','2016-05-31 13:37:28'),(3,'connecttosavan@gmail.com','123','Naga','projectmanager','active','2016-06-01 16:23:40'),(4,'aksabuthahir@gmail.com','123456','Naga Ramalingam','admin','active','2016-06-01 22:21:28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
