# Plan Interface Graphique

## Modèle :

#### _Méthodes :_

Un paragraphe méthode suffit pour l’ensemble car la plupart du temps dans le
modèle, on veut simplement setters et getters pour pouvoir modifier le modèle
de données.

#### _Jeu :_

_Données :_

- Un nom de joueur (Ou manière arcade, inscrire son nom si meilleur score sur un
niveau, au choix)
- Plusieurs niveaux (ie. style d'environnement différent ?)
- Des sauvegardes
- Sauvegarde des options de l’utilisateur (mapping de touche, choix du skin,
  ...)
- Un meilleur score (par niveau sans doute mieux)
- Touches attribuées
- Un mode d'aventure ?
- Un choix de difficulté ?
- Un choix dans le skin du goinfre ?

#### _Niveau ou Aire de jeu :_

L'idée serait de pouvoir permettre la génération aléatoire de niveaux (avec des
pièges placé dès le départ *ET/OU* des pièges qui apparaissent, en même temps que
des nutritionistes).  
On pourrait donc différencier un mode dit ```aventure```, et un mode dit
```aléatoire``` (donc deux tableaux de score différents) ?

_Données :_

- Un identifiant (ie. id du style ?) OU génération aléatoire.
- Un objectif (temps/ramasser un certain nombre de gâteaux)
- Un goinfre
- Un ou plusieurs nutritionnistes (de type différent selon la difficulté)
- Des gâteaux
- Des obstacles ? (à définir si oui, il faut leur position)
- Une taille
- Une position de départ
- Un thème musical ?
- Peut- être une table des meilleurs scores pour le niveau.

#### _Gâteau :_

_Données :_

- Un type de bonus
- Un nombre de points apporté

#### _Goinfre :_

- Une vitesse
- Des touches de contrôle (Plutôt située dans le menu du jeu).
- Des pouvoirs (débloqués ou non)

#### _Nutritionniste :_

- Un type ou effet sur le goinfre (si l’on veut varier les plaisirs)
- Un nombre de points à retirer
- Une vitesse

#### _Pièges :_

- Un effet
- Une fréquence d’apparition (Je préfère avoir une part d’aléatoire sur
l’apparition de piège SI on en rajoute, cela permettra d’inclure le facteur
chance qui offre une bonne rejouabilité, bien que frustrant par moment)

## Vue :

#### _Menu du Jeu :_

#### _Nutritionniste :_

#### _Goinfre :_

#### _Niveau :_


## Contrôleur :

#### _Menu du jeu :_

_Méthode :_

- Gérer les options
- Assigner les touches
- Choisir une difficulté
- Entrer un nom
- Lancer une partie
- Choisir un niveau

#### _Nutritionniste :_

_Méthode :_

- Suivre le goinfre (Détecter un obstacle).
- Définir une vitesse
- Être détruit ?
- Fuir le goinfre peut- être


#### _Niveau ?_

- Créer un plateau
- Lancer une partie
- Actualiser un plateau
- En cas de meilleur score, demander un nom ?
- Déclencher une musique

#### _Goinfre :_

_Méthode :_

- Se déplacer
- Utiliser un bonus
- Péremption du bonus
- Subir un malus
- Se défendre (Plusieurs méthode possible de défense dépendant
d’eventlisteners).
