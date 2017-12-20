# Application Web de covoiturage

## Présentation
Cette application a été réalisée dans le cadre d'un projet scolaire à l'INSA. Elle propose de simuler un site de covoiturage, en excluant les transactions financières.

## Comment utiliser?

Le site est accessible à l'adresse : localhost:port/CovoiturageWeb/

Il existe 5 utilisateurs enregistrés :
- admin/admin : administrateur
- marie/marie : voyageur
- jean/jean : voyageur
- alice/alice : voyageur
- toto/toto : voyageur

6 villes enregistrées :
- Lyon
- Bourges
- Marseille
- Dijon
- Paris
- Toulouse

Ainsi que 7 types de véhicule.
L'administrateur, une fois connecté, peut rajouter des villes ainsi que des types de véhicules.

## Fonctionnalités
- Un visiteur anonyme arrive sur une page présentant un formulaire de connexion ainsi que la liste des trajets disponibles sur le site.
- Si l'administrateur se connecte, il est redirigé vers une page où il peut alimenter la liste des villes ainsi que la liste des types de véhicules

Si un voyageur se connecte, il est redirigé vers une page où il peut :
- Visionner les trajets où il sera passager (le tarif sera adapté en fonction du nombre de places réservées ainsi que de la ville d'arrivée)
- Visionner les trajets où il sera conducteur
- Visionner les trajets disponibles et réserver un trajet
- Accepter ou refuser une réservation pour un covoiturage
- Proposer un trajet
- Se déconnecter

#### Précisions sur la proposition de trajet : 
L'utilisateur va :
- Renseigner la ville de départ/arrivée
- Le tarif du voyage entier
- Le type de véhicule
- Le nombre de places disponibles
- La date de départ (jour - mois - heure - minutes)
- Si il y en a, les étapes (Ville - tarif associé à l'étape)

#### Précisions sur la réservation de trajet :
Lors de la réservation d'un trajet, l'utilisateur va : 
- Selectionner sa ville d'arrivée (soit terminus ou étape)
- Le nombre de places qu'il veut réserver

Après avoir validé, une demande est envoyée au conducteur du trajet. La requète échoue si le nombre de places demandé est supérieur au nombre de places libres dans le trajet.

La demande apparaîtra sur l'écran d'accueil du conducteur, qui choisira, ou non, d'accepter la demande. Si la demande est acceptée, l'utilisateur sera ajouter à la liste des passagers.

## Modèle de l'application

![alt text](https://github.com/arnaudmdr/covoiturage/blob/master/modele_appli.png "Modèle")