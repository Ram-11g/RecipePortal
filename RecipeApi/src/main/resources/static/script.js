const API_BASE = "http://localhost:8080/api/recipes";

// ---------------------------------------------
// INDEX PAGE LOGIC (TABLE + NEXT/PREV)
// ---------------------------------------------
if (window.location.pathname.endsWith("index.html") || window.location.pathname === "/") {

    let currentPage = 1;
    const itemsPerPage = 10;
    let allRecipes = [];

    // Fetch all recipes
    fetch(API_BASE)
        .then(res => res.json())
        .then(data => {
            allRecipes = data;
            renderPage(currentPage);
            updateButtons();
        });

    // Render table rows for current page
    function renderPage(page) {
        const start = (page - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        const recipes = allRecipes.slice(start, end);

        const tableBody = document.getElementById("recipeTableBody");
        tableBody.innerHTML = "";

        recipes.forEach(recipe => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td>${recipe.title}</td>
                <td>${recipe.cuisine}</td>
                <td>${recipe.rating}</td>
                <td>${recipe.totalTime}</td>
                <td>${recipe.serves}</td>
            `;
            tr.onclick = () => window.location.href = `detail.html?id=${recipe.id}`;
            tableBody.appendChild(tr);
        });
    }

    // Enable/disable Next & Previous buttons
    function updateButtons() {
        const totalPages = Math.ceil(allRecipes.length / itemsPerPage);

        document.getElementById("prevBtn").classList.toggle("disabled", currentPage === 1);
        document.getElementById("nextBtn").classList.toggle("disabled", currentPage === totalPages);
    }

    // Previous button action
    document.getElementById("prevBtn").onclick = () => {
        if (currentPage > 1) {
            currentPage--;
            renderPage(currentPage);
            updateButtons();
        }
    };

    // Next button action
    document.getElementById("nextBtn").onclick = () => {
        const totalPages = Math.ceil(allRecipes.length / itemsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            renderPage(currentPage);
            updateButtons();
        }
    };

    // Search functionality
    document.getElementById("searchInput").addEventListener("input", function () {
        const value = this.value.toLowerCase();

        if (value.trim() === "") {
            fetch(API_BASE)
                .then(res => res.json())
                .then(data => {
                    allRecipes = data;
                    currentPage = 1;
                    renderPage(1);
                    updateButtons();
                });
            return;
        }

        fetch(`${API_BASE}/search?title=${value}`)
            .then(res => res.json())
            .then(data => {
                allRecipes = data;
                currentPage = 1;
                renderPage(1);
                updateButtons();
            });
    });
}


// ---------------------------------------------
// DETAIL PAGE LOGIC
// ---------------------------------------------
if (window.location.pathname.endsWith("detail.html")) {

    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    fetch(`${API_BASE}/${id}`)
        .then(res => res.json())
        .then(recipe => {
            document.getElementById("recipeTitle").textContent = recipe.title;
            document.getElementById("cuisine").textContent = recipe.cuisine;
            document.getElementById("rating").textContent = recipe.rating;
            document.getElementById("description").textContent = recipe.description;
            document.getElementById("totalTime").textContent = recipe.totalTime;

            // Ingredients
            const ingList = document.getElementById("ingredientsList");
            recipe.ingredients.forEach(i => {
                const li = document.createElement("li");
                li.textContent = i;
                ingList.appendChild(li);
            });

            // Instructions
            const instList = document.getElementById("instructionsList");
            recipe.instructions.forEach(step => {
                const li = document.createElement("li");
                li.textContent = step;
                instList.appendChild(li);
            });

            // Nutrients
            document.getElementById("nutrients").textContent =
                JSON.stringify(recipe.nutrients, null, 2);
        });
}
