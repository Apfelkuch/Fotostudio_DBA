-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 10. Jun 2024 um 12:23
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

--
-- Daten für Tabelle `customer`
--

INSERT INTO `customer` (`C_ID`, `BENUTZERNAME`, `PASSWORT`, `EMAIL`, `ROLLE`, `FK_U_ID`, `ZEITSTEMPEL`, `ISTMITARBEITER`) VALUES
(1, 'developer', '12345', 'developer@dev.de', 2, 16, '2024-06-07 13:36:40', 1),
(2, 'admin', '12345', 'admin@dev.de', 1, 16, '2024-06-07 13:37:52', 1),
(4, 'nobody', '12345', 'nobody@dev.de', 0, 8, '2024-06-07 13:38:38', 0),
(6, 'ShutterStar', '12345', 'shutter@example.com', 0, 1, '2024-06-07 13:49:24', 0),
(7, 'LensLover', '67890', 'lenslover@example.com', 0, 2, '2024-06-07 13:49:24', 0),
(8, 'PixelPainter', '54321', 'pixel@example.com', 0, 3, '2024-06-07 13:49:24', 0),
(9, 'FlashFocus', '09876', 'flash@example.com', 0, 4, '2024-06-07 13:49:24', 0),
(10, 'ApertureArtist', '543210', 'aperture@example.com', 0, 5, '2024-06-07 13:49:24', 0),
(11, 'FotoMaster', '33445', 'fotomaster@domain.com', 0, 18, '2024-06-09 10:33:25', 0),
(12, 'CamExpert', '77654', 'camexpert@domain.com', 0, 19, '2024-06-09 10:33:25', 0),
(14, 'Jürgen', 'jürgen', 'jfranz@hsbi.de', 0, 21, '2024-06-10 09:02:34', 0);

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

--
-- Daten für Tabelle `lieferumfang`
--

INSERT INTO `lieferumfang` (`L_ID`, `MENGE`, `FK_Z_ID`, `FK_P_ID`) VALUES
(1, 0000002, 6, 4),
(2, 0000005, 3, 8),
(3, 0000010, 7, 2),
(4, 0000001, 10, 4),
(5, 0000033, 1, 1),
(6, 0000002, 6, 4),
(7, 0000005, 3, 8),
(8, 0000010, 7, 2),
(9, 0000001, 10, 4),
(10, 0000033, 1, 1);

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

--
-- Daten für Tabelle `orders`
--

INSERT INTO `orders` (`O_ID`, `FK_C_ID`, `ZEITSTEMPEL`, `LIEFERDATUM`) VALUES
(1, 9, '2020-02-04 23:00:00', '2020-02-18'),
(2, 10, '2020-05-21 22:00:00', '2020-06-03'),
(3, 8, '2020-08-19 22:00:00', '2020-08-31'),
(4, 4, '2021-02-04 23:00:00', '2021-02-18'),
(5, 6, '2021-05-21 22:00:00', '2021-06-03'),
(6, 7, '2021-08-13 22:00:00', '2021-08-27'),
(7, 9, '2022-01-29 23:00:00', '2022-02-12'),
(8, 10, '2022-04-08 22:00:00', '2022-04-22'),
(9, 4, '2023-02-04 23:00:00', '2023-02-18'),
(10, 6, '2023-05-21 22:00:00', '2023-06-03'),
(11, 7, '2023-08-13 22:00:00', '2023-08-27'),
(12, 9, '2024-01-04 23:00:00', '2024-01-17'),
(13, 8, '2024-01-09 23:00:00', '2024-01-22'),
(14, 10, '2024-02-13 23:00:00', '2024-02-26'),
(15, 9, '2024-02-17 23:00:00', '2024-03-02'),
(16, 4, '2024-03-21 23:00:00', '2024-04-03'),
(17, 10, '2024-03-24 23:00:00', '2024-04-06'),
(18, 6, '2024-04-04 22:00:00', '2024-04-17'),
(19, 4, '2024-04-27 22:00:00', '2024-05-10'),
(20, 7, '2024-05-17 22:00:00', '2024-05-31'),
(21, 6, '2024-05-29 22:00:00', '2024-06-11'),
(62, 11, '2018-03-15 09:30:00', '2018-03-19'),
(63, 12, '2018-07-22 09:45:00', '2018-08-02'),
(64, 11, '2019-01-10 07:15:00', '2019-01-21'),
(65, 12, '2019-06-20 13:30:00', '2019-06-28'),
(66, 11, '2020-04-05 07:45:00', '2020-04-16'),
(67, 12, '2020-11-05 12:00:00', '2020-11-16'),
(68, 11, '2021-08-10 12:20:00', '2021-08-23'),
(69, 12, '2022-01-12 16:10:00', '2022-01-25'),
(70, 14, '2024-06-10 09:03:19', '2024-06-24'),
(71, 14, '2024-06-10 09:03:25', '2024-06-24'),
(72, 14, '2024-06-10 09:03:30', '2024-06-24');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `personal`
--

CREATE TABLE `personal` (
  `PSN_ID` int(11) NOT NULL,
  `FK_U_ID` int(11) NOT NULL,
  `AUFGABE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `personal`
--

INSERT INTO `personal` (`PSN_ID`, `FK_U_ID`, `AUFGABE`) VALUES
(1, 3, 'Entwicklung von Color-Kleinbildfilmen'),
(2, 7, 'Entwicklung von Schwarz/Weiß-Kleinbildfilmen'),
(3, 1, 'Bearbeitung und Digitalisierung von Diafilmen'),
(4, 5, 'Bearbeitung und Schnitt von Drohnenaufnahmen'),
(5, 4, 'Erstellung und Bearbeitung von Videografieprojekten'),
(6, 8, 'Durchführung von 30 Minuten Fotoshootings'),
(7, 4, 'Organisation und Planung und Durchführung von 60 Minuten Fotoshootings'),
(8, 2, 'Durchführung von Familienfotografie-Sessions'),
(9, 2, 'Kundenbetreuung und Unterstützung bei der Filmentwicklung'),
(10, 5, 'Verwaltung und Bearbeitung von Videoprojekten und Fotoshootings');

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
(2, 'Sony SEL 24-70mm/2,8 G-Master Version 2', 'Das Sony SEL2470GM2 Vollformat-Objektiv: Ein besonders leichtes Objektiv mit großer Blendenöffnung, höchster Bildqualität und verbesserter Bedienbarkeit für Fotos und Filme. Es bietet fortschrittliches optisches Design, zwei XA-Elemente (extrem asphärisch', 'Equipment', 'Pro Objekt', 00002399.00, 0000004, 'Low Stock', '2024-06-10 09:03:19', 'sony_sel_24-70MM.jpg'),
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

--
-- Daten für Tabelle `produktdetail`
--

INSERT INTO `produktdetail` (`PD_ID`, `FK_O_ID`, `FK_P_ID`, `MENGE`) VALUES
(1, 3, 5, 0000002),
(2, 8, 2, 0000003),
(3, 15, 9, 0000001),
(4, 4, 7, 0000004),
(5, 12, 3, 0000002),
(6, 20, 6, 0000001),
(7, 1, 10, 0000003),
(8, 17, 4, 0000002),
(9, 6, 1, 0000001),
(10, 19, 8, 0000004),
(11, 70, 2, 0000005),
(12, 72, 3, 0000001);

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

--
-- Daten für Tabelle `servicedetail`
--

INSERT INTO `servicedetail` (`SD_ID`, `FK_O_ID`, `FK_S_ID`, `MENGE`) VALUES
(1, 7, 9, 0000005),
(2, 15, 4, 0000008),
(3, 3, 6, 0000003),
(4, 18, 2, 0000006),
(5, 12, 8, 0000007),
(6, 10, 5, 0000009),
(7, 5, 1, 0000002),
(8, 20, 7, 0000004),
(9, 1, 3, 0000010),
(10, 17, 10, 0000001);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `servicepersonal`
--

CREATE TABLE `servicepersonal` (
  `SP_ID` int(11) NOT NULL,
  `FK_PSN_ID` int(11) NOT NULL,
  `FK_S_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `servicepersonal`
--

INSERT INTO `servicepersonal` (`SP_ID`, `FK_PSN_ID`, `FK_S_ID`) VALUES
(1, 1, 3),
(2, 3, 5),
(3, 8, 10),
(4, 4, 6),
(5, 5, 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `U_ID` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `VORNAME` varchar(255) NOT NULL,
  `GEBURTSDATUM` date NOT NULL DEFAULT current_timestamp(),
  `FK_A_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`U_ID`, `NAME`, `VORNAME`, `GEBURTSDATUM`, `FK_A_ID`) VALUES
(1, 'Schlaubi', 'Max', '1992-04-15', 3),
(2, 'Wunderlich', 'Paula', '1986-11-08', 7),
(3, 'Klugkopf', 'Bert', '1979-02-22', 1),
(4, 'Schnellschuss', 'Ina', '1990-07-12', 5),
(5, 'Gagamel', 'Otto', '1983-10-19', 10),
(6, 'Chaos', 'Lara', '1991-05-30', 2),
(7, 'Witzbold', 'Tom', '1987-03-25', 9),
(8, 'Flitzpiepe', 'Nina', '1995-12-04', 6),
(9, 'Blitzmerker', 'Kai', '1982-01-16', 4),
(16, 'NONE', 'NONE', '1899-12-12', 2),
(18, 'Streber', 'Tina', '1994-10-26', 5),
(19, 'Kicherer', 'Fritz', '1993-02-14', 2),
(21, 'Jürgen', 'Hans', '2020-06-20', 5);

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
  ADD PRIMARY KEY (`PSN_ID`),
  ADD KEY `PFK_U_ID` (`FK_U_ID`);

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
  ADD KEY `SPFK_S_ID` (`FK_S_ID`),
  ADD KEY `SPFK_PSN_ID` (`FK_PSN_ID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`U_ID`),
  ADD KEY `UFK_A_ID` (`FK_A_ID`),
  ADD KEY `UFK_G_ID` (`GEBURTSDATUM`);

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
  MODIFY `C_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT für Tabelle `lieferumfang`
--
ALTER TABLE `lieferumfang`
  MODIFY `L_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `orders`
--
ALTER TABLE `orders`
  MODIFY `O_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT für Tabelle `personal`
--
ALTER TABLE `personal`
  MODIFY `PSN_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `produkt`
--
ALTER TABLE `produkt`
  MODIFY `P_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `produktdetail`
--
ALTER TABLE `produktdetail`
  MODIFY `PD_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT für Tabelle `service`
--
ALTER TABLE `service`
  MODIFY `S_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `servicedetail`
--
ALTER TABLE `servicedetail`
  MODIFY `SD_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `servicepersonal`
--
ALTER TABLE `servicepersonal`
  MODIFY `SP_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `U_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

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
-- Constraints der Tabelle `personal`
--
ALTER TABLE `personal`
  ADD CONSTRAINT `PFK_U_ID` FOREIGN KEY (`FK_U_ID`) REFERENCES `user` (`U_ID`);

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
  ADD CONSTRAINT `SPFK_PSN_ID` FOREIGN KEY (`FK_PSN_ID`) REFERENCES `personal` (`PSN_ID`),
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
