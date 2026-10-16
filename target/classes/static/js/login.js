// Call the dataTables jQuery plugin
$(document).ready(function() {
});

async function iniciarSesion() {

    let datos = {};
    datos.email = document.getElementById("ColocarEmail").value;
    datos.password = document.getElementById("ColocarPassword").value;

  const request = await fetch("api/validarUsuario", {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  
  const respuesta = await request.text();
  if (respuesta == "Inicio de sesión exitoso") {
    window.location.href = 'usuarios.html'
  }else {
    alert("Usuario o contraseña incorrectos");
  }
  

  //console.log(listadoHtml);
  document.querySelector('#usuarios tbody').innerHTML = listadoHtml;
  $('#usuarios').DataTable();

}
