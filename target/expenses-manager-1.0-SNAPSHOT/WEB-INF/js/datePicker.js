/**
 * Representa un date picker
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
$( function() {
    $( "#datepicker" ).datepicker({
        dateFormat: "dd-mm-yy",
        maxDate: 0,
        minDate: -(new Date().getDate()-1),
        onClose: function(date) {
            $( "#datepicker" ).datepicker( "option", "minDate", -(new Date().getDate()-1));
        }
    });
} );