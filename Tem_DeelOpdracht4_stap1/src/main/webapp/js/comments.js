let webSocket;
webSocket = new WebSocket("ws://localhost:8080/WEB4_war_exploded/echo");
webSocket.onopen = function(event){
};
webSocket.onmessage = function(event){
	writeResponse(event.data);
};

webSocket.onclose = function(event){
};

let page = document.getElementById("plannen");
page.onload = openSocket;
let een = document.getElementById("projectweek_comment");
een.onclick = submitButtonEen;
let twee = document.getElementById("plannen_comment");
twee.onclick = submitButtonTwee;
let drie = document.getElementById("muziek_comment");
drie.onclick = submitButtonDrie;
let vier = document.getElementById("examenvragen_comment");
vier.onclick = submitButtonVier;
let vijf = document.getElementById("geen_comment");
vijf.onclick = submitButtonVijf;


function submitButtonEen(){
	let name = document.getElementById("projectweek_comment_name").value;
	let text = document.getElementById("projectweek_comment_text").value;
	let rating = document.getElementById("projectweek_comment_rating").value;
	let js = '{ "postid":"projectweek", "name":"' + encodeURI(name)+ '" , "text":"' + encodeURI(text) + '" , "rating":"' + encodeURI(rating) + '" }'
	webSocket.send(js);
}

function submitButtonTwee(){
	let name = document.getElementById("plannen_comment_name").value;
	let text = document.getElementById("plannen_comment_text").value;
	let rating = document.getElementById("plannen_comment_rating").value;
	let js = '{ "postid":"plannen", "name":"' + encodeURI(name)+ '" , "text":"' + encodeURI(text) + '" , "rating":"' + encodeURI(rating) + '" }'
	webSocket.send(js);
}

function submitButtonDrie(){
	let name = document.getElementById("muziek_comment_name").value;
	let text = document.getElementById("muziek_comment_text").value;
	let rating = document.getElementById("muziek_comment_rating").value;
	let js = '{ "postid":"muziek", "name":"' + encodeURI(name)+ '" , "text":"' + encodeURI(text) + '" , "rating":"' + encodeURI(rating) + '" }'
	webSocket.send(js);
}

function submitButtonVier(){
	let name = document.getElementById("examenvragen_comment_name").value;
	let text = document.getElementById("examenvragen_comment_text").value;
	let rating = document.getElementById("examenvragen_comment_rating").value;
	let js = '{ "postid":"examenvragen", "name":"' + encodeURI(name)+ '" , "text":"' + encodeURI(text) + '" , "rating":"' + encodeURI(rating) + '" }'
	webSocket.send(js);
}

function submitButtonVijf(){
	let name = document.getElementById("geen_comment_name").value;
	let text = document.getElementById("geen_comment_text").value;
	let rating = document.getElementById("geen_comment_rating").value;
	let js = '{ "postid":"geen", "name":"' + encodeURI(name)+ '" , "text":"' + encodeURI(text) + '" , "rating":"' + encodeURI(rating) + '" }'
	webSocket.send(js);
}


function openSocket(){

}
function writeResponse(blogtext){
	let json = JSON.parse(blogtext);
	let comments_div = document.getElementById(json.postid +"_comments")
	let comment_div = document.createElement("div")
	comment_div.setAttribute("class", "comment")
	let name = createDivPWithClass("username", json.name)
	let text = createDivPWithClass("text", json.text)
	let rating = createDivPWithClass("rating", json.rating)
    comment_div.appendChild(name)
    comment_div.appendChild(text)
    comment_div.appendChild(rating)
	comments_div.appendChild(comment_div)
}

function createDivPWithClass(classname, value){
	let div = document.createElement("div");
    div.setAttribute("class",classname);
	let divpar = document.createElement("p");
	let divnode = document.createTextNode(decodeURI(value))
    divpar.appendChild(divnode)
    div.appendChild(divpar)
    return div;
}

function send(){
	let postid = document.getElementById("firstpost_comment").parentElement.parentElement.getAttribute("id");
	let name = document.getElementById(postid+"_comment" +"_name").value;
	let text = document.getElementById(postid+"_comment" +"_text").value;
	let rating = document.getElementById(postid+"_comment" +"_rating").value;
	let js = '{ "postid":"'+ encodeURI(postid) + '", "name":"'+encodeURI(name)+'" , "text":"'+encodeURI(text)+'" , "rating":"'+encodeURI(rating)+'" }'
    webSocket.send(js);
}
