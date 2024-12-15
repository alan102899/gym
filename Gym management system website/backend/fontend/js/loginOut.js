function LoginOut(){
    window.localStorage.removeItem("user");
    alert("Log out Successfully!");
    window.location.href="../login.html";
}