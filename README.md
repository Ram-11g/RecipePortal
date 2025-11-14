🍽️ RecipeExplorer
A Lightweight Recipe Viewer built with Spring Boot + REST API + Vanilla JavaScript UI

🌟 Overview

RecipeExplorer is a simple and clean web application that loads recipe data from a JSON file, stores it in a database, and presents it through:

✔ A REST API backend
✔ A clean, lightweight frontend using HTML + Bootstrap + JavaScript
✔ Pagination with Next/Previous
✔ Search functionality
✔ Detailed recipe view

🧠 Tech Stack
Backend

Java 17

Spring Boot 3

Spring JPA / Hibernate

MySQL / PostgreSQL / H2

Frontend

HTML5

CSS3 + Bootstrap

Vanilla JavaScript (Fetch API)

📁 Project Structure
src/
├── main/java/com/Recipe/RecipeApi/
│       ├── Controller/
│       ├── Service/
│       ├── Repository/
│       └── Entity/
└── main/resources/
        ├── static/
        │     ├── index.html
        │     ├── detail.html
        │     └── script.js
        └── application.properties

🚀 Setup & Installation
1️⃣ Clone this repository
git clone https://github.com/<your-username>/RecipeExplorer.git
cd RecipeExplorer

2️⃣ Configure database

In application.properties:

spring.datasource.url=jdbc:<yourdatabaseid>://localhost:3306/recipes
spring.datasource.username=username
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update

3️⃣ Place the JSON file

Put this in the project root:

US_recipes_null.json

4️⃣ Run the Spring Boot Application
mvn spring-boot:run

🌐 REST API Endpoints
Endpoint	Method	Description
/api/recipes/import	GET	Import JSON into DB
/api/recipes	GET	Get all recipes
/api/recipes/{id}	GET	Get one recipe
/api/recipes/search?title=xxx	GET	Search by title
/api/recipes/search/rating?rating=4.5	GET	Filter by rating
🎨 Frontend Pages
👉 Home (List View)
http://localhost:8080/index.html

👉 Detail View
http://localhost:8080/detail.html?id=7



List View	Detail View

	
📝 License

This project is licensed under the MIT License.
See the LICENSE
 file for details.

💛 Credits

Built by Ramu Rayam
Documentation & UI support by Sweetie (ChatGPT) 😘
