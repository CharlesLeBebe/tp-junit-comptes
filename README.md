# BTS SIO - TP Tests unitaires avec JUnit

## Gestion de comptes bancaires

## Présentation

L'objectif de ce TP est de mettre en pratique les tests unitaires avec JUnit et l'utilisation de Git + GitHub.

Le projet permet de gérer des comptes bancaires et de tester différentes situations : les cas normaux, les cas limites et les cas d'erreur.

## Choix de conception

### Classe CompteBancaire

La classe CompteBancaire permet de représenter un compte bancaire.

Elle contient différentes infos :

-  iban: identifiant du compte bancaire.
-  titulaire : nom du titulaire du compte.
-  solde : montant disponible sur le compte.
-  decouvertAutorise : montant maximum du découvert autorisé.

La classe contient également des méthodes permettant de déposer ou retirer de l'argent, de calculer les intérêts et de vérifier si le compte est à découvertou non

L'IBAN est déclaré final car il ne doit pas être modifié après la création du compte.

### Classe GestionnaireComptes

La classe GestionnaireComptes permet de gérer plusieurs comptes bancaires.

Elle permet :

- d'ajouter un compte 
- de rechercher un compte avec son IBAN 
- d'effectuer un virement entre deux comptes
- de calculer le solde total
- de trouver les comptes qui sont à decouvert

Les comptes sont stockés dans une liste

### Gestion des exceptions

J'ai créé plusieurs exceptions personnalisées qui héritent de RuntimeException

- MontantInvalideException : utilisée lorsqu'un dépôt ou un retrait est inférieur ou égal à zéro.
- SoldeInsuffisantException : utilisée lorsqu'un retrait dépasse le découvert autorisé.
- CompteDejaExistantException : utilisée lorsqu'un compte avec le même IBAN existe déjà.
- CompteInconnuException : utilisée lorsqu'un IBAN recherché n'existe pas.

### Choix concernant les tests

J'ai choisi de développer le code d'abord puis de réaliser les tests unitaires.
Je n'ai donc pas utilisé une méthode TDD

Après avoir développé les classes, j'ai créé les tests avec JUnit  afin de vérifier le fonctionnement du programme dans les cas normaux, les cas limites et les cas d'erreur.

