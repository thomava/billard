
# Améliorations possibles, bugs et des pistes de correction

## Implémentation des règles du jeu

Normalement toutes les règles du jeu sont implémentées.
Nous n'avons pas vu de bugs liés à une mauvaise interprétation des règles du jeu.
Les règles du jeu que nous utilisons sont décrites sur la Fenetre règle du jeu
qui est affichée en appuyant sur le bouton "Règles du jeu"

## Bug liés aux contacts

Les contacts sont une partie centrale du programme. Ils doivent être traités
avec la plus grande rigueur et précision. Malgré les efforts que nous avons
fait pour s'occuper correctement les contacts, il existe des bugs. La classe
intermédiaire contact n'était peut-être pas la meilleure idée, mais elle permet
déjà d'enlever une bonne partie des problèmes. De plus, cela rend le code plus
facilement maintenable. En effet, toutes les actions suivant un contact sont
décrites dans la classe Contact.

### Des billes qui passent à travers les elements !

Un bug majeur que nous avons résolu permettait aux billes de traverser les
élements. C'est à cause du temps informatique qui est discret. Le contact n'est
pas vu exactement au bon moment, donc deux objets se chevauchent avant que le
contact soit considéré.
Étant donné que les objets pouvaient être en partie superposés, ils
étaient considérés comme tout le temps en contact. Cependant, avec
notre façon de gérer les contacts, les deux objets ne pouvaient plus se
séparer.

Pour régler ce bug, on applique une correction de décalage proportionnelle au
chevauchementi (profondeurContact dans notre code).
Ce décalage est opéré selon la normale au contact (c'est la
direction selon laquelle le décalage à faire un minimal). 

```java
    // Pour deux billes
    e1.setPosition(e1.getPosition().add(norm.mul(-(profondeurContact/2 + 0.5))));
    b2.setPosition(b2.getPosition().add(norm.mul(+(profondeurContact/2 + 0.5))));

    // Pour une bille et le terrain
    e1.setPosition(e1.getPosition().add(norm.mul(-(profondeurContact/2 + 0.5))));
```

La correction n'est pas physiquement parfaite, mais au regard de la vitesse
d'actualisation, elle est suffisante.

### Les contacts ne se font pas dans le bon ordre

Comme avant, du fait que le temps est discret, plusieurs contacts étaient
détéctés en même temps. Cependant, les contacts doivent tout de même être
traités dans le bon ordre pour ne pas nuire à l'expérience de jeu.

Pour régler ce bug, nous trions la liste des contacts détectés lors d'une
actualisation. Ce tri est basé sur la profondeur des contacts. Un contact
profond doit être traité avant un contact peu profond. Cette solution n'est pas
parfaite, mais elle permet déjà de supprimer une bonne partie des incohérences
physiques lorsque de nombreux contacts ont lieu en même temps.

## Code spaghetti

Bien que nous avons fait l'effort de bien séparer nos classes en limitant leur
champ d'action, certains objets sont modifiés dans des classes qui ne devraient
pas modifier ces objets au vu des permissions que nous voulions donner à ces
classes. Par exemple, listeElements qui est une liste gérée par Plateau est
passée à MoteurPhysique pour qu'il puisse faire ses traitements. Il va même
jusqu'à modifier la liste en question en supprimant les billes qui sont tombées.
Le programme marche bien, mais il est plus difficile à maintenir. Nous avons
décidé de ne pas corriger ce mauvais design car cela aurait compléxifié le
code. 

Une solution aurait été de passer la liste d'élements avec la méthode
`unmodifiableList(..)`. La liste n'aurait pas pu être modifiée directement par
MoteurPhysique. À côté de ça, pour tout de même gérer la supression des billes
tombées, il aurait fallu créer une interface `BilleTombeListener` implémentée
par Plateau afin d'avoir une méthode `onBilleTombée(Bille b)` appelée au moment
où une bille tombe par MoteurPhysique. Cela complexifie largement le code, mais
le rend plus propre et maintenable. 

Il y a plusieurs endroits où le code suit un design mal pensé. Cependant, pour
un petit projet comme le notre, c'est suffisant pour ne pas trop se prendre la
tête tout en ayant un jeu fonctionnel.

## Fenêtre principale qui ne marche pas

Lorsque l'on ajoute la FenetrePrincipale, il n'est plus possible de lancer la
partie. En effet, quand on appuie sur le bouton "Lancer Partie", la Fenêtre 
de jeu s'ouvre, mais les élements ne s'affichent pas. Le constructeur de
Plateau s'execute correctement. Nous avons tenté des debugs à l'aide de quelques
`System.out.println()` placées à des endroits stratégiques.

Nous n'avons pas trouvé pourquoi ce bug est présent. Nous avons donc désactivé
la Fenêtre Principale. :( La partie se lance automatiquement avec des
paramètres prédéfinis.
