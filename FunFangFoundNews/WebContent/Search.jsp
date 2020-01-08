<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body style="background-color: rgb(78, 71, 154);">
 <br><br><br><br>  
 <div
  style="text-align: center; position: absolute;left: 25%;  margin: auto;position: absolute;">
  <br>
  <img src="https://upload.cc/i1/2020/01/06/lrNxMK.png" style="display: block; margin: auto;">
  <form action='${requestUri}' method='get'>
   <input type='text' name='keyword' placeholder='請輸入關鍵字'
    style="width: 330px; height: 23px; font-size: 13px;" /> <input
    type='submit' value='中文搜尋'
    style="width: 100px; height: 30px; font-size: 12px; border: 3px white double; background-color: #C2C2FF" />
  </form>
  <form action='TestProject2.jsp'>
   <br> <input type='submit' value='切換語言'
    style="width: 100px; height: 30px; font-size: 12px; border: 3px white double; background-color: #C2C2FF" />
  </form>
 </div>
 <marquee behavior=side direction=left>
  <img src="https://upload.cc/i1/2020/01/06/fmvzLn.png">
 </marquee>


</body>
</html>