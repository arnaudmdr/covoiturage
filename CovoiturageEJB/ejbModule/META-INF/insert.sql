insert into utilisateur (id, username, password, dtype) values (1, 'toto', 'toto', 'Conducteur');
insert into utilisateur (id, username, password, dtype) values (2, 'toto', 'toto', 'Passager');
insert into utilisateur (id, username, password, dtype) values (2, 'jean', 'jean', 'Conducteur');

insert into ville (id, nom) values (1, 'Lyon');
insert into ville (id, nom) values (2, 'Bourges');

insert into trajet (id, tarif, nombreplaces, villearrivee_id, villedepart_id) values (1,20,4,1,2);

insert into lienconducteurtrajet (conducteur_id, trajet_id) values (1,1);
