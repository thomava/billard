
# Explications des évènements d'un tour avec la prise en compte des règles du jeu

Un tour de billard se résume à une action faite par le joueur au début du tour
: déterminer un angle et une force de tir.
Une fois ces actions effectuées, la classe MoteurPhysique prend le relais pour
executer la physique d'un tour. Cette physique peut amener à des conséquence
sur le score, les fautes, la validité d'un tour. Nous avons donc ajouté la
classe DescriptionTour qui se charge de déterminer les conséquences importantes
d'un tour. Pour cela, MoteurPhysique appelle des méthodes pour mettre à jour
les informations importantes : par exemple, quelle est la première bille entrée
en contact avec la bille blanche ou la liste des billes tombées dans les poches
pendant le tour. Au dur et à mesures des mises à jour, la classe
DescriptionTour prend en comptes les différentes fautes de jeu. À la fin du
tour, l'instance de DescriptionTour liée au tour est récupérée par Plateau qui
se chargera d'appliquer les pénalités, la possibilité de rejouer si le joueur a
empoché une bille de son équipe et il actualisera le score.
