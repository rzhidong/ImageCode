<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	
	function ajaxBlur(){
		var verifyCode = $("#verifyCode").val();
		$.ajax({
			type : "POST",
			async : true,
			url : "servlet/VerifyServlet",
			data : {"verifyCode":verifyCode},
			contentType: "application/x-www-form-urlencoded; charset=gb2312", 
			scriptCharset: 'utf-8',
			//
			success : function(msg) {
				if ($.trim(msg) == $.trim("success")){
					$("#tips").html("<strong>√</strong>").css({"color":"green"});
				}else if($.trim(msg)==$.trim("fail")){
					$("#tips").html("<strong>X</strong>").css({"color":"red"});
					$("#verifyCode").val("");
					reloadCode();
				}
			}
		});
	}
	
	function reloadCode()
    {
        var time=new Date().getTime();
        document.getElementById("imagecode1").src="<%= request.getContextPath()%>/servlet/ImageServlet?id="+time;
        document.getElementById("imagecode").src="<%= request.getContextPath()%>/servlet/ValidationCode?id="+time;
    }
	</script>

  </head>
  
  <body>
    <form action="<%= request.getContextPath()%>/servlet/LoginServlet" method="POST" >
    验证码:<input  type="text" name="checkCode"/>
    <a href="javascript:reloadCode();"> <img alt="验证码" id="imagecode1" src="<%= request.getContextPath()%>/servlet/ImageServlet"/></a><br>
    <input type="submit" value="提交">
    </form>
    
   <hr>
   验证码：<input type="text" name="verifyCode" id="verifyCode" maxlength="5" onblur="ajaxBlur()">
   <a href="javascript:reloadCode();"> <img alt="验证码" id="imagecode" src="<%= request.getContextPath()%>/servlet/ValidationCode"/></a>
   <div id="tips"></div>
  </body>
</html>
