document.addEventListener("DOMContentLoaded", function() {
    
    const formRegistrazione = document.getElementById("formRegistrazione");
    if (formRegistrazione) {
        formRegistrazione.addEventListener("submit", function(event) {
            let errori = [];
            const errorContainer = document.getElementById("erroriRegistrazione");
            errorContainer.innerHTML = "";
            errorContainer.classList.remove("mostra-errore");
            
            const nome = document.getElementById("nome").value.trim();
            const cognome = document.getElementById("cognome").value.trim();
            const email = document.getElementById("email").value.trim();
            const telefono = document.getElementById("telefono").value.trim();
            const password = document.getElementById("password").value.trim();
            
            if (nome.length < 2) errori.push("Il nome deve avere almeno 2 caratteri.");
            if (cognome.length < 2) errori.push("Il cognome deve avere almeno 2 caratteri.");
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) errori.push("Formato email non valido.");
            const telRegex = /^[0-9]{9,15}$/;
            if (!telRegex.test(telefono)) errori.push("Il telefono deve contenere solo numeri (min 9).");
            if (password.length < 8) errori.push("La password deve avere almeno 8 caratteri.");
            
            if (errori.length > 0) {
                event.preventDefault();
                errorContainer.innerHTML = errori.join("<br>");
                errorContainer.classList.add("mostra-errore");
            }
        });
    }

    const formLogin = document.getElementById("formLogin");
    if (formLogin) {
        formLogin.addEventListener("submit", function(event) {
            let errori = [];
            const errorContainer = document.getElementById("erroriLogin");
            errorContainer.innerHTML = "";
            errorContainer.classList.remove("mostra-errore");
            
            const email = document.getElementById("email").value.trim();
            const password = document.getElementById("password").value.trim();
            
            if (email === "") errori.push("L'email è obbligatoria.");
            if (password === "") errori.push("La password è obbligatoria.");
            
            if (errori.length > 0) {
                event.preventDefault();
                errorContainer.innerHTML = errori.join("<br>");
                errorContainer.classList.add("mostra-errore");
            }
        });
    }

    const formCheckout = document.getElementById("formCheckout");
    if (formCheckout) {
        formCheckout.addEventListener("submit", function(event) {
            let errori = [];
            const errorContainer = document.getElementById("erroriCheckout");
            errorContainer.innerHTML = "";
            errorContainer.classList.remove("mostra-errore");
            
            const via = document.getElementById("via").value.trim();
            const citta = document.getElementById("citta").value.trim();
            const cap = document.getElementById("cap").value.trim();
            
            if (via.length < 5) errori.push("L'indirizzo deve avere almeno 5 caratteri.");
            if (citta.length < 2) errori.push("La città deve avere almeno 2 caratteri.");
            const capRegex = /^[0-9]{5}$/;
            if (!capRegex.test(cap)) errori.push("Il CAP deve essere di esattamente 5 numeri.");
            
            if (errori.length > 0) {
                event.preventDefault();
                errorContainer.innerHTML = errori.join("<br>");
                errorContainer.classList.add("mostra-errore");
            }
        });
    }

    const emailRegistrazione = document.getElementById("email");
    if (emailRegistrazione && document.getElementById("formRegistrazione")) {
        emailRegistrazione.addEventListener("change", function() {
            const email = emailRegistrazione.value.trim();
            if (email === "") return;

            fetch("/JerseyVault/checkEmail?email=" + encodeURIComponent(email))
                .then(function(response) {
                    return response.json();
                })
                .then(function(data) {
                    const errorContainer = document.getElementById("erroriRegistrazione");
                    if (!data.disponibile) {
                        errorContainer.innerHTML = "Questa email è già registrata.";
                        errorContainer.classList.add("mostra-errore");
                    } else {
                        errorContainer.innerHTML = "";
                        errorContainer.classList.remove("mostra-errore");
                    }
                });
        });
    }

});