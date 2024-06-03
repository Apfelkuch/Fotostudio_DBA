-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Jun 2024 um 13:07
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
-- Tabellenstruktur für Tabelle `geburtsdatum`
--

CREATE TABLE `geburtsdatum` (
  `G_ID` int(11) NOT NULL,
  `TAG` int(2) UNSIGNED DEFAULT 1 COMMENT 'NULL ist zulässig, da das Geburtsdatum keine Pflicht ist',
  `MONAT` int(2) UNSIGNED DEFAULT 1 COMMENT 'NULL ist zulässig, da das Geburtsdatum keine Pflicht ist',
  `JAHR` int(4) UNSIGNED DEFAULT 1900 COMMENT 'NULL ist zulässig, da das Geburtsdatum keine Pflicht ist'
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
  `LETZTE ÄNDERUNG` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

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
  `PREIS` int(7) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000,
  `LAGERSTATUS` enum('In Stock','Low Stock','Out of Stock') NOT NULL DEFAULT 'In Stock',
  `ZEITSTEMPEL` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

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
  `FK_G_ID` int(11) NOT NULL,
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
-- Indizes für die Tabelle `geburtsdatum`
--
ALTER TABLE `geburtsdatum`
  ADD PRIMARY KEY (`G_ID`);

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
  ADD KEY `UFK_G_ID` (`FK_G_ID`);

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
  MODIFY `A_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `customer`
--
ALTER TABLE `customer`
  MODIFY `C_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `geburtsdatum`
--
ALTER TABLE `geburtsdatum`
  MODIFY `G_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  MODIFY `P_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `produktdetail`
--
ALTER TABLE `produktdetail`
  MODIFY `PD_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `service`
--
ALTER TABLE `service`
  MODIFY `S_ID` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `Z_ID` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `UFK_A_ID` FOREIGN KEY (`FK_A_ID`) REFERENCES `adresse` (`A_ID`),
  ADD CONSTRAINT `UFK_G_ID` FOREIGN KEY (`FK_G_ID`) REFERENCES `geburtsdatum` (`G_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
