// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
});

async function cargarUsuarios() {
  const request = await fetch("api/selectUsuarios", {
    method: 'GET',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json'
    }
  });

  const usuarios = await request.json();
  let listadoHtml = "";

  

  //console.log(botonEliminar);
  //console.log(usuarios);

  for (const usuario of usuarios) {
    
    console.log(usuario.id);
    let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';

    listadoHtml += `
      <tr>
        <td>${usuario.id}</td>
        <td>${usuario.nombre} ${usuario.apellido}</td>
        <td>${usuario.email}</td>
        <td>${usuario.telefono  ?? '' }</td> 
        <td>${botonEliminar}</td>
      </tr>
    `;
  }

  //console.log(listadoHtml);
  document.querySelector('#usuarios tbody').innerHTML = listadoHtml;
  $('#usuarios').DataTable();
}

async function eliminarUsuario(id) {
  if (!confirm("¿Estás seguro de que deseas eliminar este usuario?")) {
    return; // Exit the function if the user cancels the confirmation dialog
  }
  const request = await fetch("api/eliminarUsuario/" + id, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json'
    }
  });

  location.reload(); // Refresh the page after deletion
    
}