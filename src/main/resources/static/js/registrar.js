$(document).ready(function() {
});

async function registrarUsuarioo() {
    let datos = {}
    datos.nombre = document.getElementById("nombre").value;
    datos.apellido = document.getElementById("apellido").value;
    datos.email = document.getElementById("email").value;
    datos.password = document.getElementById("password").value;
        let repeatPassword = document.getElementById("repeatPassword").value;
    
        if (datos.password !== repeatPassword) {
            alert("Las contraseñas no coinciden. Por favor, inténtalo de nuevo.");
            return; // Exit the function if passwords do not match
        }


  const request = await fetch("api/registrarUsuario", {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  alert("Usuario registrado con éxito");
  window.location.href = "usuarios.html";
 
  

 
}
