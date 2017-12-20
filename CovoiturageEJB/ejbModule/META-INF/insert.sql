insert into utilisateur (username, password) values ('toto', 'toto');
insert into utilisateur (username, password) values ('jean', 'jean');
insert into utilisateur (username, password) values ('alice', 'alice');
insert into utilisateur (username, password) values ('marie', 'marie');
insert into utilisateur (username, password) values ('admin', 'admin');

insert into ville (nom) values ('Lyon');
insert into ville (nom) values ('Bourges');
insert into ville (nom) values ('Marseille');
insert into ville (nom) values ('Dijon');
insert into ville (nom) values ('Paris');
insert into ville (nom) values ('Toulouse');

insert into trajet (id, tarif, nombreplaces, typevoiture, conducteur_username, villearrivee_nom, villedepart_nom, date) values (1,20,4,'Break','toto','Lyon','Bourges', parsedatetime('1917-05-03 21:02', 'yyyy-MM-dd hh:mm'));
insert into trajet (id, tarif, nombreplaces, typevoiture, conducteur_username, villearrivee_nom, villedepart_nom, date) values (2,35,4,'Break','marie','Lyon','Marseille', parsedatetime('1917-02-24 18:20', 'yyyy-MM-dd hh:mm'));
insert into trajet (id, tarif, nombreplaces, typevoiture, conducteur_username, villearrivee_nom, villedepart_nom, date) values (3,29,6,'SUV','alice','Dijon','Paris', parsedatetime('1917-11-01 8:00', 'yyyy-MM-dd hh:mm'));

insert into etapes( trajet_id, tarif, ville_id ) values(1,15,'Dijon');
insert into etapes( trajet_id, tarif, ville_id ) values(2,20,'Toulouse');
insert into etapes( trajet_id, tarif, ville_id ) values(3,19,'Lyon');
