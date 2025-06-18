<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/png" sizes="16x16"  href="/favicon-32x32.png">
<meta charset="UTF-8">
<title>알림창</title>
</head>
<body>

<script type="text/javascript">
	console.log("로그 [${url}]");
	alert('${msg}');
	if(${flag}){
		var url = "${url}";
		location.href = url;
	}
	else{
		history.go(-1);
	}
</script>

</body>
</html>