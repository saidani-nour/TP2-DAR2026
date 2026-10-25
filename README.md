# TP2-DAR2026 : Échange d'objets via les sockets TCP

**Nom :** Nour SAIDANI
**Classe :** LSI 3
**Matière :** Développement d'applications réparties

# Activité 2-1 : Échange de chaînes de caractères
## Étape 1 : Préparation de l'espace de travail

![Arborescence](images/arborescence2-1.png)

Le projet `TP2-DAR2026` contient les packages `serverPackage` et `clientPackage`, le fichier README et le dossier `images`.

## Étape 2 : Application des flux de traitement de chaînes de caractères

### Code du serveur
![Code serveur](images/code-serveur2-1.png)

(Colle ici le code de `Server.java`.)

### Code du client
![Code client](images/code-client2-1.png)

(Colle ici le code de `Client.java`.)

### Tests

Console du client :

![Test client](images/test-client.png)

Console du serveur :

![Test serveur](images/test-serveur.png)

Validation de la syntaxe :

![Syntaxe incorrecte](images/test-syntaxe.png)

### Interprétation
- Dans l'Activité 1-2, `write` et `read` n'échangeaient qu'un seul octet : il était impossible d'envoyer une opération comme `55 * 25`. Ici, le client envoie une **ligne de texte** avec `PrintWriter.println` et le serveur la lit avec `BufferedReader.readLine`.
- Le serveur découpe la ligne avec `split(" ")` en trois éléments : le premier opérande, l'opérateur et le deuxième opérande. Il calcule avec un `switch` sur l'opérateur et renvoie le résultat sous forme de texte, que le client lit et affiche.
- Les résultats obtenus sont corrects : 55 * 25 = 1375.0, 12 + 8 = 20.0, 10 - 25 = -15.0 et 7 / 2 = 3.5. La division par zéro est détectée par le serveur, qui renvoie un message d'erreur.
- La syntaxe est validée **côté client** avec une expression régulière avant l'envoi : des chaînes comme `25 ** 55` ou `abc + 3` sont refusées et ne sont jamais envoyées au serveur.
- La saisie de `fin` termine la communication : les deux applications ferment leurs flux et leurs sockets.