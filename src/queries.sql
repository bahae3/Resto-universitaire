-- copier had la premiere ligne pour creer la base de donnees b3da bou7dha
CREATE DATABASE resto_univ;

-- Apres dekhlo la base de donnees w executez had les requetes kamlin de9a whda
-- copier coller 😉
-- n'oubliez pas de faire start de apache et mysql f xampp
CREATE TABLE User (
	idUser int AUTO_INCREMENT PRIMARY KEY,
	nom VARCHAR(255) NOT NULL,
	prenom VARCHAR(255) NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	mdp VARCHAR(255) NOT NULL,
	codeUser INT UNIQUE NOT NULL,
	estPersonnel BOOLEAN NOT NULL
);

CREATE TABLE Categorie (
	idCategorie INT AUTO_INCREMENT PRIMARY KEY,
	nom VARCHAR(255) NOT NULL
);

CREATE TABLE Menu (
	idMenu INT AUTO_INCREMENT PRIMARY KEY,
	idCategorie INT NOT NULL,
	nom VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	jourMenu VARCHAR(255),
	prix FLOAT NOT NULL,
	FOREIGN KEY (idCategorie) REFERENCES Categorie(idCategorie)
);


CREATE TABLE Commande (
	idCommande INT AUTO_INCREMENT PRIMARY KEY,
	idMenu INT NOT NULL,
	idUser INT NOT NULL,
	quantite INT NOT NULL,
	etat VARCHAR(255),
    numCommande INT NOT NULL,
	FOREIGN KEY (idMenu) REFERENCES Menu (idMenu),
	FOREIGN KEY (idUser) REFERENCES User (idUser)
);


-- Quelques exemples pour remplir les tableaux de la BD:
-- Pour la table Categorie
INSERT INTO Categorie (nom) VALUES 
('Boissons'),
('Entrées'),
('Plats principaux'),
('Desserts'),
('Menus spéciaux');

-- Pour la table Menu
-- Boissons
INSERT INTO Menu (idCategorie, nom, description, prix) VALUES 
(1, 'Thé à la menthe', 'Thé vert à la menthe fraîche', 8.0),
(1, 'Jus d''orange', 'Jus d''orange frais pressé', 12.0),
(1, 'Eau minérale', 'Eau minérale gazeuse ou plate', 5.0),
(1, 'Café noir', 'Café noir à la manière marocaine', 12.0),
(1, 'Café au lait', 'Café avec du lait chaud', 15.0),
(1, 'Jus de citron', 'Boisson rafraîchissante au citron faite maison', 15.0),
(1, 'Smoothie aux fruits', 'Boisson aux fruits mixés frais', 15.0);

-- Entrées
INSERT INTO Menu (idCategorie, nom, description, jourMenu, prix) VALUES 
(2, 'Harira', 'Soupe marocaine aux légumes et aux épices', 'Lundi', 15.0),
(2, 'Briouates', 'Petits feuilletés farcis à la viande ou aux légumes', 'Mardi', 18.0),
(2, 'Salade marocaine', 'Salade de tomates, concombres et poivrons', NULL, 12.0);

-- Plats principaux
INSERT INTO Menu (idCategorie, nom, description, jourMenu, prix) VALUES 
(3, 'Couscous royal', 'Couscous traditionnel avec agneau, poulet et légumes', 'Vendredi', 55.0),
(3, 'Tagine de poulet', 'Plat de poulet mijoté aux olives et aux épices', NULL, 50.0),
(3, 'Tagine kefta', 'Boulettes de viande hachée dans une sauce tomate épicée', 'Mercredi', 35.0);

-- Desserts
INSERT INTO Menu (idCategorie, nom, description, jourMenu, prix) VALUES 
(4, 'Pastilla aux amandes', 'Feuilles de brick farcies aux amandes et au miel', 'Lundi', 20.0),
(4, 'Seffa medfouna', 'Vermicelles sucrés avec cannelle et fruits secs', 'Mardi', 18.0),
(4, 'Pâtisseries marocaines', 'Assortiment de gâteaux traditionnels', 'Mercredi', 22.0);

-- Menus spéciaux
INSERT INTO Menu (idCategorie, nom, description, jourMenu, prix) VALUES 
(5, 'Menu végétarien', 'Menu sans viande avec des plats marocains végétariens', 'Lundi', 25.0),
(5, 'Menu découverte', 'Dégustation de plats marocains typiques', 'Mardi', 40.0),
(5, 'Menu famille', 'Menu complet pour une famille de quatre personnes', 'Jeudi', 120.0);

-- Menu supplémentaire
INSERT INTO Menu (idCategorie, nom, description, jourMenu, prix) VALUES 
(1, 'Tajine de poisson', 'Tajine de poisson avec légumes et épices', 'Mardi', 40.0),
(3, 'Tajine d''agneau', 'Tajine d''agneau sucré-salé aux pruneaux', 'Mercredi', 45.0),
(4, 'Chebakia', 'Pâtisserie marocaine en forme de fleur frite', 'Lundi', 12.0);
