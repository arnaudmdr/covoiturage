# Application Web de covoiturage

## Présentation
Cette application a été réalisée dans le cadre d'un projet scolaire à l'INSA. Elle propose de simuler un site de covoiturage, en excluant les transactions financières.

## Comment utiliser?
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
- Visionner les trajets où il sera passager
- Visionner les trajets où il sera conducteur
- Visionner les trajets disponibles et réserver un trajet (si un voyageur réserve un trajet, une demande sera envoyé au conducteur de ce trajet)
- Accepter ou refuser une réservation pour un covoiturage
- Proposer un trajet
- Se déconnecter

