insert into utilisateur (username, password) values ('toto', 'toto');
insert into utilisateur (username, password) values ('jean', 'jean');

insert into ville (nom) values ('Lyon');
insert into ville (nom) values ('Bourges');

insert into trajet (id, tarif, nombreplaces, typevoiture, conducteur_username, villearrivee_nom, villedepart_nom) values (1,20,4,'Break','toto','Lyon','Bourges');

insert into reservation(id, placesreservees, utilisateur_username, villearrivee_nom) values (1, 3, 'jean', 'Lyon');
insert into trajet_reservation(trajet_id, demandes_id) values (1,1);

