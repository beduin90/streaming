/**
 * Created by beduin90 on 22.06.2016.
 */




jQuery( document ).ready(function( $ ) {
    $(".btn-success").click(function() {
        var $tr = $(this).closest('tr');
            alert($tr.attr("id"));

    });





});
