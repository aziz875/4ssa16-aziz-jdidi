# Compte-Rendu Atelier 5 : Spring MVC, REST, Postman et SpringDoc OpenAPI

---

## A. Endpoints

| Contrôleur | Verbe HTTP & Route | Service appelé | Code de succès | Codes d'erreur |
| :--- | :--- | :--- | :--- | :--- |
| **ContratController** | `GET /api/contrats` | `contratService.findAll()` | 200 OK | — |
| **ContratController** | `GET /api/contrats/{id}` | `contratService.findById(id)` | 200 OK | 404 Not Found |
| **ContratController** | `POST /api/contrats` | `contratService.create(contrat)` | 201 Created | 400 Bad Request |
| **ContratController** | `PUT /api/contrats/{id}` | `contratService.update(id, contrat)` | 200 OK | 404 Not Found, 400 Bad Request |
| **ContratController** | `DELETE /api/contrats/{id}` | `contratService.deleteById(id)` | 204 No Content | 404 Not Found |
| **PaiementController** | `GET /api/paiements` | `paiementService.findAll()` | 200 OK | — |
| **PaiementController** | `GET /api/paiements/{id}` | `paiementService.findById(id)` | 200 OK | 404 Not Found |
| *(PaiementController)* | `POST /api/paiements` | *(Non implémenté - lecture seule)* | — | 405 Method Not Allowed |
| **AgenceController** | `GET /api/agences` | `agenceService.findAll()` | 200 OK | — |
| **AgenceController** | `GET /api/agences/{id}` | `agenceService.findById(id)` | 200 OK | 404 Not Found |
| **AgenceController** | `POST /api/agences` | `agenceService.create(agence)` | 201 Created | 400 Bad Request |
| **AgenceController** | `PUT /api/agences/{id}` | `agenceService.update(id, agence)` | 200 OK | 404 Not Found, 400 Bad Request |
| **AgenceController** | `DELETE /api/agences/{id}` | `agenceService.deleteById(id)` | 204 No Content | 404 Not Found |
| **EmployeController** | `GET /api/employes` | `employeService.findAll()` | 200 OK | — |
| **EmployeController** | `GET /api/employes/{id}` | `employeService.findById(id)` | 200 OK | 404 Not Found |
| **EmployeController** | `POST /api/employes` | `employeService.create(employe)` | 201 Created | 400 Bad Request |
| **EmployeController** | `PUT /api/employes/{id}` | `employeService.update(id, employe)` | 200 OK | 404 Not Found, 400 Bad Request |
| **EmployeController** | `DELETE /api/employes/{id}` | `employeService.deleteById(id)` | 204 No Content | 404 Not Found |
| **VehiculeRestController** | `GET /api/vehicules` | `vehiculeService.findAll()` | 200 OK | — |
| **VehiculeRestController** | `GET /api/vehicules/{id}` | `vehiculeService.findById(id)` | 200 OK | 404 Not Found |
| **VehiculeRestController** | `POST /api/vehicules` | `vehiculeService.create(vehicule)` | 201 Created | 400 Bad Request |
| **VehiculeRestController** | `PUT /api/vehicules/{id}` | `vehiculeService.update(id, vehicule)` | 200 OK | 404 Not Found, 400 Bad Request |
| **VehiculeRestController** | `DELETE /api/vehicules/{id}` | `vehiculeService.deleteById(id)` | 204 No Content | 404 Not Found |
| **EquipementController** | `GET /api/equipements` | `equipementService.findAll()` | 200 OK | — |
| **EquipementController** | `GET /api/equipements/{id}` | `equipementService.findById(id)` | 200 OK | 404 Not Found |
| **EquipementController** | `POST /api/equipements` | `equipementService.create(equipement)` | 201 Created | 400 Bad Request |
| **EquipementController** | `PUT /api/equipements/{id}` | `equipementService.update(id, equipement)` | 200 OK | 404 Not Found, 400 Bad Request |
| **EquipementController** | `DELETE /api/equipements/{id}` | `equipementService.deleteById(id)` | 204 No Content | 404 Not Found |
| **ClientController** | `GET /api/clients` | `clientService.findAll()` | 200 OK | — |
| **ClientController** | `GET /api/clients/{id}` | `clientService.findById(id)` | 200 OK | 404 Not Found |
| **ClientController** | `POST /api/clients` | `clientService.create(client)` | 201 Created | 400 Bad Request |
| **ClientController** | `PUT /api/clients/{id}` | `clientService.update(id, client)` | 200 OK | 404 Not Found, 400 Bad Request |
| **ClientController** | `DELETE /api/clients/{id}` | `clientService.deleteById(id)` | 204 No Content | 404 Not Found |
| **ReservationController** | `GET /api/reservations` | `reservationService.findAll()` | 200 OK | — |
| **ReservationController** | `GET /api/reservations/{id}` | `reservationService.findById(id)` | 200 OK | 404 Not Found |
| **ReservationController** | `POST /api/reservations` | `reservationService.create(reservation)` | 201 Created | 400 Bad Request |
| **ReservationController** | `PUT /api/reservations/{id}` | `reservationService.update(id, reservation)` | 200 OK | 404 Not Found, 400 Bad Request |
| **ReservationController** | `DELETE /api/reservations/{id}` | `reservationService.deleteById(id)` | 204 No Content | 404 Not Found |
| **MaintenanceController** | `GET /api/maintenances` | `maintenanceService.findAll()` | 200 OK | — |
| **MaintenanceController** | `GET /api/maintenances/{id}` | `maintenanceService.findById(id)` | 200 OK | 404 Not Found |
| **MaintenanceController** | `POST /api/maintenances` | `maintenanceService.create(maintenance)` | 201 Created | 400 Bad Request |
| **MaintenanceController** | `PUT /api/maintenances/{id}` | `maintenanceService.update(id, maintenance)` | 200 OK | 404 Not Found, 400 Bad Request |
| **MaintenanceController** | `DELETE /api/maintenances/{id}` | `maintenanceService.deleteById(id)` | 204 No Content | 404 Not Found |

---

## B. Annotations Jackson

### 1. Tableau des choix d'annotations

| Association | Annotation retenue | Justification |
| :--- | :--- | :--- |
| **Contrat ↔ Paiement** | `@JsonManagedReference("contrat-paiements")` sur `Contrat.paiements`<br>`@JsonBackReference("contrat-paiements")` sur `Paiement.contrat` | Vraie relation de composition (`cascade = ALL`, cycle de vie lié). La sérialisation du contrat doit inclure ses paiements, tandis que le retour arrière depuis Paiement vers Contrat est omis pour stopper la récursion infinie. |
| **Agence ↔ Vehicule** | `@JsonIgnore` sur `Agence.vehicules` | Un véhicule référence son agence, mais lister une agence ne doit pas embarquer l'intégralité du parc de véhicules. Aucun tag sur `Vehicule.agence` pour permettre l'envoi de `{ "agence": { "idAgence": 1 } }`. |
| **Agence ↔ Employe** | `@JsonIgnore` sur `Agence.employes` | L'employé référence son agence. Évite la récursion cyclique `Agence -> Employe -> Agence`. |
| **Client ↔ Reservation** | `@JsonIgnore` sur `Client.reservations` | Évite d'embarquer toutes les réservations lors de la lecture d'un client et supprime le cycle bidirectionnel. |
| **Vehicule ↔ Maintenance** | `@JsonIgnore` sur `Vehicule.maintenances` | Une maintenance pointe vers un véhicule (`Maintenance.vehicule`). La liste inverse est ignorée pour éviter les boucles. |
| **Vehicule ↔ Reservation** | `@JsonIgnore` sur `Vehicule.reservations` | Évite le cycle `Vehicule -> Reservation -> Vehicule`. |
| **Reservation ↔ Contrat** | `@JsonIgnore` sur `Reservation.contrat` et `Contrat.reservation` | Évite le cycle direct OneToOne entre réservation et contrat. |
| **Equipement ↔ Vehicule** | `@JsonIgnore` sur `Equipement.vehicules` et `Vehicule.equipements` | Rompt la boucle ManyToMany entre véhicules et équipements. |

### 2. Différence entre `@JsonBackReference` et `@JsonIgnore`

- **`@JsonBackReference`** : Fonctionne impérativement en tandem avec `@JsonManagedReference`. Le champ annoté n'est pas sérialisé en JSON, mais lors de la désérialisation d'un objet parent (ex: `Contrat`), Jackson reconstruit automatiquement la référence vers le parent dans chaque objet enfant (ex: `paiement.setContrat(contrat)`). C'est idéal pour les compositions parent-enfant créées conjointement.
- **`@JsonIgnore`** : Ignore inconditionnellement le champ annoté à la sérialisation (production du JSON) et/ou à la désérialisation. Contrairement à `@JsonBackReference`, il ne maintient aucun lien de réinjection automatique d'entités parentes. Il est particulièrement adapté aux associations où les entités sont gérées séparément par identifiant (ex: `Agence.vehicules`).

---

## C. Postman et Swagger

| Critère | Postman | Swagger UI (via SpringDoc) |
| :--- | :--- | :--- |
| **Rôle principal** | **Tester** et valider fonctionnellement l'API (client HTTP de test). | **Documenter** et **explorer** l'API de manière interactive. |
| **Synchronisation** | Manuelle : les requêtes et collections sont rédigées et maintenues par le développeur/testeur. | Automatique : le contrat OpenAPI est généré dynamiquement au démarrage par réflexion sur les contrôleurs. |
| **Automatisation** | Permet des suites de tests automatisés via des assertions JavaScript (`pm.test()`, chaînage de variables d'environnement). | Permet des essais manuels ponctuels ("Try it out") mais sans assertion ni scénario rejouable. |
| **Exemple concret dans l'atelier** | Vérifier qu'un `POST /api/contrats` avec un corps valide renvoie bien `201 Created`, stocker dynamiquement `pm.environment.set("contratId", id)` dans un script *After response*, puis rejouer immédiatement un `GET /api/contrats/{{contratId}}` ou `DELETE`. | Visualiser en un clin d'œil la liste de tous les endpoints groupés par Tag (`Contrats`, `Paiements`...), consulter le schéma JSON attendu pour `Contrat`, et vérifier les codes d'erreur documentés (200, 201, 400, 404). |

---

## D. Anomalies SonarQube

Lors de l'analyse du code (`Analyze with SonarQube for IDE`), voici deux anomalies typiques identifiées et résolues :

1. **Règle : "Field injection should not be used" / "Constructor injection should be used" (java:S3305 / java:S6813)**
   - *Explication :* L'injection par attribut via `@Autowired` couple fortement la classe au conteneur Spring, empêche l'immutabilité (`final`) et complique les tests unitaires.
   - *Correction :* Déclaration des dépendances en `private final` et injection par constructeur à l'aide de l'annotation Lombok `@RequiredArgsConstructor` sur tous les contrôleurs et services.

2. **Règle : "Generic exceptions should never be thrown" (java:S112) / "Unused imports should be removed" (java:S1128)**
   - *Explication :* Lancer une exception générique (`Exception` ou `RuntimeException`) ou `EntityNotFoundException` sans gestion globale empêche de distinguer les types d'erreurs HTTP (404 vs 400 vs 500). De même, les imports inutilisés polluent le code.
   - *Correction :* Création d'une exception métier dédiée `ResourceNotFoundException`, levée systématiquement lors de l'absence d'une entité et interceptée par `GlobalExceptionHandler` pour renvoyer un statut 404 propre avec message JSON structuré.

---

## E. Questions de compréhension

### 1. Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur à appeler ?
Toutes les requêtes HTTP entrantes sont interceptées par la **`DispatcherServlet`** (le *Front Controller* de Spring MVC). 
Pour trouver la méthode de contrôleur correspondante, la `DispatcherServlet` consulte le composant **`HandlerMapping`** (notamment `RequestMappingHandlerMapping`), qui maintient un registre des chemins d'URL, des méthodes HTTP associées (`@GetMapping`, `@PostMapping`, etc.) et des en-têtes définis sur les classes et méthodes annotées `@RestController` / `@RequestMapping`.

### 2. Pourquoi `ContratController` dépend-il de `IContratService` et non de `ContratRepository` ni de `ContratServiceImpl` ?
- **Non-dépendance à `ContratRepository` :** Respect du principe de séparation des responsabilités en couches (SoC). Le contrôleur appartient à la couche Web ; il ne doit pas manipuler directement la persistance ni la base de données. Toute la logique métier, la validation et les règles transactionnelles incombent à la couche Service.
- **Dépendance à l'interface `IContratService` plutôt qu'à l'implémentation `ContratServiceImpl` :** Application du principe d'inversion des dépendances (DIP - SOLID). Le contrôleur dépend d'un contrat d'abstraction. Cela réduit le couplage, facilite les tests unitaires (mock de l'interface) et permet de substituer l'implémentation sans modifier la couche Web.

### 3. Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?
- **POST → 201 (Created) :** Selon la spécification HTTP REST, une création réussie d'une ressource doit renvoyer `201 Created` (souvent accompagnée du corps de l'élément créé avec son identifiant généré ou d'un en-tête `Location`).
- **DELETE → 204 (No Content) :** La suppression a réussi et il n'y a plus aucun corps à renvoyer au client ; `204 No Content` signale ce succès sans charge utile superflue.
- **Ressource absente → 404 (Not Found) et non 500 (Internal Server Error) :** Le code 404 est une erreur côté client indiquant que l'identifiant demandé n'existe pas en base. Le code 500 indique un crash inattendu ou un bug non géré côté serveur. Une entité introuvable est une situation fonctionnelle normale et doit être traduite en 404 par `GlobalExceptionHandler`.

### 4. Pourquoi `PaiementController` n'expose-t-il que des GET ? Quelle réponse reçoit un POST `/api/paiements` ?
- **Pourquoi uniquement des GET :** Dans le modèle métier d'AutoLoc, le `Contrat` est l'agrégat parent racine qui porte ses paiements en composition (`cascade = CascadeType.ALL`). Un paiement n'a pas de sens isolé ; il naît, se met à jour et se supprime exclusivement à travers son contrat.
- **Réponse reçue par un POST `/api/paiements` :** Comme aucune méthode annotée `@PostMapping` n'existe dans `PaiementController`, Spring MVC renvoie automatiquement le code de statut **`405 Method Not Allowed`**.

### 5. Quels risques présente l'exposition directe des entités JPA ? Pourquoi `Contrat.paiements` utilise-t-il `@JsonManagedReference` alors que `Agence.vehicules` utilise `@JsonIgnore` ?
- **Risques de l'exposition directe des entités JPA :**
  1. *Couplage fort :* Toute modification du schéma de la base de données casse immédiatement les contrats d'API des clients externes.
  2. *Fuite de données internes :* Des champs sensibles ou techniques (mots de passe, versions Hibernate, lazy proxies) sont exposés par inadvertance.
  3. *Problèmes de sérialisation et performance :* Risque de récursion infinie (`StackOverflowError`) et déclenchement accidentel de requêtes SQL superflues (problème du N+1 lié au chargement paresseux). *(Ces limites seront résolues par les DTO au Chapitre 10).*
- **`Contrat.paiements` (`@JsonManagedReference`) vs `Agence.vehicules` (`@JsonIgnore`) :**
  - `Contrat` et ses `Paiement` forment une vraie composition : lorsqu'on consulte ou crée un contrat, on souhaite visualiser ses paiements imbriqués dans le JSON (d'où `@JsonManagedReference` côté parent pour sérialiser la liste des paiements).
  - En revanche, une `Agence` regroupe potentiellement des centaines de véhicules. Embarquer la liste de tous les véhicules lors de la consultation d'une agence alourdirait massivement la réponse JSON et n'est pas le cas d'usage typique (on gère les véhicules individuellement par leur propre endpoint `/api/vehicules`). On utilise donc `@JsonIgnore` sur la liste côté Agence.
