<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Successful Page</title>
</head>
<script>
	var my_json='{FBI:[{name:"rose",age:"25"},{name:"yehuan",age:"24"},{name:"yabin",age:"23"}]}';
	var my_object=eval('('+my_json+')');
	document.write(my_object.FBI[2].name+my_object.FBI[1].age);
</script>
<body>
	<h1>Congratulations! Successful!</h1>
</body>
</html>