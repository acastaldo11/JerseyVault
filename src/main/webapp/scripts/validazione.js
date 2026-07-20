function validaFormRegistrazione() {
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    let pwdRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

    if (!emailRegex.test(email)) {
        console.log("Email non valida");
        return false;
    }
    if (!pwdRegex.test(password)) {
        console.log("Password troppo debole");
        return false;
    }
    return true;
}