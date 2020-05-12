# billard - projet INSA Lyon

Jeu fait en Java permettant de jouer au billard anglais.

## Auteurs

Lanière K
Groupe 41

Diane
Léa
Thomas

## Guide d'utilisation

### Compilation et execution

Tous les documents sources sont regroupés dans le dossier src/
Il suffit donc d'executer les commandes suivantes :

```sh
    # Pour compiler les classes
    javac src/*.java -d dest-dir/

    # Pour lancer le jeu
    cd dest-dir
    java Main
```

### Manuel de jeu

Quand le programme est lancé, une partie avec des paramètres définis se lance.

Dans le panneau superieur, il y a toutes les informations pour jouer.

- Le bouton "Règles du jeu" permet de s'informer sur les règles qui sont prises
  en compte dans ce jeu.
- Le bouton "Quitter" termine l'execution du programme.
- Au milieu du panneau, on trouve la liste des joueurs par équipe ainsi que le
  nombre de boules qu'elles doivent encore marquer.
    - Le nom du joueur écrit en bleu est celui qui joue actuellement.
    - Une fois la première boule de couleur empochée, les équipes reçoivent
      leur attribution de couleur. Cette attribution se traduit par un arrière
      plan qui prend la couleur de l'equipe au niveau des écritures.
- À droite du panneau, il peut y avoir un message indiquant qu'un coup libre
  est permis. Si tel est le cas, le joueur actuel bénéfitie d'un coup libre
  (ne prend pas en compte les potentielles fautes commises pendant le tour.)

## Les documents de l'archive

- a
- b
- c
