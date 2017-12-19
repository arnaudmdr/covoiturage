insert into utilisateur (username, password) values ('toto', 'toto');
insert into utilisateur (username, password) values ('jean', 'jean');
insert into utilisateur (username, password) values ('admin', 'admin');

insert into ville (nom) values ('Lyon');
insert into ville (nom) values ('Bourges');

insert into trajet (id, tarif, nombreplaces, typevoiture, conducteur_username, villearrivee_nom, villedepart_nom, date) values (1,20,4,'Break','toto','Lyon','Bourges', parsedatetime('1917-05-03 21:02', 'yyyy-MM-dd hh:mm'));


