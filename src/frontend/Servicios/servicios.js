// Definir la URL del servidor como una variable global
const API_URL = 'http://localhost:8080/prueba/servicios';

// Obtener los elementos del DOM
const formCargarPuntos = document.getElementById("cargar-puntos");
const formUsarPuntos = document.getElementById("usar-puntos");
const formConvertir = document.getElementById("convertir");
const codeCargarPuntos = document.getElementById("code-cargar-puntos");
const codeUsarPuntos = document.getElementById("code-usar-puntos");
const codeConvertir = document.getElementById("code-convertir");



async function cargarPuntos(regla) {
  try {
    const response = await fetch(`${API_URL}/carga-puntos`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(regla)
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
}

formCargarPuntos.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const cargaPunto = {
    idCliente: formCargarPuntos.idCliente.value,
    monto: formCargarPuntos.monto.value,
  };

  const resultado = await cargarPuntos(cargaPunto);

  if (resultado) {
    formCargarPuntos.reset();
    codeCargarPuntos.innerHTML = JSON.stringify(resultado, null, 2)
  }
});


async function usarPuntos(regla) {
  try {
    const response = await fetch(`${API_URL}/uso-puntos`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(regla)
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
}

formUsarPuntos.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const usarPunto = {
    idCliente: formUsarPuntos.idCliente.value,
    idConceptoPuntos: formUsarPuntos.idConceptoPuntos.value,
  };

  const resultado = await usarPuntos(usarPunto);

  if (resultado) {
    formUsarPuntos.reset();
    codeUsarPuntos.innerHTML = JSON.stringify(resultado, null, 2)
  }
});



formConvertir.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const monto = formConvertir.monto.value

  try {
    const response = await fetch(`${API_URL}/conv-monto/${monto}`, {
      method: "GET",
    });
    const data = await response.json();
    codeConvertir.innerHTML = JSON.stringify(data, null, 2)
    return data;
  } catch (error) {
    console.error(error);
  }
});
