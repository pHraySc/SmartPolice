function checkName() {
	document.getElementById('nameinfo').innerHTML = "";
	var name = document.getElementById('name').value;
	if (name == "") {
		document.getElementById('nameinfo').innerHTML = "用户名不能为空";
		return false;
	}

	if (name.length > 8) {
		document.getElementById('nameinfo').innerHTML = "用户名不得超过8个字";
		return false;
	}
}
function checkName_l() {
	document.getElementById('nameinfo_l').innerHTML = "";
	var name_l = document.getElementById('name_l').value;
	if (name_l == "") {
		document.getElementById('nameinfo_l').innerHTML = "用户名不能为空";
		return false;
	}

	if (name_l.length > 8) {
		document.getElementById('nameinfo_l').innerHTML = "用户名不得超过8个字";
		return false;
	}
}

function checkEmail() {
	document.getElementById('emailinfo').innerHTML = "";
	var email = document.getElementById('email').value;
	if (email == "") {
		document.getElementById('emailinfo').innerHTML = "Email值不能为空";
		return false;
	}

	if (email.indexOf('@') == -1 || email.indexOf('.') == -1) {
		document.getElementById('emailinfo').innerHTML = "Email格式非法";
		return false;
	}
}

function checkPassword() {
	document.getElementById('passwordinfo').innerHTML = "";
	var pwd = document.getElementById('pwd').value;
	if (pwd == "") {
		document.getElementById("passwordinfo").innerHTML = "密码不能为空";
		return false;
	}

	if (pwd.length < 6) {
		document.getElementById("passwordinfo").innerHTML = "密码长度必须大于或者等于6";
		return false;
	}
}
function checkPassword_l() {
	document.getElementById('passwordinfo_l').innerHTML = "";
	var pwd_l = document.getElementById('pwd_l').value;
	if (pwd_l == ""|| pwd_l.length < 6) {
		document.getElementById("passwordinfo_l").innerHTML = "密码格式错误";
		return false;
	}
}

function checkRepassword() {
	document.getElementById('repasswordinfo').innerHTML = "";
	var pwd1 = document.getElementById('pwd1').value;
	var pwd = document.getElementById('pwd').value;
	if (pwd1 == pwd) {
		document.getElementById('repasswordinfo').innerHTML = "";
	}
	else{
		document.getElementById('repasswordinfo').innerHTML = "两次输入不同，请重新输入！";
		return false;
	}	
}

function checkIdcard() {
	document.getElementById('idcardinfo').innerHTML = "";
	var idcard = document.getElementById('idcard').value;
	if (idcard == "") {
		document.getElementById('idcardinfo').innerHTML = "身份证不能为空";
		return false;
	}

	if (idcard.length != 18) {
		document.getElementById('idcardinfo').innerHTML = "身份证号码位数错误";
		return false;
	}
}

function checkTel() {
	document.getElementById('telinfo').innerHTML = "";
	var number = document.getElementById('number').value;
	if (number == "") {
		document.getElementById('telinfo').innerHTML = "联系方式不能为空";
		return false;
	}

	if (number.length != 11) {
		document.getElementById('telinfo').innerHTML = "电话号码位数错误";
		return false;
	}
}

function checkAddress() {
	document.getElementById('addressinfo').innerHTML = "";
	var number = document.getElementById('address').value;
	if (number == "") {
		document.getElementById('addressinfo').innerHTML = "公司地址不能为空";
		return false;
	}
}

function checkPerson() {
	document.getElementById('personinfo').innerHTML = "";
	var number = document.getElementById('person').value;
	if (number == "") {
		document.getElementById('personinfo').innerHTML = "负责人员不能为空";
		return false;
	}
}