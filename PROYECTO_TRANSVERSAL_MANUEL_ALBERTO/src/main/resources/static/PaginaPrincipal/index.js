
/*MENU USUARIO*/
document.addEventListener("DOMContentLoaded", function() {
    var balanceButton = document.getElementById("bot_usuario");
    var menu = document.getElementById("menu");
    var cerrarSesionBtn = document.getElementById("cerrarSesion");
    var username = document.getElementById("username");
  
    // Ocultar el menú y el botón de cerrar sesión al cargar la página
    menu.style.display = "none";
    cerrarSesionBtn.style.display = "none";
  
    // Agregar evento de clic al botón
    balanceButton.addEventListener("click", function() {
      // Mostrar el nombre de usuario y el botón de cerrar sesión si el menú está inactivo
      if (menu.style.display === "none") {
        
        
        // Mostrar el botón de cerrar sesión
        cerrarSesionBtn.style.display = "block";
        // Mostrar el menú
        menu.style.display = "block";
      } else {
        // Ocultar el nombre de usuario y el botón de cerrar sesión si el menú está activo
        username.innerText = "";
        cerrarSesionBtn.style.display = "none";
        // Ocultar el menú
        menu.style.display = "none";
      }
    });
  
    // Agregar evento de clic al botón de cerrar sesión
    cerrarSesionBtn.addEventListener("click", function() {
      // Aquí puedes agregar la lógica para cerrar sesión
      alert("Sesión cerrada");
    });
  });


/*JUEGOS*/


