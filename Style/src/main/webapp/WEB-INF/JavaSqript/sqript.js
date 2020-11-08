function showMessage (message) {
    alert(message);
}

function ShowDiv(time) {
    var popup = document.getElementById("myDiv");
    if(popup.style.display === 'none')  popup.style.display = 'block';
    else popup.style.display = 'none';
    document.getElementById("myTime").value = time;
}

function ValidPhone() {
    var re = /^([+]?[0-9\s-\(\)]{10,25})*$/;
    var myPhone = document.getElementById('tel').value;
    var valid = re.test(myPhone);
    if (myPhone == '') valid = false;
    if (valid) {
        output = ' ';
        if(document.getElementById('exampleCheck1').checked) {
            document.getElementById('ok').disabled = false;
        }
    }
    else {
        output = 'Wrong phone number!';
        document.getElementById('ok').disabled = true;
    }
    document.getElementById('message').innerHTML = output;
    return valid;
}

function ValidCheck() {
    var element = document.getElementById('exampleCheck1');
    if (element.checked) {
        document.getElementById('ok').disabled = false;
        ValidPhone();
    }
    else {
        document.getElementById('ok').disabled = true
    }
}
