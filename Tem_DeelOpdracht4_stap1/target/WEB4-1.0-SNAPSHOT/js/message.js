let when = 0;

$(document).on('click','.msg',function(e) {
    let id = $(this).parent().attr("id");
    $("#user_"+id).toggle();

});
$(document).on('click','.messagebtn',function(e) {
	let tid = $(this).parent().attr("id");
    let id = $("#"+tid+"_hidden").val();
    let message = $("#"+tid+"_field").val();
    let url = "Controller?action=message&message="+encodeURI(message)+"&userid="+encodeURI(id);
    $.get(url);
})

$(document).ready(function() {
	setInterval(updateMessages,2500);
});

function updateMessages(){
	$.getJSON( "Controller?action=messages&when="+encodeURI(when), function( data ) {
		$.each( data, function( key, val ) {
            $.each(val, function(i, value){
                let par = $("<p/>")
				par.text(value.from.firstName + " " + value.from.lastName+ ": " + value.text)
                par.appendTo("#user_"+key+"_messages")
            })
		});
	});
	when = (new Date).getTime();
}

function addMessageButton(persoon){
	if($("#user_"+persoon.userId).length === 0){
		$( "<div/>", {"class": "dmessages","id": "user_"+persoon.userId}).appendTo('#messages')
		let par = $("<p />", {"class":"messageheader"});
		par.text(""+persoon.firstName)
		par.appendTo("#user_"+persoon.userId);
		$("<div />", {"id":"user_"+persoon.userId+"_messages", "class":"messagep"}).appendTo("#user_"+persoon.userId);
		$("<input />", {"type": "hidden", "value":""+persoon.userId, "id":"user_"+persoon.userId+"_hidden"}).appendTo("#user_"+persoon.userId);
		$("<input />", {"type": "textfield", "id":"user_"+persoon.userId+"_field", "class":"message_input"}).appendTo("#user_"+persoon.userId);
		$("<input />", {"type": "button", "class":"messagebtn submit", "value": "Send"}).appendTo("#user_"+persoon.userId);
	}
}

export { addMessageButton, updateMessages};
