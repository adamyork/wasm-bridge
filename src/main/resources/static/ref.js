document.addEventListener("DOMContentLoaded", () => {
    // ==========================================
    // 1. POPULATE HEADER
    // ==========================================
    const header = document.querySelector("header");
    if (header) {
        // Create Logo Link
        const logoLink = document.createElement("a");
        logoLink.href = "#";
        logoLink.className = "logo";
        logoLink.textContent = "Wasm Bridge";
        header.appendChild(logoLink);

        // Create Nav and List Structure
        const nav = document.createElement("nav");
        const ul = document.createElement("ul");

        // Define menu items: [Text, Link Anchor]
        const menuItems = [
            ["One", "#one"],
            ["Two", "#two"],
            ["Three", "#three"]
        ];

        menuItems.forEach(([text, hash]) => {
            const li = document.createElement("li");
            const a = document.createElement("a");
            a.href = hash;
            a.textContent = text;
            li.appendChild(a);
            ul.appendChild(li);
        });

        nav.appendChild(ul);
        header.appendChild(nav);
    }

    // ==========================================
    // 2. POPULATE MAIN
    // ==========================================
    const main = document.querySelector("main");
    if (main) {
        // Create Hero Image
        const img = document.createElement("img");
        img.className = "hero-image";
        img.src = "https://picsum.photos/800/400";
        img.alt = "Abstract modern visual representing data bridge";
        main.appendChild(img);

        // Create Article Container
        const article = document.createElement("article");
        article.className = "content-block";

        // Create Article Heading
        const h2 = document.createElement("h2");
        h2.textContent = "Connecting Environments";
        article.appendChild(h2);

        // Create Article Paragraph
        const p = document.createElement("p");
        p.textContent = `
            Welcome to the interface hub. This section is structured to handle text readouts, 
            execution logs, or operational controls right beneath your visual outputs. Built 
            with clean, modern layout structures, it will seamlessly scale down to mobile 
            screens while remaining crisp and centered on desktop displays.
        `.trim();
        article.appendChild(p);

        main.appendChild(article);
    }

    // ==========================================
    // 3. POPULATE FOOTER
    // ==========================================
    const footer = document.querySelector("footer");
    if (footer) {
        // Create Copyright Paragraph
        const footerP = document.createElement("p");
        // Using HTML entity for the copyright symbol
        footerP.innerHTML = "&copy; 2026 Wasm Bridge Project. All rights reserved.";
        footer.appendChild(footerP);
    }
});