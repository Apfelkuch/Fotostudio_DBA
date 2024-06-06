-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 06. Jun 2024 um 18:52
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `fotostudio_dba`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `adresse`
--

CREATE TABLE `adresse` (
  `A_ID` int(11) NOT NULL,
  `STRASSE` varchar(255) NOT NULL,
  `HAUSNR` int(4) UNSIGNED ZEROFILL NOT NULL,
  `PLZ` int(5) UNSIGNED ZEROFILL NOT NULL,
  `ORT` varchar(255) NOT NULL,
  `LAND` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `adresse`
--

INSERT INTO `adresse` (`A_ID`, `STRASSE`, `HAUSNR`, `PLZ`, `ORT`, `LAND`) VALUES
(1, 'Musterstraße', 0001, 12345, 'Musterstadt', 'Deutschland'),
(2, 'Beispielweg', 0002, 23456, 'Beispielstadt', 'Deutschland'),
(3, 'Teststraße', 0003, 34567, 'Teststadt', 'Deutschland'),
(4, 'Demoweg', 0004, 45678, 'Demostadt', 'Deutschland'),
(5, 'Probierweg', 0005, 56789, 'Probierstadt', 'Deutschland'),
(6, 'Erfindungstraße', 0006, 67890, 'Erfindungsstadt', 'Deutschland'),
(7, 'Vorzeigestraße', 0007, 78901, 'Vorzeigestadt', 'Deutschland'),
(8, 'Erlebnisweg', 0008, 89012, 'Erlebnisstadt', 'Deutschland'),
(9, 'Fantasiestraße', 0009, 90123, 'Fantasienstadt', 'Deutschland'),
(10, 'Innovationsweg', 0010, 01234, 'Innovationsstadt', 'Deutschland');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `customer`
--

CREATE TABLE `customer` (
  `C_ID` int(11) NOT NULL,
  `BENUTZERNAME` varchar(255) NOT NULL,
  `PASSWORT` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `ROLLE` int(1) UNSIGNED NOT NULL DEFAULT 0,
  `FK_U_ID` int(11) NOT NULL,
  `ZEITSTEMPEL` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `ISTMITARBEITER` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `lieferumfang`
--

CREATE TABLE `lieferumfang` (
  `L_ID` int(11) NOT NULL,
  `MENGE` bigint(7) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000,
  `FK_Z_ID` int(11) NOT NULL,
  `FK_P_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orders`
--

CREATE TABLE `orders` (
  `O_ID` int(11) NOT NULL,
  `FK_C_ID` int(11) NOT NULL,
  `ZEITSTEMPEL` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `LIEFERDATUM` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `personal`
--

CREATE TABLE `personal` (
  `PSN_ID` int(11) NOT NULL,
  `FK_U_ID` int(11) NOT NULL,
  `AUFGABE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `produkt`
--

CREATE TABLE `produkt` (
  `P_ID` int(11) NOT NULL,
  `NAME` varchar(255) CHARACTER SET utf16 COLLATE utf16_german2_ci NOT NULL,
  `BESCHREIBUNG` varchar(255) CHARACTER SET utf16 COLLATE utf16_german2_ci NOT NULL,
  `KATEGORIE` enum('Alles','Equipment') NOT NULL DEFAULT 'Alles',
  `ABRECHNUNGSART` enum('Pro Bild','Pro Person','Pro Objekt','Pro Familie') NOT NULL DEFAULT 'Pro Bild',
  `PREIS` decimal(10,2) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000.00,
  `MENGE` bigint(7) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000,
  `LAGERSTATUS` enum('In Stock','Low Stock','Out of Stock') NOT NULL DEFAULT 'In Stock',
  `LETZTE ÄNDERUNG` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `DATEIPFAD` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `produkt`
--

INSERT INTO `produkt` (`P_ID`, `NAME`, `BESCHREIBUNG`, `KATEGORIE`, `ABRECHNUNGSART`, `PREIS`, `MENGE`, `LAGERSTATUS`, `LETZTE ÄNDERUNG`, `DATEIPFAD`) VALUES
(1, 'Sony Alpha 7 IV Body', 'Sony Alpha 7 IV:\r\n\r\n- Verbesserte Video Funktionen mit seitlich schwenkbaren Bildschirm und mehr\r\n- Videoauflösung von 4K 60p (Nur im Super-35-mm-Modus)\r\n- Rückwärtig belichteter Exmor R-Vollformatsensor\r\n- Neueste Echtzeit-Tracking-Technologie\r\n- Autofok', 'Equipment', 'Pro Objekt', 00002399.00, 0000100, 'In Stock', '2024-06-06 15:07:32', 'sony_alpha7vi.jpg'),
(2, 'Sony SEL 24-70mm/2,8 G-Master Version 2', 'Das Sony SEL2470GM2 Vollformat-Objektiv: Ein besonders leichtes Objektiv mit großer Blendenöffnung, höchster Bildqualität und verbesserter Bedienbarkeit für Fotos und Filme. Es bietet fortschrittliches optisches Design, zwei XA-Elemente (extrem asphärisch', 'Equipment', 'Pro Objekt', 00002399.00, 0000005, 'Low Stock', '2024-06-06 15:12:27', 'sony_sel_24-70MM.jpg'),
(3, 'verbranntes Toastbrot', 'lecker, aber krebserregend ;)', 'Equipment', 'Pro Objekt', 01000000.00, 0000001, 'Low Stock', '2024-06-06 15:17:46', 'toastbrot.jpg'),
(4, 'Canon EOS R5 Body', 'Canon EOS R5\r\n\r\n- 5 Achsen integrierter Bildstabilisator IBIS\r\n- 4K/120p RAW-Video im Vollformat\r\n- 8K RAW-Video im Vollformat mit interner Aufzeichnung\r\n- Volle kreative Freiheit bei deinen Filmaufnahmen\r\n- Zwei Speicherkarten-Steckplätze\r\n', 'Equipment', 'Pro Objekt', 00003499.00, 0000250, 'In Stock', '2024-06-06 15:20:48', 'canon_eos_r5_body.jpg'),
(5, 'Canon RF 1200mm F8L IS USM', 'Canon RF 1200mm F8L IS USM - Leichtes Superteleobjektiv\r\n\r\n- Hohe Lichtstärke\r\n- 4-Stufen-Bildstabilisator\r\n- 9-Lamellen-Irisblende\r\n- Konfigurierbare Bedienelemente\r\n- Hochwertige Vergütung\r\n- Ausgewogene Gewichtsverteilung\r\n- Blende in 1/8 Stufen\r\n- Vie', 'Equipment', 'Pro Objekt', 00023449.00, 0000000, 'Out of Stock', '2024-06-06 15:25:18', 'canon_rf_1200mm_f8l.jpg'),
(6, 'Sachtler SAC3012 System Video 30 Kohlefaser mit Stativkopf, EFP 2 MCF-Stativ, Mittelspinne und Abdeckung ', 'Sachtler SAC3012 System Video 30 Kohlefaser mit Stativkopf, EFP 2 MCF-Stativ, Mittelspinne und Abdeckung\r\n\r\nOb Du im Studio oder vor Ort arbeitest, Du brauchst einen Stativkopf, mit dem Du schnell und effektiv arbeiten kannst. Der Sachtler Video 30 HD ist', 'Equipment', 'Pro Objekt', 00013142.89, 0000033, 'In Stock', '2024-06-06 15:29:14', 'sachtler_stativ.jpg'),
(7, 'Canon Speedlite EL-10 ', 'Canon Speedlite EL-10\r\n\r\nDas Speedlite EL-10 bietet eine Wiederaufladezeit von nur ca. 1,5 Sekunden und lässt sich nahtlos in verknüpfte benutzerdefinierte Modi und eine Blitzfernsteuerung integrieren. Dank der Kompatibilität mit dem Multifunktions-Zubehö', 'Equipment', 'Pro Objekt', 00000299.00, 0000345, 'Out of Stock', '2024-06-06 15:44:50', 'canon_speedlite_el-10.jpg'),
(8, 'Sony Alpha 9 III mit FE 300mm F2.8 GM OSS G-Master ', '\r\nSony Alpha 9 III mit FE 300mm F2.8 GM OSS G-Master\r\n\r\nDie Alpha 9 III verfügt über einen neu entwickelten Exmor RS™ Bildsensor - den weltweit ersten Stacked 24,6-Megapixel-CMOS-Vollformat-Sensor mit Global Shutter-System. Eine Revolution im Kameradesign', 'Equipment', 'Pro Objekt', 00012499.00, 0000009, 'Low Stock', '2024-06-06 15:34:50', 'sony_alpha_9_III_mit_fe_300MM.jpg'),
(9, 'Hoppstar Expert Laurel ', 'Hoppstar Expert Laurel\r\n\r\nDie Hoppstar Expert ist die ideale Kinderkamera mit allen Funktionen, die man im täglichen Leben braucht. Mit zusätzlicher Selfie-Kamera auf der Rückseite, 16 GB Speicherplatz für bis zu 8.000 Fotos und einer Videokamera Funktion', 'Equipment', 'Pro Person', 00000089.90, 0004321, 'In Stock', '2024-06-06 15:37:19', 'hoppstar_expert_laurel.jpg'),
(10, 'B+W Vario-Filter ND 82mm MRC XS-Pro Digital Nano ', 'Variabler Graufilter ND Vario\r\n\r\nFlexible Lichtkontrolle ohne Filterwechsel', 'Equipment', 'Pro Objekt', 00000201.05, 0000555, 'In Stock', '2024-06-06 15:42:11', 'nd_filter.jpg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `produktdetail`
--

CREATE TABLE `produktdetail` (
  `PD_ID` int(11) NOT NULL,
  `FK_O_ID` int(11) NOT NULL,
  `FK_P_ID` int(11) NOT NULL,
  `MENGE` int(7) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `service`
--

CREATE TABLE `service` (
  `S_ID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `BESCHREIBUNG` varchar(255) NOT NULL,
  `KATEGORIE` enum('Alles','Innerhaus','Außerhaus') NOT NULL DEFAULT 'Alles',
  `ABRECHNUNGSART` enum('Pro Bild','Pro Person','Pro Objekt','Pro Familie') NOT NULL DEFAULT 'Pro Bild',
  `PREIS` decimal(10,2) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000.00,
  `LAGERSTATUS` enum('In Stock','Low Stock','Out of Stock') NOT NULL DEFAULT 'In Stock',
  `ZEITSTEMPEL` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `DATEIPFAD` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `service`
--

INSERT INTO `service` (`S_ID`, `NAME`, `BESCHREIBUNG`, `KATEGORIE`, `ABRECHNUNGSART`, `PREIS`, `LAGERSTATUS`, `ZEITSTEMPEL`, `DATEIPFAD`) VALUES
(1, 'CEWE FOTOBUCH Groß ', '- Praktisches Hochformat: ca. 21 x - 28 cm | ca. A4 \r\n- Besonders beliebt als Reise- oder Jahrbuch\r\n- Hochwertige Veredelung mit fühlbaren Effekten\r\n- Passende Geschenkbox für dieses Format erhältlich\r\n- FSC®-zertifiziertes Fotoprodukt \r\n\r\n', 'Innerhaus', 'Pro Objekt', 00000019.95, 'In Stock', '2024-06-06 16:02:49', 'cewe_fotobuch.jpg'),
(2, 'CEWE FOTOBUCH Klein', '- Praktisches Hochformat: ca. 21 x - 28 cm | ca. A5 \r\n- Besonders beliebt als Reise- oder Jahrbuch\r\n- Hochwertige Veredelung mit fühlbaren Effekten\r\n- Passende Geschenkbox für dieses Format erhältlich\r\n- FSC®-zertifiziertes Fotoprodukt \r\n\r\n', 'Innerhaus', 'Pro Objekt', 00000014.95, 'In Stock', '2024-06-06 16:02:49', 'cewe_fotobuch.jpg'),
(3, 'Color-Kleinbildfilme', '- Filmentwicklung erfolgt immer in Verbindung mit Fotoabzügen\r\n- Fotos im Format 9 x 13 cm bis 13 x 18 cm\r\n- Fotoabzüge in Hochglanz und Matt - Digitalisierung auf Foto-CD möglich\r\n- Digitale Belichtung der Fotos mit 300 dpi ', 'Alles', 'Pro Bild', 00000002.30, 'In Stock', '2024-06-06 16:11:02', 'analogbilder_entwickeln.jpg'),
(4, 'Schwarz/Weiß-Kleinbildfilme\r\n', '- Fotos im Format 10x15 und 13x18\r\n- Fotoabzüge in Hochglanz\r\n- Digitalisierung auf dm-Foto-CD möglich ', 'Innerhaus', 'Pro Bild', 00000001.75, 'Low Stock', '2024-06-06 16:11:02', 'analogbilder_entwickeln.jpg'),
(5, 'Diafilme', 'Filmentwicklung möglich (Bitte beachte, dass wir keine Rahmung mehr anbieten können und wir die Erstellung von Fotoabzügen nur noch von gerahmten Dias vornehmen)', 'Innerhaus', 'Pro Bild', 00000003.20, 'In Stock', '2024-06-06 16:11:35', 'analogbilder_entwickeln.jpg'),
(6, 'Drohnenaufnahmen', 'Hier findest Du Drohnenaufnahmen, auch Luftbildaufnahmen genannt,  aus Bielefeld.\r\n\r\nAls professioneller Drohnenpilot helfe ich Dir einen anderen Blick auf die Dinge zu bekommen. Fotos und/oder Videos aus der Luft bereichern Deine Hochzeitsreportage oder ', 'Außerhaus', 'Pro Familie', 00000150.00, 'In Stock', '2024-06-06 16:15:15', 'drohnenaufnahmen.jpg'),
(7, 'Videografie', 'Videografie made in Bielefeld.\r\n\r\nBewegte Bilder faszinieren uns: Egal, ob Du einen Videografen für Deine Hochzeit suchst oder einen Film für YouTube drehen willst. Vielleicht möchtest Du auch einen Veranstaltungsmitschnitt, Imagefilm oder eine Diashow vo', 'Außerhaus', 'Pro Person', 00000060.00, 'In Stock', '2024-06-06 16:17:42', 'videografie.jpg'),
(8, '30 Minuten Fotoshooting', '- Inkl. 1 Bild als Ausdruck und Datei\r\n- Wähle dein Lieblingsfoto aus ca. 80 verschiedenen Aufnahmen aus\r\n', 'Innerhaus', 'Pro Person', 00000049.99, 'In Stock', '2024-06-06 16:24:47', 'porträtfoto.jpg'),
(9, '60 Minuten Fotoshooting', '- Inkl. 1 Bild aus Ausdruck und Datei\r\n- Wähle dein Lieblingsfoto aus ca. 120 verschiedenen Aufnahmen aus\r\n', 'Innerhaus', 'Pro Person', 00000099.99, 'In Stock', '2024-06-06 16:24:47', 'porträtfoto.jpg'),
(10, 'Familienfotografie', '„Unsere wertvollsten Erinnerungen sind niemals inszeniert oder\r\nerzwungen“', 'Außerhaus', 'Pro Familie', 00000025.00, 'In Stock', '2024-06-06 16:31:13', 'familienbilder.jpg');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `servicedetail`
--

CREATE TABLE `servicedetail` (
  `SD_ID` int(11) NOT NULL,
  `FK_O_ID` int(11) NOT NULL,
  `FK_S_ID` int(11) NOT NULL,
  `MENGE` int(7) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `servicepersonal`
--

CREATE TABLE `servicepersonal` (
  `SP_ID` int(11) NOT NULL,
  `FK_P_ID` int(11) NOT NULL,
  `FK_S_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `U_ID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `VORNAME` varchar(255) NOT NULL,
  `Geburtsdatum` date NOT NULL DEFAULT current_timestamp(),
  `FK_A_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `zubehör`
--

CREATE TABLE `zubehör` (
  `Z_ID` int(11) NOT NULL,
  `ARTIKELNAME` varchar(255) CHARACTER SET utf16 COLLATE utf16_german2_ci NOT NULL,
  `ARTIKELBESCHREIBUNG` text CHARACTER SET utf16 COLLATE utf16_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `zubehör`
--

INSERT INTO `zubehör` (`Z_ID`, `ARTIKELNAME`, `ARTIKELBESCHREIBUNG`) VALUES
(1, 'Sony USB-C Ladekabel', 'Das Sony USB-C Ladekabel ist ideal zum schnellen und effizienten Aufladen Ihrer Sony Kameras und anderer Geräte. Robustes Design für dauerhafte Nutzung.'),
(2, 'Canon USB-C Ladekabel', 'Das Canon USB-C Ladekabel ermöglicht schnelles Aufladen und Datentransfer für Canon Kameras. Hohe Qualität und Zuverlässigkeit.'),
(3, 'Sony NP-FZ100 Akku', 'Der Sony NP-FZ100 ist ein wiederaufladbarer Akku für Sony Alpha Kameras, der lange Laufzeiten und zuverlässige Leistung bietet.'),
(4, 'Canon LP-E6N Akku', 'Der Canon LP-E6N ist ein leistungsstarker Akku für Canon DSLR- und spiegellose Kameras, der eine lange Betriebsdauer und Zuverlässigkeit gewährleistet.'),
(5, 'Sony SDXC 128GB Speicherkarte', 'Die Sony SDXC 128GB Speicherkarte bietet hohe Speicherkapazität und schnelle Schreibgeschwindigkeiten, ideal für 4K-Videoaufnahmen und Serienbildaufnahmen.'),
(6, 'Canon CFexpress 256GB Speicherkarte', 'Die Canon CFexpress 256GB Speicherkarte bietet ultraschnelle Datenübertragung und hohe Speicherkapazität, perfekt für professionelle Fotografie und Videografie.'),
(7, 'Sony Kamerareinigungsset', 'Das Sony Kamerareinigungsset enthält alles, was Sie zur Pflege und Reinigung Ihrer Kamera benötigen, einschließlich Reinigungslösung, Mikrofasertuch und Pinsel.'),
(8, 'Canon Linsenreinigungskit', 'Das Canon Linsenreinigungskit enthält spezielle Reinigungslösungen und Werkzeuge zur Pflege Ihrer Objektive und Kamerasensoren, ideal für klare Aufnahmen.'),
(9, 'Sony Akku-Ladegerät BC-QZ1', 'Das Sony Akku-Ladegerät BC-QZ1 lädt Ihre Sony NP-FZ100 Akkus schnell und effizient auf, ideal für unterwegs und zu Hause.'),
(10, 'Canon LC-E6E Akkuladegerät', 'Das Canon LC-E6E Akkuladegerät ist kompatibel mit LP-E6N Akkus und bietet schnelles und zuverlässiges Aufladen, perfekt für alle Canon Fotografen.');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`A_ID`);

--
-- Indizes für die Tabelle `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`C_ID`),
  ADD KEY `CFK_U_ID` (`FK_U_ID`);

--
-- Indizes für die Tabelle `lieferumfang`
--
ALTER TABLE `lieferumfang`
  ADD PRIMARY KEY (`L_ID`),
  ADD KEY `LFK_P_ID` (`FK_P_ID`),
  ADD KEY `LFK_Z_ID` (`FK_Z_ID`);

--
-- Indizes für die Tabelle `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`O_ID`),
  ADD KEY `OFK_C_ID` (`FK_C_ID`);

--
-- Indizes für die Tabelle `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`PSN_ID`);

--
-- Indizes für die Tabelle `produkt`
--
ALTER TABLE `produkt`
  ADD PRIMARY KEY (`P_ID`);

--
-- Indizes für die Tabelle `produktdetail`
--
ALTER TABLE `produktdetail`
  ADD PRIMARY KEY (`PD_ID`),
  ADD KEY `PFK_O_ID` (`FK_O_ID`),
  ADD KEY `PFK_P_ID` (`FK_P_ID`);

--
-- Indizes für die Tabelle `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`S_ID`);

--
-- Indizes für die Tabelle `servicedetail`
--
ALTER TABLE `servicedetail`
  ADD PRIMARY KEY (`SD_ID`),
  ADD KEY `SFK_O_ID` (`FK_O_ID`),
  ADD KEY `SDFK_S_ID` (`FK_S_ID`);

--
-- Indizes für die Tabelle `servicepersonal`
--
ALTER TABLE `servicepersonal`
  ADD PRIMARY KEY (`SP_ID`),
  ADD KEY `SPFK_P_ID` (`FK_P_ID`),
  ADD KEY `SPFK_S_ID` (`FK_S_ID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`U_ID`),
  ADD KEY `UFK_A_ID` (`FK_A_ID`),
  ADD KEY `UFK_G_ID` (`Geburtsdatum`);

--
-- Indizes für die Tabelle `zubehör`
--
ALTER TABLE `zubehör`
  ADD PRIMARY KEY (`Z_ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `adresse`
--
ALTER TABLE `adresse`
  MODIFY `A_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `customer`
--
ALTER TABLE `customer`
  MODIFY `C_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `lieferumfang`
--
ALTER TABLE `lieferumfang`
  MODIFY `L_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `orders`
--
ALTER TABLE `orders`
  MODIFY `O_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `personal`
--
ALTER TABLE `personal`
  MODIFY `PSN_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `produkt`
--
ALTER TABLE `produkt`
  MODIFY `P_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `produktdetail`
--
ALTER TABLE `produktdetail`
  MODIFY `PD_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `service`
--
ALTER TABLE `service`
  MODIFY `S_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `servicedetail`
--
ALTER TABLE `servicedetail`
  MODIFY `SD_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `servicepersonal`
--
ALTER TABLE `servicepersonal`
  MODIFY `SP_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `U_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `zubehör`
--
ALTER TABLE `zubehör`
  MODIFY `Z_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `CFK_U_ID` FOREIGN KEY (`FK_U_ID`) REFERENCES `user` (`U_ID`);

--
-- Constraints der Tabelle `lieferumfang`
--
ALTER TABLE `lieferumfang`
  ADD CONSTRAINT `LFK_P_ID` FOREIGN KEY (`FK_P_ID`) REFERENCES `produkt` (`P_ID`),
  ADD CONSTRAINT `LFK_Z_ID` FOREIGN KEY (`FK_Z_ID`) REFERENCES `zubehör` (`Z_ID`);

--
-- Constraints der Tabelle `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `OFK_C_ID` FOREIGN KEY (`FK_C_ID`) REFERENCES `customer` (`C_ID`);

--
-- Constraints der Tabelle `produktdetail`
--
ALTER TABLE `produktdetail`
  ADD CONSTRAINT `PFK_O_ID` FOREIGN KEY (`FK_O_ID`) REFERENCES `orders` (`O_ID`),
  ADD CONSTRAINT `PFK_P_ID` FOREIGN KEY (`FK_P_ID`) REFERENCES `produkt` (`P_ID`);

--
-- Constraints der Tabelle `servicedetail`
--
ALTER TABLE `servicedetail`
  ADD CONSTRAINT `SDFK_S_ID` FOREIGN KEY (`FK_S_ID`) REFERENCES `service` (`S_ID`),
  ADD CONSTRAINT `SFK_O_ID` FOREIGN KEY (`FK_O_ID`) REFERENCES `orders` (`O_ID`);

--
-- Constraints der Tabelle `servicepersonal`
--
ALTER TABLE `servicepersonal`
  ADD CONSTRAINT `SPFK_P_ID` FOREIGN KEY (`FK_P_ID`) REFERENCES `produkt` (`P_ID`),
  ADD CONSTRAINT `SPFK_S_ID` FOREIGN KEY (`FK_S_ID`) REFERENCES `service` (`S_ID`);

--
-- Constraints der Tabelle `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `UFK_A_ID` FOREIGN KEY (`FK_A_ID`) REFERENCES `adresse` (`A_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
