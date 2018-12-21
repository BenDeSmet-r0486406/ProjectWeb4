let current = document.getElementById("statustext");
let status = current.innerText;
let button = document.getElementById("statusbutton");
button.onclick = changeStatus;
button.onload = displayTextBox;
let dropdown = document.getElementById("statusdropdown");
dropdown.onchange = setTemp;
let xHRObject = new XMLHttpRequest();

function setTemp(){
	status = dropdown.options[dropdown.selectedIndex].value;
	displayTextBox();
}

function displayTextBox() {
	if(dropdown.value == "CUSTOM") {
		showTextBox();
	}else{
		hideTextBox();
	}
}

function showTextBox(){
	let input = document.getElementById("statustextbox");
	input.style.display = 'block';
}

function hideTextBox() {
	let input = document.getElementById("statustextbox");
	input.style.display = 'none';
}

function getTextFromTextBox(){
	return document.getElementById("statustextbox").value;
}

function changeStatus () {
	if(status == "CUSTOM"){
		xHRObject.open("GET","Controller?action=status&status="+encodeURI(status)+"&message="+encodeURI(getTextFromTextBox()), true);
	}else{
		xHRObject.open("GET", "Controller?action=status&status="+encodeURI(status), true);
	}
	xHRObject.onreadystatechange = getStatus;
	xHRObject.send(null);
}

function getStatus() {
	if (xHRObject.status == 200) {
		if (xHRObject.readyState == 4) {
			let serverResponse = JSON.parse(xHRObject.responseText);
			let state = serverResponse.statusMessage;

			let statusDiv = document.getElementById("status");
			statusDiv.setAttribute("class","status "+status.toLowerCase())
			let statusSpan = document.createElement('span')
			statusSpan.setAttribute("class","statustext")
			if(statusDiv == null){
				statusDiv = document.createElement('div');
				statusDiv.setAttribute("id","status");
				statusDiv.setAttribute("class","status");
				let statusText = document.createTextNode(state);
				statusDiv.appendChild(statusText);
			}else{
				let statusText = document.createTextNode(state);
				statusSpan.appendChild(statusText)
				statusDiv.removeChild(statusDiv.childNodes[0]);
				statusDiv.appendChild(statusSpan);
			}
		}
	}
}

