const app = document.getElementById('root');

function updateImage(data) {
    encodedData = btoa(data);
    document.getElementById("profile_image").src = "data:" + encodedData;
}

function sendImage() {
    var file = document.getElementById('image_input').files[0];

    var imageEl = document.getElementById("profile_image");

    var request = new XMLHttpRequest();
    request.open('POST', 'http://localhost:8080/hackathon/postimage', true);
    request.setRequestHeader("Content-Type", file.type);
    request.setRequestHeader("id", 7);
    request.setRequestHeader("type", file.type.substr(file.type.indexOf('/') + 1, file.type.length))
    request.onreadystatechange = function() {
            var data = JSON.parse(this.response);
            
    }

    request.send(file);
}

function updateBio(data) {
    document.getElementById("user_bio").innerHTML = data;
}

function sendBio() {
    var bio = document.getElementById("update_bio").value;

    var request = new XMLHttpRequest();
    request.open('POST', 'http://localhost:8080/hackathon/postbio', true);
    request.setRequestHeader("id", 32);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    request.onreadystatechange = function() {
        var data = JSON.parse(this.response);
    }
    request.send("bio=" + bio);


}

var urlParams = new URLSearchParams(window.location.search);
var userId = urlParams.get('username');
var request = new XMLHttpRequest();
request.setRequestHeader("id", 32)
request.open('GET', 'http://localhost:8080/hackathon/getbio', true);
    request.onload = function() {
        var data = this.response;
        updateBio(data);
    }
request.send();

var imageReq = new XMLHttpRequest();
imageReq.setRequestHeader("id", 32);
imageReq.open('GET', 'http://localhost:8080/hackathon/getimage', true);
    imageReq.onload = function() {
        var data = this.response;
        updateImage(data);
    }
imageReq.send();