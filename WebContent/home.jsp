<%@ taglib prefix="s" uri="/struts-tags" %> 

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<!--<![endif]-->
<!-- BEGIN HEAD -->
<html lang="en" class="no-js">

<s:include value="head.jsp" />
<!-- BEGIN CONTAINER -->
<body class="page-header-fixed page-sidebar-fixed">
<s:include value="header.jsp"/>
<div class="page-container">
	<s:include value="navBar.jsp"/>

	<!-- aside-->
	 
	<input type="hidden" id="sessionID">  
	<div class="page-content-wrapper">
		<div  class="page-content" id="appView" ></div>
	    <div id="loadingDiv" class="loadingajax"></div>   
	</div>  
 
</div>
<s:include value="footer.jsp"/>

</body>
<!-- END BODY -->
</html>
