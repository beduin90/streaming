/**
 * Created by beduin90 on 22.06.2016.
 */

// alternative to DOMContentLoaded



jQuery( document ).ready(function( $ ) {


    document.onreadystatechange = function () {
        if (document.readyState == "interactive") {
            initApplication();
        }
    }


    $(".btn-success").click(function() {

        var tr = $(this).closest('tr').attr('id');
        var add=$('#draggablePanelList');


        add.before(' <li class="panel panel-info" id="'+tr+'c'+'"> '+
            +'<div class="panel-heading">USER</div>'+
            +'<div class="panel-body">user details</div>'+
            +' </li>');
        $(this).attr('disabled','disabled');
        $(this).next().removeAttr('disabled');
    });



    $(".del").click(function() {
        var $tr = $(this).closest('tr').attr('id');
        $('#'+$tr+'c').remove();
        $(this).prev().removeAttr("disabled");
        $(this).attr('disabled','disabled');

    });





});




