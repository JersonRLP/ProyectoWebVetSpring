/* Mascota */

// Boton eliminar mascota
$(document).ready(function(){
	$('.deletebtn').on('click',function(){
		$('#eliminarModal').modal('show');
		$tr = $(this).closest('tr');
		var data = $tr.children("td").map(function(){
			return $(this).text();
		}).get();
		console.log(data);
		$('#codigo').val(data[0]);
		$('#nombreMascota').text("¿Está seguro de eliminar las mascota: "+ data[2]+"?");
	})
});

// Botón para editar mascota
$('.btn_editar_mascota').on('click', function () {
    $('#modal_editar_mascota').modal('show');
    $tr = $(this).closest('tr');
    var data = $tr.children("td").map(function () {
        return $(this).text();
    }).get();
    console.log('data[3]:', data[3]); // Registra el valor de data
    $('#codigo_mascota_edit').val(data[0]);
    $('#nombre_mascota_edit').val(data[2]);
    $('#especie_mascota_edit').val(data[3]);
    console.log('especie_mascota_edit value:', $('#especie_mascota_edit').val()); // Registra el valor del campo de selección
    $('#raza_mascota_edit').val(data[5]);
    $('#sexo_mascota_edit').val(data[7]);

    // Convertir la fecha a formato 'yyyy-MM-dd'
    var fecha = data[8].split("-");
    var fechaFormatoCorrecto = fecha[2] + "-" + fecha[1] + "-" + fecha[0];
    $('#fecha_nacimiento_mascota_edit').val(fechaFormatoCorrecto);

    $('#peso_mascota_edit').val(data[9]);
    $('#cliente_mascota_edit').val(data[10]);
    $('#chip_identificacion_mascota_edit').val(data[11]);
    $('#color_mascota_edit').val(data[12]);
    $('#estado_mascota_edit').val(data[13]);

    // Obtener la ruta de la imagen
    var imgSrc = $tr.find('img').attr('src');
    // Extraer el nombre de la imagen de la ruta
    var imgName = imgSrc.substring(imgSrc.lastIndexOf('/') + 1);
    // Establecer el nombre de la imagen en el campo oculto
    $('#foto_mascota_edit').val(imgName);
});


// Obtener el listado de especies
$(document).ready(function () {
    $.getJSON('/api/Especie/findall', function (data) {
        $.each(data, function (key, value) {
            $('#especie_mascota_edit')
                .append($("<option></option>")
                    .attr("value", value.codEspecie)
                    .text(value.nombre_especie));
        });
    });
});

// Obtener el listado de razas
$(document).ready(function () {
    $.getJSON('/api/Raza/findall', function (data) {
        $.each(data, function (key, value) {
            $('#raza_mascota_edit')
                .append($("<option></option>")
                    .attr("value", value.codRaza)
                    .text(value.nombre_raza));
        });
    });
});


/* Cliente */

// Boton eliminar cliente
$(document).ready(function(){
	$('.deletebtn').on('click',function(){
		$('#modal_eliminar_cliente').modal('show');
		$tr = $(this).closest('tr');
		var data = $tr.children("td").map(function(){
			return $(this).text();
		}).get();
		console.log(data);
		$('#codigo_cliente').val(data[0]);
		$('#nombre_cliente').text("¿Está seguro de eliminar al cliente: "+ data[1]+"?");
	})
});

// Botón para editar cliente
$('.btn_editar_cliente').on('click', function () {
    $('#modal_editar_cliente').modal('show');
    $tr = $(this).closest('tr');
    var data = $tr.children("td").map(function () {
        return $(this).text();
    }).get();
    console.log('data[4]:', data[4]); // Registra el valor de data[4]
    $('#codigo_cliente_edit').val(data[0]);
    $('#nombre_cliente_edit').val(data[1]);
    $('#apellidos_cliente_edit').val(data[2]);
    $('#dni_cliente_edit').val(data[3]);
    $('#distrito_cliente_edit').val(data[4]);
    console.log('distrito_cliente_edit value:', $('#distrito_cliente_edit').val()); // Registra el valor del campo de selección
    $('#direccion_cliente_edit').val(data[6]);
    $('#telefono_cliente_edit').val(data[7]);
    $('#email_cliente_edit').val(data[8]);
    $('#estado_cliente_edit').val(data[9]);
});


// Obtener el listado de distritos
$(document).ready(function () {
    $.getJSON('/api/Distrito/findall', function (data) {
        $.each(data, function (key, value) {
            $('#distrito_cliente_edit')
                .append($("<option></option>")
                    .attr("value", value.codDistrito)
                    .text(value.nom_distrito));
        });
    });
});


/* Servicio */

// Boton eliminar servicio
$(document).ready(function(){
	$('.deletebtn').on('click',function(){
		$('#modal_eliminar_servicio').modal('show');
		$tr = $(this).closest('tr');
		var data = $tr.children("td").map(function(){
			return $(this).text();
		}).get();
		console.log(data);
		$('#codigo_servicio').val(data[0]);
		$('#nombre_servicio').text("¿Está seguro de eliminar el servicio: "+ data[1]+"?");
	})
});

// Botón para editar servicio
$('.btn_editar_servicio').on('click', function () {
    $('#modal_editar_servicio').modal('show');
    $tr = $(this).closest('tr');
    var data = $tr.children("td").map(function () {
        return $(this).text();
    }).get();
    console.log('data[4]:', data[4]); // Registra el valor de data[4]
    $('#codigo_servicio_edit').val(data[0]);
    $('#nombre_servicio_edit').val(data[1]);
    $('#descripcion_servicio_edit').val(data[2]);
    $('#tipo_servicio_edit').val(data[3]);
    $('#precio_servicio_edit').val(data[5]);
    $('#duracion_estimada_servicio_edit').val(data[6]);
    $('#materiales_necesarios_servicio_edit').val(data[7]);
    $('#estado_servicio_edit').val(data[8]);
});


// Obtener el listado de tipos de servicio
$(document).ready(function () {
    $.getJSON('/api/TipoServicio/findall', function (data) {
        $.each(data, function (key, value) {
            $('#tipo_servicio_edit')
                .append($("<option></option>")
                    .attr("value", value.codTipoServicio)
                    .text(value.descripcion_tipo));
        });
    });
});



/* Veterinario */

// Boton eliminar veterinario
$(document).ready(function(){
    $('.deletebtn').on('click',function(){
        $('#modal_eliminar_veterinario').modal('show');
        $tr = $(this).closest('tr');
        var data = $tr.children("td").map(function(){
            return $(this).text();
        }).get();
        console.log(data);
        $('#codigo_veterinario').val(data[0]);
        $('#nombre_veterinario').text("¿Está seguro de eliminar al veterinario: "+ data[1]+"?");
    })
});

// Botón para editar veterinario
$('.btn_editar_veterinario').on('click', function () {
    $('#modal_editar_veterinario').modal('show');
    $tr = $(this).closest('tr');
    var data = $tr.children("td").map(function () {
        return $(this).text();
    }).get();
    console.log('data[4]:', data[4]); // Registra el valor de data[4]
    $('#codigo_veterinario_edit').val(data[0]);
    $('#nombre_veterinario_edit').val(data[1]);
    $('#apellidos_veterinario_edit').val(data[2]);
    $('#dni_veterinario_edit').val(data[3]);
    $('#distrito_veterinario_edit').val(data[4]);
    console.log('distrito_veterinario_edit value:', $('#distrito_veterinario_edit').val()); // Registra el valor del campo de selección
    $('#direccion_veterinario_edit').val(data[6]);
    $('#especialidad_veterinario_edit').val(data[7]);
    $('#estado_veterinario_edit').val(data[8]);
});


// Obtener el listado de distritos
$(document).ready(function () {
    $.getJSON('/api/Distrito/findall', function (data) {
        $.each(data, function (key, value) {
            $('#distrito_veterinario_edit')
                .append($("<option></option>")
                    .attr("value", value.codDistrito)
                    .text(value.nom_distrito));
        });
    });
});








