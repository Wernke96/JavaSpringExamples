const app = document.getElementById('root');
/*
Function to format data into an html table
*/
function makeTable(data){
    //create table
    var table = document.createElement("TABLE");
    table.setAttribute('id', 'dataTable');
    document.body.appendChild(table);

    //create a new row for column names
    var row = document.createElement("TR");
    document.getElementById('dataTable').appendChild(row);

    //make cells
    var subjectCell = document.createElement('td');
    subjectCell.innerHTML = "<b>Subject</b>";

    var senderCell = document.createElement('td');
    senderCell.innerHTML = "<b>Sender</b>";

    var recipientCell = document.createElement('td');
    recipientCell.innerHTML = "<b>Recipient</b>";

    var bodyCell = document.createElement('td');
    bodyCell.innerHTML = "<b>Body</b>";

    var dateCell = document.createElement('td');
    dateCell.innerHTML = "<b>Send Date</b>";

    var attachmentCell = document.createElement('td');
    attachmentCell.innerHTML = "<b>Attachment</b>";

    //append cells to row
    row.appendChild(subjectCell);
    row.appendChild(senderCell);
    row.appendChild(recipientCell);
    row.appendChild(bodyCell);
    row.appendChild(dateCell);
    row.appendChild(attachmentCell);


    data.forEach(element => {

        //create a new row per json object
        var row = document.createElement("TR");
        document.getElementById('dataTable').appendChild(row);

        //make cells
        var subjectCell = document.createElement('td');
        subjectCell.innerHTML = element.subject;

        var senderCell = document.createElement('td');
        senderCell.innerHTML = element.sender;

        var recipientCell = document.createElement('td');
        recipientCell.innerHTML = element.recipient;

        var bodyCell = document.createElement('td');
        bodyCell.innerHTML = element.body;

        var dateCell = document.createElement('td');
        dateCell.innerHTML = element.sendDate;

        var attachmentCell = document.createElement('td');
        attachmentCell.innerHTML = element.attachment;

        //append cells to row
        row.appendChild(subjectCell);
        row.appendChild(senderCell);
        row.appendChild(recipientCell);
        row.appendChild(bodyCell);
        row.appendChild(dateCell);
        row.appendChild(attachmentCell);
    });
}

var tempData = [{
    "subject": "test subject",
    "sender": "test sender",
    "recipient": "test recipient",
    "body": "test body",
    "date": "test date",
    "attachment": "test attachment"
},
{
    "subject": "test subject",
    "sender": "test sender",
    "recipient": "test recipient",
    "body": "test body",
    "date": "test date",
    "attachment": "test attachment"
},
{
    "subject": "test subject",
    "sender": "test sender",
    "recipient": "test recipient",
    "body": "test body",
    "date": "test date",
    "attachment": "test attachment"
}
];

//makeTable(tempData);


var request = new XMLHttpRequest();
request.open('GET', 'http://localhost:8080/hackathon', true);

 request.onload = function(){
     var data = JSON.parse(this.response);
     makeTable(data.data);
 }
 request.send()

//alert("I am Active");
