<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SearchResults</title>
</head>
<body style="background-color: rgb(78, 71, 154);">
 <img src="https://upload.cc/i1/2020/01/08/ZVWMhX.png"
  style="display: block; margin: auto;" />
 <style>
hr.style-one {
 border: 0;
    height: 3px;
    background: #ACD6FF ;
}
</style>
 <hr class="style-one" />
 <%
  String[][] orderList = (String[][]) request.getAttribute("query");
  for (int i = 0; i < orderList.length; i++) {
 %>
 <img src="https://upload.cc/i1/2020/01/08/TWNbAX.png">
 <a href='<%=orderList[i][1]%>' style="font-size:20px;color: white;"><%=orderList[i][0]%></a>
 <br>
 <%
  if (orderList[i][2].contains("我最愛中時電子豹")) {
 %>
 <h style="font-size:16px;color: white;">來源:</h>
 <h style="font-size:16px;color: #FF2626;">&nbsp;<%=orderList[i][2]%></h>
 <h style="font-size:16px;color: white;">&nbsp;&nbsp;&nbsp;時間:&nbsp;<%=orderList[i][3]%></h>
 
 <br>
 <br>
 <%
  } else if (orderList[i][2].contains("我最討厭set兩粒")) {
 %>
 <h style="font-size:16px;color: white;">來源:</h>
 <h style="font-size:16px;color: #45FF45;">&nbsp;<%=orderList[i][2]%></h>
 <h style="font-size:16px;color: white;">&nbsp;&nbsp;&nbsp;時間:&nbsp;<%=orderList[i][3]%></h>

 <br>
 <br>
 <%
  } else if (orderList[i][2].contains("TVBS")) {
 %>
 <h style="font-size:16px;color: white;">來源:</h>
 <h style="font-size:16px;color: #9BE2FF;">&nbsp;<%=orderList[i][2]%></h>
 <h style="font-size:16px;color: white;">&nbsp;&nbsp;&nbsp;時間:&nbsp;<%=orderList[i][3]%></h>
 
 <br>
 <br>
 <%
  } else if (orderList[i][2].contains("風傳媒")) {
 %>
 <h style="font-size:16px;color: white;">來源:</h>
 <h style="font-size:16px;color: #FAFAFA;">&nbsp;<%=orderList[i][2]%></h>
 <h style="font-size:16px;color: white;">&nbsp;&nbsp;&nbsp;時間:&nbsp;<%=orderList[i][3]%></h>

 <br>
 <br>
 <%
  } else if (orderList[i][2].contains("udn聯合新聞網")) {
 %>
 <h style="font-size:16px;color: white;">來源:</h>
 <h style="font-size:16px;color: #FFBA3B;">&nbsp;<%=orderList[i][2]%></h>
 <h style="font-size:16px;color: white;">&nbsp;&nbsp;&nbsp;時間:&nbsp;<%=orderList[i][3]%></h>

 <br>
 <br>
 <%
  } else if (orderList[i][2].contains("自由時報")) {
 %>
 <h style="font-size:16px;color: white;">來源:</h>
 <h style="font-size:16px;color: #82FF82;">&nbsp;<%=orderList[i][2]%></h>
 <h style="font-size:16px;color: white;">&nbsp;&nbsp;&nbsp;時間:&nbsp;<%=orderList[i][3]%></h>

 <br>
 <br>
 <%
  } else {
 %>
 <h style="font-size:16px;color: white;">來源:</h>
 <h style="font-size:16px;color: #A1A1A1;">&nbsp;<%=orderList[i][2]%></h>
 <h style="font-size:16px;color: white;">&nbsp;&nbsp;&nbsp;時間:&nbsp;<%=orderList[i][3]%></h>

 <br>
 <br>
 <%
  }
 %>
 <%
  }
 %>

</body>
</html>