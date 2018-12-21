let friendsButton = document.getElementById("searchbutton");
let friendsXHRObject = new XMLHttpRequest();
let friendsXHR = new XMLHttpRequest();
friendsButton.onclick = searchFriends;
friendsButton.onload = getFriends;
import { addMessageButton } from './message.js';

setInterval(getFriends, 2500);

function getFriends(){
	friendsXHR.open("GET","Controller?action=friendsList", true);
	friendsXHR.onreadystatechange = getFriendsResult;
	friendsXHR.send(null);
}

function getFriendsResult(){
	if(friendsXHR.status == 200){
		if(friendsXHR.readyState == 4){
			let json = JSON.parse(friendsXHR.responseText);
			let div = document.getElementById("friends");
			let ul = document.createElement("ul");
			for ( let p in json){
				let li = document.createElement("li");
				let persoon = json[p];
				li.setAttribute("class","friend")
				li.setAttribute("id",persoon.userId);
				
				addMessageButton(persoon);

				let statusDiv = createStatusDiv(persoon.state, persoon.statusMessage);
				li.appendChild(statusDiv);

				let par = document.createElement("p");
				let text = persoon.email + " " + persoon.firstName + " " + persoon.lastName;
				let node = document.createTextNode(text);
				par.appendChild(node);
				li.appendChild(par);

				let newbutton = document.createElement("input");
				newbutton.setAttribute("class", "remove");
				newbutton.setAttribute("type","button");
				newbutton.setAttribute("value","Remove");
				newbutton.onclick = function() {removePerson(persoon.userId)};

				li.appendChild(newbutton);
				let message = document.createElement("input")
				message.setAttribute("class", "msg")
				message.setAttribute("type", "button")
				message.setAttribute("value", "message")
				li.appendChild(message)

				ul.appendChild(li);
			}
			if(div.children[0] != null){
				div.removeChild(div.children[0])
			}
			div.appendChild(ul);
		}
	}
}

function createStatusDiv(state, statusMessage){
	let statusDiv = document.createElement("div");
	statusDiv.setAttribute("class","status "+state.toLowerCase());
	let statusSpan = document.createElement('span');
	statusSpan.setAttribute("class","statustext");
	let statusText = document.createTextNode(""+statusMessage);
	statusSpan.appendChild(statusText);
	statusDiv.appendChild(statusSpan);
	return statusDiv;
}

function searchFriends() {
	let input = document.getElementById("searchfield").value;
	friendsXHRObject.open("GET","Controller?action=searchUser&key=" + encodeURI(input), true);
	friendsXHRObject.onreadystatechange = getSearchResult;
	friendsXHRObject.send(null);
}

function getSearchResult() {
	if (friendsXHRObject.status == 200) {
		if (friendsXHRObject.readyState == 4) {
			let json = JSON.parse(friendsXHRObject.responseText);
			let div = document.getElementById("search");
			let ul = document.createElement("ul");
			for (let p in json){
				let li = document.createElement("li");
				let persoon = json[p];
				li.setAttribute("class","user")
				li.setAttribute("id",persoon.userId);

				let statusDiv = createStatusDiv(persoon.state, persoon.statusMessage);
				li.appendChild(statusDiv);

				let par = document.createElement("p");
				let text = persoon.email + " " + persoon.firstName + " " + persoon.lastName;
				let node = document.createTextNode(text);
				par.appendChild(node);
				li.appendChild(par);

				let newbutton = document.createElement("input");
				newbutton.setAttribute("class", "add");
				newbutton.setAttribute("type","button");
				newbutton.setAttribute("value","Add");
				newbutton.onclick = function() {addPerson(persoon.userId)};

				li.appendChild(newbutton);

				ul.appendChild(li);
			}
			if(div.children[0] != null){
				div.removeChild(div.children[0]);
			}
			div.appendChild(ul);
		}
	}
}

function addPerson(userid) {
	let xhr = new XMLHttpRequest();
	xhr.open("GET","Controller?action=addUser&key=" + encodeURI(userid), true);
	xhr.send(null);
	searchFriends();
}

function removePerson(userid){
	let xhr = new XMLHttpRequest();
	xhr.open("GET","Controller?action=removefriend&key="+encodeURI(userid), true);
	xhr.send(null);
	getFriends();
}

