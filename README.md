# top-collegue-back-crm

Lien vers l'application heroku: https://top-collegue-back-crm.herokuapp.com


# API

- POST /auth : permet de se connecter a l'application. 

  - renvois un jeton JWS 

- PATCH /registration : permet de finaliser l'inscription en donnant ou pas une photo

- GET /participant : permet de récupérer la liste des participant.

  - l'utilisateur doit être inscrit pour accéder a ces infos
  
  - l'utilisateur inscrit est exclu de cette liste

- PATCH /vote : enregistre un vote

  - l'utilisateur doit être inscrit
  
  - l'utilisateur doit avoir des votes restant
  
- GET /votes : récupère les votes de l'utilisateur

- GET /result : renvoi les collègue vainqueur (Uniquement les x premiers, x étant parametrable dans le fichiers application.properties)

- GET /me : renvoi les information sur l'utilisateur
