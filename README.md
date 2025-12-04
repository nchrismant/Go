# Go — Jeu de société (version 3 joueurs)

Projet universitaire réalisé en 2ᵉ année de Licence Informatique à **Cergy Paris Université (CYU)**.  
Ce dépôt propose une implémentation Java du jeu de société **Go**, adaptée pour **3 joueurs**, avec interface graphique, gestion des coups, captures, score, et intégration d’un bot.

---

## 📌 Sommaire

- [Go — Jeu de société (version 3 joueurs)](#go--jeu-de-société-version-3-joueurs)
  - [📌 Sommaire](#-sommaire)
  - [🎯 Objectif du projet](#-objectif-du-projet)
  - [✨ Fonctionnalités](#-fonctionnalités)
    - [🎮 Gameplay](#-gameplay)
    - [🧠 Intelligence artificielle (Bot)](#-intelligence-artificielle-bot)
    - [🆘 Aide au joueur](#-aide-au-joueur)
    - [🕹️ Actions spéciales](#️-actions-spéciales)
  - [🧩 Architecture](#-architecture)
  - [🚀 Installation \& Déploiement](#-installation--déploiement)
    - [1. Cloner le dépôt](#1-cloner-le-dépôt)
    - [2. Ouvrir dans Eclipse ou un autre IDE Java](#2-ouvrir-dans-eclipse-ou-un-autre-ide-java)
    - [3. Lancer l’application](#3-lancer-lapplication)
  - [🏗️ Design Patterns utilisés](#️-design-patterns-utilisés)
  - [🛠️ Technologies \& Outils utilisés](#️-technologies--outils-utilisés)
  - [🧪 Tests](#-tests)
  - [👥 Auteurs \& Licence](#-auteurs--licence)

---

## 🎯 Objectif du projet

Ce projet a pour but de :

- Comprendre et implémenter les règles principales du **Go**.
- Gérer une variante **3 joueurs**.
- Mettre en œuvre une **architecture claire et modulaire**.
- Appliquer plusieurs **design patterns** étudiés en cours.
- Fournir une **interface graphique** simple permettant de jouer.

---

## ✨ Fonctionnalités

### 🎮 Gameplay

- Jeu **à 3 joueurs** (humains ou bots).
- Gestion des **captures** de pierres et de chaînes.
- **Score en temps réel**.
- Système de **méga pierre** pour éliminer les pierres autour d’une zone.
- **Historique des coups** (annulation / actions successives).

### 🧠 Intelligence artificielle (Bot)

- Bot simple capable de jouer automatiquement lorsque sélectionné comme joueur.

### 🆘 Aide au joueur

- Option « demander de l’aide » pour suggérer un coup potentiel.

### 🕹️ Actions spéciales

- **Passer son tour**
- **Abandonner**
- **Méga pierre**

---

## 🧩 Architecture

Le projet suit une structure modulaire :

```text
src/
 ├── config/       # Configuration des variables globales
 ├── goban/        # Gestion du goban (plateau), positions, chaînes de pierres & Gestion du tour, règles, captures, score, ...
 ├── gui/          # Interface graphique, interactions utilisateur
 ├── log/          # Fonctions utilitaires (log4j)
 └── test/         
  ├── GoGUI.java   # Main
  └── unit/        # Tests unitaires JUnit
```

---

## 🚀 Installation & Déploiement

### 1. Cloner le dépôt

```bash
git clone https://github.com/nchrismant/Go.git
cd Go
```

### 2. Ouvrir dans Eclipse ou un autre IDE Java

### 3. Lancer l’application

Exécuter la classe suivante :

```**bash**
src/test/GoGUI.java
```

---

## 🏗️ Design Patterns utilisés

- **MVC (Model–View–Controller)** :
  - Séparation entre :
    - **Model** : Plateau, règles, joueurs
    - **View** : GoGUI
    - **Controller** : Gestion des actions utilisateur

- **Strategy Pattern** :
  - Utilisé pour les différents types de joueurs :
    - HumanPlayer
    - BotPlayer

- **Observer Pattern** :
  - Mise à jour automatique de l’interface lorsque le plateau ou les scores changent.

- **Factory Pattern** :
  - Pour la création automatique des joueurs selon le mode choisi.

---

## 🛠️ Technologies & Outils utilisés

| Technologie      | Rôle              |
| ---------------- | ----------------- |
| **Java**         | Langage principal |
| **Eclipse**      | IDE recommandé    |
| **JUnit 4.11**   | Tests unitaires   |
| **Log4j 1.2.17** | Gestion des logs  |

---

## 🧪 Tests

Le projet utilise **JUnit 4.11** pour tester :

- Les règles du jeu
- La gestion des captures
- Le fonctionnement du goban
- Les scores

---

## 👥 Auteurs & Licence

- **AFATCHAWO Koffi Junior** — Étudiant L2 Informatique, Cergy Paris Université.
- **CHRISMANT Nathan** — Étudiant L2 Informatique, Cergy Paris Université.
- **DACRUZ Mathis** — Étudiant L2 Informatique, Cergy Paris Université.

Projet distribué sous licence **Open Source**.
