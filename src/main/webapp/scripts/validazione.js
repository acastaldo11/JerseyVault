function validaFormRegistrazione() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    if (email === "" || password === "") {
        alert("Compila tutti i campi!");
        return false;
    }
    return true;
}