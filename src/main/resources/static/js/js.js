	$(document).ready(function () {
		$("#inputCliente").change(function () {
			var codCliente = $(this).val();
			if (codCliente === '-1') {
				// Si se selecciona la opción "Seleccione", limpia el select de mascotas
				$("#inputMascota").empty();
				$("#inputMascota").append("<option value='-1'>Seleccione</option>");
				return;
			}
			console.log("Función AJAX ejecutada");
$.ajax({
    url: "/ListadocodNombres?cod_cliente=" + codCliente,
    type: "GET",
    success: function (data) {
        $("#inputMascota").empty();
        $("#inputMascota").append("<option value='-1'>Seleccione</option>");
        $.each(data, function (index, mascota) {
            var codigoMascota = mascota[0]; // Primer elemento del array es el código
            var nombreMascota = mascota[1]; // Segundo elemento del array es el nombre
            $("#inputMascota").append("<option value='" + codigoMascota + "'>" + nombreMascota + "</option>");
        });
    },
    error: function () {
        alert("Error al cargar las mascotas.");
    }
});


		});
	});